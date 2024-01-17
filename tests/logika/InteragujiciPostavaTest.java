package logika;

import junit.framework.TestCase;

import java.util.Set;

public class InteragujiciPostavaTest extends TestCase {

    public void testVymena() {
        InteragujiciPostava postava = new InteragujiciPostava("postava", "ne");
        Vec vec1 = new Vec("vec1", true);
        Vec vec2 = new Vec("vec2", true);
        Set<Vec> coChce = new java.util.HashSet<>();
        coChce.add(vec1);
        coChce.add(vec2);
        postava.nastavVymenu(coChce, "ano");
        assertEquals("ano", postava.Vymena("vec1 vec2 ", "vec2 vec1 "));
    }
}