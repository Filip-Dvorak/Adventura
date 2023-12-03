package logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 *  
 */
class PrikazNapoveda implements IPrikaz {
    private static final String NAZEV = "nápověda";
    private SeznamPrikazu platnePrikazy;

    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je připravit se na soutěž\ndojet pro svoji partnerku a společně vyrazit na soutěž.\n\nMůžeš zadat tyto příkazy:\n" + this.platnePrikazy.vratNazvyPrikazu();
    }

    public String getNazev() {
        return "nápověda";
    }
}
