package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DirectionRotatorTest {
    @Test
    void should_move_right_to_EAST() {
        //Given
        Direction direction = Direction.NORTH;

        //When
        Direction newDirection = direction.rightDirection();

        //Then
        Assertions.assertThat(newDirection).isEqualTo(Direction.EAST);
    }

    @Test
    void should_move_left_to_EAST() {
        //Given
        Direction direction = Direction.SOUTH;

        //When
        Direction newDirection = direction.leftDirection();

        //Then
        Assertions.assertThat(newDirection).isEqualTo(Direction.EAST);
    }

    @Test
    void should_move_left_to_WEST() {
        //Given
        Direction direction = Direction.NORTH;

        //When
        Direction newDirection = direction.leftDirection();

        //Then
        Assertions.assertThat(newDirection).isEqualTo(Direction.WEST);
    }

    @Test
    void should_move_right_to_NORTH() {
        //Given
        Direction direction = Direction.WEST;

        //When
        Direction newDirection = direction.rightDirection();

        //Then
        Assertions.assertThat(newDirection).isEqualTo(Direction.NORTH);
    }
}
