package logika;

public class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    public PrikazMluv(HerniPlan plan){
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "Musíš zadat s kým chceš mluvit";
        }
        if(parametry[0].equals(plan.getAktualniProstor().getPostava().getJmeno())){
            return plan.getAktualniProstor().getPostava().getMluv();
        }
        else{
            return "Tahle postava tady není";
        }

    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
