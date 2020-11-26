package harvestgame.core;

import java.util.ArrayList;

/**
 * Manages the inventory and money
 */
public class Player {
    private int money;
    private ArrayList<Plant> plants;

    // Constructor
    public Player(int money) {
        this.money = money;
        plants = new ArrayList<>();
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

    // Add plant, make new methods for different item types
    public void addItem(Plant plant) {
        if (plant != null) {
            plants.add(plant);
        }
    }

    public void removeItem(Plant plant) {
        plants.remove(plant);
    }

    public ArrayList<Plant> getItems() {
        return plants;
    }
}
