/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.ui;

import harvestgame.database.Store;

import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

/**
 *
 * @author jpasikainen
 */
public class TextUI {
    private Store store;
    
    private final Scanner scanner;
    private final Map<String, String> commands;
    
    // Create new scanner for input
    public TextUI(Store store) {
        this.store = store;
        
        scanner = new Scanner(System.in);
        commands = new TreeMap<>();
        
        commands.put("q", "q quit");
        commands.put("1", "1 Store");
    }
    
    public void start() {
        while (true) {
            printInstructions();
            System.out.println("Input command:");
            String command = scanner.nextLine();
            System.out.println();
            
            switch (command) {
                case "q":
                    System.out.println("Quit");
                    System.exit(0);
                    break;
                case "1":
                    System.out.println("Entering the store");
                    activateStore();
                    break;
                //default breaks Netbeans' autocomplete
                //default:
            }
            
            System.out.println("Press any key to continue....");
            scanner.nextLine();
        }
    }
    
    private void printInstructions() {
        System.out.println("----------------");
        commands.values().forEach(command -> {
            System.out.println(command);
        });
    }
    
    private void activateStore() {
        store.listPlants().forEach(plant -> {
            System.out.println(plant);
        });
    }
}
