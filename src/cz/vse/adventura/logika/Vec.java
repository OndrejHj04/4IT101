package cz.vse.adventura.logika;

/**
 *  Třída Vec - reprezentuje věc, která může ležet v prostoru nebo být v batohu.
 *  Věc má jednoznačný název a příznak, zda je přenositelná
 *  (lze ji sebrat do batohu).
 *
 *  @author  Ondřej Hájek
 */
public class Vec {

    private String nazev;
    private boolean prenositelna;

    /**
     *  Konstruktor.
     *
     *  @param nazev        jednoznačný název věci bez mezer
     *  @param prenositelna true, pokud lze věc sebrat do batohu
     */
    public Vec(String nazev, boolean prenositelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
    }

    /**
     *  Vrací název věci.
     *
     *  @return název věci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     *  Vrací true, pokud je věc přenositelná (lze ji sebrat do batohu).
     *
     *  @return true, pokud je věc přenositelná
     */
    public boolean jePrenositelna() {
        return prenositelna;
    }
}
