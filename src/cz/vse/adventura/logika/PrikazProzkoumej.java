package cz.vse.adventura.logika;

/**
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *  Vrátí popis věci nebo postavy v aktuálním prostoru, případně věci
 *  v batohu hráče.
 *
 *  @author Ondřej Hájek
 */
class PrikazProzkoumej implements IPrikaz {

    private static final String NAZEV = "prozkoumej";
    private HerniPlan plan;

    /**
     *  Konstruktor.
     *
     *  @param plan herní plán
     */
    public PrikazProzkoumej(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "prozkoumej".
     *
     *  @param parametry název věci nebo postavy
     *  @return popis nalezeného objektu, nebo chybové hlášení
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám prozkoumat? Musíš zadat jméno věci nebo postavy.";
        }
        String nazev = parametry[0];
        Prostor aktualni = plan.getAktualniProstor();

        Postava postava = aktualni.najdiPostavu(nazev);
        if (postava != null) {
            return postava.getPopis();
        }
        Vec vec = aktualni.najdiVec(nazev);
        if (vec == null) {
            vec = plan.getBatoh().najdiVec(nazev);
        }
        if (vec != null) {
            return vec.getPopis();
        }
        return "Taková věc není ani v prostoru ani v batohu";
    }

    /**
     *  @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
