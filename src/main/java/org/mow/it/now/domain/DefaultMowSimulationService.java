package org.mow.it.now.domain;

import org.mow.it.now.domain.model.SimulationResult;
import org.mow.it.now.port.MowSimulationRetriever;

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
