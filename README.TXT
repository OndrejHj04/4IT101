# Zadání adventury — Honza a začarovaná princezna

## Výchozí situace
Hráč je Honza, který se vydává z domova zachránit princeznu zakletou
v hradní komnatě zlým drakem. Začíná ve své světnici, kde má batoh
a jablko na cestu.

## Cíl hry
Dostat se do komnaty princezny a osvobodit ji.

## Postup jak zvítězit
V lese hráč najde meč. V jeskyni získá klíč od hradní komnaty
(drak tam spí). S mečem a klíčem se vydá do hradu a odtud do komnaty
princezny. Když v komnatě s princeznou promluví, vyhrává.

## Prostory (5)
- **světnice** — startovní prostor, východ: les
- **les** — východy: světnice, jeskyně, hrad
- **jeskyně** — leží tu klíč, spí drak. východ: les
- **hrad** — vstupní hala, stojí tu truhla. východy: les, komnata
- **komnata** — finální prostor, čeká princezna. východ: hrad
  - Vstup do komnaty vyžaduje klíč i meč v batohu.

## Věci
| Věc     | Kde leží  | Sebratelná |
|---------|-----------|------------|
| jablko  | světnice  | ano        |
| meč     | les       | ano        |
| klíč    | jeskyně   | ano        |
| truhla  | hrad      | ne         |

## Postavy
- **drak** — v jeskyni, spí, statická kulisa
- **princezna** — v komnatě, cíl hry

## Příkazy
Standardní (z výchozího projektu): `jdi`, `konec`, `nápověda`
Z domácího úkolu týdne 11: `seber`, `polož`, `vypiš` (s parametrem `prostor` / `batoh`)
Vlastní: `prozkoumej`, `použij`, `mluv`

## Konec hry
- `konec` → ukončení
- vstup do komnaty s mečem i klíčem + `mluv` s princeznou → výhra