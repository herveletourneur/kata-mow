package org.mow.it.now.application.controller;

import org.mow.it.now.application.domain.IMowSimulationService;
import org.mow.it.now.core.SimulationResult;

public class DefaultSimulationController implements ISimulationController {
    private final IMowSimulationService simulationService;
    private final Printer printer;

    public DefaultSimulationController(IMowSimulationService simulationService, Printer printer) {
        this.simulationService = simulationService;
        this.printer = printer;
    }


    @Override
    public void launchAndPrint() {
        SimulationResult simulationResult = simulationService.launchSimulation();
        printer.print(simulationResult);
    }
}
