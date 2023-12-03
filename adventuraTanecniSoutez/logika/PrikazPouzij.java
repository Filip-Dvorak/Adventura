package logika;

public class PrikazPouzij implements IPrikaz {
    private static final String NAZEV = "použij";
    private HerniPlan plan;
    private Hra hra;
    public PrikazPouzij(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš zadat co chceš použít";
        } else {
            Prostor kdejsme = this.plan.getAktualniProstor();
            String nazevVeci = parametry[0];
            Vec vec = this.plan.getBatoh().getPredmet(nazevVeci);
            if (vec == null) {
                if (nazevVeci.equals("líčení") && kdejsme.getNazev().equals("koupelna")) {
                    hra.setGoodEnding(true);
                    return "Nalíčil jsi se";
                } else if (nazevVeci.equals("svačina") && kdejsme.getNazev().equals("jídelna")) {
                    return "Tahle svačina asi není pro tebe";
                } else if (nazevVeci.equals("auto") && kdejsme.getNazev().equals("garáž")) {
                    return "Auto bez klíčů nenastartuješ";
                } else if (nazevVeci.equals("snídaně") && kdejsme.getNazev().equals("jídelna")) {
                    this.plan.getProstor("koupelna").zamknout(false);
                    return "To byl dobrej chálek";
                } else {
                    return "Tohle u sebe v batohu nemáš";
                }
            } else if (vec.getNazev().equals("klíče_od_auta") && kdejsme.getNazev().equals("garáž")) {
                this.plan.getBatoh().vyndejZBatohu(nazevVeci);
                this.plan.getAktualniProstor().vratSousedniProstor("byt_partnerky").zamknout(false);
                return "Odemkl jsi auto a můžeš se vydat za partnerkou";
            } else {
                return "Tohle nejde";
            }
        }
    }

    public String getNazev() {
        return "použij";
    }
}
