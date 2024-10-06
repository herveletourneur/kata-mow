package org.mow.it.now.controller;

import org.mow.it.now.domain.IMowSimulationService;
import org.mow.it.now.domain.model.SimulationResult;

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
