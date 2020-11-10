/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.database;

import harvestgame.Plant;

import java.util.ArrayList;

/**
 *
 * @author jpasikainen
 */
public class Store {
    ArrayList<Plant> plants;
    private Database db;
    
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
        
        plants.forEach(plant -> {
            plantList.add(plant.toString());
        });
        
        return plantList;
    }
}
