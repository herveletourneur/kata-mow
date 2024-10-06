package org.mow.it.now;

import org.mow.it.now.controller.ConsolePrinter;
import org.mow.it.now.controller.DefaultSimulationController;
import org.mow.it.now.controller.ISimulationController;
import org.mow.it.now.controller.Printer;
import org.mow.it.now.domain.DefaultMowSimulationService;
import org.mow.it.now.domain.IMowSimulationService;
import org.mow.it.now.port.MowSimulationFileAdapter;
import org.mow.it.now.port.MowSimulationRetriever;
import org.mow.it.now.port.ProgrammatedMowMapper;

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
