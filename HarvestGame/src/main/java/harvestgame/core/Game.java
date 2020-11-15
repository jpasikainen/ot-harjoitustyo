/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

import harvestgame.ui.GUI;
import javafx.application.Application;

/**
 *
 * @author jpasikainen
 */

public class Game {
    public static void main(String[] args) {
        GameManager.gameInit();
        Application.launch(GUI.class);
    }
}
