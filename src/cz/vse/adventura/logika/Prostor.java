package cz.vse.adventura.logika;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;        // obsahuje sousední místnosti
    private Map<String, Vec> veci;       // věci ležící v prostoru, klíč = název věci
    private Map<String, Postava> postavy;// postavy v prostoru, klíč = název postavy
    private boolean zamcen;              // true = do prostoru nelze vstoupit

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new LinkedHashMap<>();
        postavy = new LinkedHashMap<>();
        zamcen = false;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + popisVeci() + "\n"
                + popisPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje postavy v prostoru.
     *
     * @return Popis postav v prostoru
     */
    private String popisPostav() {
        String vracenyText = "postavy:";
        if (postavy.isEmpty()) {
            return vracenyText + " nikdo";
        }
        for (Postava p : postavy.values()) {
            vracenyText += " " + p.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje věci v prostoru, například:
     * "věci: jablko meč" nebo "věci: nic".
     *
     * @return Popis věcí v prostoru
     */
    private String popisVeci() {
        String vracenyText = "věci:";
        if (veci.isEmpty()) {
            return vracenyText + " nic";
        }
        for (Vec v : veci.values()) {
            vracenyText += " " + v.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Vloží věc do prostoru.
     *
     * @param vec věc, která se má v prostoru objevit
     */
    public void vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
    }

    /**
     * Vrátí věc z prostoru podle názvu (bez odebrání).
     *
     * @param nazev název hledané věci
     * @return nalezená věc, nebo null, pokud věc v prostoru není
     */
    public Vec najdiVec(String nazev) {
        return veci.get(nazev);
    }

    /**
     * Vyjme věc z prostoru podle názvu.
     *
     * @param nazev název věci
     * @return vyjmutá věc, nebo null, pokud věc v prostoru nebyla
     */
    public Vec vyberVec(String nazev) {
        return veci.remove(nazev);
    }

    /**
     * Vrací nemodifikovatelnou kolekci věcí, které leží v prostoru.
     *
     * @return kolekce věcí v prostoru
     */
    public Collection<Vec> getVeci() {
        return Collections.unmodifiableCollection(veci.values());
    }

    /**
     * Vloží postavu do prostoru.
     *
     * @param postava postava, která se má v prostoru objevit
     */
    public void vlozPostavu(Postava postava) {
        postavy.put(postava.getNazev(), postava);
    }

    /**
     * Vrátí postavu z prostoru podle názvu (bez odebrání).
     *
     * @param nazev název hledané postavy
     * @return nalezená postava, nebo null, pokud v prostoru není
     */
    public Postava najdiPostavu(String nazev) {
        return postavy.get(nazev);
    }

    /**
     * Vrací nemodifikovatelnou kolekci postav v prostoru.
     *
     * @return kolekce postav v prostoru
     */
    public Collection<Postava> getPostavy() {
        return Collections.unmodifiableCollection(postavy.values());
    }

    /**
     * Vrací true, pokud je prostor zamčený (nelze do něj vstoupit).
     *
     * @return true, pokud je prostor zamčený
     */
    public boolean jeZamcen() {
        return zamcen;
    }

    /**
     * Nastaví, zda je prostor zamčený.
     *
     * @param zamcen true = zamčeno, false = odemčeno
     */
    public void setZamcen(boolean zamcen) {
        this.zamcen = zamcen;
    }
}
