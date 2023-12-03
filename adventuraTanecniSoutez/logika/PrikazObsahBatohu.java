package logika;

public class PrikazObsahBatohu implements IPrikaz {
    private static final String NAZEV = "obsah_batohu";
    private Batoh batoh;

    public PrikazObsahBatohu(HerniPlan plan) {
        this.batoh = plan.getBatoh();
    }

    public String provedPrikaz(String... parametry) {
        return this.batoh.getAllItems();
    }

    public String getNazev() {
        return "obsah_batohu";
    }
}

