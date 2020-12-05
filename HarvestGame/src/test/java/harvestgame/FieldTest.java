package harvestgame;

import harvestgame.core.*;
import harvestgame.dao.PlayerDao;
import harvestgame.dao.StoreDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldTest {
    private static StoreDao store;
    private static PlayerDao player;
    private static Field field;

    private int money = 10;
    private Plant testPlant = new Plant(0, "", 10, 0, 0);

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
        // Starting money 10 + plant gives price * 2 = 20, = 30
        assertEquals(30, player.getBalance());
    }
}
