package cz.vse.adventura.logika;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Sebere věc z aktuálního prostoru a vloží ji do batohu,
 *  pokud je věc přenositelná a batoh není plný.
 *
 *  @author  Ondřej Hájek
 *  @version pro semestrální práci 4IT101
 */
class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";
    private HerniPlan plan;

    /**
     *  Konstruktor.
     *
     *  @param plan herní plán, ze kterého se bude věc brát
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "seber".
     *
     *  @param parametry název věci, kterou chce hráč sebrat
     *  @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Musíš zadat jméno věci.";
        }
        String nazev = parametry[0];
        Prostor aktualni = plan.getAktualniProstor();
        Vec vec = aktualni.najdiVec(nazev);
        if (vec == null) {
            return "Taková věc tu není.";
        }
        if (!vec.jePrenositelna()) {
            return "Tohle se sebrat nedá.";
        }
        if (plan.getBatoh().jePlny()) {
            return "Batoh je plný, nejdřív něco polož.";
        }
        aktualni.vyberVec(nazev);
        plan.getBatoh().vlozVec(vec);
        return "Sebral jsi: " + nazev;
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
