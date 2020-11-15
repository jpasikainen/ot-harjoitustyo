/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.ui;

import harvestgame.core.*;

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
    private final Field field;

    // Create new scanner for input
    public TextUI(Store store, Player player, Field field) {
        this.store = store;
        this.player = player;
        this.field = field;
        
        scanner = new Scanner(System.in);
        
        mainCommands = new TreeMap<>(); 
        mainCommands.put("0", "0 Quit");
        mainCommands.put("1", "1 Store");
        mainCommands.put("2", "2 Inventory");
        mainCommands.put("3", "3 Plant");
        mainCommands.put("4", "4 View field");
        mainCommands.put("5", "5 Water a plant");
        mainCommands.put("6", "6 Harvest ripe plants");
        
        storeCommands = new TreeMap<>();
        storeCommands.put("0", "0 Return");
        storeCommands.put("1", "1 Buy");
    }

    public void start() {
        while (true) {
            printInstructions(mainCommands);
            System.out.println("Input command:");
            String command = scanner.nextLine();
            System.out.println();

            switch (command.toLowerCase()) {
                case "0":
                    GameManager.exitGame();
                    break;
                case "1":
                    System.out.println("Entering the store");
                    activateStore();
                    break;
                case "2":
                    System.out.println("Inventory:");
                    // TODO: Print different message if the inventory is empty
                    player.getInventory().forEach(plant -> System.out.println(plant.toString()));
                    break;
                case "3":
                    player.getInventory().forEach(plant -> System.out.println(plant.toString()));
                    System.out.println("Type the id of the plant you wish to plant");
                    int plantID = scanner.nextInt();
                    field.plant(player.getItem(plantID));
                    break;
                case "4":
                    field.viewField();
                    break;
                case "5":
                    field.viewField();
                    System.out.println("Type the id of the plant you wish to water");
                    plantID = scanner.nextInt();
                    field.water(field.getPlant(plantID));
                    break;
                case "6":
                    field.harvest();
                    break;
                default:
                    System.out.println("Command not found");
                    break;
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
                case "0":
                    return;
                case "1":
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
        }
    }

    private void storeListPlants() {
        int i = 0;
        for (String plant : store.listPlants()) {
            System.out.println(i + ": " + plant);
            i++;
        }
    }
}
