package org.mow.it.now.application.controller;

import org.mow.it.now.core.SimulationResult;

public class ConsolePrinter implements Printer {
    @Override
    public void print(SimulationResult result) {
        result.positions().forEach(System.out::println);
    }
}
