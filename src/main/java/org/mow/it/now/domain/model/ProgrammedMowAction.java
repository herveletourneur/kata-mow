package org.mow.it.now.domain.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ProgrammedMowAction {
    TURN_RIGHT("D", Mow::turnRight),
    TURN_LEFT("G", Mow::turnLeft),
    ADVANCE("A", Mow::advance);

    private final String label;

    private final static Map<String, ProgrammedMowAction> cacheByLabel;

    private final Consumer<Mow> actionApplier;

    static {
        cacheByLabel = Arrays.stream(ProgrammedMowAction.values())
                             .collect(Collectors.collectingAndThen(Collectors.toMap(ProgrammedMowAction::label, Function.identity()), TreeMap::new));
    }

    ProgrammedMowAction(String label, Consumer<Mow> actionApplier) {
        this.label = label;
        this.actionApplier = actionApplier;
    }

    public String label() {
        return label;
    }

    public void doActionOn(Mow mow) {
        actionApplier.accept(mow);
    }

    public static ProgrammedMowAction fromLabel(String label) {
        return Optional.ofNullable(cacheByLabel.get(label))
                       .orElseThrow(() -> new InvalidProgrammedMowActionLabelException("Invalid label " + label + " for ProgrammedMowAction"));
    }
}
