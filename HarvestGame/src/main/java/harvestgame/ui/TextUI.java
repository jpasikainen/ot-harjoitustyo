/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.ui;

import harvestgame.database.Store;
import harvestgame.core.Player;

import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jpasikainen
 */
public class TextUI { 
    private final Scanner scanner;
    private final Map<String, String> mainCommands;
    private final Map<String, String> storeCommands;
    private final Store store;
    private final Player player;

    // Create new scanner for input
    public TextUI(Store store, Player player) {
        this.store = store;
        this.player = player;
        
        scanner = new Scanner(System.in);
        
        mainCommands = new TreeMap<>(); 
        mainCommands.put("0", "0 Quit");
        mainCommands.put("1", "1 Store");
        
        storeCommands = new TreeMap<>();
        storeCommands.put("0", "0 Return");
        storeCommands.put("1", "1 Buy");
        storeCommands.put("2", "2 Sell");
    }
    
    public void start() {
        clearScreen();

        while (true) {
            printInstructions(mainCommands);
            System.out.println("Input command:");
            String command = scanner.nextLine();
            System.out.println();
            
            switch (command.toLowerCase()) {
                case "0":
                    System.out.println("Quit");
                    System.exit(0);
                    break;
                case "1":
                    System.out.println("Entering the store");
                    activateStore();
                    break;
                default:
                    System.out.println("Command not found");
                    break;
            }
            clearScreen();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void printInstructions(Map commandsMap) {
        System.out.println("----------------");
        commandsMap.values().forEach(command -> {
            System.out.println(command);
        });
    }
    
    private void activateStore() {
        clearScreen();

        while (true) {
            printInstructions(storeCommands);
            System.out.println("Input command:");
            String command = scanner.nextLine();
            System.out.println();

            switch (command.toLowerCase()) {
                case "0":
                    return;
                case "1":
                    clearScreen();
                    storeListPlants();
                    System.out.println("Type the id of the plant you wish to buy");

                    // TODO: Crashes if not digit
                    int plantID = scanner.nextInt();
                    store.buyPlant(plantID, player);
                    break;
                default:
                    System.out.println("Command not found");
                    break;
            }
            clearScreen();
        }
    }
    
    private void storeListPlants() {
        int i = 0;
        for (String plant : store.listPlants()) {
            System.out.println("id: " + i + " " + plant);
            i++;
        }
    }
}
