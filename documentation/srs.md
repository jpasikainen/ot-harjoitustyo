# Software Requirement Specification

## Harvest Game
The core gameplay loop of the game is to grow plants and eventually sell the harvest to earn money. With the earned money, the player can buy new plants.

### Basic functions:
- First implementation with text interface
- Plants can be bought from a predefined database
- The attributes of the plants are name, price, watering interval and growth time
- In order to keep the plants alive, the play must water the plants every (watering interval * t)
- Plants are ready to be harvested after (growth time * t)

### Further Development Ideas:
- GUI with JavaFX
- Game saving
- Fertilizers -> speeds the growth, new database
- Seasons -> all plants can't be grown every season

