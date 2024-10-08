package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.core.*;

class PositionAdvancerTest {
    @Test
    void should_move_position_up() {
        //Given
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = new Coordinates(Abscissa.of(3), Ordinate.of(3));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition advancedPosition = Direction.NORTH.advance(mowPosition);

        //Then
        Assertions.assertThat(advancedPosition).isEqualTo(new MowPosition(coordinates.shiftUp(), garden));
    }

    @Test
    void should_move_position_down() {
        //Given
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = new Coordinates(Abscissa.of(3), Ordinate.of(3));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition advancedPosition = Direction.SOUTH.advance(mowPosition);

        //Then
        Assertions.assertThat(advancedPosition).isEqualTo(new MowPosition(coordinates.shiftDown(), garden));
    }

    @Test
    void should_move_position_right() {
        //Given
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = new Coordinates(Abscissa.of(3), Ordinate.of(3));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition advancedPosition = Direction.EAST.advance(mowPosition);

        //Then
        Assertions.assertThat(advancedPosition).isEqualTo(new MowPosition(coordinates.shiftRight(), garden));
    }

    @Test
    void should_move_position_left() {
        //Given
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = new Coordinates(Abscissa.of(3), Ordinate.of(3));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition advancedPosition = Direction.WEST.advance(mowPosition);

        //Then
        Assertions.assertThat(advancedPosition).isEqualTo(new MowPosition(coordinates.shiftLeft(), garden));
    }
}
