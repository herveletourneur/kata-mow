package org.mow.it.now.port;

import org.mow.it.now.domain.model.Direction;
import org.mow.it.now.domain.model.Garden;
import org.mow.it.now.domain.model.ProgrammedMow;
import org.mow.it.now.domain.model.ProgrammedMowAction;

import java.util.List;

public class ProgrammatedMowMapper {
    public ProgrammedMow map(String[] rawMow, Garden garden) {
        validateRawMow(rawMow);
        return ProgrammedMowBuilder.builder()
                                   .abscissa(Integer.parseInt(rawMow[0]))
                                   .ordinate(Integer.parseInt(rawMow[1]))
                                   .garden(garden)
                                   .direction(Direction.fromLabel(rawMow[2]))
                                   .actions(parseActions(rawMow[3]));
    }

    private List<ProgrammedMowAction> parseActions(String rawActions) {
        return rawActions.chars()
                         .mapToObj(i -> String.valueOf((char) i))
                         .map(ProgrammedMowAction::fromLabel)
                         .toList();
    }

    private void validateRawMow(String[] rawMow) {
        if (rawMow.length != 4) {
            throw new InvalidFileContentException("A mow must be contain 4 value separated");
        }
    }
}
