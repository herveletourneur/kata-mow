package org.mow.it.now.batch.step;

import org.mow.it.now.core.*;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.util.List;

public class ProgrammedMowFieldSetMapper implements FieldSetMapper<ProgrammedMow> {

    private final StepExecution stepExecution;

    public ProgrammedMowFieldSetMapper(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ProgrammedMow mapFieldSet(FieldSet fieldSet) throws BindException {
        Garden garden = parseGarden();
        List<ProgrammedMowAction> actions = computeProgrammedActionFrom(fieldSet.readString(ProgrammedMowFileField.ACTIONS.fileOrder()));
        Direction direction = Direction.fromLabel(fieldSet.readString(ProgrammedMowFileField.DIRECTION.fileOrder()));
        return ProgrammedMowBuilder.builder()
                                   .abscissa(fieldSet.readInt(ProgrammedMowFileField.ABSCISSA.fileOrder()))
                                   .ordinate(fieldSet.readInt(ProgrammedMowFileField.ORDINATE.fileOrder()))
                                   .garden(garden)
                                   .direction(direction)
                                   .actions(actions);
    }

    private List<ProgrammedMowAction> computeProgrammedActionFrom(String rawActions) {
        return rawActions.chars()
                         .mapToObj(i -> String.valueOf((char) i))
                         .map(ProgrammedMowAction::fromLabel)
                         .toList();

    }

    private Garden parseGarden() {
        String rawGarden = stepExecution.getExecutionContext().getString("garden");
        String[] gardenCoordinate = rawGarden.split(" ");
        Abscissa abscissaMax = Abscissa.of(Integer.parseInt(gardenCoordinate[0]));
        Ordinate ordinateMax = Ordinate.of(Integer.parseInt(gardenCoordinate[1]));
        return Garden.of(abscissaMax, ordinateMax);
    }
}
