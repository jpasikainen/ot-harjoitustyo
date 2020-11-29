package harvestgame.core;

import harvestgame.database.Database;
import java.util.ArrayList;

/**
 * Store manages the item data from the database
 * and provides methods to access it
 */
public class Store {
    private ArrayList<Plant> plants;
    private Database db;

    // Constructor
    public Store(Database db) {
        this.db = db;
        plants = db.getAllPlants();
    }
    
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    // Generates a String list of plants
    // Good for classes that don't have Plant imported
    public ArrayList<String> listPlants() {
        ArrayList<String> plantList = new ArrayList<>();
        plants.forEach(plant -> plantList.add(plant.toString()));
        
        return plantList;
    }

    public ArrayList<Plant> getAllPlants() {
        return plants;
    }

    // Clone the plant so the values in store don't get updated
    public Plant buyPlant(int plantID, Player player) {
        if (plants.size() < plantID) {
            return null;
        }

        int price = plants.get(plantID).getPrice();

        if (price > player.getBalance()) {
            System.out.println("Not enough money");
            return null;
        } else {
            player.changeBalance(-price);
            //player.addItem(plant);
            System.out.println("New item bought");
            return new Plant(plants.get(plantID));      // Pass a clone
        }
    }

    public void sellPlant(int plantID, Player player) {
        if (plants.size() < plantID) {
            return;
        }

        if (player.hasItem(plants.get(plantID))) {
            int price = (int) (0.8 * plants.get(plantID).getPrice());
            player.changeBalance(price);
            player.removeItem(plants.get(plantID));
        } else {
            System.out.println("Item not in inventory");
        }
    }
}
