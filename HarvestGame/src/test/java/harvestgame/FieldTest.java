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
    int fieldSize;
    int money = 10;

    @Before
    public void setUp() {
        GameManager.gameInit(10, 3, 3);
        fieldSize = 9;
    }

    @After
    public void close() {
        GameManager.db.disconnect();
    }

    @Test
    public void plantInvalidPlant() {
        GameManager.field.plant(null, 0);
        assertEquals(true, GameManager.field.getPlant(0) == null);
    }

    // Create x amount of plants for testing
    private void plant(int x) {
        if (x > fieldSize) {
            x = fieldSize;
        }

        for (int i = 0; i < x; i++) {
            Plant plant = new Plant(i, Integer.toString(i), 0, 0, 0);
            GameManager.field.plant(plant, i);
        }
    }
}
