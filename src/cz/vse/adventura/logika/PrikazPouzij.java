package cz.vse.adventura.logika;

/**
 *  Třída PrikazPouzij implementuje pro hru příkaz použij.
 *  Hráč může použít věc, kterou má v batohu. Efekt závisí na věci
 *  a na aktuálním prostoru:
 *  - klíč v hradu odemkne sousední komnatu princezny,
 *  - jablko lze sníst kdekoli (zmizí z batohu),
 *  - ostatní kombinace nemají efekt.
 *
 *  @author Ondřej Hájek
 */
class PrikazPouzij implements IPrikaz {

    private static final String NAZEV = "použij";
    private HerniPlan plan;

    /**
     *  Konstruktor.
     *
     *  @param plan herní plán
     */
    public PrikazPouzij(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "použij".
     *
     *  @param parametry název věci, kterou chce hráč použít
     *  @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám použít? Zadej jméno věci kterou mám použít.";
        }
        String nazev = parametry[0];
        Vec vec = plan.getBatoh().najdiVec(nazev);
        if (vec == null) {
            return "Tuto věc v batohu nemáš.";
        }
        Prostor aktualni = plan.getAktualniProstor();

        switch (nazev) {
            case "klíč":
                Prostor komnata = aktualni.vratSousedniProstor("komnata");
                if (komnata == null) {
                    return "Tady ti klíč nepomůže";
                }
                if (!komnata.jeZamcen()) {
                    return "Komnata už je odemčená.";
                }
                komnata.setZamcen(false);
                return "Vsuneš klíč do zámku, dveře komnaty se s vrznutím otevírají.";

            case "jablko":
                plan.getBatoh().vyberVec("jablko");
                return "Snědl jsi jablko.";

            default:
                return "Tato věc se takhle použít nedá.";
        }
    }

    /**
     *  @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
