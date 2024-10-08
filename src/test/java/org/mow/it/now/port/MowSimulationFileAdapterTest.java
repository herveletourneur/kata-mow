package org.mow.it.now.port;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.application.port.FileNotFoundException;
import org.mow.it.now.application.port.InvalidFileContentException;
import org.mow.it.now.application.port.MowSimulationFileAdapter;
import org.mow.it.now.application.port.ProgrammatedMowMapper;
import org.mow.it.now.core.*;

import java.util.Collections;

class MowSimulationFileAdapterTest {

    @Test
    void should_throw_file_not_found_exception() {
        //Given
        MowSimulationFileAdapter mowSimulationRetriever = createWithFile("unknown_file");

        //When & Then
        Assertions.assertThatThrownBy(mowSimulationRetriever::retrieve)
                  .isInstanceOf(FileNotFoundException.class)
                  .hasMessage("Unable to read content of file " + "unknown_file");

    }

    @Test
    void should_throw_invalid_content_file() {
        //Given
        MowSimulationFileAdapter mowSimulationRetriever = createWithFile("empty_file");

        //When & Then
        Assertions.assertThatThrownBy(mowSimulationRetriever::retrieve)
                  .isInstanceOf(InvalidFileContentException.class)
                  .hasMessage("The file must contain at least two line");

    }

    @Test
    void should_throw_invalid_content_file_for_garden() {
        //Given
        MowSimulationFileAdapter mowSimulationRetriever = createWithFile("invalid_garden");

        //When & Then
        Assertions.assertThatThrownBy(mowSimulationRetriever::retrieve)
                  .isInstanceOf(InvalidFileContentException.class)
                  .hasMessage("The first line must contain two value separated");

    }

    @Test
    void should_throw_invalid_content_file_for_mow() {
        //Given
        MowSimulationFileAdapter mowSimulationRetriever = createWithFile("invalid_mow");

        //When & Then
        Assertions.assertThatThrownBy(mowSimulationRetriever::retrieve)
                  .isInstanceOf(InvalidFileContentException.class)
                  .hasMessage("A mow must be contain 4 value separated");

    }

    @Test
    void should_map_one_mow() {
        //Given
        MowSimulationFileAdapter mowSimulationRetriever = createWithFile("valid_with_one_mow");

        //When
        MowSimulation mowSimulation = mowSimulationRetriever.retrieve();

        //When & Then
        Assertions.assertThat(mowSimulation.mows()).hasSize(1);
        Assertions.assertThat(mowSimulation.mows().get(0)).isEqualTo(ProgrammedMowBuilder.builder()
                                                                                         .abscissa(2)
                                                                                         .ordinate(3)
                                                                                         .garden(Garden.of(Abscissa.of(5), Ordinate.of(5)))
                                                                                         .direction(Direction.NORTH)
                                                                                         .actions(Collections.singletonList(ProgrammedMowAction.ADVANCE)));

    }

    @Test
    void should_map_two_mow() {
        //Given
        MowSimulationFileAdapter mowSimulationRetriever = createWithFile("valid_with_two_mow");

        //When
        MowSimulation mowSimulation = mowSimulationRetriever.retrieve();

        //When & Then
        Assertions.assertThat(mowSimulation.mows()).hasSize(2);
        Assertions.assertThat(mowSimulation.mows().get(0)).isEqualTo(ProgrammedMowBuilder.builder()
                                                                                         .abscissa(2)
                                                                                         .ordinate(3)
                                                                                         .garden(Garden.of(Abscissa.of(5), Ordinate.of(5)))
                                                                                         .direction(Direction.NORTH)
                                                                                         .actions(Collections.singletonList(ProgrammedMowAction.ADVANCE)));
        Assertions.assertThat(mowSimulation.mows().get(1)).isEqualTo(ProgrammedMowBuilder.builder()
                                                                                         .abscissa(2)
                                                                                         .ordinate(3)
                                                                                         .garden(Garden.of(Abscissa.of(5), Ordinate.of(5)))
                                                                                         .direction(Direction.SOUTH)
                                                                                         .actions(Collections.singletonList(ProgrammedMowAction.ADVANCE)));
    }


    private MowSimulationFileAdapter createWithFile(String file) {
        return new MowSimulationFileAdapter(file, " ", new ProgrammatedMowMapper());
    }
}