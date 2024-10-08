package org.mow.it.now.core;

import org.mow.it.now.common.BeanValidation;

public record Coordinates(Abscissa x, Ordinate y) {

    public Coordinates {
        BeanValidation.begin()
                      .guardNonNull(x, "Abcissa must not be null")
                      .guardNonNull(y, "Ordinate must not be null");
    }

    public static Coordinates of(Abscissa x, Ordinate y) {
        return new Coordinates(x, y);
    }

    public Coordinates shiftUp() {
        return new Coordinates(x, y.shiftUp());
    }

    public Coordinates shiftDown() {
        return new Coordinates(x, y.shiftDown());
    }

    public Coordinates shiftLeft() {
        return new Coordinates(x.shiftLeft(), y);
    }

    public Coordinates shiftRight() {
        return new Coordinates(x.shiftRight(), y);
    }

    public String asString() {
        return String.format("%s %s", x.x(), y.y());
    }
}
