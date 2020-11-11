/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

import harvestgame.database.Database;
import harvestgame.database.Store;
import harvestgame.ui.TextUI;

import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author jpasikainen
 */

public class Game {
    private static Database db;
    private static Store store;
    private static TextUI ui;
    private static Player player;
    private static Field field;

    private static boolean running = true;

    public static void main(String[] args) {
        gameInit();

        db.disconnect();
    }
    
    private static void gameInit() {
        player = new Player(100);
        db = new Database("jdbc:sqlite:database/Database.db");
        store = new Store(db);
        field = new Field(10, player);
        ui = new TextUI(store, player, field);
        ui.start();
    }
    
    public static Player getPlayer() {
        return player;
    }
}
