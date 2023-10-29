package logika;

import java.util.*;

public class PrikazDej implements IPrikaz {
    private static final String NAZEV = "dej";
    private HerniPlan plan;

    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co chceš komu dát? (nápověda: dej komu vec1,vec2,vec3...";
        }
        String prijemce = parametry[0];
        String[] veciArr = parametry[1].split(",");
        String veci = veciArr[0]+ " " +veciArr[1] +" ";
        String veciCoMam = plan.getBatoh().getAllItems();
        if(veci.equals(veciCoMam)){
            if(prijemce.equals(plan.getAktualniProstor().getPostava().getJmeno())){
                String odpoved =  plan.getAktualniProstor().getPostava().Vymena(veci);
                if(odpoved.equals(plan.getAktualniProstor().getPostava().getMluv())){
                    plan.getAktualniProstor().vratSousedniProstor("soutěž").zamknout(false);
                }
                return odpoved;
            }
            else {
                return "Tahle postava tu není";
            }
        }
        else{
            return "Tohle nemáš v batohu";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}

