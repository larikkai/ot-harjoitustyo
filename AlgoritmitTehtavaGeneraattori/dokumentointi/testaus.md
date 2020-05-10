# Testausdokumentti

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluksessa testit on suositettu itse tehtyjen automatisoitujen testien avulla. Yksikkötestit testejä tehty luokille [User](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/domain/UserTest.java) ja [Task](AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/domain/TaskTest.java).

Integraatiotestaus on toteutettu [AlgoritmittehtavageneraattoriService](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/domain/AlgoritmittehtavageneraattoriServiceTest.java)-avulla, joka mallintaa käyttöliittymän toiminnallisuuksia. Integraatiotestauksessa on hyväksikäytetty fakedaoita [FakeUserDao](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/domain/FakeUserDao.java) ja [FakeTaskDao](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/domain/FakeTaskDao.java), jotka eivät tallenna tietoa tiedostoihin.

### Dao

Yleisesti DAO-luokkia on testattu väliaikaistiedostoilla, jotka poistetaan testien jälkeen. [FileUserDao](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/dao/FileUserDaoTest.java) ja [FileTaskDao](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/dao/FileTaskDaoTest.java) testit on suoritettu [TemporaryFolder](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/dao/FileTaskDaoTest.java#L20)-luokkaa hyväksi käyttäen, luomalla tekstitiedostot väliaikais hakemistoon. [DBUserDao](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/dao/DBUserDaoTest.java) ja [DBTaskDao](/AlgoritmitTehtavaGeneraattori/src/test/java/algoritmittehtavageneraattori/dao/DBTaskDaoTest.java) on testattu testien ajaksi luodun test-tietokannan avulla, joka poistetaan testien päätyttyä.

### Testikattavuus

Käyttöliittymää lukuunottamatta testejä yleisesti on suoritettu siten, että rivikattavuus on 94% ja haarautumiskattavuus 87%:

![testikattavuus](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/testikattavuus.PNG "Algoritmittehtavageneraattori testikattavuus")

## Järjestelmätestaus

Järjestelmä testausta on suoritettu manuaalisesti harraste- ja opiskelupohjalta.

### Asennus ja konfigurointi

Sovellus on asennettu ja konfiguroitu sekä windowsissa, että linux cubblilla [käyttöohjeen](/AlgoritmitTehtavaGeneraattori/dokumentointi/kayttoohje.md) mukaisesti. Erikseen on huomioitava, että sovellus tarvitsee [config.properties](https://github.com/larikkai/ot-harjoitustyo/releases/download/viikko7/config.properties)-tiedoston toimiakseen.

### Toiminallisuudet

[Määrittelydokumentissa](/AlgoritmitTehtavaGeneraattori/dokumentointi/vaatimustenmaarittely.md) määritellyt toiminnallisuudet on testattu molempia tiedon tallennusmoodeja käyttäen. Erityisesti validointia ja _"tyhmän"_-käyttäjän virheitä on pyritty mallintamaan.

## Laatuongelmat

Tehtävien lataus ladatusta tiedostosta, joka luotu windowsilla ei linuxilla toimi.