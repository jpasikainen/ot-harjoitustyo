package harvestgame;

import harvestgame.core.Field;
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
    int fieldSize = 1;
    Player player;
    int money = 10;

    @Before
    public void setUp() {
        field = new Field(fieldSize);
        player = new Player(money);
    }

    @Test
    public void fieldInitWorks() {
        assertEquals(fieldSize, field.getFieldSize());
        assertEquals(0, field.getPlants().size());
    }

    @Test
    public void plantingWorksWhenSpaceInField() {
        Plant plant = new Plant(0, "a", 0, 0, 0);
        field.plant(plant);
        assertEquals(fieldSize, field.getPlants().size());
    }

    @Test
    public void plantingWhenNoSpaceLeftInField() {
        plant(2);
        assertEquals(fieldSize, field.getPlants().size());
    }

    private void plant(int x) {
        for (int i = 0; i < x; i++) {
            Plant plant = new Plant(i, "a", 0, 0, 0);
            field.plant(plant);
        }
    }
}
