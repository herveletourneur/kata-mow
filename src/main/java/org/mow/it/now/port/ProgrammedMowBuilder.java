package org.mow.it.now.port;

import org.mow.it.now.domain.model.*;

import java.util.List;

public class ProgrammedMowBuilder {
    private ProgrammedMowBuilder() {
    }

    public static MowAbscissa builder() {
        return abscissa -> ordinate -> garden -> direction -> actions ->
                new ProgrammedMow(
                        new Mow(direction, new MowPosition(Coordinates.of(Abscissa.of(abscissa), Ordinate.of(ordinate)), garden))
                        ,actions);
    }

    public interface MowAbscissa {
        MowOrdinate abscissa(int x);
    }

    public interface MowOrdinate {
        GardenToMow ordinate(int y);
    }

    public interface GardenToMow {
        MowDirection garden(Garden garden);
    }

    public interface MowDirection {
        Actions direction(Direction direction);
    }

    public interface Actions {
        ProgrammedMow actions(List<ProgrammedMowAction> actions);
    }
}
