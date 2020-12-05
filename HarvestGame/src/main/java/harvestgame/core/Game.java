package harvestgame.core;

import harvestgame.ui.GUI;
import harvestgame.ui.GUI2;
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
        GameManager.gameInit();
        Application.launch(GUI.class);
    }
}
