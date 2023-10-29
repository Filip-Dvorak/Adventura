package logika;

public class PrikazObsahBatohu implements IPrikaz{
    private static final String NAZEV = "obsah_batohu";
    private Batoh batoh;

    public PrikazObsahBatohu(HerniPlan plan){
        batoh=plan.getBatoh();
    }

    @Override
    public String provedPrikaz(String... parametry) {
        return batoh.getAllItems();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
