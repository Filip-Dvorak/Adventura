package logika;

import junit.framework.TestCase;

public class ProstorTest extends TestCase {

    public void testSetVychod() {
        Prostor prostor = new Prostor("prostor1","popis",0,0);
        prostor.setVychod(prostor);
        assertEquals("prostor1", prostor.vratSousedniProstor("prostor1").getNazev());}
}