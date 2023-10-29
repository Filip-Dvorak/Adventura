package logika;

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
    public void nastavVymenu(Set<Vec> coChce,String mluvPo){
        this.coChce = coChce;
        this.mluvPo = mluvPo;
    }
    public String Vymena (String nabidka){
        String chci="";
        for(Vec neco:coChce){
            chci += neco.getNazev() +" ";
        }
        if(chci.equals(nabidka)){
            probehlaVymena = true;
            return "Ok muzeme jet";
        }
        else{
            return"Tohle není všechno co jsem po tobě chtěla";
        }
    }

    public String getJmeno() {
        return jmeno;
    }
    public String getMluv(){
        if(probehlaVymena){
            return mluvPo;
        }
        else {
            return mluvPred;
        }
    }
}
