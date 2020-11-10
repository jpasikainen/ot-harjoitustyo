/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

import harvestgame.database.Database;
import harvestgame.database.Store;
import harvestgame.ui.TextUI;

/**
 *
 * @author jpasikainen
 */

public class Game {
    private static Database db;
    private static Store store;
    private static TextUI ui;
    private static Player player;
    
    public static void main(String[] args) {
        gameInit();
        
        db.disconnect();
    }
    
    private static void gameInit() {
        player = new Player(100);
        db = new Database("jdbc:sqlite:database/Database.db");
        store = new Store(db);
        ui = new TextUI(store, player);
        ui.start();
    }
}
