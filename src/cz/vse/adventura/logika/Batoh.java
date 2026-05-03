package cz.vse.adventura.logika;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  Třída Batoh - kolekce věcí, které u sebe hráč nese.
 *  Batoh má pevnou kapacitu (počet věcí, které do něj lze najednou vložit).
 *
 *  @author  Ondřej Hájek
 */
public class Batoh {

    private static final int KAPACITA = 3;
    private Map<String, Vec> veci;

    /**
     *  Konstruktor - vytvoří prázdný batoh.
     */
    public Batoh() {
        veci = new LinkedHashMap<>();
    }

    /**
     *  Vloží věc do batohu, pokud batoh není plný.
     *
     *  @param vec věc, která se má vložit
     *  @return true, pokud se věc podařilo vložit; false, pokud je batoh plný
     */
    public boolean vlozVec(Vec vec) {
        if (jePlny()) {
            return false;
        }
        veci.put(vec.getNazev(), vec);
        return true;
    }

    /**
     *  Vyjme věc z batohu podle názvu.
     *
     *  @param nazev název věci
     *  @return vyjmutá věc, nebo null, pokud věc v batohu není
     */
    public Vec vyberVec(String nazev) {
        return veci.remove(nazev);
    }

    /**
     *  Vrátí věc z batohu podle názvu (bez odebrání).
     *
     *  @param nazev název věci
     *  @return nalezená věc, nebo null, pokud věc v batohu není
     */
    public Vec najdiVec(String nazev) {
        return veci.get(nazev);
    }

    /**
     *  Vrací true, pokud je batoh plný.
     *
     *  @return true, pokud je batoh plný
     */
    public boolean jePlny() {
        return veci.size() >= KAPACITA;
    }

    /**
     *  Vrací nemodifikovatelnou kolekci věcí v batohu.
     *
     *  @return kolekce věcí v batohu
     */
    public Collection<Vec> getVeci() {
        return Collections.unmodifiableCollection(veci.values());
    }
}
