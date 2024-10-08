package org.mow.it.now.core;

import org.mow.it.now.common.BeanValidation;

public record Abscissa(int x) implements Comparable<Abscissa> {
    public Abscissa {
        BeanValidation.begin()
                      .guard(() -> x >= 0, "Abscissa must not be negative");
    }

    public static Abscissa of(int x) {
        return new Abscissa(x);
    }

    @Override
    public int compareTo(Abscissa o) {
        return Integer.compare(x, o.x);
    }

    public Abscissa shiftRight() {
        return Abscissa.of(x + 1);
    }

    public Abscissa shiftLeft() {
        return Abscissa.of(x - 1);
    }
}
