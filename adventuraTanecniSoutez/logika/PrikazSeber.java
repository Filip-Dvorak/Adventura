package logika;

public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private final HerniPlan plan;

    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Musíš zadat název předmětu.";
        } else if (parametry.length > 1) {
            return "Moc věcí k sebrání. Prosím vyber si jednu";
        } else {
            String nazevVeci = parametry[0];
            if (this.plan.getAktualniProstor().obsahujeVec(nazevVeci)) {
                Vec pozadovanaVec = this.plan.getAktualniProstor().vyberVec(nazevVeci);
                if (pozadovanaVec == null) {
                    return nazevVeci + " nedá se sebrat";
                } else {
                    boolean povedloSeVlozit = this.plan.getBatoh().vlozDoBatohu(pozadovanaVec);
                    if (povedloSeVlozit) {
                        return "Sebral jsi " + nazevVeci;
                    } else {
                        this.plan.getAktualniProstor().vlozVec(pozadovanaVec);
                        return nazevVeci + " se snažíš nacpat do plného košíčku";
                    }
                }
            } else {
                return nazevVeci + " není v prostoru";
            }
        }
    }

    public String getNazev() {
        return "seber";
    }
}
