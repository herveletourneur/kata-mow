package org.sfeir.quaterback.fourretout;

public class Plat {
    private final String pain;
    private final String fromage;
    private final String viande;
    private final String sauce;
    private final boolean crudite;
    private final String pate;
    private final String accompagnement1;
    private final String accompagnement2;
    private final String accompagnement3;
    private final String typePate;
    private final boolean parmesan;

    public Plat(String pain, String fromage, String viande, String sauce, boolean crudite, String pate, String accompagnement1, String accompagnement2, String accompagnement3, String typePate, boolean parmesan) {
        this.pain = pain;
        this.fromage = fromage;
        this.viande = viande;
        this.sauce = sauce;
        this.crudite = crudite;
        this.pate = pate;
        this.accompagnement1 = accompagnement1;
        this.accompagnement2 = accompagnement2;
        this.accompagnement3 = accompagnement3;
        this.typePate = typePate;
        this.parmesan = parmesan;
    }


    public String getPain() {
        return pain;
    }

    public String getFromage() {
        return fromage;
    }

    public String getViande() {
        return viande;
    }

    public String getSauce() {
        return sauce;
    }

    public boolean isCrudite() {
        return crudite;
    }

    public String getPate() {
        return pate;
    }

    public String getAccompagnement1() {
        return accompagnement1;
    }

    public String getAccompagnement2() {
        return accompagnement2;
    }

    public String getAccompagnement3() {
        return accompagnement3;
    }

    public String getTypePate() {
        return typePate;
    }

    public boolean isParmesan() {
        return parmesan;
    }

}
