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
    Field field;
    int fieldSize;
    Player player;
    int money = 10;

    @Before
    public void setUp() {
        GameManager.gameInit(10, 3, 3);
        field = new Field(3, 3);
        player = new Player(money);
        fieldSize = 9;
    }

    @Test
    public void fieldInitWorks() {
        for (int i = 0; i < fieldSize; i++) {
            assertEquals(true, field.isPlotFree(i));
        }
        assertEquals(fieldSize, field.getFieldSize());
    }

    @Test
    public void plantInvalidPlant() {
        field.plant(null, 0);
        assertEquals(true, field.isPlotFree(0));
    }

    // Create x amount of plants for testing
    private void plant(int x) {
        if (x > fieldSize) {
            x = fieldSize;
        }

        for (int i = 0; i < x; i++) {
            Plant plant = new Plant(i, Integer.toString(i), 0, 0, 0);
            field.plant(plant, i);
        }
    }
}
