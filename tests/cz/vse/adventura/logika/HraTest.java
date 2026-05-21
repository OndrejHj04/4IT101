package cz.vse.adventura.logika;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Testovací třída HraTest slouží ke komplexnímu otestování třídy Hra.
 *
 * Pokrývá průchod hry z výchozí světnice až k výhře v komnatě princezny,
 * zamykání a odemykání komnaty pomocí klíče a omezení vstupu bez meče.
 *
 * @author Ondřej Hájek
 */
public class HraTest {
    private Hra hra;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Hráč hned po startu hry stojí ve světnici a hra neskončila.
     */
    @Test
    public void testStartHry() {
        assertEquals("světnice", hra.getHerniPlan().getAktualniProstor().getNazev());
        assertFalse(hra.konecHry());
    }

    /**
     * Po zadání příkazu "konec" hra skončí, ale není označena jako výhra
     * (epilog neobsahuje vítěznou hlášku).
     */
    @Test
    public void testKonecPrikazem() {
        hra.zpracujPrikaz("konec");
        assertTrue(hra.konecHry());
        assertFalse(hra.vratEpilog().contains("zachránil"));
    }

    /**
     * Bez meče se hráč do komnaty princezny nedostane,
     * a to ani po odemčení dveří klíčem.
     */
    @Test
    public void testKomnataVyzadujeMec() {
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi jeskyně");
        hra.zpracujPrikaz("seber klíč");
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi hrad");
        hra.zpracujPrikaz("použij klíč");

        // klíč už komnatu odemkl, ale hráč nemá meč v batohu
        hra.zpracujPrikaz("jdi komnata");
        assertEquals("hrad", hra.getHerniPlan().getAktualniProstor().getNazev());
    }

    /**
     * Plný průchod hrou až k výhře: hráč sebere meč i klíč,
     * odemkne komnatu, vejde dovnitř s mečem v batohu
     * a promluvou s princeznou Elenkou vyhraje.
     */
    @Test
    public void testVitezstvi() {
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("seber meč");
        hra.zpracujPrikaz("jdi jeskyně");
        hra.zpracujPrikaz("seber klíč");
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi hrad");
        hra.zpracujPrikaz("použij klíč");
        hra.zpracujPrikaz("jdi komnata");
        assertEquals("komnata", hra.getHerniPlan().getAktualniProstor().getNazev());

        assertFalse(hra.konecHry());
        hra.zpracujPrikaz("mluv Elenka");
        assertTrue(hra.konecHry());
        assertTrue(hra.vratEpilog().contains("zachránil"));
    }

    /**
     * Truhla v hradu je nepřenositelná, sebrat ji nelze
     * a po pokusu o sebrání zůstává v prostoru.
     */
    @Test
    public void testTruhlaNelzeSebrat() {
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi hrad");
        String odpoved = hra.zpracujPrikaz("seber truhla");
        assertTrue(odpoved.toLowerCase().contains("nedá"));
        assertNotNull(hra.getHerniPlan().getAktualniProstor().najdiVec("truhla"));
        assertNull(hra.getHerniPlan().getBatoh().najdiVec("truhla"));
    }
}
