/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.ui;

import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jpasikainen
 */
public class TextUI {
    private final Scanner scanner;
    private final Map<String, String> commands;
    
    // Create new scanner for input
    public TextUI() {
        scanner = new Scanner(System.in);
        commands = new TreeMap<>();
        
        commands.put("q", "q quit");
        commands.put("1", "1 Buy a plant");
        commands.put("2", "2 Plant a plant");
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
                    System.out.println("Command 1");
                    break;
                case "2":
                    System.out.println("Command 2");
                    break;
                //default breaks Netbean's autocomplete
                //default:
            }
            
            System.out.println("Press any key to continue....");
            scanner.nextLine();
        }
    }
    
    public void printInstructions() {
        System.out.println("----------------");
        commands.values().forEach(command -> {
            System.out.println(command);
        });
    }
}
