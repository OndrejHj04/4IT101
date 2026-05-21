package cz.vse.adventura.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Testovací třída PrikazPouzijTest ověřuje chování příkazu použij:
 * odemčení komnaty klíčem v hradu, snědení jablka
 * a chybové reakce na nesmyslné použití.
 *
 * @author Ondřej Hájek
 */
public class PrikazPouzijTest {
    private Hra hra;

    @BeforeEach
    public void setUp() {
        hra = new Hra();
    }

    /**
     * Klíč použitý v hradu odemkne komnatu. Klíč zůstává v batohu
     * (může se hodit pro vrácení se).
     */
    @Test
    public void testKlicOdemkneKomnatu() {
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi jeskyně");
        hra.zpracujPrikaz("seber klíč");
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi hrad");

        Prostor komnata = hra.getHerniPlan()
                             .getAktualniProstor()
                             .vratSousedniProstor("komnata");
        assertTrue(komnata.jeZamcen());

        hra.zpracujPrikaz("použij klíč");
        assertFalse(komnata.jeZamcen());
        assertNotNull(hra.getHerniPlan().getBatoh().najdiVec("klíč"));
    }

    /**
     * Použití klíče mimo hrad (kde komnata není sousední) nemá efekt
     * a komnata zůstává zamčená.
     */
    @Test
    public void testKlicMimoHrad() {
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi jeskyně");
        hra.zpracujPrikaz("seber klíč");

        String odpoved = hra.zpracujPrikaz("použij klíč");
        assertTrue(odpoved.toLowerCase().contains("nepomůže"));
    }

    /**
     * Jablko se po použití sní a zmizí z batohu.
     */
    @Test
    public void testSnedeniJablka() {
        hra.zpracujPrikaz("seber jablko");
        assertNotNull(hra.getHerniPlan().getBatoh().najdiVec("jablko"));

        hra.zpracujPrikaz("použij jablko");
        assertNull(hra.getHerniPlan().getBatoh().najdiVec("jablko"));
    }

    /**
     * Nelze použít věc, kterou hráč nemá v batohu.
     */
    @Test
    public void testPouzitiBezVeci() {
        String odpoved = hra.zpracujPrikaz("použij klíč");
        assertTrue(odpoved.toLowerCase().contains("nemáš"));
    }

    /**
     * Volání příkazu bez parametru oznámí, že hráč musí zadat jméno věci.
     */
    @Test
    public void testBezParametru() {
        String odpoved = hra.zpracujPrikaz("použij");
        assertEquals("Co mám použít? Zadej jméno věci kterou mám použít.", odpoved);
    }
}
