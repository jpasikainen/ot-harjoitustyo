/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame;

/**
 *
 * @author jpasikainen
 */

public class Game {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:database/Database.db";
        Database.connect(url);
        
        if (Database.databaseConnected())
            createPlants();
        
        Database.disconnect();
    }
    
    public static void createPlants() {
        for (Plant plant : Database.getAllPlants()) {
            System.out.println(plant);
        }
    }  
}
