# Algoritmit tehtavageneraattori

## Ohjelmistotekniikka kevät 2020 harjoitustyö

## Kuvaus
Sovelluksen avulla käyttäjät voivat opiskella algoritmeja ratkaisemalla ennalta määriteltyjä tehtäviä. 

## Dokumentaatio
[Vaatimusmäärittely](/AlgoritmitTehtavaGeneraattori/dokumentointi/vaatimustenmaarittely.md)

[Työaikakirjanpito](/AlgoritmitTehtavaGeneraattori/dokumentointi/tyoaikakirjanpito.md)

[Arkkitehtuurikuvaus](/AlgoritmitTehtavaGeneraattori/dokumentointi/arkkitehtuurikuvaus.md)

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

Checkstyle on käytössä ja määritykset löytyvät tiedostosta [checkstyle.xml](/checkstyle.xml) ja tarkistuksen voi suorittaa komennolla:
```
mvn jxr:jxr checkstyle:checkstyle
```
Checkstyle-raporttia voi tarkastella avaamalla tiedoston /target/site/checkstyle.html

