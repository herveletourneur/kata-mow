package org.mow.it.now.domain.model;

import org.mow.it.now.common.BeanValidation;

import java.util.List;

public record MowSimulation(List<ProgrammedMow> mows) {
    public MowSimulation {
        BeanValidation.begin()
                      .guardNonNull(mows, "mows must not be null")
                      .guard(() -> !mows.isEmpty(), "mows must not be empty");
    }

    public List<String> launchSimulation() {
        mows.forEach(ProgrammedMow::launch);
        return mows.stream()
                   .map(ProgrammedMow::mow)
                   .map(Mow::position)
                   .toList();
    }
}
