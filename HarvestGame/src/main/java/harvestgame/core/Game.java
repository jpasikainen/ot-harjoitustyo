package harvestgame.core;

import harvestgame.ui.GUI;
import javafx.application.Application;

/**
 * Game is the starting point of the application.
 * It initializes the GameManager and launches the GUI
 */
public class Game {
    public static void main(String[] args) {
        GameManager.gameInit(100, 3, 3);
        Application.launch(GUI.class);
    }
}
