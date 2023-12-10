package logika;

import java.util.Iterator;
import java.util.Set;

public class Postava {
    private String jmeno;
    protected String mluvPred;

    public Postava(String jmeno, String mluvPred) {
        this.jmeno = jmeno;
        this.mluvPred = mluvPred;
    }


    public String getJmeno() {
        return this.jmeno;
    }

    public String getMluv() {
       return this.mluvPred;
    }
}

