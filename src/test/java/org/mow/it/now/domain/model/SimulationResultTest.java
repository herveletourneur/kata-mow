package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;
import org.mow.it.now.core.SimulationResult;

import java.util.Collections;
import java.util.List;

class SimulationResultTest {
    @Test
    void should_not_create_mow_simulation_with_null_mows() {
        //Given
        List<String> positions = null;
        //When & Then
        Assertions.assertThatThrownBy(() -> new SimulationResult(positions))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("positions must not be null");

    }

    @Test
    void should_not_create_mow_simulation_with_empty_mows() {
        //Given
        List<String> positions = Collections.emptyList();

        //When & Then
        Assertions.assertThatThrownBy(() -> new SimulationResult(positions))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("positions must not be empty");

    }
}