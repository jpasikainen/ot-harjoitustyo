package harvestgame.core;

import harvestgame.core.*;
import harvestgame.core.Player;
import harvestgame.dao.StoreDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldTest {
    private static StoreDao store;
    private static Player player;
    private static Field field;

    private Plant testPlant = new Plant(0, "", 10, 0, 0);

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

        // Buy the plots used in the tests
        field.buyPlot(0);
        field.buyPlot(1);
    }

    @Test
    public void initializationCorrectly() {
        assertEquals(9, field.getFieldSize());
    }

    @Test
    public void plantingWorks() {
        field.plant(testPlant, 0);
        assertEquals(testPlant, field.getPlant(0));
    }

    @Test
    public void getPlantFromEmptyField() {
        assertNull(field.getPlant(0));
    }

    @Test
    public void isEmptyWorks() {
        assertTrue(field.isEmpty(0));
    }

    @Test
    public void removeValidPlant() {
        field.plant(testPlant, 0);
        field.plant(testPlant, 1);
        field.removePlant(0);
        assertTrue(field.isEmpty(0));
        assertFalse(field.isEmpty(1));
    }

    @Test
    public void removeInvalidPlant() {
        field.plant(testPlant, 1);
        field.removePlant(-1);
        assertFalse(field.isEmpty(1));
    }

    @Test
    public void harvestingWorks() {
        field.plant(testPlant, 0);
        field.harvest(0);
        // Starting money 100 - 20 + plant gives price * 2 = 20, = 120
        assertEquals(100, player.getBalance());
    }

    @Test
    public void fieldClearsCompletely() {
        field.plant(testPlant, 0);
        field.plant(testPlant, 1);
        field.clearField();
        assertTrue(field.isEmpty(0));
        assertTrue(field.isEmpty(1));
    }

    @Test
    public void buyPlot() {
        field.buyPlot(0);
        assertTrue(field.isBought(0));
    }
}
