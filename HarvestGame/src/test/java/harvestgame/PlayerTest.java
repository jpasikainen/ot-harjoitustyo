package harvestgame;

import harvestgame.core.*;
import harvestgame.dao.PlayerDao;
import harvestgame.dao.StoreDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TODO: Check the actual item, not the inventory size
 * Not possible because:
 * expected: harvestgame.core.Plant<0 : costs 0, dries soil every 0 days, and takes 0 days to grow>
 * but was: harvestgame.core.Plant<0 : costs 0, dries soil every 0 days, and takes 0 days to grow>
 * which makes no sense
 */
public class PlayerTest {
    private Plant testPlant = new Plant(0, "", 0, 0, 0);

    private static StoreDao store;
    private static PlayerDao player;
    private static Field field;

    private void initialize() {
        store = GameManager.getStore();
        player = GameManager.getPlayer();
        field = GameManager.getField();
    }

    @Before
    public void setUp() {
        GameManager.gameInit();
        initialize();
    }

    @Test
    public void initCorrectly() {
        assertEquals(0, player.getBalance());
    }

    @Test
    public void increaseBalance() {
        player.changeBalance(10);
        assertEquals(10, player.getBalance());
    }

    @Test
    public void reduceBalanceBelowZero() {
        player.changeBalance(-100);
        assertEquals(0, player.getBalance());
    }
}
