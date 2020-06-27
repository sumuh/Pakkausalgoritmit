## Testausdokumentti

### Suorituskykytestaus

Huffman- ja Lzw-pakkauksista löytyy kummastakin Tester-luokka, jonka avulla algoritmeja voi testata. 
Aloitin suorituskyvyn testaamisen melko yksinkertaisella testillä, jossa tiedostoon kirjoitetaan ensin noin 1 kilotavun edestä satunnaisia
integerejä (välillä 0-19), sitten sama noin 10 kilotavun edestä ja vielä 100 kilotavun edestä. Seuraavaksi nämä tiivistetyt 
tiedostot purettiin. Tulokset ovat taulukoissa. Huom: purkamis-taulukossa tiedoston koko on alkuperäisen tiedoston koko eikä tiivistetyn
tiedoston koko.

#### Satunnaiset integerit

##### Tiivistäminen

Tiedoston koko | Huffman | LZW
---------------|---------|----
1 kt | 55.1971 ms | 35.7571 ms
10 kt | 518.0178 ms | 355.7193 ms
100 kt | 28554.4319 ms | 39681.2608 ms

##### Purkaminen

Tiedoston koko | Huffman | LZW
---------------|---------|----
1 kt | 27.3836 ms | 27.2028 ms
10 kt | 175.4755 ms | 12.5347 ms
100 kt | 15387.7518 ms | 65.5531 ms

Tämä testaustapa on tietysti aika huono, koska satunnaiset integerit eivät kuvaa kovin hyvin tyypillisiä tekstitiedostoja, joissa
toiset kirjaimet esiintyvät toisia useammin. Siksi testasin algoritmeja myös 40 kt ja 100 kt tekstitiedostoilla, jotka sisälsivät tekstiä Emily Brontën Humisevasta harjusta.
Tulokset ovat keskiarvoja kymmenestä mittauskerrasta.

#### "Oikea" teksti

##### Tiivistäminen

Tiedoston koko | Huffman | LZW
---------------|---------|----
40 kt | 5129.62203 ms | 117.76088 ms
100 kt | 45146.45394 ms | 197.90201 ms

##### Purkaminen

Tiedoston koko | Huffman | LZW
---------------|---------|----
40 kt | 2142.53678 ms | 131.48892 ms
100 kt | 10584.6464 ms | 234.61973 ms
