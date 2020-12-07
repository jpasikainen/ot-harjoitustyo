package harvestgame.core;

import harvestgame.dao.PlayerDao;
import harvestgame.dao.StoreDao;
import harvestgame.ui.GuiManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * GameManager creates and distributes all the objects used globally.
 */
public class GameManager {
    private static StoreDao store;
    private static PlayerDao player;
    private static Field field;

    public static StoreDao getStore() {
        return store;
    }

    public static PlayerDao getPlayer() {
        return player;
    }

    public static Field getField() {
        return field;
    }

    /**
     * Initializes the game by creating.
     *{@link PlayerDao}, {@link StoreDao}, {@link StoreDao}, and {@link Field}.
     */
    public static void gameInit() {
        player = new PlayerDao("jdbc:sqlite:database/Database.db");
        store = new StoreDao("jdbc:sqlite:database/Database.db");
        field = new Field();
    }

    /**
     * Starts the update timer which ticks once every second.
     */
    public static void start() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GuiManager.update();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000l);
    }

    /**
     * Call this to exit game cleanly.
     */
    public static void exitGame() {
        player.writeBalance();
        System.exit(0);
    }
}
