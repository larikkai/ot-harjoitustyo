# Arkkitehtuurikuvaus

## Rakenne

## Käyttöliittymä

Käyttöliittymässä on kolme erilaista näkymää:
   - Kirjautuminen
   - Uuden käyttäjän luominen
   - Päänäkymä
      - Uuden tehtävän luominen
      - Tehtävän ratkaiseminen

[Käyttöliittymän](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavaGeneraattori/ui/AlgoritmitTehtavaGeneraattoriUi.java) näkymät ovat [Scene-olioita](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html), joista yksi kerrallaan asetetaan sovelluksen [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html) ja näytetään käyttäjälle. Kirjautumisnäkymä ja uuden käyttäjänä luomisnäkymä ovat kiinteitä, päänäkymän näkymää muokataan sovelluksen toiminnallisuuden mukaan asettamalla näkymän keskiöön erilaisia [paneja](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/Pane.html).

Käyttäliittymän rakentaminen on jaettu käyttöliittymässä pieniin metodeihin joita kutsumalla rakennetaan näkymät, tehtävälistan toteutetaan kutsumalla omaa [metodiaan](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/ui/AlgoritmitTehtavaGeneraattoriUi.java#L461).

Sovelluksen toiminnallisuuteen liittyen tehtävälistaa ja käyttäjätietoja päivitetään metodilla [update](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/ui/AlgoritmitTehtavaGeneraattoriUi.java#L450). 

Tehtävälistan haku-ominaisuudet on toteutettu omilla [metodeillaan](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/ui/AlgoritmitTehtavaGeneraattoriUi.java#L494).

## Sovelluslogiikka

Luokat [User](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/User.java) ja [Task](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/Task.java) edustavat sovelluksessa käyttäjiä ja tehtäviä. 

![luokkakaavio](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/Luokkakaavio.png "Algoritmittehtavageneraattori luokkakaavio")

Toiminnallisuudesta vastaa [AlgoritmitTehtavaService](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/AlgoritmitehtavageneraattoriService.java), joka tarjoaa käyttöön mm. metodit käyttäjien luomiseen, sisään ja uloskirjautumiseen, tehtävien lisäämiseen ja tehtävien ratkaisuden validointiin.

[Käyttäjien](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/dao/UserDao.java) ja [tehtävien](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/dao/TaskDao.java) edustajat injektoidaan sovelluslogiikalle niiden rajapintojen avulla kontruktorissa.

![pakkauskaakaavio](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/luokka_pakkauskaavio.png "Algoritmittehtavageneraattori pakkauskaavio")

## Tietojen tallennus

Käyttäjä- ja tehtävätiedot tallennetaan sovelluksessa tiedostoihin. Tietoja luetaan ja tallennetaan tiedostoihin dao-pakkauksessa olevien [FileTaskDao](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/dao/FileTaskDao.java) ja [UserTaskDao](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/dao/FileUserDao.java) avulla.

## Tiedostot

Hakemistossa jossa sovellus suoritetaan oletetaan olevan tiedosto [config.properties](https://github.com/larikkai/ot-harjoitustyo/releases/download/viikko6/config.properties), joka sisältää tiedot mistä tiedostoista tarvittavat käyttäjä ja tehtävä tiedot löytyvät.

Käyttäjätiedot tallennetaan tiedostoihin mallilla:
```
username; hashedPassword; points
```

Tehtävätiedot tallennetaan mallilla:
```
title;description;hashedResult;difficulty;id;done;categoryId;input;user
```

## Pääominaisuudet

### Kirjautuminen

Kirjautumisnäkymässä käyttäjän syöttää käyttäjänimen ja salasanan. Kun login-nappia klikkaa, käyttöliittymästä lähetetään tekstikenttien tiedot parametreina sovelluslogiikan [login](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/AlgoritmitehtavageneraattoriService.java#L55)-metodille, jonka avulla validoidaan tiedot. Validointi jakautuun kahteen osaan, ensin tarkistetaan löytyykö käyttäjää käyttäjänimen perusteella. Jos käyttäjä löytyy verrataan parametrina saatua salasanaa ja sovellukseen tallennettua hashattua salasanaa. Mikäli validointi suoritetaan onnistuneesti siirrytään päänäkymään ja päivitetään käyttäjän tehtävälista. Mikäli validointi epäonnistuu käyttäjälle näytetään virheteksti.

![kirjautuminen](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/Kirjautuminen.png "Algoritmittehtavageneraattori kirjautuminen")

### Uuden käyttäjän luominen

Uuden käyttäjän näkymässä käyttäjä syöttää valitsemansa käyttäjänimen ja salasanan. Kun create-nappia klikkaa lähetetään tekstikenttien tiedot parametreina sovelluslogiikan [createUser](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/AlgoritmitehtavageneraattoriService.java#L34)-metodille, jonka avulla validoidaan tiedot. Sovelluksessa käyttäjänimen tulee olla indentifoiva ja se tarkistetaan validoinnissa ensin. Jos käyttäjänimi on vapaa luodaan parametrina saadusta salasanasta hashattuversio ja tallennetaan sovellukseen. Validoinnin tuloksesta näytetään viesti käyttäjälle.

![uudenkayttajanluominen](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/Uuden_kayttajan_luominen.png "Algoritmittehtavageneraattori uuden käyttäjän luominen")

### Uuden tehtävän luominen

Päänäkymän menuPanesta newTask-nappia klikastesta näkymän keskiöön vaihdetaan ikkuna, jonka avulla käyttäjä voi luoda uuden tehtävän. Create-nappia klikatessa tehtäväkentän tiedot lähetetään parametreina [createNewTask](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/AlgoritmitehtavageneraattoriService.java#L107)-metodille. Metodissa luodaan parametrina saadusta vastauksesta hashattuversio ja luodaan tehtävä parametreista ja hashatusta vastauksesta jonka jälkeen käyttäjälle näytetään ilmoitus onnistuiko uuden tehtävän luonti.

![pakkauskaakaavio](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/Uuden_tehtavan_luominen.png "Algoritmittehtavageneraattori uuden tehtavan luominen")

### Tehtävän ratkaiseminen

Pääkymästä käyttäjän klikatessa tehtävää tehtävälistasta muutetaan päänäkymän keskiön yksittäisen tehtävän ikkuna. Solve-nappia klikatessa tekstikentän arvo ja valittu tehtävä läheteään parametreina [compareResult](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/AlgoritmitehtavageneraattoriService.java#L87)-metodille joka validoi parametrina saadun vastauksen vertaamalla tehtävän vastaukseen. 

Jos validointi onnistuu ja tehtävä on ratkaisematon kutsutaan käyttäjäluokan [setPoints](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/User.java#L47)-metodia tehtävän vaikeusasteella ja sovelluslogiikan [markSolved](/AlgoritmitTehtavaGeneraattori/src/main/java/algoritmittehtavageneraattori/domain/AlgoritmitehtavageneraattoriService.java)-metodia tehtävä id:llä jonka avulla asetetaan tehtävä ratkaistuksi ja tallennetaan käyttäjän pisteet tiedostoon.

![pakkauskaakaavio](/AlgoritmitTehtavaGeneraattori/dokumentointi/kuvat/Tehtavan_ratkaiseminen.png "Algoritmittehtavageneraattori tehtavan ratkaiseminen")

## Heikkoudet

- Vaikka käyttäjätietojen salasanoja tai tehtävien ratkaisuja __ei ole__ tallennettu selkotekstillä, ilkeämielinen käyttäjä pystyy halutessaan purkamaan salauksen.
- Vaikka sovelluslogiikkaa on pääosin eriytetty käyttöliittymästä, sisältää käyttöliittymä vielä vähän esimerkiksi validointia liittyen käyttäjänimen tai salasanan pituuteen.
- Tehtävien tuominen tiedostosta on turhan kankean oloinen.