package org.sfeir.quaterback.fourretout.functional;

import org.sfeir.quaterback.fourretout.Plat;

public class PateBuilder {

    public static TypePate pate() {
        return typePate -> parmesan -> sauce -> accompagnement -> accompagnement1 ->
                new Plat(null, null, null, sauce, false, null, accompagnement, accompagnement1, null, typePate, parmesan);

    }

    public interface TypePate {
        Parmesan typePate(String typePate);
    }

    public interface Parmesan {
        Sauce withParmesan(boolean parmesan);
    }

    public interface Sauce {
        Accompagnement1 sauce(String sauce);
    }

    public interface Accompagnement1 {
        Accompagnement2 accompagnement(String accompagnement);
    }

    public interface Accompagnement2 {
        Plat accompagnement(String accompagnement);
    }
}
