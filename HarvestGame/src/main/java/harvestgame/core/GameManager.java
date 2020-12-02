package harvestgame.core;

import harvestgame.database.Database;

/**
 * TODO: Create getters for the static variables
 * GameManager creates and distributes all the objects used globally
 */
public class GameManager {
    private static Database db;
    private static Store store;
    private static Player player;
    private static Field field;

    /**
     * @return {@link Database}
     */
    public static Database getDb() {
        return db;
    }

    /**
     * @return {@link Store}
     */
    public static Store getStore() {
        return store;
    }

    /**
     * @return {@link Player}
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * @return {@link Field}
     */
    public static Field getField() {
        return field;
    }

    /**
     * Initializes the game by creating
     *{@link Player}, {@link Database}, {@link Store}, and {@link Field}.
     *
     * @param money set Player's balance
     */
    public static void gameInit(int money) {
        player = new Player(money);
        db = new Database("jdbc:sqlite:database/Database.db");
        store = new Store(db);
        field = new Field();
    }


    /**
     * Call this to exit game cleanly.
     * Closes the database connection
     *
     * @see Database
     */
    public static void exitGame() {
        db.disconnect();
        System.exit(0);
    }
}
