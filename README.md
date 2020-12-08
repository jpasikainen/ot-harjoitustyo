# Farming game | Harvest Game
The core gameplay loop of the game is to grow plants and eventually sell the harvest to earn money. With the earned money, the player can buy new plants.

## Documentation
[Instructions](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/instructions.md)\
[Software Requirement Specification](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/srs.md)\
[Architecture](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/architecture.md)\
[Working Hours](https://github.com/jpasikainen/ot-harjoitustyo/blob/master/documentation/workinghours.md)

## Download & Play
Go to [Releases](https://github.com/jpasikainen/ot-harjoitustyo/releases) and download the release you wish or click [HERE](https://github.com/jpasikainen/ot-harjoitustyo/releases/download/viikko5/HarvestGame.zip) to download the latest release. Save the *HarvestGame.zip* file and extract it. To play the game, use command
```
java -jar HarvestGame.jar
```
in the extracted directory. Game has been tested on Java version 11.0.9.1 on various Ubuntu derivated Linux distributions.

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
