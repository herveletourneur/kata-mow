package org.mow.it.now.batch.step;

import org.mow.it.now.application.port.InvalidFileContentException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.LineCallbackHandler;

public class GardenLineCallbackHandler implements LineCallbackHandler {
    private final StepExecution stepExecution;
    private final String separator;

    public GardenLineCallbackHandler(StepExecution stepExecution, String separator) {
        this.stepExecution = stepExecution;
        this.separator = separator;
    }

    @Override
    public void handleLine(String rawGarden) {
        String[] gardenCoordinate = rawGarden.split(separator);
        if (gardenCoordinate.length != 2) {
            throw new InvalidFileContentException("The first line must contain two value separated");
        }
        stepExecution.getExecutionContext().put("garden", rawGarden);
    }
}
