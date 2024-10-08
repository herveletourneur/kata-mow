package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;
import org.mow.it.now.core.*;

class MowTest {

    @Test
    void should_not_create_mow_without_position() {
        //Given
        MowPosition position = null;
        Direction direction = Direction.NORTH;

        //When & Then
        Assertions.assertThatThrownBy(() -> new Mow(direction, position))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Mow Position must not be null");
    }

    @Test
    void should_not_create_mow_without_direction() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        MowPosition position = new MowPosition(new Coordinates(Abscissa.of(1), Ordinate.of(1)), garden);
        Direction direction = null;

        //When & Then
        Assertions.assertThatThrownBy(() -> new Mow(direction, position))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Direction must not be null");
    }

    @Test
    void should_move_right() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(3));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.NORTH, mowPosition);

        //When
        mow.turnRight();

        //Then
        Assertions.assertThat(mow.position()).isEqualTo("2 3 E");
    }

    @Test
    void should_move_left() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(3));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.NORTH, mowPosition);

        //When
        mow.turnLeft();

        //Then
        Assertions.assertThat(mow.position()).isEqualTo("2 3 W");
    }

    @Test
    void should_move_on_abscissa() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(3));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.EAST, mowPosition);

        //When
        mow.advance();

        //Then
        Assertions.assertThat(mow.position()).isEqualTo("3 3 E");
    }

    @Test
    void should_move_on_ordinate() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(3));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.SOUTH, mowPosition);

        //When
        mow.advance();

        //Then
        Assertions.assertThat(mow.position()).isEqualTo("2 2 S");
    }

}