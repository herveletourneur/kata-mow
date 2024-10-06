package org.mow.it.now.domain.model;

import org.mow.it.now.common.BeanValidation;

import java.util.List;

public record ProgrammedMow(Mow mow, List<ProgrammedMowAction> actions) {
    public ProgrammedMow {
        BeanValidation.begin()
                      .guardNonNull(mow, "Mow must not be null")
                      .guardNonNull(actions, "actions must not ne null")
                      .guard(() -> !actions.isEmpty(), "actions must not be empty");
    }

    public void launch() {
        actions.forEach(action -> action.doActionOn(mow));
    }
}
