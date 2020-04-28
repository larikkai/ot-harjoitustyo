# Algoritmit tehtavageneraattori

## Ohjelmistotekniikka kevät 2020 harjoitustyö

## Kuvaus
Sovelluksen avulla käyttäjät voivat opiskella algoritmeja ratkaisemalla tehtäviä. Käyttäjät voivat itse luoda ja lisätä tehtäviä ratkaistavaksi joko yksitellen tai tiedostosta. Tiedostosta lisättäessä käyttäjä voi valita tuoda uuden tehtävälistan tai lisätä tiedostosta nykyiseen tehtävälistaan.


## Dokumentaatio
[Vaatimusmäärittely](/AlgoritmitTehtavaGeneraattori/dokumentointi/vaatimustenmaarittely.md)

[Työaikakirjanpito](/AlgoritmitTehtavaGeneraattori/dokumentointi/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](/AlgoritmitTehtavaGeneraattori/dokumentointi/arkkitehtuuri.md)

[Käyttöohje](/AlgoritmitTehtavaGeneraattori/dokumentointi/kayttoohje.md)

## Releaset
[Viikko 5](https://github.com/larikkai/ot-harjoitustyo/releases/tag/viikko5)
[Viikko 6](https://github.com/larikkai/ot-harjoitustyo/releases/tag/viikko6)

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
Testien kattavuusraporttia voi tarkastella avaamalla tiedoston _target/site/jacoco/index.html_

### Checkstyle

Checkstyle on käytössä ja määritykset löytyvät tiedostosta [checkstyle.xml](/AlgoritmitTehtavaGeneraattori/checkstyle.xml) ja tarkistuksen voi suorittaa komennolla:
```
mvn jxr:jxr checkstyle:checkstyle
```
Checkstyle-raporttia voi tarkastella avaamalla tiedoston _target/site/checkstyle.html_

### Suoritettavan jarin generointi
Komennolla:
```
mvn package
```
generoidaan suoritettava AlgoritmitTehtavaGeneraattori-1.0-SNAPSHOT.jar tiedosto hakemistoon _target_

Jar suoritetaan komennolla:
```
java -jar tiedoston_nimi.jar
```
### JavaDoc
JavaDoc :n saa generoitua komennolla:
```
mvn javadoc:javadoc
```
Generoitua JavaDoci :a voi tarkastella hakemistossa _target/site/apidocs/index.html_