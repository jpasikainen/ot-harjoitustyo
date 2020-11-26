package harvestgame.core;

import java.util.ArrayList;

/**
 * Manages the inventory and money
 */
public class Player {
    private int money;
    private ArrayList<Plant> inventory;

    // Constructor
    public Player(int money) {
        this.money = money;
        this.inventory = new ArrayList<>();
    }
    
    public int getBalance() {
        return money;
    }

    // Changes balance and checks if the amount is meant to add or subtract balance
    public void changeBalance(int amount) {
        if (amount > 0) {
            money += amount;
        } else if (getBalance() >= Math.abs(amount)) {
            money += amount;
        }
    }
    
    public void addItem(Plant plant) {
        if (plant != null) {
            Plant clone = new Plant(plant);
            inventory.add(clone);
        }
    }

    public void removeItem(Plant plant) {
        inventory.remove(plant);
    }

    public ArrayList<Plant> getInventory() {
        return inventory;
    }

    public boolean hasItem(Plant plant) {
        return inventory.contains(plant);
    }
}
