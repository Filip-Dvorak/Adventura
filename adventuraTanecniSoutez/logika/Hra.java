package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy = new SeznamPrikazu();
    private HerniPlan herniPlan = new HerniPlan();
    private boolean konecHry = false;
    private boolean goodEnding=false;

    public Hra() {
        this.platnePrikazy.vlozPrikaz(new PrikazNapoveda(this.platnePrikazy));
        this.platnePrikazy.vlozPrikaz(new PrikazJdi(this.herniPlan, this));
        this.platnePrikazy.vlozPrikaz(new PrikazSeber(this.herniPlan));
        this.platnePrikazy.vlozPrikaz(new PrikazPoloz(this.herniPlan));
        this.platnePrikazy.vlozPrikaz(new PrikazPouzij(this.herniPlan, this));
        this.platnePrikazy.vlozPrikaz(new PrikazObsahBatohu(this.herniPlan));
        this.platnePrikazy.vlozPrikaz(new PrikazMluv(this.herniPlan));
        this.platnePrikazy.vlozPrikaz(new PrikazDej(this.herniPlan));
        this.platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        this.platnePrikazy.vlozPrikaz(new PrikazIdkfa(this));
        this.platnePrikazy.vlozPrikaz(new PrikazVypis(this.herniPlan));
    }

    public String vratUvitani() {
        return "Vítej!\nV této hře je tvým úkolem připravit se na taneční soutěž, dojet pro svoji partnerku \n a společně na soutež vyrazit.\nNapiš 'nápověda', pokud si nevíš, jak hrát dál.\n\n" + this.herniPlan.getAktualniProstor().dlouhyPopis();
    }

    public String vratEpilog() {
        if(this.goodEnding) {
            return "Na soutěži jste vyhrali";
        }
        return "Ale ne, že ty ses zapomněl nalíčit, než jsi vyjel? \n" +
                "Na soutěž jste sice dorazili ale nevyhrali jste";
    }

    public boolean konecHry() {
        return this.konecHry;
    }

    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];

        for(int i = 0; i < parametry.length; ++i) {
            parametry[i] = slova[i + 1];
        }

        String textKVypsani = " .... ";
        if (this.platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = this.platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";
        }

        return textKVypsani;
    }

    public void setGoodEnding(boolean goodEnding) {
        this.goodEnding = goodEnding;
    }

    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    public HerniPlan getHerniPlan() {
        return this.herniPlan;
    }
}