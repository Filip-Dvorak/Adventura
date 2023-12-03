package logika;

public class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "polož";
    private final HerniPlan plan;

    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš zadat co chceš položit";
        } else if (parametry.length > 1) {
            return "Musíš si vybrat pouze jednu věc k položení";
        } else {
            String nazevVeci = parametry[0];
            Vec pozadovanaVec = this.plan.getBatoh().vyndejZBatohu(nazevVeci);
            if (pozadovanaVec != null) {
                this.plan.getAktualniProstor().vlozVec(pozadovanaVec);
                return nazevVeci + " jsi položil v prostoru";
            } else {
                return nazevVeci + " v košíku nemáš";
            }
        }
    }

    public String getNazev() {
        return "polož";
    }
}

