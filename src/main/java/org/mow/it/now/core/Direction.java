package org.mow.it.now.core;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public enum Direction implements PositionAdvancer, DirectionRotator {
    NORTH("N", MowPosition::moveUp, 0),
    EAST("E", MowPosition::moveRight, 1),
    SOUTH("S", MowPosition::moveDown, 2),
    WEST("W", MowPosition::moveLeft, 3);

    private final String label;
    private final UnaryOperator<MowPosition> positionAdvancer;
    private final int order;

    private static final Map<String, Direction> cacheByLabel;

    private static final List<Direction> orderedDirection;

    static {
        cacheByLabel = Arrays.stream(Direction.values())
                             .collect(Collectors.collectingAndThen(Collectors.toMap(Direction::label, Function.identity()), TreeMap::new));
        orderedDirection = Arrays.stream(Direction.values())
                                 .sorted(Comparator.comparing(Direction::order))
                                 .toList();
    }


    Direction(String label, UnaryOperator<MowPosition> positionAdvancer, int order) {
        this.label = label;
        this.positionAdvancer = positionAdvancer;
        this.order = order;
    }

    public String label() {
        return label;
    }

    public int order() {
        return order;
    }

    public static Direction fromLabel(String label) {
        return Optional.ofNullable(cacheByLabel.get(label))
                       .orElseThrow(() -> new InvalidDirectionLabelException("Invalid label " + label + " for Direction"));
    }

    @Override
    public MowPosition advance(MowPosition mowPosition) {
        return positionAdvancer.apply(mowPosition);
    }

    @Override
    public Direction rightDirection() {
        return orderedDirection.size() == this.order + 1 ? orderedDirection.getFirst() : orderedDirection.get(this.order + 1);
    }

    @Override
    public Direction leftDirection() {
        return this.order == 0 ? orderedDirection.getLast() : orderedDirection.get(this.order - 1);
    }
}
