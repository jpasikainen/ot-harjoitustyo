# Architecture
## Application structure
![Class Diagram](./class_diagram.png)

Ui package creates and controls the graphical interface. Graphical interface is created using JavaFX11 and FXML. Styling is done with css and a custom font. Core package takes care of the game logic and works without database access. Database (dao package) contains objects necessary to read and write the database. Database is stored in separate file *workingdir/database/database.db*.

### Database structure
![Database](./database.png)
Database.db contains all the necessary data for the game to function and is created with SQLite 3. It contains two tables, *Plants* and *HighScores*. *Plants* is used to store all the data required to create plants: id, name, price, soil_dryness, and growing_time. *HighScores* contains the scores and names of the local users. The game can be broken or modified and expanded by accessing the database and changing values by any user.

## GUI Startup
![Startup Diagram](./startup.png)\
Program starts from the Game class. It calls static class GameManager's initialization method which in turn creates the "core" objects required to run the application. On the diagram above, the application is supposed have working access to the database.
