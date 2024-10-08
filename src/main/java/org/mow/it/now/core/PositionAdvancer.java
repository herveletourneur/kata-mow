package org.mow.it.now.core;

@FunctionalInterface
public interface PositionAdvancer {
    MowPosition advance(MowPosition mowPosition);
}
