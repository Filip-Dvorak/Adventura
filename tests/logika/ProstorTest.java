package logika;

import junit.framework.TestCase;

public class ProstorTest extends TestCase {

    public void testSetVychod() {
        Prostor prostor = new Prostor("prostor1","popis",0,0);
        prostor.setVychod(prostor);
        assertEquals("prostor1", prostor.vratSousedniProstor("prostor1").getNazev());}

    public void testVlozVec() {
        Vec vec = new Vec("vec1",true);
        Prostor prostor = new Prostor("prostor1","popis",0,0);
        prostor.vlozVec(vec);
        assertEquals(true, prostor.obsahujeVec("vec1"));
    }

    public void testAddPostava() {
        Postava postava = new Postava("postava1","Ahoj");
        Prostor prostor = new Prostor("prostor1","popis",0,0);
        prostor.addPostava(postava);
        assertEquals("Ahoj", prostor.getPostava().getMluv());}
}