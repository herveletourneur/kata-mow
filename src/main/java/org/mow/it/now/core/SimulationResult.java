package org.mow.it.now.core;

import org.mow.it.now.common.BeanValidation;

import java.util.List;

public record SimulationResult(List<String> positions) {
    public SimulationResult {
        BeanValidation.begin()
                      .guardNonNull(positions, "positions must not be null")
                      .guard(() -> !positions.isEmpty(), "positions must not be empty");
    }
}
