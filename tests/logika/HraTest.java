package logika;

import junit.framework.TestCase;

public class HraTest extends TestCase {

    public void testPrubehHry() {
        Hra hra = new Hra();
        assertEquals("pokoj",hra.getHerniPlan().getAktualniProstor().getNazev());
        hra.zpracujPrikaz("seber klíče_od_auta");
        hra.zpracujPrikaz("jdi jídelna");
        assertEquals(false,hra.konecHry());
        hra.zpracujPrikaz("použij snídaně");
        hra.zpracujPrikaz("seber svačina");
        hra.zpracujPrikaz("jdi pokoj");
        hra.zpracujPrikaz("jdi koupelna");
        hra.zpracujPrikaz("použij líčení");
        hra.zpracujPrikaz("seber pinetky");
        hra.zpracujPrikaz("jdi pokoj");
        hra.zpracujPrikaz("jdi garáž");
        hra.zpracujPrikaz("použij klíče_od_auta");
        hra.zpracujPrikaz("jdi byt_partnerky");
        assertEquals("Čau máš pro mě ty věci co jsem chtěla?",hra.getHerniPlan().getAktualniProstor().getPostava().getMluv());
        hra.zpracujPrikaz("dej partnerka svačina,pinetky");
        hra.zpracujPrikaz("jdi soutěž");
        assertEquals(true,hra.konecHry());
    }
}