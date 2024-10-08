package org.mow.it.now.core;

import org.mow.it.now.common.BeanValidation;

public record Ordinate(int y) implements Comparable<Ordinate> {

    public Ordinate {
        BeanValidation.begin()
                      .guard(() -> y >= 0, "Ordinate must not be negative");
    }

    public static Ordinate of(int y) {
        return new Ordinate(y);
    }


    @Override
    public int compareTo(Ordinate o) {
        return Integer.compare(y, o.y);
    }

    public Ordinate shiftUp() {
        return Ordinate.of(y + 1);
    }

    public Ordinate shiftDown() {
        return Ordinate.of(y - 1);
    }
}
