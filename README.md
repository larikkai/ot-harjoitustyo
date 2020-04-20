# Algoritmit tehtavageneraattori

## Ohjelmistotekniikka kevät 2020 harjoitustyö

## Kuvaus
Sovelluksen avulla käyttäjät voivat opiskella algoritmeja ratkaisemalla tehtäviä. Käyttäjät voivat itse luoda ja lisätä tehtäviä ratkaistavaksi joko yksitellen tai tiedostosta. Tiedostosta lisättäessä käyttäjä voi valita tuoda uuden tehtävälistan tai lisätä tiedostosta nykyiseen tehtävälistaan.


## Dokumentaatio
[Vaatimusmäärittely](/AlgoritmitTehtavaGeneraattori/dokumentointi/vaatimustenmaarittely.md)

[Työaikakirjanpito](/AlgoritmitTehtavaGeneraattori/dokumentointi/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](/AlgoritmitTehtavaGeneraattori/dokumentointi/arkkitehtuuri.md)

## Releaset
[Viikko 5](https://github.com/larikkai/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Koodin suorittaminen

Koodin pystyy suorittamaan komentoriviltä komennolla:
```
mvn compile exec:java -Dexec.mainClass=algoritmittehtavageneraattori.ui.AlgoritmitTehtavaGeneraattoriUi
```

### Testaus

Testit suoritetaan komennolla
```
mvn test
```

Testikattavuusraportin voi luoda komennolla
```
mvn jacoco:report
```
Testien kattavuusraporttia voi tarkastella avaamalla tiedoston target/site/jacoco/index.html

### Checkstyle

Checkstyle on käytössä ja määritykset löytyvät tiedostosta [checkstyle.xml](/AlgoritmitTehtavaGeneraattori/checkstyle.xml) ja tarkistuksen voi suorittaa komennolla:
```
mvn jxr:jxr checkstyle:checkstyle
```
Checkstyle-raporttia voi tarkastella avaamalla tiedoston /target/site/checkstyle.html

### Suoritettavan jarin generointi
Komennolla:
```
mvn package
```
generoidaan suoritettava AlgoritmitTehtavaGeneraattori-1.0-SNAPSHOT.jar tiedosto hakemistoon target
