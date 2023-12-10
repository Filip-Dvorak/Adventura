package logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {
    private String nazev;
    private Postava postava;
    private String popis;
    private boolean zamcena = false;
    private Set<Prostor> vychody;
    private List<Vec> seznamVeci;
    private final double posLeft;
    private final double posTop;

    public void zamknout(boolean zamceno) {
        this.zamcena = zamceno;
    }

    public boolean jeZamceno() {
        return this.zamcena;
    }

    public void addPostava(Postava postava) {
        this.postava = postava;
    }

    public Prostor(String nazev, String popis , double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        this.vychody = new HashSet();
        this.seznamVeci = new ArrayList();
        this.posLeft = posLeft;
        this.posTop = posTop;
    }

    public void setVychod(Prostor vedlejsi) {
        this.vychody.add(vedlejsi);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Prostor)) {
            return false;
        } else {
            Prostor druhy = (Prostor)o;
            return Objects.equals(this.nazev, druhy.nazev);
        }
    }

    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    public String getNazev() {
        return this.nazev;
    }

    public String dlouhyPopis() {
        String var10000;
        if (this.postava != null) {
            var10000 = this.popis;
            return "Jsi v mistnosti/prostoru " + var10000 + ".\nVeci: " + this.seznamVeci() + "\nPostavy: " + this.postava.getJmeno() + "\n" + this.popisVychodu();
        } else {
            var10000 = this.popis;
            return "Jsi v mistnosti/prostoru " + var10000 + ".\nVeci: " + this.seznamVeci() + "\n" + this.popisVychodu();
        }
    }

    private String popisVychodu() {
        String vracenyText = "východy:";

        Prostor sousedni;
        for(Iterator var2 = this.vychody.iterator(); var2.hasNext(); vracenyText = vracenyText + " " + sousedni.getNazev()) {
            sousedni = (Prostor)var2.next();
        }

        return vracenyText;
    }

    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor> hledaneProstory = (List)this.vychody.stream().filter((sousedni) -> {
            return sousedni.getNazev().equals(nazevSouseda);
        }).collect(Collectors.toList());
        return hledaneProstory.isEmpty() ? null : (Prostor)hledaneProstory.get(0);
    }

    public void vlozVec(Vec vec) {
        this.seznamVeci.add(vec);
    }

    public boolean obsahujeVec(String nazevVeci) {
        Iterator var2 = this.seznamVeci.iterator();

        Vec neco;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            neco = (Vec)var2.next();
        } while(!neco.getNazev().equals(nazevVeci));

        return true;
    }

    public Vec vyberVec(String nazevVeci) {
        Vec vybraVec = null;
        Iterator var3 = this.seznamVeci.iterator();

        while(var3.hasNext()) {
            Vec neco = (Vec)var3.next();
            if (neco.getNazev().equals(nazevVeci)) {
                vybraVec = neco;
            }
        }

        if (vybraVec != null) {
            if (vybraVec.jePrenositelna()) {
                this.seznamVeci.remove(vybraVec);
            } else {
                vybraVec = null;
            }
        }

        return vybraVec;
    }

    private String seznamVeci() {
        String seznam = "";

        Vec vec;
        for(Iterator var2 = this.seznamVeci.iterator(); var2.hasNext(); seznam = seznam + vec.getNazev() + " ") {
            vec = (Vec)var2.next();
        }

        return seznam;
    }

    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(this.vychody);
    }

    public Postava getPostava() {
        return this.postava;
    }
    public InteragujiciPostava getInteragujiciPostava() {
        return (InteragujiciPostava) this.postava;
    }

    public double getPosLeft() {
        return posLeft;
    }

    public double getPosTop() {
        return posTop;
    }

    public List<Vec> getVsechnyVeci() {
        return this.seznamVeci;
    }

    public String infoOProstoru() {
        String info ="";
        if (this.postava != null) {
            info = this.popis;
            return "Veci: " + this.seznamVeci() + "\nPostavy: " + this.postava.getJmeno() + "\n" + this.popisVychodu();
        } else {
            info = this.popis;
            return "Veci: " + this.seznamVeci() + "\n" + this.popisVychodu();
        }
    }
}
