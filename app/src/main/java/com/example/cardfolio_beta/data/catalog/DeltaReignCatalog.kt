package com.example.cardfolio_beta.data.catalog

import com.example.cardfolio_beta.data.model.PokemonCard

/**
 * Delta Reign (DLR) English checklist.
 * Names and rarity are transcribed from the YesTCG set checklist.
 * Elemental types are intentionally left empty until individually verified.
 * Card image URLs use the numbered English Limitless image convention.
 */
val deltaReignCards: List<PokemonCard> = """
1|Heracross|Common|Pokemon
2|Surskit|Common|Pokemon
3|Masquerain|Common|Pokemon
4|Cacnea|Common|Pokemon
5|Cacturne|Rare|Pokemon
6|Combee|Common|Pokemon
7|Vespiquen|Common|Pokemon
8|Shaymin|Uncommon|Pokemon
9|Virizion|Uncommon|Pokemon
10|Wimpod|Common|Pokemon
11|Mega Golisopod ex|Double Rare|Pokemon
12|Sprigatito|Common|Pokemon
13|Floragato|Uncommon|Pokemon
14|Meowscarada ex|Double Rare|Pokemon
15|Smoliv|Common|Pokemon
16|Dolliv|Common|Pokemon
17|Arboliva|Uncommon|Pokemon
18|Growlithe|Common|Pokemon
19|Arcanine|Rare|Pokemon
20|Magmar|Uncommon|Pokemon
21|Magmortar|Rare|Pokemon
22|Torkoal|Common|Pokemon
23|Heat Rotom ex|Double Rare|Pokemon
24|Heatmor|Common|Pokemon
25|Mantine|Common|Pokemon
26|Kyogre|Rare|Pokemon
27|Frillish|Common|Pokemon
28|Jellicent|Uncommon|Pokemon
29|Wishiwashi ex|Double Rare|Pokemon
30|Electabuzz|Common|Pokemon
31|Electivire|Uncommon|Pokemon
32|Jolteon|Uncommon|Pokemon
33|Raikou ex|Double Rare|Pokemon
34|Rotom|Common|Pokemon
35|Thundurus|Uncommon|Pokemon
36|Pincurchin|Common|Pokemon
37|Pawmi|Common|Pokemon
38|Pawmo|Common|Pokemon
39|Pawmot|Uncommon|Pokemon
40|Wattrel|Common|Pokemon
41|Kilowattrel|Uncommon|Pokemon
42|Miraidon|Uncommon|Pokemon
43|Drowzee|Common|Pokemon
44|Hypno|Common|Pokemon
45|Giratina|Rare|Pokemon
46|Golett|Common|Pokemon
47|Mega Golurk ex|Double Rare|Pokemon
48|Enamorus|Uncommon|Pokemon
49|Sandshrew|Common|Pokemon
50|Sandslash|Common|Pokemon
51|Onix|Common|Pokemon
52|Steelix|Rare|Pokemon
53|Groudon|Rare|Pokemon
54|Landorus|Uncommon|Pokemon
55|Nidoran F|Common|Pokemon
56|Nidorina|Common|Pokemon
57|Nidoqueen|Rare|Pokemon
58|Spinarak|Common|Pokemon
59|Ariados|Common|Pokemon
60|Sableye|Common|Pokemon
61|Carvanha|Common|Pokemon
62|Sharpedo|Common|Pokemon
63|Darkrai|Uncommon|Pokemon
64|Purrloin|Common|Pokemon
65|Liepard|Common|Pokemon
66|Zorua|Common|Pokemon
67|Zoroark ex|Double Rare|Pokemon
68|Inkay|Common|Pokemon
69|Mega Malamar ex|Double Rare|Pokemon
70|Morpeko|Uncommon|Pokemon
71|Zarude|Uncommon|Pokemon
72|Druddigon|Uncommon|Pokemon
73|Jangmo-o|Common|Pokemon
74|Hakamo-o|Common|Pokemon
75|Kommo-o|Rare|Pokemon
76|Cyclizar|Common|Pokemon
77|Tauros|Common|Pokemon
78|Eevee|Common|Pokemon
79|Delibird|Uncommon|Pokemon
80|Azurill|Common|Pokemon
81|Swablu|Common|Pokemon
82|Altaria|Rare|Pokemon
83|Kecleon|Common|Pokemon
84|Mega Rayquaza ex|Double Rare|Pokemon
85|Tornadus|Uncommon|Pokemon
86|Fletchling|Common|Pokemon
87|Fletchinder|Common|Pokemon
88|Talonflame ex|Double Rare|Pokemon
89|Aarune|Uncommon|Trainer
90|Adventure Lantern|Uncommon|Trainer
91|Custom Vest|Uncommon|Trainer
92|Emcee's Hype|Common|Trainer
93|Grisham|Uncommon|Trainer
94|Legendary Pyroduct (Left)|Uncommon|Trainer
95|Legendary Pyroduct (Right)|Uncommon|Trainer
96|Legendary Summit (Left)|Uncommon|Trainer
97|Legendary Summit (Right)|Uncommon|Trainer
98|Legendary Trench (Left)|Uncommon|Trainer
99|Legendary Trench (Right)|Uncommon|Trainer
100|Mega Rayquaza Cap|Uncommon|Trainer
101|Tasty Rice Ball|Common|Trainer
102|Tate & Liza's Training|Uncommon|Trainer
103|Zinnia's Trust|Uncommon|Trainer
104|Masquerain|Illustration Rare|Pokemon
105|Sprigatito|Illustration Rare|Pokemon
106|Growlithe|Illustration Rare|Pokemon
107|Magmortar|Illustration Rare|Pokemon
108|Electivire|Illustration Rare|Pokemon
109|Pincurchin|Illustration Rare|Pokemon
110|Enamorus|Illustration Rare|Pokemon
111|Groudon|Illustration Rare|Pokemon
112|Zorua|Illustration Rare|Pokemon
113|Inkay|Illustration Rare|Pokemon
114|Eevee|Illustration Rare|Pokemon
115|Azurill|Illustration Rare|Pokemon
116|Altaria|Illustration Rare|Pokemon
117|Kecleon|Illustration Rare|Pokemon
118|Mega Golisopod ex|Ultra Rare|Pokemon
119|Heat Rotom ex|Ultra Rare|Pokemon
120|Wishiwashi ex|Ultra Rare|Pokemon
121|Raikou ex|Ultra Rare|Pokemon
122|Mega Golurk ex|Ultra Rare|Pokemon
123|Mega Malamar ex|Ultra Rare|Pokemon
124|Talonflame ex|Ultra Rare|Pokemon
125|Aarune|Ultra Rare|Trainer
126|Adventure Lantern|Ultra Rare|Trainer
127|Custom Vest|Ultra Rare|Trainer
128|Emcee's Hype|Ultra Rare|Trainer
129|Pokemon Catcher|Ultra Rare|Trainer
130|Tate & Liza's Training|Ultra Rare|Trainer
131|Zinnia's Trust|Ultra Rare|Trainer
132|Bubbly Water Energy|Ultra Rare|Energy
133|Growing Grass Energy|Ultra Rare|Energy
134|Nitro Fire Energy|Ultra Rare|Energy
135|Mega Golisopod ex|Special Illustration Rare|Pokemon
136|Raikou ex|Special Illustration Rare|Pokemon
137|Mega Golurk ex|Special Illustration Rare|Pokemon
138|Mega Rayquaza ex|Special Illustration Rare|Pokemon
139|Aarune|Special Illustration Rare|Trainer
140|Zinnia's Trust|Special Illustration Rare|Trainer
141|Mega Rayquaza ex|Mega Hyper Rare|Pokemon
""".trimIndent().lines().map { line ->
    val fields = line.split("|")
    val number = fields[0].toInt()
    val name = fields[1]
    val rarity = fields[2]
    val category = fields[3]
    val imageNumber = number.toString().padStart(3, '0')
    PokemonCard(
        id = "DLR-${number.toString().padStart(3, '0')}",
        name = name,
        expansionCode = "DLR",
        expansionName = "Delta Reign",
        cardNumber = number,
        category = category,
        types = emptyList(),
        rarity = rarity,
        imageUrl = "https://limitlesstcg.nyc3.cdn.digitaloceanspaces.com/tpci/DLR/DLR_${imageNumber}_R_EN_SM.png"
    )
}
