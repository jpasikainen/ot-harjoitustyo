package harvestgame.ui;

import harvestgame.core.GameManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GUI extends Application {
    private StackPane root;

    @Override
    public void start(Stage stage) throws Exception {
        // Create layout
        root = new StackPane();
        changeScene(createMenu());

        // Create scene and change stage settings
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> GameManager.exitGame());
        stage.show();
    }

    // Remove previous scene and add the new one
    private void changeScene(Pane pane) {
        root.getChildren().clear();
        root.getChildren().add(pane);
    }

    // Main menu
    private VBox createMenu() {
        // Create buttons
        Button buttonExit = new Button("Exit");
        Button buttonStore = new Button("Store");
        Button buttonInventory = new Button("Inventory");

        // Add button events
        // Exit
        EventHandler<ActionEvent> buttonExitEvent = actionEvent -> GameManager.exitGame();
        buttonExit.setOnAction(buttonExitEvent);
        // Activate Store
        EventHandler<ActionEvent> buttonStoreEvent = actionEvent -> changeScene(createStorePane());
        buttonStore.setOnAction(buttonStoreEvent);
        // Open inventory
        EventHandler<ActionEvent> buttonInventoryEvent = actionEvent -> changeScene(createInventoryPane());
        buttonInventory.setOnAction(buttonInventoryEvent);

        // Create layout
        VBox vbox = new VBox();

        // Add components to the layout
        vbox.getChildren().addAll(buttonStore, buttonInventory, buttonExit);

        return vbox;
    }

    // Store
    // Layout: VBox with two items, second item is HBox
    private VBox createStorePane() {
        // Create layout
        VBox vbox = new VBox();
        HBox hbox = new HBox();

        Button buttonReturn = new Button("Return");

        // Button events
        EventHandler<ActionEvent> buttonReturnEvent = actionEvent -> changeScene(createMenu());
        buttonReturn.setOnAction(buttonReturnEvent);

        // List all items
        VBox vboxPlants = new VBox();
        GameManager.store.listPlants().forEach(plant -> {
            Label plantLabel = new Label(plant);

            Button buyButton = new Button("Buy");
            EventHandler<ActionEvent> buttonBuyEvent = actionEvent ->
                    GameManager.store.buyPlant(Integer.parseInt(plant.split(" ")[0]), GameManager.player);
            buyButton.setOnAction(buttonBuyEvent);

            HBox plantBox = new HBox(25);
            plantBox.getChildren().addAll(plantLabel, buyButton);
            vboxPlants.getChildren().add(plantBox);
        });

        hbox.getChildren().addAll(buttonReturn);
        vbox.getChildren().addAll(vboxPlants, hbox);

        return vbox;
    }

    private VBox createInventoryPane() {
        VBox vbox = new VBox();

        Button buttonReturn = new Button("Return");
        // Button events
        EventHandler<ActionEvent> buttonReturnEvent = actionEvent -> changeScene(createMenu());
        buttonReturn.setOnAction(buttonReturnEvent);

        VBox itemBox = new VBox();
        GameManager.player.listInventory().forEach(item -> {
            Label itemLabel = new Label(item);
            itemBox.getChildren().add(itemLabel);
        });

        vbox.getChildren().addAll(itemBox, buttonReturn);

        return vbox;
    }

    private VBox createPlantingPane() {
        return null;
    }
}
