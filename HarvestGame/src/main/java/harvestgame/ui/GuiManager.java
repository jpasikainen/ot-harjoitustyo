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
    public static ArrayList<Integer> idleIncome = new ArrayList<>();
    private static Stage stage;
    private static Label moneyLabel;

    public static void setMoneyLabel(Label lb) {
        moneyLabel = lb;
    }

    private static void updateMoneyLabel() {
        moneyLabel.setText("$" + GameManager.getPlayer().getBalance());
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/view.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setOnCloseRequest(e -> GameManager.exitGame());
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
                // Update field labels
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

                // Idle income
                idleIncome.forEach(i -> GameManager.getPlayer().changeBalance(i));
                updateMoneyLabel();

            }
        });
    }
}
