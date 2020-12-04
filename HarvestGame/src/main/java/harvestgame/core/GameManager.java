package harvestgame.core;

import harvestgame.database.StoreDao;

/**
 * TODO: Create getters for the static variables
 * GameManager creates and distributes all the objects used globally
 */
public class GameManager {
    private static StoreDao store;
    private static Player player;
    private static Field field;

    /**
     * @return {@link StoreDao}
     */
    public static StoreDao getStore() {
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
     *{@link Player}, {@link StoreDao}, {@link StoreDao}, and {@link Field}.
     *
     * @param money set Player's balance
     */
    public static void gameInit(int money) {
        player = new Player(money);
        store = new StoreDao("jdbc:sqlite:database/Database.db");
        field = new Field();
    }


    /**
     * Call this to exit game cleanly.
     */
    public static void exitGame() {
        System.exit(0);
    }
}
