package org.mow.it.now.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.application.controller.ConsolePrinter;
import org.mow.it.now.application.controller.DefaultSimulationController;
import org.mow.it.now.application.controller.ISimulationController;
import org.mow.it.now.application.controller.Printer;
import org.mow.it.now.application.domain.IMowSimulationService;
import org.mow.it.now.core.SimulationResult;
import org.mow.it.now.utils.ArgumentCaptor;

import java.util.Collections;

class DefaultSimulationControllerTest {

    @Test
    void should_throw_an_exception_if_service_failed() {
        //Given
        IMowSimulationService service = () -> {throw new IllegalStateException();};
        Printer printer = new ConsolePrinter();
        ISimulationController controller = new DefaultSimulationController(service, printer);

        //When & Then
        Assertions.assertThatThrownBy(controller::launchAndPrint)
                  .isInstanceOf(IllegalStateException.class);

    }

    @Test
    void should_print_result() {
        //Given
        IMowSimulationService service = () -> new SimulationResult(Collections.singletonList("3 3 E"));
        ArgumentCaptor<SimulationResult> captor = new ArgumentCaptor<>();
        Printer printer = captor::capte;
        ISimulationController controller = new DefaultSimulationController(service, printer);

        //When
        controller.launchAndPrint();

        //Then
        Assertions.assertThat(captor.argument()).isEqualTo(new SimulationResult(Collections.singletonList("3 3 E")));
    }
}