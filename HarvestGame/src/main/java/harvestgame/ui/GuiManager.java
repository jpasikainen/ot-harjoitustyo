package harvestgame.ui;

import harvestgame.core.GameManager;
import harvestgame.core.Plant;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class GuiManager extends Application {
    public static Button currentPlot;
    public static Map<Integer, Button> activePlots = new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        update();
    }

    @Override
    public void init() {

    }

    private void update() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
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
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000l);
    }
}
