# Ohjelmistotekniikka, harjoitustyö
## Alustava määrittelydokumentti
### Kasvien viljelypeli | Harvest Game
Pelissä on tarkoitus kasvattaa kasveja ja myydä niitä saadaksesi rahaa. Rahalla voi ostaa lisää yhä hienompia kasveja ja sykli jatkuu. Sovelluksessa ei ole muita käyttäjärooleja pelaajan lisäksi.

#### Perustoiminnallisuus:
- Toteutus aluksi tekstikäyttöliittymällä
- Kasveja voi ostaa tietokantaan määritellystä valikoimasta
- Kasvien atribuutteja ovat nimi, hinta, kasteluväli ja kasvuaika
- Jotta kasvit eivät kuole, niitä tulee kastella joka (kasteluväli * t)
- Kun kasvi on selvinnyt (kasvuaika * t), sen voi poimia ja myydä

#### Jatkokehitysideoita:
- Graafinen käyttöliittymä JavaFX:llä
- Pelin tallentaminen
- Lannoitteet -> nopeuttavat kasvien kasvamista, uusi tietokantataulu
- Vuodenajat -> tietyt kasvit 

## Software Requirement Specification
### Farming game | Harvest Game
The core gameplay loop of the game is to grow plants and eventually sell the harvest to earn money. With the earned money, the player can buy new plants.

#### Basic functions:
- First implementation with text interface
- Plants can be bought from a predefined database
- The attributes of the plants are name, price, watering interval and growth time
- In order to keep the plants alive, the play must water the plants every (watering interval * t)
- Plants are ready to be harvested after (growth time * t)

#### Further Development Ideas:
- GUI with JavaFX
- Game saving
- Fertilizers -> speeds the growth, new database
- Seasons -> all plants can't be grown every season

## Laskarit
### Viikko 1
[gitlog.txt](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/laskarit/viikko1/gitlog.txt)\
[komentorivi.txt](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/laskarit/viikko1/komentorivi.txt)

### Viikko 2
[testikattavuus.png](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/laskarit/viikko2/testikattavuus.png)\
[työaikakirjanpito](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/workinghours.md)
