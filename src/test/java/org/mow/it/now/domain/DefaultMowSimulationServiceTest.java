package org.mow.it.now.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.application.domain.DefaultMowSimulationService;
import org.mow.it.now.application.domain.IMowSimulationService;
import org.mow.it.now.core.*;
import org.mow.it.now.application.port.FileNotFoundException;

import java.io.IOException;
import java.util.Collections;

class DefaultMowSimulationServiceTest {

    @Test
    void should_throw_an_exception_when_retriever_fail() {
        //Given
        IMowSimulationService simulationService = new DefaultMowSimulationService(() -> {throw new FileNotFoundException("fichier introuvable", new IOException());});

        //When & Then
        Assertions.assertThatThrownBy(simulationService::launchSimulation)
                  .isInstanceOf(FileNotFoundException.class)
                  .hasMessage("fichier introuvable");
    }

    @Test
    void should_launch_simmulation() {
        //Given
        IMowSimulationService simulationService = new DefaultMowSimulationService(this::createValidSimulation);

        //When
        SimulationResult simulationResult = simulationService.launchSimulation();

        //Then
        Assertions.assertThat(simulationResult).isEqualTo(new SimulationResult(Collections.singletonList("3 4 N")));
    }


    private MowSimulation createValidSimulation() {
        ProgrammedMow mow = ProgrammedMowBuilder.builder()
                                                .abscissa(3)
                                                .ordinate(3)
                                                .garden(Garden.of(Abscissa.of(5), Ordinate.of(5)))
                                                .direction(Direction.NORTH)
                                                .actions(Collections.singletonList(ProgrammedMowAction.ADVANCE));
        return new MowSimulation(Collections.singletonList(mow));
    }
}