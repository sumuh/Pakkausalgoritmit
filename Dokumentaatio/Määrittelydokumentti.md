## Määrittelydokumentti

### Toteutettavat algoritmit ja tietorakenteet

Tarkoituksena on toteuttaa tiedostoja tiivistävät pakkausalgoritmit sekä Huffmanin että Lempel-Ziv-Welchin algoritmilla. 
Toteutettavia tietorakenteita ovat ainakin Huffmanin algoritmin käyttämä binääripuu ja LZW:n taulukko.

### Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet

Huffmanin ja Lempel-Zivin algoritmit näyttävät olevan tiedon tiivistämisessä yksiä suosituimpia ja käytetyimpiä menetelmiä, ja koska en tiedä aiheesta ennestään mitään, ne vaikuttavat hyviltä lähtökohdilta siihen tutustumiseen.

Juuri LZW:n toteuttamiseen useista LZ-algoritmeistä päädyin myöskin siksi, että se vaikuttaa olevan nykypäivänä paljon käytössä ja se on tehokkaampi kuin alkuperäiset LZ77 ja LZ78.

### Mitä syötteitä ohjelma saa ja miten näitä käytetään


### Tavoitteena olevat aika- ja tilavaativuudet

Huffmanin algoritmin osalta aikavaativuustavoitteena on O(nlogn) ja LZW:n O(n).

### Lähteet

* http://homes.sice.indiana.edu/yye/lab/teaching/spring2014-C343/huffman.php
* http://math.mit.edu/~goemans/18310S15/lempel-ziv-notes.pdf
* https://www.geeksforgeeks.org/lzw-lempel-ziv-welch-compression-technique/
* http://mat.hjg.com.ar/tic/img/LempelZivReport.pdf
* https://www.includehelp.com/algorithms/huffman-coding-algorithm-example-and-time-complexity.aspx
