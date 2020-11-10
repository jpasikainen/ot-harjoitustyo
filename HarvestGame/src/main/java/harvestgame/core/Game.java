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
    public static void main(String[] args) {
        Database db = new Database("jdbc:sqlite:database/Database.db");
        Store store = new Store(db);
        TextUI ui = new TextUI(store);
        ui.start();
        db.disconnect();
    }
}
