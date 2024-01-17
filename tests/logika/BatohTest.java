package logika;

import junit.framework.TestCase;

public class BatohTest extends TestCase {

    public void testVlozDoBatohu() {
        Vec vec1 = new Vec("vec1", false);
        Batoh batoh = Batoh.getInstance(2);
        assertEquals(false,batoh.vlozDoBatohu(vec1));

        Vec vec2 = new Vec("vec2",true);
        assertEquals(true,batoh.vlozDoBatohu(vec2));
    }
}