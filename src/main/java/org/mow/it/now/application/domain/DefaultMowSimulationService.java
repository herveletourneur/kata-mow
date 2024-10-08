package org.mow.it.now.application.domain;

import org.mow.it.now.core.SimulationResult;
import org.mow.it.now.application.port.MowSimulationRetriever;

import java.util.List;

public class DefaultMowSimulationService implements IMowSimulationService {
    private final MowSimulationRetriever mowSimulationRetriever;

    public DefaultMowSimulationService(MowSimulationRetriever mowSimulationRetriever) {
        this.mowSimulationRetriever = mowSimulationRetriever;
    }

    @Override
    public SimulationResult launchSimulation() {
        List<String> positions = mowSimulationRetriever.retrieve()
                                                       .launchSimulation();
        return new SimulationResult(positions);
    }
}
