package org.mow.it.now.application.port;

import org.mow.it.now.core.Abscissa;
import org.mow.it.now.core.Garden;
import org.mow.it.now.core.MowSimulation;
import org.mow.it.now.core.Ordinate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MowSimulationFileAdapter implements MowSimulationRetriever {
    private final String simulationFileNamePath;
    private final String separator;
    private final ProgrammatedMowMapper programmatedMowMapper;

    public MowSimulationFileAdapter(String simulationFilePath, String separator, ProgrammatedMowMapper programmatedMowMapper) {
        this.simulationFileNamePath = Objects.requireNonNull(simulationFilePath);
        this.separator = Objects.requireNonNull(separator);
        this.programmatedMowMapper = Objects.requireNonNull(programmatedMowMapper);
    }


    @Override
    public MowSimulation retrieve() {
        List<String> content = readFileContent();
        validateContentSize(content);
        Garden garden = parseGarden(content.removeFirst());
        return content.stream()
                      .map(line -> line.split(separator))
                      .map(rawMow -> programmatedMowMapper.map(rawMow, garden))
                      .collect(Collectors.collectingAndThen(Collectors.toList(), MowSimulation::new));

    }

    private void validateContentSize(List<String> content) {
        if (content.size() < 2) {
            throw new InvalidFileContentException("The file must contain at least two line");
        }
    }

    private Garden parseGarden(String rawGarden) {
        String[] gardenCoordinate = rawGarden.split(separator);
        if (gardenCoordinate.length != 2) {
            throw new InvalidFileContentException("The first line must contain two value separated");
        }
        Abscissa abscissaMax = Abscissa.of(Integer.parseInt(gardenCoordinate[0]));
        Ordinate ordinateMax = Ordinate.of(Integer.parseInt(gardenCoordinate[1]));
        return new Garden(abscissaMax, ordinateMax);
    }

    private List<String> readFileContent() {
        try {
            URI uri = this.getClass().getClassLoader().getResource(simulationFileNamePath).toURI();
            return Files.readAllLines(Paths.get(uri), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException | NullPointerException e) {
            throw new FileNotFoundException("Unable to read content of file " + simulationFileNamePath, e);
        }
    }
}
