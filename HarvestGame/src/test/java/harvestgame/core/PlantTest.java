package harvestgame.core;

import harvestgame.core.*;
import harvestgame.core.Player;
import harvestgame.dao.StoreDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlantTest {
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
    public void getNameWorks() {
        Plant testPlant = new Plant(0, "test", 10, 0, 0);
        assertEquals("test", testPlant.getName());
    }

    @Test
    public void getPriceWorks() {
        Plant testPlant = new Plant(0, "", 10, 0, 0);
        assertEquals(10, testPlant.getPrice());
    }

    @Test
    public void getIdWorks() {
        Plant testPlant = new Plant(5, "", 10, 0, 0);
        assertEquals(5, testPlant.getId());
    }

    @Test
    public void timeReduces() {
        Plant testPlant = new Plant(0, "", 10, 0, 10);
        testPlant.reduceTime();
        assertEquals(9, testPlant.getTimeLeft());
    }

    @Test
    public void timeLeftDoesNotGoUnderZero() {
        Plant testPlant = new Plant(0, "", 10, 0, 0);
        testPlant.reduceTime();
        assertEquals(0, testPlant.getTimeLeft());
    }

    @Test
    public void plantDoesNotRequireWateringWhenPlanted() {
        Plant testPlant = new Plant(0, "test", 0, 10, 0);
        assertFalse(testPlant.requiresWatering());
    }

    @Test
    public void growingTimeInitializedCorrectly() {
        Plant testPlant = new Plant(0, "test", 10, 0, 10);
        assertEquals(10, testPlant.getGrowingTime());
    }
}
