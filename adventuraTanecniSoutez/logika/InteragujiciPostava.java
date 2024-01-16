package logika;

import java.util.Iterator;
import java.util.Set;

//soucast DU
public class InteragujiciPostava extends Postava{
    private Set<Vec> coChce;
    private String mluvPo;
    boolean probehlaVymena = false;

    public InteragujiciPostava(String jmeno, String mluvPred) {
        super(jmeno, mluvPred);
    }

    public void nastavVymenu(Set<Vec> coChce, String mluvPo) {
        this.coChce = coChce;
        this.mluvPo = mluvPo;
    }

    public String Vymena(String nabidka, String inversedNabidka) {
        String chci = "";

        Vec neco;
        //soucast DU
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


    @Override
    public String getMluv() {
        return this.probehlaVymena ? this.mluvPo : this.mluvPred;
    }
}

