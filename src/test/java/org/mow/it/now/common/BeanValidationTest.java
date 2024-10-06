package org.mow.it.now.common;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BeanValidationTest {


    @Test
    void should_validate() {
        //Given
        BeanValidation validation = BeanValidation.begin();

        //When & Then (nothing happen)
        validation.guard(() -> true, null);
    }

    @Test
    void should_throw_exception() {
        //Given
        BeanValidation validation = BeanValidation.begin();
        String errorMessage = "error message";

        //When & Then
        Assertions.assertThatThrownBy(() -> validation.guard(() -> false, errorMessage))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage(errorMessage);
    }

    @Test
    void should_validate_non_null() {
        //Given
        BeanValidation validation = BeanValidation.begin();

        //When & Then (nothing happen)
        validation.guardNonNull(new Object(), null);
    }

    @Test
    void should_not_validate_non_null() {
        //Given
        BeanValidation validation = BeanValidation.begin();
        String errorMessage = "error message";

        //When & Then
        Assertions.assertThatThrownBy(() -> validation.guardNonNull(null, errorMessage))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage(errorMessage);
    }

}