package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;

class MowPositionTest {

    @Test
    void should_not_create_mow_position_without_garden() {
        //Given
        Garden garden = null;
        Coordinates coordinates = Coordinates.of(Abscissa.of(1), Ordinate.of(1));

        //When & Then
        Assertions.assertThatThrownBy(() -> new MowPosition(coordinates, garden))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Garden should not be null");

    }

    @Test
    void should_not_create_mow_position_without_coordinates() {
        //Given
        Garden garden = Garden.of(Abscissa.of(1), Ordinate.of(1));
        Coordinates coordinates = null;

        //When & Then
        Assertions.assertThatThrownBy(() -> new MowPosition(coordinates, garden))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Coordinates should not be null");

    }

    @Test
    void should_not_create_mow_position_with_ordinate_coordinate_out_of_garden() {
        //Given
        Garden garden = Garden.of(Abscissa.of(1), Ordinate.of(1));
        Coordinates coordinates = Coordinates.of(Abscissa.of(1), Ordinate.of(2));

        //When & Then
        Assertions.assertThatThrownBy(() -> new MowPosition(coordinates, garden))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Ordinate of Coordinate should be in Garden limit");

    }

    @Test
    void should_not_create_mow_position_with_abscissa_coordinate_out_of_garden() {
        //Given
        Garden garden = Garden.of(Abscissa.of(1), Ordinate.of(1));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(1));

        //When & Then
        Assertions.assertThatThrownBy(() -> new MowPosition(coordinates, garden))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Abscissa of Coordinate should be in Garden limit");

    }

    @Test
    void should_move_up() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(4));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition newPosition = mowPosition.moveUp();

        //Then
        Assertions.assertThat(newPosition)
                  .isEqualTo(new MowPosition(coordinates.shiftUp(), garden));
    }

    @Test
    void should_not_move_up() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(5));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition newPosition = mowPosition.moveUp();

        //Then
        Assertions.assertThat(newPosition)
                  .isEqualTo(mowPosition);
    }

    @Test
    void should_move_down() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(1));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition newPosition = mowPosition.moveDown();

        //Then
        Assertions.assertThat(newPosition)
                  .isEqualTo(new MowPosition(coordinates.shiftDown(), garden));
    }

    @Test
    void should_not_move_down() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(2), Ordinate.of(0));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition newPosition = mowPosition.moveDown();

        //Then
        Assertions.assertThat(newPosition)
                  .isEqualTo(mowPosition);
    }

    @Test
    void should_move_left() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(1), Ordinate.of(1));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition newPosition = mowPosition.moveLeft();

        //Then
        Assertions.assertThat(newPosition)
                  .isEqualTo(new MowPosition(coordinates.shiftLeft(), garden));
    }

    @Test
    void should_not_move_left() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(0), Ordinate.of(1));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition newPosition = mowPosition.moveLeft();

        //Then
        Assertions.assertThat(newPosition)
                  .isEqualTo(mowPosition);
    }

    @Test
    void should_move_right() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(4), Ordinate.of(1));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition newPosition = mowPosition.moveRight();

        //Then
        Assertions.assertThat(newPosition)
                  .isEqualTo(new MowPosition(coordinates.shiftRight(), garden));
    }

    @Test
    void should_not_move_right() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Coordinates coordinates = Coordinates.of(Abscissa.of(5), Ordinate.of(1));
        MowPosition mowPosition = new MowPosition(coordinates, garden);

        //When
        MowPosition newPosition = mowPosition.moveRight();

        //Then
        Assertions.assertThat(newPosition)
                  .isEqualTo(mowPosition);
    }

}