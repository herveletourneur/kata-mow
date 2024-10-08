package org.mow.it.now.core;

import org.mow.it.now.common.BeanValidation;

import java.util.Objects;

public class Mow {

    private Direction direction;

    private MowPosition mowPosition;

    public Mow(Direction direction, MowPosition mowPosition) {
        this.direction = direction;
        this.mowPosition = mowPosition;
        guard();
    }

    private void guard() {
        BeanValidation.begin()
                      .guardNonNull(direction, "Direction must not be null")
                      .guardNonNull(mowPosition, "Mow Position must not be null");
    }

    public void turnRight() {
        direction = direction.rightDirection();
    }

    public void turnLeft() {
        direction = direction.leftDirection();
    }

    public void advance() {
        mowPosition = direction.advance(mowPosition);
    }

    public String position() {
        return String.format("%s %s", mowPosition.coordinates().asString(), direction.label());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mow mow = (Mow) o;
        return direction == mow.direction && Objects.equals(mowPosition, mow.mowPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, mowPosition);
    }
}
