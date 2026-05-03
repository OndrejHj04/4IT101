package cz.vse.adventura.logika;

/**
 *  Třída Postava - reprezentuje postavu, která stojí v některém prostoru
 *  hry (drak, princezna). Postava má jednoznačný název, popis (pro příkaz
 *  prozkoumej) a text, který hráči řekne při příkazu mluv.
 *
 *  @author Ondřej Hájek
 */
public class Postava {

    private String nazev;
    private String popis;
    private String mluvText;

    /**
     *  Konstruktor.
     *
     *  @param nazev    jednoznačný název postavy bez mezer
     *  @param popis    popis, který hráč uvidí při příkazu prozkoumej
     *  @param mluvText text, který postava řekne při příkazu mluv
     */
    public Postava(String nazev, String popis, String mluvText) {
        this.nazev = nazev;
        this.popis = popis;
        this.mluvText = mluvText;
    }

    /**
     *  @return název postavy
     */
    public String getNazev() {
        return nazev;
    }

    /**
     *  @return popis postavy
     */
    public String getPopis() {
        return popis;
    }

    /**
     *  @return text, který postava říká hráči
     */
    public String getMluvText() {
        return mluvText;
    }
}
