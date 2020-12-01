# Farming game | Harvest Game
The core gameplay loop of the game is to grow plants and eventually sell the harvest to earn money. With the earned money, the player can buy new plants.

## Documentation
[Software Requirement Specification](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/srs.md)\
[Architecture](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/architecture.md)\
[Working Hours](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/workinghours.md)

## Download & Play
Go to [Releases](https://github.com/jpasikainen/ot-harjoitustyo/releases) and download the latest *HarvestGame.zip* file and extract it. To play the game run
```
java -jar HarvestGame.jar
```
in the extracted directory. Game has been tested on Java version 11.0.9.1. 

## Command line commands
Run commands in *HarvestGame* directory.

### Generate .jar
For .jar to work, *database* folder must be in the same directory as the executable.
```
mvn package
```
If you encounter warnings / errors try
```
mvn clean package
```

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
View report by opening file *target/site/jacoco/index.html*

### Checkstyle
```
mvn jxr:jxr checkstyle:checkstyle
```
View report by opening file *target/site/checkstyle.html*
