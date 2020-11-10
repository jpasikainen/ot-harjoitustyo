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

/**
 *
 * @author jpasikainen
 */
public class TextUI { 
    private final Scanner scanner;
    private final Map<String, String> baseCommands;
    private final Map<String, String> storeCommands;
    private final Store store;
    
    // Create new scanner for input
    public TextUI(Store store) {
        this.store = store;
        scanner = new Scanner(System.in);
        
        baseCommands = new TreeMap<>(); 
        baseCommands.put("q", "q Quit");
        baseCommands.put("1", "1 Store");
        
        storeCommands = new TreeMap<>();
        storeCommands.put("q", "q Return");
        storeCommands.put("b", "b Buy");
        storeCommands.put("s", "s Sell");
    }
    
    public void start() {
        while (true) {
            printInstructions(baseCommands);
            System.out.println("Input command:");
            String command = scanner.nextLine();
            System.out.println();
            
            switch (command.toLowerCase()) {
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
        }
    }
    
    private void printInstructions(Map commandsMap) {
        System.out.println("----------------");
        commandsMap.values().forEach(command -> {
            System.out.println(command);
        });
    }
    
    private void activateStore() {
        while (true) {
            printInstructions(storeCommands);
            System.out.println("Input command:");
            String command = scanner.nextLine();
            System.out.println();

            switch (command.toLowerCase()) {
                case "q":
                    return;
                case "b":
                    storeListPlants();
                    break;
            }
        }
    }
    
    private void storeListPlants() {
        int i = 0;
        for (String plant : store.listPlants()) {
            System.out.println(i + " " + plant);
            i++;
        }
    }
}
