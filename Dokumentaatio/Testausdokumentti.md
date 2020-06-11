## Testausdokumentti

### Suorituskykytestaus

Huffman- ja Lzw-pakkauksista löytyy kummastakin Tester-luokka, jonka avulla algoritmeja voi testata. 
Aloitin suorituskyvyn testaamisen melko yksinkertaisella testillä, jossa tiedostoon kirjoitetaan ensin noin 1 kilotavun edestä satunnaisia
integerejä (välillä 0-19), sitten sama noin 10 kilotavun edestä ja vielä 100 kilotavun edestä. Seuraavaksi nämä tiivistetyt 
tiedostot purettiin. Tulokset ovat taulukoissa. Huom: purkamis-taulukossa tiedoston koko on alkuperäisen tiedoston koko eikä tiivistetyn
tiedoston koko.

#### Tiivistäminen

Tiedoston koko | Huffman | LZW
---------------|---------|----
1 kt | 55.1971 ms | 35.7571 ms
10 kt | 518.0178 ms | 355.7193 ms
100 kt | 28554.4319 ms | 39681.2608 ms

#### Purkaminen

Tiedoston koko | Huffman | LZW
---------------|---------|----
1 kt | 27.3836 ms | 27.2028 ms
10 kt | 175.4755 ms | 12.5347 ms
100 kt | 15387.7518 ms | 65.5531 ms

Tämä testaustapa on tietysti aika huono, koska satunnaiset integerit eivät kuvaa kovin hyvin tyypillisiä tekstitiedostoja, joissa
toiset kirjaimet esiintyvät toisia useammin. Tarkoituksena on testata algoritmeja myös "normaaleilla" tekstitiedostoilla. Tällä testaustavalla
myös LZW:llä tiivistetystä tiedostosta tuli kooltaan isompi kuin alkuperäisestä tiedostosta, mikä ehkä johtuu juuri tästä satunnaisuudesta 
ja siitä, että samat merkkijonot tuskin toistuvat kovin usein. Kuten taulukosta näkyy, LZW:llä purkamiseen menneet ajat näyttävät oudoilta, 
mikä varmaan johtuu tästä samasta syystä.
