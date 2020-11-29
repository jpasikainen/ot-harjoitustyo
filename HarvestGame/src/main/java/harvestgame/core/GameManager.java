package harvestgame.core;

import harvestgame.database.Database;
import harvestgame.ui.GUIv2;

/**
 * TODO: Create getters for the static variables
 * GameManager creates and distributes all the objects used globally
 */
public class GameManager {
    public static int day;
    public static Database db;
    public static Store store;
    public static Player player;
    public static Field field;
    public static GUIv2 gui;

    public static void gameInit(int money, int fieldWidth, int fieldHeight) {
        player = new Player(money);
        db = new Database("jdbc:sqlite:database/Database.db");
        store = new Store(db);
        field = new Field();
    }

    // Close the database connection and exit the game
    public static void exitGame() {
        db.disconnect();
        System.exit(0);
    }

    // Advances to the next day
    public static void nextDay() {
        field.advanceDay();
        day++;
    }
}
