package org.mow.it.now.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.common.BeanValidationException;
import org.mow.it.now.core.MowSimulation;
import org.mow.it.now.core.ProgrammedMow;

import java.util.Collections;
import java.util.List;

class MowSimulationTest {

    @Test
    void should_not_create_mow_simulation_with_null_mows() {
        //Given
        List<ProgrammedMow> mows = null;

        //When & Then
        Assertions.assertThatThrownBy(() -> new MowSimulation(mows))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("mows must not be null");

    }

    @Test
    void should_not_create_mow_simulation_with_empty_mows() {
        //Given
        List<ProgrammedMow> mows = Collections.emptyList();

        //When & Then
        Assertions.assertThatThrownBy(() -> new MowSimulation(mows))
                  .isInstanceOf(BeanValidationException.class)
                  .hasMessage("mows must not be empty");

    }
}