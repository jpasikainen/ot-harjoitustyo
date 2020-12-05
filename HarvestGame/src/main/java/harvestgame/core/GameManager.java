package harvestgame.core;

import harvestgame.dao.PlayerDao;
import harvestgame.dao.StoreDao;

/**
 * TODO: Create getters for the static variables
 * GameManager creates and distributes all the objects used globally
 */
public class GameManager {
    private static StoreDao store;
    private static PlayerDao player;
    private static Field field;

    /**
     * @return {@link StoreDao}
     */
    public static StoreDao getStore() {
        return store;
    }

    /**
     * @return {@link PlayerDao}
     */
    public static PlayerDao getPlayer() {
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
     *{@link PlayerDao}, {@link StoreDao}, {@link StoreDao}, and {@link Field}.
     */
    public static void gameInit() {
        player = new PlayerDao("jdbc:sqlite:database/Database.db");
        store = new StoreDao("jdbc:sqlite:database/Database.db");
        field = new Field();
    }


    /**
     * Call this to exit game cleanly.
     */
    public static void exitGame() {
        player.writeBalance();
        System.exit(0);
    }
}
