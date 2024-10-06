package org.mow.it.now.controller;

import org.mow.it.now.domain.model.SimulationResult;

public class ConsolePrinter implements Printer {
    @Override
    public void print(SimulationResult result) {
        result.positions().forEach(System.out::println);
    }
}
