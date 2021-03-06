# Algoritmittehtavageneraattori käyttöohje

## Sovellus
Lataa sovellus [AlgoritmitTehtavaGeneraattoriApp.jar](https://github.com/larikkai/ot-harjoitustyo/releases/download/viikko6/AlgoritmitTehtavaGeneraattoriApp.jar)

## Konfigurointi
Sovellus olettaa, että hakemistossa jossa jar suoritetaan on config.properties. Tiedoston avulla valitaan sovelluksen käyttämä tiedontallennustapa ja on välttämätön sovelluksen toiminnan kannalta.

Voit luoda tiedoston komentoriviltä komennolla:
```
echo $'data=file\nuserFile=users.txt\ntaskFile=tasks.txt' >config.properties
```
tai halutessasi ladata valmiiksi tehdyn [config.properties](https://github.com/larikkai/ot-harjoitustyo/releases/download/viikko7/config.properties)

__teksitiedostoon tallennus__
```
data=file
userFile=users.txt
taskFile=tasks.tx
```

__h2-tietokanta__
```
data=db
userFile=users.txt
taskFile=tasks.tx
```

## Ohjelman käynnistäminen
Ohjelman voi käynnistää klikkaamalla kuvaketta tai komentoriviltä komennolla:
```
java -jar AlgoritmitTehtavaGeneraattoriApp.jar
```
Sovellus aukeaa alkunäkymään:

![alkunakyma](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_alku.PNG "Algoritmittehtavageneraattori alkunakyma")

## Uuden käyttäjän luominen
Uuden käyttäjän voi luoda klikkaamalla aloitusnäkymästä "create new user".

Uuden käyttäjän validointi:
   - Username täytyy olla pituudeltaan 3-10 merkkiä
   - Password täytyy olla pituudeltaan vähintään 8 merkkiä.
   - Username täytyy olla uniikki

![uusi kayttaja](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_uusi.PNG "Algoritmittehtavageneraattori uusi kayttaja")

Klikkaamalla "create" luodaan uusi käyttäjä jos validointi menee läpi:

![uusi kayttaja ok](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_uusi_ok.PNG "Algoritmittehtavageneraattori uusi kayttaja OK")

Jos validointi ei mene läpi käyttäjälle näytetään virheilmoitus:

![uusi kayttaja fail](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_uusi_fail.PNG "Algoritmittehtavageneraattori uusi kayttaja FAIL")

## Kirjautuminen
Kun käyttäjä on luotu, voidaan kirjautua. Pääset takaisin kirjautumislomakkeelle klikkaamalla "back to login".

Täytä kirjautumistietosi ja klikkaa login. 

![sovellus kirjautuminen](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_kirjautuminen.PNG "Algoritmittehtavageneraattori sovellus kirjautuminen")

Jos validointi menee läpi pääset sisään sovellukseen:

![sovellus alkunakyma](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_alkunakyma.PNG "Algoritmittehtavageneraattori sovellus alkunakyma")

## Uuden tehtävän luominen
Alkunäkymästä klikkaamalla "new task" pääset luomaan uuden tehtävän:

![sovellus uusi tehtava](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_uusi_t.PNG "Algoritmittehtavageneraattori uusi tehtava")

Uuden tehtävän validointi:
   - Title täytyy olla vähintään 5 merkkiä
   - Descriptionin täytyy olla vähintään 10 merkkiä
   - Mitään kohtaa ei saa jättää tyhjäksi

Klikkaamalla "create" luodaan uusi tehtävä jos validointi menee läpi. Tehtävä lisätään käyttäjän tehtävälistaan:

![sovellus uusi tehtava ok](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_uusi_t_ok.PNG "Algoritmittehtavageneraattori uusi tehtava OK")

Jos validointi ei mene läpi käyttäjälle näytetään virheilmoitus:

![sovellus uusi tehtava fail](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_uusi_t_fail.PNG "Algoritmittehtavageneraattori uusi tehtava FAIL")

## Uusien tehtävien tuominen tiedostosta

Käyttäjä voi tuoda tehtatehtäviä tiedostosta jos tiedoston rakenne on oikea.

### Nykyisen tehtavalistan korvaaminen uudella
Klikkaamalla "File -> load new task list" käyttäjä voi tuoda täysin uuden tehtävä listan:

![sovellus uusi tehtavalista](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_uusi_tehtavalista.PNG "Algoritmittehtavageneraattori uusi tehtavalista")

Käyttäjälle aukeaa tiedoston haku hakemistosta, kaksoisklikkaa tehtävälista-tiedostoa tai valitse. Tämän jälkeen aukeaa uusi haku hakemisto jossa valitaan minne juuri valittu tehtävälista-tiedosto halutaan tallentaa. Uusi tehtävälista tulee tallentaa samaan hakemistoon jarin kanssa.

Onnistuneen latauksen jälkeen tehtävät siirtyvät käyttäjän tehtävälista näkymään:

![sovellus uusi tehtavalista OK](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_uusi_tehtavalista_ok.PNG "Algoritmittehtavageneraattori uusi tehtavalista OK")

### Tehtävien lisaaminen nykyiseen tehtavalistaan
Klikkaamalla "File -> load new tasks to list" käyttäjä voi tuoda uusia tehtäviä nykyiseen listaan:

![sovellus uusia tehtavia](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_lisaa_tehtavalistaan.PNG "Algoritmittehtavageneraattori sovellus uusia tehtavia")

Käyttäjälle aukeaa tiedoston haku hakemistosta, kaksoisklikkaa tehtävälista-tiedostoa tai valitse tiedosto hiirellä ja paina valitse. Tämän jälkeen aukeaa uusi haku hakemisto jossa valitaan missa sovelluksen nykyinen tehtävälista sijaitsee.

## Tehtävän ratkaiseminen
Käyttäjä voi valita tehtävän, jonka haluaa ratkaista klikkaaamalla tehtävää tehtävä näkymästä. Kun tehtävä on valittu, tehtävän tiedot aukeavat.

Täyttämällä ratkaisun ja painamalla "Solve" käyttäjä voi ratkaista tehtävän jos vastaus on oikein:

![sovellus tehtavan rat](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_tehtavan_r.PNG "Algoritmittehtavageneraattori tehtava rat")

Mikäli vastaus on oikein tehtävä merkitään tehdyksi ja käyttäjälle näytetään onnistunut ilmoitus:

![sovellus tehtavan rat ok](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_tehtavan_r_ok.PNG "Algoritmittehtavageneraattori tehtava rat OK")

Jos vastaus on väärin, näytetään käyttäjälle virheilmoitus:

![sovellus tehtavan rat fail](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/sovellus_tehtavan_r_fail.PNG "Algoritmittehtavageneraattori tehtava rat FAIL")

## Käyttajän tiedot
Kun käyttäjä ratkaisee tehtävän, käyttäjälle lisätään pisteitä tehtävän vaikeusasteen verran. Pisteitä voi saada vain kerran yhdestä tehtävästä. Pisteet päivittyvät menupalkkiin käyttäjän nähtäväksi.

## Muuta
Kuten aikaisemmin kuvattu, tehtäviä tiedostosta lisättäessä tulee tiedoston rakenne olla oikea. Voit hyödyntää valmiiksi tehtyä tiedostoa:
[load.txt](https://github.com/larikkai/ot-harjoitustyo/releases/download/viikko6/load.txt "tehtava tiedosto")

Tehtävät ovat käyttäjä kohtaisia, joten ennen käyttöä muuta load tiedostossa olevat käyttäjään viittaavat tiedot manuaalisesti käsin tai
komentoriviltä komennolla:
```
sed -i 's/username/luomasi_kayttajanimi/g' load.txt
```

Huomioithan, että tiedostolla josta tehtävät ladataan tulee olla kirjoitus- ja lukuoikeudet. Voit antaa oikeudet ladatulle load.txt tiedolle tai copypasteta sisällän itse luomaasi.