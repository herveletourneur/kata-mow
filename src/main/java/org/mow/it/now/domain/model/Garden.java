package org.mow.it.now.domain.model;

import org.mow.it.now.common.BeanValidation;

public record Garden(Abscissa xMax, Ordinate yMax) {
    public Garden {
        BeanValidation.begin()
                      .guardNonNull(xMax, "Max abscissa nust no be null")
                      .guardNonNull(yMax, "Max ordinate nust no be null");
    }

    public static Garden of(Abscissa xMax, Ordinate yMax) {
        return new Garden(xMax, yMax);
    }

    public boolean abscissaCanShiftRight(Abscissa abscissa) {
        return abscissa.compareTo(xMax) < 0;
    }

    public boolean abscissaCanShiftLeft(Abscissa abscissa) {
        return abscissa.x() > 0;
    }

    public boolean ordinateCanShiftUp(Ordinate ordinate) {
        return ordinate.compareTo(yMax) < 0;
    }

    public boolean ordinateCanShiftDown(Ordinate ordinate) {
        return ordinate.y() > 0;
    }
}
