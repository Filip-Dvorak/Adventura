package logika;

import util.Observer;
import util.SubjectOfChange;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Batoh implements SubjectOfChange {
    private static Batoh instance;
    private final int kapacitaBatohu;
    private Set<Vec> obsahBatohu;
    private final Set<Observer> observers = new HashSet<>();

    private Batoh(int kapacitaBatohu) {
        this.kapacitaBatohu = kapacitaBatohu;
        this.obsahBatohu = new HashSet();
    }
    //soucast DU
    public static Batoh getInstance(int kapacitaBatohu) {
        if (instance == null) {
            instance = new Batoh(kapacitaBatohu);
        }
        return instance;
    }

    public boolean vlozDoBatohu(Vec neco) {
        if (this.obsahBatohu.size() < this.kapacitaBatohu && neco.jePrenositelna()) {
            this.obsahBatohu.add(neco);
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    public Vec vyndejZBatohu(String nazevVeci) {
        Iterator var2 = this.obsahBatohu.iterator();

        Vec neco;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            neco = (Vec)var2.next();
        } while(!neco.getNazev().equals(nazevVeci));

        this.obsahBatohu.remove(neco);
        notifyObservers();
        return neco;
    }

    public Vec getPredmet(String nazevVeci) {
        Iterator var2 = this.obsahBatohu.iterator();

        Vec neco;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            neco = (Vec)var2.next();
        } while(!neco.getNazev().equals(nazevVeci));

        return neco;
    }

    public String getAllItems() {
        String vypis = "";

        Vec neco;
        for(Iterator var2 = this.obsahBatohu.iterator(); var2.hasNext(); vypis = vypis + neco.getNazev() + " ") {
            neco = (Vec)var2.next();
        }

        return vypis;
    }

    public Set<Vec> getItems() {
        return this.obsahBatohu;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public Set<String> getMnozinaVeci() {
        Set<String> mnozinaVeci = new HashSet();
        for (Vec vec : this.obsahBatohu) {
            mnozinaVeci.add(vec.getNazev());
        }
        return mnozinaVeci;
    }
}
