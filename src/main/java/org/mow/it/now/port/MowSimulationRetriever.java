package org.mow.it.now.port;

import org.mow.it.now.domain.model.MowSimulation;

@FunctionalInterface
public interface MowSimulationRetriever {
    MowSimulation retrieve();
}
