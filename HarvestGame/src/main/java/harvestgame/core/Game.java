package harvestgame.core;

import harvestgame.ui.GUI;
import javafx.application.Application;

/**
 * Contains Main method of the application
 */
public class Game {
    /**
     * Starting point of the application
     * Initializes GameManager class and launches GUI
     * @param args
     */
    public static void main(String[] args) {
        GameManager.gameInit(100);
        Application.launch(GUI.class);
    }
}
