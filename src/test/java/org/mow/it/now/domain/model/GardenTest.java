package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;
import org.mow.it.now.core.Abscissa;
import org.mow.it.now.core.Garden;
import org.mow.it.now.core.Ordinate;

class GardenTest {

    @Test
    void should_not_create_a_garden_with_null_abscissa() {
        //Given
        Abscissa abscissa = null;
        Ordinate ordinate = Ordinate.of(1);

        //When & Then
        Assertions.assertThatThrownBy(() -> Garden.of(abscissa, ordinate))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Max abscissa nust no be null");
    }

    @Test
    void should_not_create_a_garden_with_null_ordinate() {
        //Given
        Abscissa abscissa = Abscissa.of(1);
        Ordinate ordinate = null;

        //When & Then
        Assertions.assertThatThrownBy(() -> Garden.of(abscissa, ordinate))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Max ordinate nust no be null");
    }

    @Test
    void should_validate_shift_up_of_abscissa() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Abscissa abscissaToCheck = Abscissa.of(4);

        //When
        boolean validationResult = garden.abscissaCanShiftRight(abscissaToCheck);

        //Then
        Assertions.assertThat(validationResult).isTrue();
    }

    @Test
    void should_not_validate_shift_up_of_abscissa() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Abscissa abscissaToCheck = Abscissa.of(5);

        //When
        boolean validationResult = garden.abscissaCanShiftRight(abscissaToCheck);

        //Then
        Assertions.assertThat(validationResult).isFalse();
    }

    @Test
    void should_validate_shift_down_of_abscissa() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Abscissa abscissaToCheck = Abscissa.of(1);

        //When
        boolean validationResult = garden.abscissaCanShiftLeft(abscissaToCheck);

        //Then
        Assertions.assertThat(validationResult).isTrue();
    }

    @Test
    void should_not_validate_shift_down_of_abscissa() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Abscissa abscissaToCheck = Abscissa.of(0);

        //When
        boolean validationResult = garden.abscissaCanShiftLeft(abscissaToCheck);

        //Then
        Assertions.assertThat(validationResult).isFalse();
    }

    @Test
    void should_validate_shift_right_of_ordinate() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Ordinate ordinateToCheck = Ordinate.of(4);

        //When
        boolean validationResult = garden.ordinateCanShiftUp(ordinateToCheck);

        //Then
        Assertions.assertThat(validationResult).isTrue();
    }

    @Test
    void should_not_validate_shift_right_of_ordinate() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Ordinate ordinateToCheck = Ordinate.of(5);

        //When
        boolean validationResult = garden.ordinateCanShiftUp(ordinateToCheck);

        //Then
        Assertions.assertThat(validationResult).isFalse();
    }

    @Test
    void should_validate_shift_left_of_ordinate() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Ordinate ordinateToCheck = Ordinate.of(1);

        //When
        boolean validationResult = garden.ordinateCanShiftDown(ordinateToCheck);

        //Then
        Assertions.assertThat(validationResult).isTrue();
    }

    @Test
    void should_not_validate_shift_left_of_ordinate() {
        //Given
        Garden garden = Garden.of(Abscissa.of(5), Ordinate.of(5));
        Ordinate ordinateToCheck = Ordinate.of(0);

        //When
        boolean validationResult = garden.ordinateCanShiftDown(ordinateToCheck);

        //Then
        Assertions.assertThat(validationResult).isFalse();
    }
}