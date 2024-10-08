package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;
import org.mow.it.now.core.Abscissa;
import org.mow.it.now.core.Coordinates;
import org.mow.it.now.core.Ordinate;

class CoordinatesTest {

    @Test
    void should_not_create_coordinate_with_null_abscissa() {
        //Given
        Abscissa abscissa = null;
        Ordinate ordinate = Ordinate.of(1);

        //When & Then
        Assertions.assertThatThrownBy(() -> new Coordinates(abscissa, ordinate))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Abcissa must not be null");
    }

    @Test
    void should_not_create_coordinate_with_null_ordinate() {
        //Given
        Abscissa abscissa = Abscissa.of(1);
        Ordinate ordinate = null;

        //When & Then
        Assertions.assertThatThrownBy(() -> new Coordinates(abscissa, ordinate))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("Ordinate must not be null");
    }
}