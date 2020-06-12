## Viikkoraportti 5

Työhön käytetty aika: 20h

Tällä viikolla koitin ratkoa kaikenlaisia bugeja ja myös saada algoritmeista tehokkaampia, mikä on osoittautunut aika haastavaksi. Muokkasin 
mm. Tablen toteutusta niin, että sen koko muuttuu vasta tarvittaessa staattisen koon sijaan, mutta en ole ihan varma parantuiko tehokkuus 
tästä merkittävästi. Tein myös NodePriorityQueuesta min heap -tyylisen rakenteen siinä toivossa, että se toimisi tehokkaammin, mutta 
en ole varma auttoiko sekään. Jälkeenpäin ajateltuna olisi kannattanut ottaa muistiin algoritmien suoritusajat ennen muutoksien tekemistä 
ja verrata niitä muutosten jälkeisiin aikoihin.

Aloitin jonkunlaista suorituskykytestausta joka kuitenkin kaipaa vielä paljon parantelua. Oli yllättävän vaikeaa miettiä, minkälaisilla 
syötteillä ohjelmaa kannattaisi järkevästi testata. Koitin saada myös junit-testejä järkevämmiksi yhdistämällä
compression- ja decompression-luokkien testit yhdeksi testiluokaksi. Ehdin aloitella myös tekstikäyttöliittymää: alunperin halusin tehdä 
graafisen käyttöliittymän, mutta se vaatisi ehkä turhan paljon aikaa ja vaivaa siihen nähden että ohjelmaa voi hyvin käyttää myös pelkän
tekstikäyttöliittymän kautta.

Myös vertaisarviointiin kului tällä viikolla aika monta tuntia. Toisen kirjoittaman koodin lukeminen osoittautui jälleen kerran vaikeaksi 
tehtäväksi mutta toisaalta oli myös mielenkiintoista nähdä erilaista toteutusta samasta aiheesta ja saada itsekin uutta näkökulmaa omaan 
toteutukseen.

Ohjelman perustoiminnallisuus on nyt ainakin jollain tasolla valmis: molemmat algoritmit tekevät tehtävänsä suht hyvin (vaikka 
paranneltavaa riittääkin). 
Ensi viikolla keskityn testaukseen ja dokumentaation kirjoittamiseen. Lisäksi pitäisi vielä korvata Javan Random-luokkaa yms.
käyttävät kohdat omilla toteutuksilla. 
Jos aikaa jää, koitan vielä viilata itse algoritmeja.

__Vielä kysymys__: riittääkö loppupalautuksen kannalta se, että algoritmit osaavat pakata/purkaa pelkkiä tekstitiedostoja? Huffman-algoritmin 
pitäisi periaatteessa pystyä käsittelemään muitakin tiedostoja, mutta käytännössä se ei toimi vaan muut kuin tekstitiedostot tuottavat 
outoja bugeja. Lähinnä ajankäytön kannalta pohdin että kannattaisiko koittaa uhrata aikaa sen korjaamiseen vai tyytyä tähän tilanteeseen.
