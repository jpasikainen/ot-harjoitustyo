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

    /**
     * Constructor
     *
     * @param db {@link Database} where the store gets data from
     */
    public Store(Database db) {
        this.db = db;
        plants = db.getAllPlants();
    }

    public ArrayList<Plant> getAllPlants() {
        return plants;
    }

    /**
     * Buy plant and change balance
     * Clones the plant so the values in store don't get updated.
     *
     * @param plantID id of the plant to buy
     * @return If enough money, returns the {@link Plant} with given id
     */
    public Plant buyPlant(int plantID) {
        Player player = GameManager.getPlayer();
        if (plants.size() < plantID) {
            return null;
        }

        int price = plants.get(plantID).getPrice();

        if (price > player.getBalance()) {
            return null;
        } else {
            player.changeBalance(-price);
            return new Plant(plants.get(plantID));      // Pass a clone, use secondary constructor
        }
    }
}
