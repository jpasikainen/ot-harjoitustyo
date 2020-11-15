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
}
