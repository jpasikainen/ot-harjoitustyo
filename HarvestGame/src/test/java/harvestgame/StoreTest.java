package harvestgame;

import harvestgame.core.*;
import harvestgame.core.Player;
import harvestgame.dao.StoreDao;
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
public class StoreTest {
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
        assertEquals(3, store.getPlants().size());
    }

    @Test
    public void buyingReturnsCorrectPlant() {
        assertEquals(store.getPlant(0).getId(), store.buyPlant(0, player).getId());
    }

    @Test
    public void buyInvalidPlant() {
        assertNull(store.buyPlant(100, player));
    }
}
