package org.mow.it.now.domain.model;

@FunctionalInterface
public interface PositionAdvancer {
    MowPosition advance(MowPosition mowPosition);
}
