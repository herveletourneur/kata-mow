package org.mow.it.now.application.port;

import org.mow.it.now.core.MowSimulation;

@FunctionalInterface
public interface MowSimulationRetriever {
    MowSimulation retrieve();
}
