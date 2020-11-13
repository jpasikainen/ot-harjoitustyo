# Farming game | Harvest Game
The core gameplay loop of the game is to grow plants and eventually sell the harvest to earn money. With the earned money, the player can buy new plants.

## Documentation
[Manual](https://github.com/jpasikainen-ot-harjoitustyo/blob/master/documentation/manual.md)\
[Software Requirement Specification](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/srs.md)\
[Working Hours](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/workinghours.md)

## Command line commands
### Run
```
mvn compile exec:java -Dexec.mainClass=harvestgame.core.Game
```
### Testing
Run tests:
```
mvn test
```
Create test coverage report:
```
mvn jacoco:report
```
View report by opening file target/site/jacoco/index.html
