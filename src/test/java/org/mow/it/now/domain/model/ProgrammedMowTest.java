package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;

import java.util.Collections;
import java.util.List;

class ProgrammedMowTest {
    @Test
    void should_not_create_programmed_mow_without_mow() {
        //Given
        Mow mow = null;
        List<ProgrammedMowAction> actions = List.of(ProgrammedMowAction.ADVANCE);

        //When & Then
        Assertions.assertThatThrownBy(() -> new ProgrammedMow(mow, actions))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Mow must not be null");

    }

    @Test
    void should_not_create_programmed_mow_without_actions() {
        //Given
        Coordinates coordinates = new Coordinates(Abscissa.of(2), Ordinate.of(3));
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.NORTH, mowPosition);
        List<ProgrammedMowAction> actions = null;

        //When & Then
        Assertions.assertThatThrownBy(() -> new ProgrammedMow(mow, actions))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("actions must not ne null");

    }

    @Test
    void should_not_create_programmed_mow_with_empty_actions() {
        //Given
        Coordinates coordinates = new Coordinates(Abscissa.of(2), Ordinate.of(3));
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.NORTH, mowPosition);
        List<ProgrammedMowAction> actions = Collections.emptyList();

        //When & Then
        Assertions.assertThatThrownBy(() -> new ProgrammedMow(mow, actions))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("actions must not be empty");

    }

    @Test
    void should_advance_mow() {
        //Given
        Coordinates coordinates = new Coordinates(Abscissa.of(2), Ordinate.of(3));
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.NORTH, mowPosition);
        ProgrammedMow programmedMow = new ProgrammedMow(mow, List.of(ProgrammedMowAction.ADVANCE));

        //When
        programmedMow.launch();

        //Then
        Assertions.assertThat(programmedMow.mow().position()).isEqualTo("2 4 N");
    }

    @Test
    void should_turn_right_mow() {
        //Given
        Coordinates coordinates = new Coordinates(Abscissa.of(2), Ordinate.of(3));
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.NORTH, mowPosition);
        ProgrammedMow programmedMow = new ProgrammedMow(mow, List.of(ProgrammedMowAction.TURN_RIGHT));

        //When
        programmedMow.launch();

        //Then
        Assertions.assertThat(programmedMow.mow().position()).isEqualTo("2 3 E");
    }

    @Test
    void should_turn_left_mow() {
        //Given
        Coordinates coordinates = new Coordinates(Abscissa.of(2), Ordinate.of(3));
        Garden garden = new Garden(Abscissa.of(5), Ordinate.of(5));
        MowPosition mowPosition = new MowPosition(coordinates, garden);
        Mow mow = new Mow(Direction.NORTH, mowPosition);
        ProgrammedMow programmedMow = new ProgrammedMow(mow, List.of(ProgrammedMowAction.TURN_LEFT));

        //When
        programmedMow.launch();

        //Then
        Assertions.assertThat(programmedMow.mow().position()).isEqualTo("2 3 W");
    }
}