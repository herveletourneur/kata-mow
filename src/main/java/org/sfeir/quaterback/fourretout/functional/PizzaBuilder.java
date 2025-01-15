package org.sfeir.quaterback.fourretout.functional;

import org.sfeir.quaterback.fourretout.Plat;

public class PizzaBuilder {

    public static Pate pizza() {
        return pate -> fromage -> sauce -> accompagnement -> accompagnement1 -> accompagnement2 ->
                new Plat(null, fromage, null, sauce, false, null, accompagnement, accompagnement1, accompagnement2, null, false);

    }

    public interface Pate {
        Fromage pate(String pate);
    }

    public interface Fromage {
        Sauce fromage(String fromage);
    }

    public interface Sauce {
        Accompagnement1 sauce(String sauce);
    }

    public interface Accompagnement1 {
        Accompagnement2 accompagnement(String accompagnement);
    }

    public interface Accompagnement2 {
        Accompagnement3 accompagnement(String accompagnement);
    }

    public interface Accompagnement3 {
        Plat accompagnement(String accompagnement);
    }
}
