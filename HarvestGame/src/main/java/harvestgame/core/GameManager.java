package harvestgame.core;

import harvestgame.database.Database;
import harvestgame.ui.GUI;

/**
 * TODO: Create getters for the static variables
 * GameManager creates and distributes all the objects used globally
 */
public class GameManager {
    public static Database db;
    public static Store store;
    public static Player player;
    public static Field field;
    public static GUI gui;

    public static void gameInit(int money) {
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
}
