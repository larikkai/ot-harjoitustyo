# Algoritmit tehtavageneraattori

## Ohjelmistotekniikka kevät 2020 harjoitustyö

## Kuvaus
Sovelluksen avulla käyttäjät voivat opiskella algoritmeja ratkaisemalla ennalta määriteltyjä tehtäviä. 

## Dokumentaatio
[Vaatimusmäärittely](/AlgoritmitTehtavaGeneraattori/dokumentointi/vaatimustenmaarittely.md)

[Työaikakirjanpito](/AlgoritmitTehtavaGeneraattori/dokumentointi/tyoaikakirjanpito.md)

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
Kattavuusraporttia voi tarkastella avaamalla selaimella target/site/jacoco/index.html

