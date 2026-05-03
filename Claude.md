# Adventura — kontext pro Claude Code

## Co to je
Semestrální práce do předmětu 4IT101 Programování v Javě (VŠE).
Stavíme nad výchozím projektem cvičícího (klonován z Bitbucketu).
Konkrétní zadání hry: viz `ZADANI.md`.

## Pracovní pravidla

### Jazyk a styl
- Veškerá jména tříd, metod, proměnných a komentáře píšeme **česky**
  (bez diakritiky v identifikátorech, s diakritikou v komentářích a textech hry).
- JavaDoc česky, u každé veřejné třídy a metody.
- Zachováváme styl a strukturu výchozího projektu — package `cz.vse.adventura.logika`,
  rozhraní `IPrikaz`, `IHra`, atd.

### Postup
- Pracujeme po malých krocích. Vždy nejdřív navrhni, co změníš, a počkej na potvrzení.
- Nepíšeme God Class. Logiku patřící k věci / prostoru / batohu umisťujeme
  do odpovídajících tříd, ne do `HerniPlan`.
- Každý příkaz = vlastní třída implementující `IPrikaz`.
- Cílíme na **minimum funkční implementace** — žádná zbytečná abstrakce nebo features navíc.

### Co nedělat
- Negeneruj velké bloky kódu naráz. Preferuj malé snippety s vysvětlením.
- Neměň strukturu výchozího projektu (package, rozhraní), pokud o to výslovně neřeknu.
- Nepřidávej knihovny / závislosti bez konzultace.
- Nepiš testy, dokud o ně výslovně nepožádám.

## Klíčové třídy výchozího projektu
(uprav podle reality, jakmile projekt prozkoumáš)

- `Hra` (implementuje `IHra`) — hlavní třída logiky, drží `HerniPlan` a `SeznamPrikazu`,
  zpracovává příkazy.
- `HerniPlan` — drží prostory, aktuální prostor, batoh.
- `Prostor` / `Mistnost` — uzel mapy, drží východy a věci.
- `Vec` — věc v prostoru / batohu, má název a příznak `prenositelna`.
- `Batoh` — kolekce věcí, které u sebe hráč nese.
- `IPrikaz` — rozhraní pro příkazy (`getNazev()`, `provedPrikaz(String...)`).
- `SeznamPrikazu` — registr příkazů.
- `TextoveRozhrani` — textové UI, metody `hraj()` a `hrajZeSouboru(String)`.

## Domácí úkol týdne 11 (musí být hotový před semestrálkou)
1. `polož <věc>` — vyjme věc z batohu a vloží do aktuálního prostoru.
2. `vypiš <prostor|batoh>` — vypíše obsah daného prostoru / batohu.
3. Vlastní příkazy podle `ZADANI.md`: `prozkoumej`, `použij`, `mluv`.

## Workflow
1. Vždy začni přečtením `ZADANI.md` a kontrolou aktuálního stavu kódu.
2. Navrhni řešení **slovně**, počkej na potvrzení.
3. Teprve pak edituj soubory.
4. Po každé změně mi řekni, co se změnilo a proč.