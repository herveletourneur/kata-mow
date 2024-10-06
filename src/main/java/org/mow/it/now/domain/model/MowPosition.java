package org.mow.it.now.domain.model;

import org.mow.it.now.common.BeanValidation;

public record MowPosition(Coordinates coordinates, Garden garden) {
    public MowPosition {
        BeanValidation.begin()
                      .guardNonNull(coordinates, "Coordinates should not be null")
                      .guardNonNull(garden, "Garden should not be null")
                      .guard(() -> garden.xMax().compareTo(coordinates.x()) >= 0, "Abscissa of Coordinate should be in Garden limit")
                      .guard(() -> garden.yMax().compareTo(coordinates.y()) >= 0, "Ordinate of Coordinate should be in Garden limit");
    }

    public MowPosition moveRight() {
        if (garden().abscissaCanShiftRight(coordinates.x())) {
            return new MowPosition(coordinates.shiftRight(), garden);
        }
        return this;
    }

    public MowPosition moveLeft() {
        if (garden().abscissaCanShiftLeft(coordinates.x())) {
            return new MowPosition(coordinates.shiftLeft(), garden);
        }
        return this;
    }

    public MowPosition moveUp() {
        if (garden().ordinateCanShiftUp(coordinates.y())) {
            return new MowPosition(coordinates.shiftUp(), garden);
        }
        return this;
    }

    public MowPosition moveDown() {
        if (garden().ordinateCanShiftDown(coordinates.y())) {
            return new MowPosition(coordinates.shiftDown(), garden);
        }
        return this;
    }
}
