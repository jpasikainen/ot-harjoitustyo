package harvestgame.ui;

import harvestgame.core.GameManager;
import harvestgame.core.Plant;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class GuiManager extends Application {
    public static Button currentPlot;
    public static Map<Integer, Button> activePlots = new HashMap<>();
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
        GameManager.start();
    }

    public static void displayMessage(String message) {
        Popup pp = new Popup();
        Label label = new Label(message);
        label.setStyle(" -fx-background-color: white;");
        pp.getContent().add(label);
        pp.setAutoHide(true);
        pp.show(stage);
    }

    public static void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                activePlots.forEach((index, plot) -> {
                    Plant plant = GameManager.getField().getPlant(index);
                    if (plant == null) return;
                    if (plant.canHarvest()) {
                        plot.setText("Harvest");
                    } else if(plant.requiresWatering()) {
                        plot.setText("Water");
                    } else {
                        GameManager.getField().getPlant(index).reduceTime();
                        plot.setText(Integer.toString(GameManager.getField().getPlant(index).getTimeLeft()));
                    }
                });
            }
        });
    }
}
