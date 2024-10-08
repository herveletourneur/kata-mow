package org.mow.it.now.batch.step;

public enum ProgrammedMowFileField {
    ABSCISSA(0),
    ORDINATE(1),
    DIRECTION(2),
    ACTIONS(3)
    ;

    private final int fileOrder;

    ProgrammedMowFileField(int fileOrder) {
        this.fileOrder = fileOrder;
    }

    public int fileOrder() {
        return fileOrder;
    }
}
