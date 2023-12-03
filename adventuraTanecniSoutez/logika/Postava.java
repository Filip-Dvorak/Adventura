package logika;

import java.util.Iterator;
import java.util.Set;

public class Postava {
    private String jmeno;
    private Set<Vec> coChce;
    private String mluvPred;
    private String mluvPo;
    String recChce;
    boolean probehlaVymena = false;

    public Postava(String jmeno, String mluvPred) {
        this.jmeno = jmeno;
        this.mluvPred = mluvPred;
    }

    public void nastavVymenu(Set<Vec> coChce, String mluvPo) {
        this.coChce = coChce;
        this.mluvPo = mluvPo;
    }

    public String Vymena(String nabidka, String inversedNabidka) {
        String chci = "";

        Vec neco;
        for(Iterator var4 = this.coChce.iterator(); var4.hasNext(); chci = chci + neco.getNazev() + " ") {
            neco = (Vec)var4.next();
        }

        if (!chci.equals(nabidka) && !chci.equals(inversedNabidka)) {
            return "Tohle není všechno co jsem po tobě chtěla";
        } else {
            this.probehlaVymena = true;
            return this.getMluv();
        }
    }

    public String getJmeno() {
        return this.jmeno;
    }

    public String getMluv() {
        return this.probehlaVymena ? this.mluvPo : this.mluvPred;
    }
}

