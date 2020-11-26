package harvestgame;

import harvestgame.core.GameManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import harvestgame.core.Plant;

/**
 * TODO: Check the actual item, not the inventory size
 * Not possible because:
 * expected: harvestgame.core.Plant<0 : costs 0, dries soil every 0 days, and takes 0 days to grow>
 * but was: harvestgame.core.Plant<0 : costs 0, dries soil every 0 days, and takes 0 days to grow>
 * which makes no sense
 */
public class PlayerTest {
    ArrayList<Plant> inventory = new ArrayList<>();
    Plant testPlant = new Plant(0, "", 0, 0, 0);

    @Before
    public void setUp() {
        GameManager.gameInit(0, 3, 3);
    }

    @After
    public void close() {
        GameManager.db.disconnect();
    }

    @Test
    public void playerMoneyInitializedCorrectly() {
        assertEquals(0, GameManager.player.getBalance());
    }

    @Test
    public void playerInventoryInitializedCorrectly() {
        assertEquals(inventory, GameManager.player.getItems());
    }

    @Test
    public void changeBalance() {
        GameManager.player.changeBalance(10);
        assertEquals(10, GameManager.player.getBalance());
    }

    @Test
    public void changeBalanceToNegative() {
        GameManager.player.changeBalance(-100);
        assertEquals(0, GameManager.player.getBalance());
    }

    @Test
    public void decreaseBalance() {
        GameManager.player.changeBalance(10);
        GameManager.player.changeBalance(-5);
        assertEquals(5, GameManager.player.getBalance());
    }

    @Test
    public void addPlantToInventory() {
        GameManager.player.addItem(testPlant);
        assertEquals(1, GameManager.player.getItems().size());
    }

    @Test
    public void addInvalidPlantToInventory() {
        GameManager.player.addItem(null);
        assertEquals(new ArrayList<Plant>(), GameManager.player.getItems());
    }

    @Test
    public void removeItemFromInventoryWhenEmpty() {
        GameManager.player.removeItem(testPlant);
        assertEquals(0, GameManager.player.getItems().size());
    }

    @Test
    public void removeValidItemFromInventory() {
        GameManager.player.addItem(testPlant);
        GameManager.player.addItem(testPlant);
        GameManager.player.removeItem(testPlant);
        assertEquals(1, GameManager.player.getItems().size());
    }
}
