## Viikkoraportti 3

Työhön käytetty aika: 18 h

Tällä viikolla jatkoin Huffman-koodin toteuttamista. Jouduin muuttamaan ohjelman rakennetta ja yhdistämään Node- ja Leaf- luokat yhdeksi luokaksi, 
koska erilliset luokat aiheuttivat ongelmia. Aloitin Decompression-luokan tekemistä ja aloin toteuttamaan tietorakenteita.

Tällä hetkellä ohjelman Huffman-osuus on vielä vähän kesken, puun uudelleenrakentava metodi on vielä vaiheessa mutta 
tiedon tiivistäminen, tiedostoon kirjoittaminen ja sieltä lukeminen toimii. Compression-luokassa on käytössä oma OrderedList-tietorakenne. 
Myös HashMapia muistuttava Table-luokka on olemassa, mutta se ei ole vielä käytössä, koska en ole ehtinyt toteuttaa sille iteraattoria.

Tällä viikolla opin paljon uutta tietotyyppien (byte, integer yms) käsittelystä ja miten niitä voi muuntaa keskenään. Varsinkin sen 
selvittäminen, miten ja missä muodossa tietoa kannattaa kirjoittaa tiedostoon, oli työn takana. Opin myös tietorakenteiden toteuttamisen
perusidean ja sen mitä rakenteilta vaaditaan.

En keksi suoraan mitään kysymyksiä, monet asiat ovat tuottaneet vaikeuksia mutta ne ovat selvinneet ajan kanssa. Ehkä lähinnä mietityttää onko
testikattavuus tarpeeksi ja onko koodi tarpeeksi hyvin kommentoitua, selkeää jne.

Ensi viikolla koitan saada Huffman-osuuden tehtyä loppuun ja aloittaa Lempel-Ziv-Welch-koodia. Jatkan tietorakenteiden tekemistä.
