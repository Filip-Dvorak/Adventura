package logika;


import util.Observer;
import util.SubjectOfChange;

import java.util.*;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan implements SubjectOfChange {
    private Prostor aktualniProstor;
    private Batoh batoh;
    private static final int kapacitaBatohu = 4;
    private HashSet<Prostor> prostory = new HashSet();
    private final Set<Observer> observers = new HashSet<>();
    private boolean secretMenu = false;

    public HerniPlan() {
        this.zalozProstoryHry();
    }

    private void zalozProstoryHry() {
        Prostor pokoj = new Prostor("pokoj", "tvůj pokoj", 155 , 105);
        Prostor koupelna = new Prostor("koupelna", "koupelna", 290 , 20);
        Prostor jidlena = new Prostor("jídelna", "jídelna, kde se dá najíst", 80 , 20);
        Prostor garaz = new Prostor("garáž", "garáž kde máě zaparkované auto", 190, 205);
        Prostor bytPartnerky = new Prostor("byt_partnerky", "byt ve kterém bydlí tvoje partnerka", 290, 265);
        Prostor soutez = new Prostor("soutěž", "Dorazili jste na soutež", 400, 205);
        pokoj.setVychod(koupelna);
        koupelna.setVychod(pokoj);
        pokoj.setVychod(jidlena);
        jidlena.setVychod(pokoj);
        pokoj.setVychod(garaz);
        garaz.setVychod(pokoj);
        garaz.setVychod(bytPartnerky);
        bytPartnerky.setVychod(garaz);
        bytPartnerky.setVychod(soutez);
        soutez.setVychod(bytPartnerky);
        soutez.zamknout(true);
        bytPartnerky.zamknout(true);
        koupelna.zamknout(true);
        this.prostory.add(pokoj);
        this.prostory.add(koupelna);
        this.prostory.add(jidlena);
        this.prostory.add(garaz);
        this.prostory.add(bytPartnerky);
        Vec pinetky = new Vec("pinetky", true);
        Vec svacina = new Vec("svačina", true);
        pokoj.vlozVec(new Vec("klíče_od_auta", true));
        koupelna.vlozVec(new Vec("líčení", false));
        koupelna.vlozVec(pinetky);
        jidlena.vlozVec(svacina);
        jidlena.vlozVec(new Vec("snídaně", false));
        garaz.vlozVec(new Vec("auto", false));
        InteragujiciPostava partnerka = new InteragujiciPostava("partnerka", "Čau máš pro mě ty věci co jsem chtěla?");
        Set<Vec> veciCoChce = new HashSet();
        veciCoChce.add(pinetky);
        veciCoChce.add(svacina);
        partnerka.nastavVymenu(veciCoChce, "Dobrý můžeme jet");
        bytPartnerky.addPostava(partnerka);
        this.batoh = Batoh.getInstance(4);
        this.aktualniProstor = pokoj;
    }

    public Prostor getAktualniProstor() {
        return this.aktualniProstor;
    }

    public Prostor getProstor(String nazevProstoru) {
        Iterator var2 = this.prostory.iterator();

        Prostor prostor;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            prostor = (Prostor)var2.next();
        } while(!prostor.getNazev().equals(nazevProstoru));

        return prostor;
    }

    public void setAktualniProstor(Prostor prostor) {

        this.aktualniProstor = prostor;
        notifyObservers();
    }

    public Batoh getBatoh() {
        return this.batoh;
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

    public HashSet<Prostor> getVsechnyProstory() {
        return this.prostory;
    }

    public boolean isSecretMenu() {
        return secretMenu;
    }

    public void setSecretMenu(boolean secretMenu) {
        this.secretMenu = secretMenu;
    }
}