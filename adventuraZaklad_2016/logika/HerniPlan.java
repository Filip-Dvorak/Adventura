package logika;


import java.util.HashSet;
import java.util.Set;

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
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Batoh batoh;
    private static final int kapacitaBatohu = 4;
    private HashSet<Prostor> prostory = new HashSet<>();

    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor pokoj = new Prostor("pokoj","tvůj pokoj");
        Prostor koupelna = new Prostor("koupelna", "koupelna");
        Prostor jidlena = new Prostor("jídelna","jídelna, kde se dá najíst");
        Prostor garaz = new Prostor("garáž","garáž kde máě zaparkované auto");
        Prostor bytPartnerky = new Prostor("byt_partnerky","byt ve kterém bydlí tvoje partnerka");
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        pokoj.setVychod(koupelna);
        koupelna.setVychod(pokoj);
        pokoj.setVychod(jidlena);
        jidlena.setVychod(pokoj);
        pokoj.setVychod(garaz);
        garaz.setVychod(pokoj);
        garaz.setVychod(bytPartnerky);
        bytPartnerky.setVychod(garaz);
        bytPartnerky.zamknout(true);
        koupelna.zamknout(true);
        prostory.add(pokoj);
        prostory.add(koupelna);
        prostory.add(jidlena);
        prostory.add(garaz);
        prostory.add(bytPartnerky);

        //přiřazují se věci prostorům
        pokoj.vlozVec(new Vec("klíče_od_auta", true));
        koupelna.vlozVec(new Vec("líčení", false));
        koupelna.vlozVec(new Vec("pinetky", true));
        jidlena.vlozVec(new Vec("snídaně", false));
        jidlena.vlozVec(new Vec("svačina", true));
        garaz.vlozVec(new Vec("auto", false));

        batoh = new Batoh(kapacitaBatohu);
                
        aktualniProstor = pokoj;  // hra začíná ve tvém pokoji
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    public Prostor getProstor(String nazevProstoru){
        for(Prostor prostor : prostory){
            if (prostor.getNazev().equals(nazevProstoru)){
                return prostor;
            }
        }
        return null;
    }


    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    public Batoh getBatoh(){
        return this.batoh;
    }


}
