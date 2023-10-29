package logika;

public class PrikazPouzij implements IPrikaz{

    private static final String NAZEV = "pouzij";
    private HerniPlan plan;

    //konstruktor
    public PrikazPouzij(HerniPlan plan){
        this.plan = plan;

    }
    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "Musíš zadat co chceš použít";
        }
        Prostor kdejsme = plan.getAktualniProstor();
        String nazevVeci = parametry[0];
        Vec vec = plan.getBatoh().getPredmet(nazevVeci);
        if (vec==null){
            if(nazevVeci.equals("sprcha") && kdejsme.getNazev().equals("koupelna")){
                return "Osprchoval jsi se";
            }
            if(nazevVeci.equals("auto") && kdejsme.getNazev().equals("garáž")){
                return "Auto bez klíčů nenastartuješ";
            }
            if(nazevVeci.equals("snídaně") && kdejsme.getNazev().equals("jídelna")){
                plan.getProstor("koupelna").zamknout(false);
                return "To byl dobrej chálek";
            }
            return "Tohle u sebe v batohu nemáš";
        }
        if(vec.getNazev().equals("klíče_od_auta") &&kdejsme.getNazev().equals("garáž")){
            plan.getBatoh().vyndejZBatohu(nazevVeci);
            plan.getAktualniProstor().vratSousedniProstor("byt_partnerky").zamknout(false);
            return "Odemkl jsi auto a můžeš se vydat za partnerkou";
        }
        else {
            return "Tohle nejde";
        }
        }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
