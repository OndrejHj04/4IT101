package cz.vse.adventura.logika;

/**
 *  Třída PrikazVypis implementuje pro hru příkaz vypiš.
 *  Podle parametru vypíše obsah aktuálního prostoru ("prostor")
 *  nebo obsah batohu ("batoh").
 *
 *  @author  Ondřej Hájek
 *  @version pro semestrální práci 4IT101
 */
class PrikazVypis implements IPrikaz {

    private static final String NAZEV = "vypiš";
    private HerniPlan plan;

    /**
     *  Konstruktor.
     *
     *  @param plan herní plán, ze kterého se bude číst obsah
     */
    public PrikazVypis(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "vypiš".
     *
     *  @param parametry "prostor" nebo "batoh"
     *  @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám vypsat? Použij 'vypiš prostor' nebo 'vypiš batoh'.";
        }
        String co = parametry[0];
        switch (co) {
            case "prostor":
                return vypisProstor();
            case "batoh":
                return vypisBatoh();
            default:
                return "Nevím, co je '" + co + "'. Použij 'prostor' nebo 'batoh'.";
        }
    }

    private String vypisProstor() {
        Prostor p = plan.getAktualniProstor();
        StringBuilder sb = new StringBuilder("V prostoru " + p.getNazev() + " je:");
        if (p.getVeci().isEmpty()) {
            sb.append(" nic");
        } else {
            for (Vec v : p.getVeci()) {
                sb.append(" ").append(v.getNazev());
            }
        }
        return sb.toString();
    }

    private String vypisBatoh() {
        Batoh b = plan.getBatoh();
        StringBuilder sb = new StringBuilder("V batohu máš:");
        if (b.getVeci().isEmpty()) {
            sb.append(" nic");
        } else {
            for (Vec v : b.getVeci()) {
                sb.append(" ").append(v.getNazev());
            }
        }
        return sb.toString();
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
