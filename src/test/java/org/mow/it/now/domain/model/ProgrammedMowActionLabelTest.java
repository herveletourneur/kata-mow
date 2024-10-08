package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.core.InvalidProgrammedMowActionLabelException;
import org.mow.it.now.core.ProgrammedMowAction;

class ProgrammedMowActionLabelTest {
    @Test
    void should_return_ADVANCE() {
        //Given
        String label = "A";

        //When
        ProgrammedMowAction programmedMowAction = ProgrammedMowAction.fromLabel(label);

        //Then
        Assertions.assertThat(programmedMowAction).isEqualTo(ProgrammedMowAction.ADVANCE);
    }

    @Test
    void should_return_TURN_LEFT() {
        //Given
        String label = "G";

        //When
        ProgrammedMowAction programmedMowAction = ProgrammedMowAction.fromLabel(label);

        //Then
        Assertions.assertThat(programmedMowAction).isEqualTo(ProgrammedMowAction.TURN_LEFT);
    }

    @Test
    void should_return_TURN_RIGHT() {
        //Given
        String label = "D";

        //When
        ProgrammedMowAction programmedMowAction = ProgrammedMowAction.fromLabel(label);

        //Then
        Assertions.assertThat(programmedMowAction).isEqualTo(ProgrammedMowAction.TURN_RIGHT);
    }

    @Test
    void should_throw_an_exception_when_unknown_label() {
        //Given
        String label = "unknown";

        //When & Then
        Assertions.assertThatThrownBy(() -> ProgrammedMowAction.fromLabel(label))
                  .isInstanceOf(InvalidProgrammedMowActionLabelException.class)
                  .hasMessage("Invalid label " + label + " for ProgrammedMowAction");
    }
}