package org.mow.it.now;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mow.it.now.controller.DefaultSimulationController;
import org.mow.it.now.controller.ISimulationController;
import org.mow.it.now.controller.Printer;
import org.mow.it.now.domain.DefaultMowSimulationService;
import org.mow.it.now.domain.IMowSimulationService;
import org.mow.it.now.domain.model.SimulationResult;
import org.mow.it.now.port.MowSimulationFileAdapter;
import org.mow.it.now.port.MowSimulationRetriever;
import org.mow.it.now.port.ProgrammatedMowMapper;
import org.mow.it.now.utils.ArgumentCaptor;

import java.util.List;

public class MowSimulationIntegrationTest {
    @Test
    void should_lunch_full_simulation() {
        //Given
        MowSimulationRetriever mowSimulationRetriever = new MowSimulationFileAdapter("integration_test", " ", new ProgrammatedMowMapper());
        IMowSimulationService simulationService = new DefaultMowSimulationService(mowSimulationRetriever);
        ArgumentCaptor<SimulationResult> argumentCaptor = new ArgumentCaptor<>();
        Printer printer = argumentCaptor::capte;
        ISimulationController controller = new DefaultSimulationController(simulationService, printer);

        //When
        controller.launchAndPrint();

        //Then
        Assertions.assertThat(argumentCaptor.argument()).isEqualTo(new SimulationResult(List.of("1 3 N", "5 1 E")));
    }
}
