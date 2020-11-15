/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

import harvestgame.core.Plant;
import harvestgame.core.Player;
import harvestgame.database.Database;

import java.util.ArrayList;

/**
 *
 * @author jpasikainen
 */
public class Store {
    ArrayList<Plant> plants;
    private Database db;
    private Plant selectedPlant;

    public Store(Database db) {
        this.db = db;
        plants = db.getAllPlants();
    }
    
    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void selectPlant(Plant plant) { selectedPlant = plant; }

    // Generates a String list of plants
    // Good for classes that don't have Plant imported
    public ArrayList<String> listPlants() {
        ArrayList<String> plantList = new ArrayList<>();
        plants.forEach(plant -> plantList.add(plant.toString()));
        
        return plantList;
    }
    
    public void buyPlant(int plantID, Player player) {
        if (plants.size() < plantID)
            return;

        int price = plants.get(plantID).getPrice();
        
        // TODO: Move the check to Player class
        if (price > player.getBalance())
            System.out.println("Not enough money");
        else {
            player.changeBalance(price);
            player.addItem(plants.get(plantID));
            System.out.println("New item bought");
        }
    }
}
