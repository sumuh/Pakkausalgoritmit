## Toteutus

### Yleisrakenne

Ohjelmassa on erikseen omat pakkauksensa Huffman-algoritmille ja LZW-algoritmille. Algoritmien hyödyntämille tietorakenteille on oma pakkauksensa.

#### Huffman

Huffman-algoritmissa syötteenä saatu tiedosto luetaan tavuina. Tavujen perusteella muodostetaan nodeja, joilla on arvona yksi tavu. 
Näistä nodeista rakennetaan Huffman-puu eli binääripuu, jonka lehtiä ovat tavuja sisältävät nodet. Puuta kuljetaan juuresta alaspäin niin, että noden vasemmanpuoleisen lapsen valitseminen vastaa bittiä 0 ja oikeanpuoleisen bittiä 1. Kulkemalla puuta saadaan näin jokaiselle 
lehdelle uniikki koodi. Koodeista ja lehtien tavuarvoista tehdään Huffman-taulukko, jonka perusteella alkuperäistä syötettä luetaan ja tavuarvot korvataan koodeilla. Algoritmi perustuu siihen, että koodit ovat alkuperäisiä tavuja lyhyempiä ja data mahtuu siis tiiviimpään 
tilaan.

Purkamisohjelma muodostaa tiedoston alussa kuljetetun tiedon mukaan saman Huffman-puun ja taulukon kuin tiivistämisohjelma ja kääntää 
tiivistetyn datan alkuperäiseksi dataksi niiden perusteella.

Kuvassa esimerkki Huffman-puusta, jossa esim. tavun 2 koodi olisi 01.

![Hufman-puu](https://github.com/sumuh/Pakkausalgoritmit/blob/master/Dokumentaatio/huffmanpuu.png)

#### LZW

Lempel-Ziv-Welch-algoritmi perustuu tiedostossa toistuvien merkkijonojen hyödyntämiseen. Tiivistämisohjelma lukee tiedostoa ja rakentaa 
siinä esiintyvien merkkijonojen perusteella taulukon, jossa kutakin merkkijonoa vastaa 12-bittinen koodi. Näin esimerkiksi merkkijono, 
joka alun perin veisi 40 bittiä tilaa, saadaan tiivistettyä 12 bitin koodiin. Huffman-algoritmiin verrattuna LZW on siinä mielessä kätevämpi, ettei purkamisohjelmalle tarvitse välittää muuta tietoa kuin tiivistetty data, sillä ohjelma päättelee alkuperäiset merkkijonot koodien perusteella. 

