package logika;

public class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;

    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Musíš zadat s kým chceš mluvit";
        } else {
            return parametry[0].equals(this.plan.getAktualniProstor().getPostava().getJmeno()) ? this.plan.getAktualniProstor().getPostava().getMluv() : "Tahle postava tady není";
        }
    }

    public String getNazev() {
        return "mluv";
    }
}
