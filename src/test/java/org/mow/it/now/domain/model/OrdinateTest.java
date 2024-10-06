package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;

class OrdinateTest {
    @Test
    void should_not_create_negative_ordinate() {
        //Given
        int y = -1;

        //When & Then
        Assertions.assertThatThrownBy(() -> Ordinate.of(y))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Ordinate must not be negative");
    }

    @Test
    void should_have_superior_ordinate() {
        //Given
        Ordinate ordinate = Ordinate.of(1);
        Ordinate other = Ordinate.of(0);

        //When
        int result = ordinate.compareTo(other);

        //Then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void should_have_equal_ordinate() {
        //Given
        Ordinate ordinate = Ordinate.of(1);
        Ordinate other = Ordinate.of(1);

        //When
        int result = ordinate.compareTo(other);

        //Then
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    void should_have_inferior_ordinate() {
        //Given
        Ordinate ordinate = Ordinate.of(0);
        Ordinate other = Ordinate.of(1);

        //When
        int result = ordinate.compareTo(other);

        //Then
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    void should_shift_up() {
        //Given
        Ordinate abscissa = Ordinate.of(1);

        //When
        Ordinate ordinateShift = abscissa.shiftUp();

        //Then
        Assertions.assertThat(ordinateShift).isEqualTo(Ordinate.of(2));
    }

    @Test
    void should_shift_down() {
        //Given
        Ordinate abscissa = Ordinate.of(1);

        //When
        Ordinate ordinateShift = abscissa.shiftDown();

        //Then
        Assertions.assertThat(ordinateShift).isEqualTo(Ordinate.of(0));
    }
}