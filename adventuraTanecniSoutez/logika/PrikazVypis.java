package logika;

public class PrikazVypis implements IPrikaz {
private final String nazev = "vypiš";
private Batoh batoh;
private HerniPlan plan;
public PrikazVypis(HerniPlan plan) {
    batoh = plan.getBatoh();
    this.plan = plan;
}


    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co chceš vypsat?";
        }
        String info = "";
        if (parametry[0].equals("prostor")) {
             info = plan.getAktualniProstor().infoOProstoru();
             return info;
        } else if (parametry[0].equals("batoh")) {
            info = batoh.getAllItems();
            return info;
        }
        return "Tohle nejde vypsat";
    }

    @Override
    public String getNazev() {
        return nazev;
    }
}
