package cz.vse.adventura.logika;

/**
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Hráč osloví postavu v aktuálním prostoru a postava mu odpoví.
 *  Promluva s princeznou Elenkou v její komnatě znamená výhru.
 *
 *  @author Ondřej Hájek
 */
class PrikazMluv implements IPrikaz {

    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    private Hra hra;

    /**
     *  Konstruktor.
     *
     *  @param plan herní plán
     *  @param hra  odkaz na hru, kterou je možné dokončit výhrou
     */
    public PrikazMluv(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "mluv".
     *
     *  @param parametry název postavy, se kterou chce hráč mluvit
     *  @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "S kým mám mluvit? Musíš zadat jméno postavy.";
        }
        String nazev = parametry[0];
        Postava postava = plan.getAktualniProstor().najdiPostavu(nazev);
        if (postava == null) {
            return "Nikdo takový tu není.";
        }
        String odpoved = postava.getMluvText();
        if (postava.getNazev().equals("Elenka")
                && plan.getAktualniProstor().getNazev().equals("komnata")) {
            hra.nastavVyhru();
        }
        return odpoved;
    }

    /**
     *  @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
