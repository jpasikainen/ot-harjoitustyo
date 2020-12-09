package harvestgame.core;

import harvestgame.dao.HighScoreDao;
import harvestgame.dao.StoreDao;
import harvestgame.ui.GuiManager;
import java.util.Timer;
import java.util.TimerTask;

/**
 * GameManager creates and distributes all the objects used globally.
 */
public class GameManager {
    private static String databaseUrl = "jdbc:sqlite:database/Database.db";
    private static StoreDao store;
    private static Player player;
    private static Field field;
    private static HighScoreDao scores;

    public static StoreDao getStore() {
        return store;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Field getField() {
        return field;
    }

    public static HighScoreDao getScores() {
        return scores;
    }

    /**
     * Initializes the game by creating.
     *{@link Player}, {@link StoreDao}, {@link StoreDao}, and {@link Field}.
     */
    public static void gameInit() {
        player = new Player();
        store = new StoreDao(databaseUrl);
        field = new Field();
        scores = new HighScoreDao(databaseUrl);
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
        System.exit(0);
    }
}
