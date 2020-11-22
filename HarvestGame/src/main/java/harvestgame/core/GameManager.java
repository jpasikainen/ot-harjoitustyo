package harvestgame.core;

import harvestgame.database.Database;

/**
 * TODO: Create getters for the static variables
 */
public class GameManager {
    public static int day;
    public static Database db;
    public static Store store;
    public static Player player;
    public static Field field;

    public static void gameInit(int money, int fieldWidth, int fieldHeight) {
        player = new Player(money);
        db = new Database("jdbc:sqlite:database/Database.db");
        store = new Store(db);
        field = new Field(fieldWidth, fieldHeight);
    }

    // Close the database connection and exit the game
    public static void exitGame() {
        db.disconnect();
        System.exit(0);
    }

    public static void nextDay() {
        field.newDay();
        day++;
    }
}
