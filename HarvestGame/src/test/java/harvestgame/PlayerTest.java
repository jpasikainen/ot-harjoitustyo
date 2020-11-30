package harvestgame;

import harvestgame.core.GameManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import harvestgame.core.Plant;

/**
 * TODO: Check the actual item, not the inventory size
 * Not possible because:
 * expected: harvestgame.core.Plant<0 : costs 0, dries soil every 0 days, and takes 0 days to grow>
 * but was: harvestgame.core.Plant<0 : costs 0, dries soil every 0 days, and takes 0 days to grow>
 * which makes no sense
 */
public class PlayerTest {
    Plant testPlant = new Plant(0, "", 0, 0, 0);

    @Before
    public void setUp() {
        GameManager.gameInit(0);
    }

    @After
    public void close() {
        GameManager.db.disconnect();
    }

    @Test
    public void initCorrectly() {
        assertEquals(0, GameManager.player.getBalance());
    }

    @Test
    public void increaseBalance() {
        GameManager.player.changeBalance(10);
        assertEquals(10, GameManager.player.getBalance());
    }

    @Test
    public void reduceBalanceBelowZero() {
        GameManager.player.changeBalance(-100);
        assertEquals(0, GameManager.player.getBalance());
    }
}
