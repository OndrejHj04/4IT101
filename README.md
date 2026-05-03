# Zadání adventury — Honza a začarovaná princezna Elenka

## Výchozí situace
Hráč je Honza, který se vydává z domova zachránit princeznu Elenku,
zakletou zlým drakem v hradní komnatě. Začíná ve své světnici,
kde má batoh a jablko na cestu.

## Cíl hry
Dostat se do komnaty princezny Elenky a osvobodit ji.

## Postup jak zvítězit
V lese hráč najde meč. V jeskyni získá klíč od hradní komnaty
(drak tam jen spí, je to kulisa). S klíčem a mečem se vydá do hradu,
příkazem `použij klíč` odemkne komnatu a s mečem v batohu do ní vejde.
Když pak s princeznou Elenkou promluví, vyhrává.

## Prostory (5)
- **světnice** — startovní prostor, východ: les
- **les** — východy: světnice, jeskyně, hrad
- **jeskyně** — leží tu klíč, spí drak. východ: les
- **hrad** — vstupní hala, stojí tu truhla. východy: les, komnata
- **komnata** — finální prostor, čeká princezna Elenka. východ: hrad
  - Komnata je na začátku zamčená; odemkne se příkazem `použij klíč` v hradu.
  - I po odemčení vyžaduje vstup meč v batohu.

## Věci
| Věc     | Kde leží  | Sebratelná |
|---------|-----------|------------|
| jablko  | světnice  | ano        |
| meč     | les       | ano        |
| klíč    | jeskyně   | ano        |
| truhla  | hrad      | ne         |

## Postavy
- **drak** — v jeskyni, spí, statická kulisa
- **princezna Elenka** — v komnatě, cíl hry

## Příkazy
Standardní (z výchozího projektu): `jdi`, `konec`, `nápověda`

- `seber <věc>` — vyjme přenositelnou věc z aktuálního prostoru a vloží ji do batohu (kapacita 3).
- `polož <věc>` — vyjme věc z batohu a vloží ji do aktuálního prostoru.
- `vypiš <prostor|batoh>` — vypíše obsah aktuálního prostoru, nebo batohu.

Vlastní:
- `prozkoumej <věc|postava>` — vrátí popis věci nebo postavy v prostoru,
  případně věci v batohu.
- `použij <věc>` — věc musí být v batohu; efekt závisí na věci a prostoru:
  - `použij klíč` v hradu odemkne sousední komnatu (klíč zůstane v batohu),
  - `použij jablko` jablko sní (zmizí z batohu),
  - jiné kombinace nemají efekt.
- `mluv <postava>` — postava musí být v aktuálním prostoru; vypíše její
  repliku. `mluv Elenka` v komnatě znamená výhru.

## Konec hry
- `konec` → ukončení bez výhry
- `použij klíč` v hradu + vstup do komnaty s mečem v batohu + `mluv Elenka` → výhra