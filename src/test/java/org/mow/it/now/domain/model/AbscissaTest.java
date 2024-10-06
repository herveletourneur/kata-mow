package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;

class AbscissaTest {

    @Test
    void should_not_create_negative_abscissa() {
        //Given
        int x = -1;

        //When & Then
        Assertions.assertThatThrownBy(() -> Abscissa.of(x))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Abscissa must not be negative");
    }

    @Test
    void should_have_superior_abscissa() {
        //Given
        Abscissa abscissa = Abscissa.of(1);
        Abscissa other = Abscissa.of(0);

        //When
        int result = abscissa.compareTo(other);

        //Then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void should_have_equal_abscissa() {
        //Given
        Abscissa abscissa = Abscissa.of(1);
        Abscissa other = Abscissa.of(1);

        //When
        int result = abscissa.compareTo(other);

        //Then
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    void should_have_inferior_abscissa() {
        //Given
        Abscissa abscissa = Abscissa.of(0);
        Abscissa other = Abscissa.of(1);

        //When
        int result = abscissa.compareTo(other);

        //Then
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    void should_shift_right() {
        //Given
        Abscissa abscissa = Abscissa.of(1);

        //When
        Abscissa abscissaShift = abscissa.shiftRight();

        //Then
        Assertions.assertThat(abscissaShift).isEqualTo(Abscissa.of(2));
    }

    @Test
    void should_shift_left() {
        //Given
        Abscissa abscissa = Abscissa.of(1);

        //When
        Abscissa abscissaShift = abscissa.shiftLeft();

        //Then
        Assertions.assertThat(abscissaShift).isEqualTo(Abscissa.of(0));
    }
}