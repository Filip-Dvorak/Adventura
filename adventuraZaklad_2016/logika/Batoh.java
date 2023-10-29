package logika;

import java.util.HashSet;
import java.util.Set;

public class Batoh {
    private final int omezeniKosicku;
    private Set<Vec> obsahBatohu;

    public Batoh(int omezeniKosicku){
        this.omezeniKosicku = omezeniKosicku;
        obsahBatohu = new HashSet<>();
    }

    public boolean vlozDoBatohu(Vec neco){
        if(this.obsahBatohu.size()< omezeniKosicku && neco.jePrenositelna()) {
            obsahBatohu.add(neco);
            return true;
        }
        return false;
    }

    public Vec vyndejZBatohu(String nazevVeci){
        for (Vec neco: obsahBatohu){
            if (neco.getNazev().equals(nazevVeci)){
                this.obsahBatohu.remove(neco);
                return neco;
            }
        }
        return null;
    }

    public String dlouhyPopis(){
        String vypis = "Obsah kosicku: ";
        for(Vec neco: obsahBatohu){
            vypis += neco.getNazev() + " ";
        }
        return vypis;
    }

    public Vec getPredmet(String nazevVeci) {
        for (Vec neco: obsahBatohu){
            if (neco.getNazev().equals(nazevVeci)){
                return neco;
            }
        }
        return null;
    }

    public String getAllItems() {
        String vypis = "";
        for(Vec neco: obsahBatohu){
            vypis += neco.getNazev() + " ";
        }
        return vypis;
    }
}
