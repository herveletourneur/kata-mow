package org.sfeir.quaterback.fourretout.common;

import org.sfeir.quaterback.fourretout.Plat;

public final class PlatBuilder {
    private String pain;
    private String fromage;
    private String viande;
    private String sauce;
    private boolean crudite;
    private String pate;
    private String accompagnement1;
    private String accompagnement2;
    private String accompagnement3;
    private String typePate;
    private boolean parmesan;

    private PlatBuilder() {
    }

    public static PlatBuilder aPlat() {
        return new PlatBuilder();
    }

    public PlatBuilder withPain(String pain) {
        this.pain = pain;
        return this;
    }

    public PlatBuilder withFromage(String fromage) {
        this.fromage = fromage;
        return this;
    }

    public PlatBuilder withViande(String viande) {
        this.viande = viande;
        return this;
    }

    public PlatBuilder withSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    public PlatBuilder withCrudite(boolean crudite) {
        this.crudite = crudite;
        return this;
    }

    public PlatBuilder withPate(String pate) {
        this.pate = pate;
        return this;
    }

    public PlatBuilder withAccompagnement1(String accompagnement1) {
        this.accompagnement1 = accompagnement1;
        return this;
    }

    public PlatBuilder withAccompagnement2(String accompagnement2) {
        this.accompagnement2 = accompagnement2;
        return this;
    }

    public PlatBuilder withAccompagnement3(String accompagnement3) {
        this.accompagnement3 = accompagnement3;
        return this;
    }

    public PlatBuilder withTypePate(String typePate) {
        this.typePate = typePate;
        return this;
    }

    public PlatBuilder withParmesan(boolean parmesan) {
        this.parmesan = parmesan;
        return this;
    }

    public Plat build() {
        return new Plat(pain, fromage, viande, sauce, crudite, pate, accompagnement1, accompagnement2, accompagnement3, typePate, parmesan);
    }
}
