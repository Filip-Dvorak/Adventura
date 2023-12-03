package logika;

import util.Observer;
import util.SubjectOfChange;

import java.util.ArrayList;
import java.util.List;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
class PrikazJdi implements IPrikaz , SubjectOfChange {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    private boolean secretMenu = false;
    private List<String> history = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat jméno východu";
        } else {
            String smer = parametry[0];
            Prostor sousedniProstor = this.plan.getAktualniProstor().vratSousedniProstor(smer);
            if (sousedniProstor == null) {
                return "Tam se odsud jít nedá!";
            } else if (sousedniProstor.jeZamceno() && sousedniProstor.getNazev().equals("byt_partnerky")) {
                return "Pěšky je to daleko, musíš jet autem";
            } else if (sousedniProstor.jeZamceno() && sousedniProstor.getNazev().equals("koupelna")) {
                return "Měl by ses nejdřív najíst a až potom se nalíčit (aby sis to nezničil jako minule)";
            } else if (sousedniProstor.jeZamceno() && sousedniProstor.getNazev().equals("soutěž")) {
                return "Bez partnerky bych nejezdil, promluv sis ní nejdřív";
            }else if(!sousedniProstor.jeZamceno() && sousedniProstor.getNazev().equals("soutěž")) {
                this.plan.setAktualniProstor(sousedniProstor);
                hra.setKonecHry(true);
                return hra.vratEpilog();
            } else {
                this.plan.setAktualniProstor(sousedniProstor);
                history.add(smer);
                try {

                    if(history.get(history.size() - 1).equals(history.get(history.size() - 3)) && history.get(history.size() - 2).equals(history.get(history.size() - 4)))
                    {
                        plan.setSecretMenu(true);
                    }
                }
                catch (IndexOutOfBoundsException e) {

                }
                return sousedniProstor.dlouhyPopis();
            }
        }
    }

    public String getNazev() {
        return "jdi";
    }
    public boolean isSecretMenu(){
        return secretMenu;
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
}
