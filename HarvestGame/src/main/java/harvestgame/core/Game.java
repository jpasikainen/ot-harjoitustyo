package harvestgame.core;

import harvestgame.ui.GUI;
import harvestgame.ui.GuiManager;
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
        Application.launch(GuiManager.class);
    }
}
