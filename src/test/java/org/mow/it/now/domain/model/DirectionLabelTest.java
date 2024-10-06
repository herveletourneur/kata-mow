package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectionLabelTest {

    @Test
    void should_return_NORTH() {
        //Given
        String northLabel = "N";

        //When
        Direction direction = Direction.fromLabel(northLabel);

        //Then
        Assertions.assertThat(direction).isEqualTo(Direction.NORTH);
    }

    @Test
    void should_return_SOUTH() {
        //Given
        String northLabel = "S";

        //When
        Direction direction = Direction.fromLabel(northLabel);

        //Then
        Assertions.assertThat(direction).isEqualTo(Direction.SOUTH);
    }

    @Test
    void should_return_EAST() {
        //Given
        String northLabel = "E";

        //When
        Direction direction = Direction.fromLabel(northLabel);

        //Then
        Assertions.assertThat(direction).isEqualTo(Direction.EAST);
    }

    @Test
    void should_return_WEST() {
        //Given
        String northLabel = "W";

        //When
        Direction direction = Direction.fromLabel(northLabel);

        //Then
        Assertions.assertThat(direction).isEqualTo(Direction.WEST);
    }

    @Test
    void should_throw_exception_due_to_invalid_label() {
        //Given
        String unknownLabel = "unknownLabel";

        //When & Then
        Assertions.assertThatThrownBy(() -> Direction.fromLabel(unknownLabel))
                  .isInstanceOf(InvalidDirectionLabelException.class)
                  .hasMessage("Invalid label " + unknownLabel + " for Direction");

    }
}