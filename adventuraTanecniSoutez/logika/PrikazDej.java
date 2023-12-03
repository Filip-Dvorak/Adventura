package logika;

public class PrikazDej implements IPrikaz {
    private static final String NAZEV = "dej";
    private HerniPlan plan;

    public PrikazDej(HerniPlan plan) {
        this.plan = plan;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co chceš komu dát? (nápověda: dej komu vec1,vec2,vec3...";
        } else {
            String prijemce = parametry[0];
            String[] veciArr = parametry[1].split(",");
            String veci = "";

            for(int i = 0; i < veciArr.length; ++i) {
                veci = veci + veciArr[i] + " ";
            }

            String inversedVeci = "";

            for(int i = veciArr.length - 1; i >= 0; --i) {
                inversedVeci = inversedVeci + veciArr[i] + " ";
            }

            String veciCoMam = this.plan.getBatoh().getAllItems();
            if (!veci.equals(veciCoMam) && !inversedVeci.equals(veciCoMam)) {
                return "Tohle nemáš v batohu";
            } else if (prijemce.equals(this.plan.getAktualniProstor().getPostava().getJmeno())) {
                String odpoved = this.plan.getAktualniProstor().getPostava().Vymena(veci, inversedVeci);
                if (odpoved.equals(this.plan.getAktualniProstor().getPostava().getMluv())) {
                    this.plan.getAktualniProstor().vratSousedniProstor("soutěž").zamknout(false);
                    this.plan.getBatoh().vyndejZBatohu("pinetky");
                    this.plan.getBatoh().vyndejZBatohu("svačina");

                }

                return odpoved;
            } else {
                return "Tahle postava tu není";
            }
        }
    }

    public String getNazev() {
        return "dej";
    }
}
