package harvestgame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import harvestgame.core.Player;
import harvestgame.core.Plant;

public class PlayerTest {
    Player player = new Player(0);
    ArrayList<Plant> inventory = new ArrayList<>();
    Plant testPlant = new Plant(0, "", 0, 0, 0);

    @Before
    public void setUp() {
    }

    @Test
    public void playerMoneyInitializedCorrectly() {
        assertEquals(0, player.getBalance());
    }

    @Test
    public void playerInventoryInitializedCorrectly() {
        assertEquals(inventory, player.getInventory());
    }

    @Test
    public void changeBalance() {
        player.changeBalance(10);
        assertEquals(10, player.getBalance());
    }

    @Test
    public void changeBalanceToNegative() {
        player.changeBalance(-100);
        assertEquals(0, player.getBalance());
    }

    @Test
    public void decreaseBalance() {
        player.changeBalance(10);
        player.changeBalance(-5);
        assertEquals(5, player.getBalance());
    }

    @Test
    public void addPlantToInventory() {
        player.addItem(testPlant);
        assertEquals(testPlant, player.getInventory().get(0));
    }

    @Test
    public void addInvalidPlantToInventory() {
        player.addItem(null);
        assertEquals(new ArrayList<Plant>(), player.getInventory());
    }

    @Test
    public void removeItemFromInventoryWhenEmpty() {
        player.removeItem(testPlant);
        assertEquals(new ArrayList<Plant>(), player.getInventory());
    }

    @Test
    public void removeValidItemFromInventory() {
        player.addItem(testPlant);
        player.addItem(testPlant);
        player.removeItem(testPlant);
        assertEquals(testPlant, player.getInventory().get(0));
    }


}
