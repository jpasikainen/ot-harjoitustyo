package harvestgame.dao;

import harvestgame.core.*;
import harvestgame.core.Player;
import harvestgame.dao.StoreDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StoreDaoTest {
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
        assertEquals(4, store.getPlants().size());
    }

    @Test
    public void tryGettingInvalidPlant() {
        assertNull(store.getPlant(1000));
    }

    @Test
    public void buyingReturnsCorrectPlant() {
        assertEquals(store.getPlant(0).getId(), store.buyPlant(0, player).getId());
    }

    @Test
    public void buyInvalidPlant() {
        assertNull(store.buyPlant(100, player));
    }

    @Test
    public void tryBuyingWithInsufficientFunds() {
        player.changeBalance(-100);
        assertNull(store.buyPlant(0, player));
    }
}
