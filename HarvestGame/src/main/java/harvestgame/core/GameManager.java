package harvestgame.core;

import harvestgame.database.Database;

public class GameManager {
    public static Database db;
    public static Store store;
    public static Player player;
    public static Field field;

    public static void gameInit() {
        player = new Player(100);
        db = new Database("jdbc:sqlite:database/Database.db");
        store = new Store(db);
        field = new Field(10, player);
    }

    // Close the database connection and exit the game
    public static void exitGame() {
        db.disconnect();
        System.exit(0);
    }
}
