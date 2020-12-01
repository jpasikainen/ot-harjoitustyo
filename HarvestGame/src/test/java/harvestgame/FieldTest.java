package harvestgame;

import harvestgame.core.Field;
import harvestgame.core.GameManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import harvestgame.core.Plant;
import harvestgame.core.Player;

public class FieldTest {
    int money = 10;
    Plant testPlant = new Plant(0, "", 10, 0, 0);

    @Before
    public void setUp() {
        GameManager.gameInit(money);
    }

    @After
    public void close() {
        GameManager.db.disconnect();
    }

    @Test
    public void initializationCorrectly() {
        assertEquals(9, GameManager.field.getFieldSize());
    }

    @Test
    public void plantingWorks() {
        GameManager.field.plant(testPlant, 0);
        assertEquals(testPlant, GameManager.field.getPlant(0));
    }

    @Test
    public void getPlantFromEmptyField() {
        assertEquals(null, GameManager.field.getPlant(0));
    }

    @Test
    public void isEmptyWorks() {
        assertEquals(true, GameManager.field.isEmpty(0));
    }

    @Test
    public void removeValidPlant() {
        GameManager.field.plant(testPlant, 0);
        GameManager.field.plant(testPlant, 1);
        GameManager.field.removePlant(0);
        assertEquals(true, GameManager.field.isEmpty(0));
        assertEquals(false, GameManager.field.isEmpty(1));
    }

    @Test
    public void removeInvalidPlant() {
        GameManager.field.plant(testPlant, 1);
        GameManager.field.removePlant(-1);
        assertEquals(false, GameManager.field.isEmpty(1));
    }

    @Test
    public void harvestingWorks() {
        GameManager.field.plant(testPlant, 0);
        GameManager.field.harvest(0);
        // Starting money 10 + plant gives price * 2 = 20, = 30
        assertEquals(30, GameManager.player.getBalance());
    }
}
