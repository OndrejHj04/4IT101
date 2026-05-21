package cz.vse.adventura.logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Batoh batoh;

     /**
     *  Konstruktor, který vytváří jednotlivé prostory, rozmisťuje věci
     *  a inicializuje batoh hráče.
     */
    public HerniPlan() {
        batoh = new Batoh();
        zalozProstoryHry();
    }
    /**
     *  Vytváří jednotlivé prostory, propojuje je pomocí východů
     *  a rozmisťuje do nich věci.
     *  Jako výchozí aktuální prostor nastaví světnici.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor svetnice = new Prostor("světnice", "světnice Honzova rodného domu");
        Prostor les      = new Prostor("les",      "hustý les plný stromů a křovin");
        Prostor jeskyne  = new Prostor("jeskyně",  "temná jeskyně, kde spí drak");
        Prostor hrad     = new Prostor("hrad",     "vstupní hala starého hradu");
        Prostor komnata  = new Prostor("komnata",  "komnata, ve které čeká začarovaná princezna Elenka");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        svetnice.setVychod(les);
        les.setVychod(svetnice);
        les.setVychod(jeskyne);
        les.setVychod(hrad);
        jeskyne.setVychod(les);
        hrad.setVychod(les);
        hrad.setVychod(komnata);
        komnata.setVychod(hrad);

        // rozmístění věcí
        svetnice.vlozVec(new Vec("jablko", true,
                "Červené šťavnaté jablko, které si můžeš vzít na cestu."));
        les.vlozVec(new Vec("meč", true,
                "Starý rytířský meč, ostrý a překvapivě dobře padne do ruky."));
        jeskyne.vlozVec(new Vec("klíč", true,
                "Rezavý železný klíč, pravděpodobně od moc důležitých dveří"));
        hrad.vlozVec(new Vec("truhla", false,
                "Robustní dřevěná truhla, na zámku visí těžký řetěz. Nejde otevřít."));

        // postavy
        jeskyne.vlozPostavu(new Postava("drak",
                "Obrovský šupinatý drak, bylo by lepší ho neprobudit.",
                "Drak ve spánku zafuněl. Raději jdi pryč."));
        komnata.vlozPostavu(new Postava("Elenka",
                "Zakletá princezna Elenka už dlouhé roky čeká na svého zachránce.",
                "Princezna Elenka se rozzáří: \"Honzo, tys mě zachránil!\""));

        // komnata je na začátku zamčená
        komnata.setZamcen(true);

        aktualniProstor = svetnice;  // hra začíná ve světnici
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    /**
     *  Vrací odkaz na batoh hráče.
     *
     *  @return batoh hráče
     */
    public Batoh getBatoh() {
        return batoh;
    }

}
