# Adventura — Honza a začarovaná princezna Elenka

Textová adventura odevzdaná jako semestrální práce předmětu **4IT101**
(Programování v jazyce Java) na FIS VŠE.

- **Autor:** Ondřej Hájek
- **Verze:** 1.0 (LS 2025/2026)
- **Java:** 11 a vyšší (testováno na JDK 21)

## Příběh

Hráč se vžije do role Honzy, který se vydává z domova zachránit
princeznu Elenku, kterou zlý drak zaklel a uvěznil v hradní komnatě.
Začíná ve své světnici, kde má jablko na cestu. V lese najde meč,
v dračí jeskyni získá klíč od hradní komnaty.
S oběma věcmi se vydá do hradu, klíčem otevře komnatu a promluvou
s princeznou ji vysvobodí.

## Cíl hry

Dostat se do komnaty princezny Elenky a osvobodit ji.

## Postup k výhře

1. V lese sebrat **meč**.
2. V jeskyni sebrat **klíč** (drak naštěstí spí).
3. V hradu příkazem `použij klíč` odemknout komnatu.
4. S mečem v batohu vejít do komnaty a `mluv Elenka`.

## Spuštění

### Spustitelný JAR

```bash
java -jar adventura.jar
```

JAR je součástí ZIP archivu odevzdávaného do InSIS.

### Ze zdrojových kódů

```bash
javac -d out -sourcepath src src/cz/vse/adventura/Start.java
java -cp out cz.vse.adventura.Start
```

## Příkazy

| Příkaz | Popis |
|---|---|
| `jdi <prostor>` | Přesun do sousedního prostoru. |
| `seber <věc>` | Vyjme přenositelnou věc z prostoru a vloží ji do batohu (kapacita 3). |
| `polož <věc>` | Vyjme věc z batohu a položí ji do aktuálního prostoru. |
| `vypiš <prostor\|batoh>` | Vypíše obsah aktuálního prostoru, nebo batohu. |
| `prozkoumej <věc\|postava>` | Popíše věc nebo postavu v prostoru (případně věc v batohu). |
| `použij <věc>` | Použije věc z batohu — `klíč` v hradu odemkne komnatu, `jablko` se sní. |
| `mluv <postava>` | Promluví s postavou v aktuálním prostoru. |
| `nápověda` | Zobrazí seznam příkazů. |
| `konec` | Ukončí hru bez výhry. |

## Prostory hry

```
světnice ── les ── jeskyně
            │
           hrad ── komnata (zamčená)
```

| Prostor | Co tam je |
|---|---|
| **světnice** | startovní prostor; leží zde **jablko** |
| **les** | leží zde **meč** |
| **jeskyně** | leží zde **klíč**, spí **drak** |
| **hrad** | vstupní hala; stojí zde nepřenositelná **truhla** |
| **komnata** | zamčená; po odemčení čeká princezna **Elenka** |

## Struktura projektu

```
src/cz/vse/adventura/
├── Start.java                — vstupní bod aplikace (main)
├── logika/                   — herní logika
│   ├── Hra, IHra             — hlavní třída a její rozhraní
│   ├── HerniPlan             — mapa prostorů a stav hry
│   ├── Prostor, Vec, Postava — modely
│   ├── Batoh                 — kolekce s omezenou kapacitou
│   ├── SeznamPrikazu         — registr platných příkazů
│   ├── IPrikaz               — rozhraní příkazu (návrhový vzor Command)
│   └── PrikazXxx             — implementace jednotlivých příkazů
└── uiText/TextoveRozhrani    — textové uživatelské rozhraní

tests/cz/vse/adventura/logika/
├── HraTest                   — průchod hrou až k výhře
├── BatohTest                 — kapacita a operace s batohem
├── PrikazPouzijTest          — odemčení komnaty, snědení jablka
├── ProstorTest               — sousední prostory
└── SeznamPrikazuTest         — registr příkazů
```