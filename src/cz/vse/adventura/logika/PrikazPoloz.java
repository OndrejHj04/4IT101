package cz.vse.adventura.logika;

/**
 *  Třída PrikazPoloz implementuje pro hru příkaz polož.
 *  Vyjme věc z batohu a vloží ji do aktuálního prostoru.
 *
 *  @author  Ondřej Hájek
 *  @version pro semestrální práci 4IT101
 */
class PrikazPoloz implements IPrikaz {

    private static final String NAZEV = "polož";
    private HerniPlan plan;

    /**
     *  Konstruktor.
     *
     *  @param plan herní plán, do jehož aktuálního prostoru se věc položí
     */
    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "polož".
     *
     *  @param parametry název věci, kterou chce hráč položit
     *  @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám položit? Musíš zadat jméno věci.";
        }
        String nazev = parametry[0];
        Vec vec = plan.getBatoh().vyberVec(nazev);
        if (vec == null) {
            return "Tuhle věc v batohu nemáš.";
        }
        plan.getAktualniProstor().vlozVec(vec);
        return "Položil jsi: " + nazev;
    }

    /**
     *  Vrací název příkazu.
     *
     *  @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
