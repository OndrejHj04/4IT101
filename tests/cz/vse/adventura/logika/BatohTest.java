package cz.vse.adventura.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Testovací třída BatohTest ověřuje chování třídy Batoh,
 * zejména omezenou kapacitu a vkládání/vyjímání věcí.
 *
 * @author Ondřej Hájek
 */
public class BatohTest {
    private Batoh batoh;
    private Vec jablko;
    private Vec mec;
    private Vec klic;
    private Vec lano;

    @BeforeEach
    public void setUp() {
        batoh = new Batoh();
        jablko = new Vec("jablko", true, "červené jablko");
        mec    = new Vec("meč",    true, "ostrý meč");
        klic   = new Vec("klíč",   true, "rezavý klíč");
        lano   = new Vec("lano",   true, "dlouhé lano");
    }

    /**
     * Nový batoh je prázdný a není plný.
     */
    @Test
    public void testNovyBatohJePrazdny() {
        assertFalse(batoh.jePlny());
        assertTrue(batoh.getVeci().isEmpty());
    }

    /**
     * Po vložení věci ji lze najít podle názvu.
     */
    @Test
    public void testVlozeniANalezeni() {
        assertTrue(batoh.vlozVec(jablko));
        assertSame(jablko, batoh.najdiVec("jablko"));
    }

    /**
     * Batoh má kapacitu 3 — čtvrtou věc už nepřijme,
     * a zůstane v něm původní trojice věcí.
     */
    @Test
    public void testKapacita() {
        assertTrue(batoh.vlozVec(jablko));
        assertTrue(batoh.vlozVec(mec));
        assertTrue(batoh.vlozVec(klic));
        assertTrue(batoh.jePlny());

        assertFalse(batoh.vlozVec(lano));
        assertNull(batoh.najdiVec("lano"));
        assertEquals(3, batoh.getVeci().size());
    }

    /**
     * Po vyjmutí věci v batohu už není a batoh přestane být plný.
     */
    @Test
    public void testVyjmuti() {
        batoh.vlozVec(jablko);
        batoh.vlozVec(mec);
        batoh.vlozVec(klic);

        Vec vyjmute = batoh.vyberVec("jablko");
        assertSame(jablko, vyjmute);
        assertNull(batoh.najdiVec("jablko"));
        assertFalse(batoh.jePlny());

        // do uvolněného slotu lze vložit jinou věc
        assertTrue(batoh.vlozVec(lano));
    }

    /**
     * Pokus o vyjmutí věci, která v batohu není, vrátí null.
     */
    @Test
    public void testVyjmutiNeexistujici() {
        assertNull(batoh.vyberVec("klíč"));
    }
}
