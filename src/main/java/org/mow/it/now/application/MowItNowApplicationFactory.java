package org.mow.it.now.application;

import org.mow.it.now.application.controller.ConsolePrinter;
import org.mow.it.now.application.controller.DefaultSimulationController;
import org.mow.it.now.application.controller.ISimulationController;
import org.mow.it.now.application.controller.Printer;
import org.mow.it.now.application.domain.DefaultMowSimulationService;
import org.mow.it.now.application.domain.IMowSimulationService;
import org.mow.it.now.application.port.MowSimulationFileAdapter;
import org.mow.it.now.application.port.MowSimulationRetriever;
import org.mow.it.now.application.port.ProgrammatedMowMapper;

public class MowItNowApplicationFactory {
    private final String fileName;

    public MowItNowApplicationFactory(String fileName) {
        this.fileName = fileName;
    }

    public ISimulationController controller() {
        MowSimulationRetriever mowSimulationRetriever = new MowSimulationFileAdapter(fileName, " ", new ProgrammatedMowMapper());
        IMowSimulationService simulationService = new DefaultMowSimulationService(mowSimulationRetriever);
        Printer printer = new ConsolePrinter();
        return new DefaultSimulationController(simulationService, printer);
    }
}
