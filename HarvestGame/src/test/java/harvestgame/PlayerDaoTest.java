package harvestgame;

import harvestgame.core.*;
import harvestgame.core.Player;
import harvestgame.dao.StoreDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerDaoTest {
    private Plant testPlant = new Plant(0, "", 0, 0, 0);

    private static StoreDao store;
    private static Player player;
    private static Field field;

    private void initialize() {
        store = GameManager.getStore();
        player = GameManager.getPlayer();
        player.resetData();
        field = GameManager.getField();
    }

    @Before
    public void setUp() {
        GameManager.gameInit();
        initialize();
    }

    @Test
    public void initCorrectly() {
        assertEquals(100, player.getBalance());
    }

    @Test
    public void increaseBalance() {
        player.changeBalance(10);
        assertEquals(110, player.getBalance());
    }

    @Test
    public void reduceBalanceBelowZero() {
        player.changeBalance(-100);
        assertEquals(0, player.getBalance());
    }
}
