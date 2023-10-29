package logika;

public class Vec {

    private String nazev;
    private boolean prenositelnost;

    public Vec(String nazev, boolean prenositelnost) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
    }

    public String getNazev(){
        return  nazev;
    }

    public boolean jePrenositelna(){
        return prenositelnost;
    }

}

