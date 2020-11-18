package harvestgame.ui;

import harvestgame.core.GameManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class GUI extends Application {
    private StackPane root;

    @Override
    public void start(Stage stage) throws Exception {
        // Create layout
        root = new StackPane();
        changeScene(createWorld());
        File styles = new File("styles.css");
        System.out.println(styles.getAbsolutePath());
        styles = new File("/styles.css");
        System.out.println(styles.getAbsolutePath());
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

    // World view
    private HBox createWorld() {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(createMenuPane(), createFieldPane());
        return hbox;
    }

    // Main menu
    private VBox createMenuPane() {
        // Create buttons
        Button buttonExit = new Button("Exit");
        Button buttonStore = new Button("Store");
        Button buttonInventory = new Button("Inventory");

        buttonExit.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        buttonStore.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        buttonInventory.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

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
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);

        // Add components to the layout
        vbox.getChildren().addAll(buttonStore, buttonInventory, buttonExit);

        return vbox;
    }

    // Field
    private HBox createFieldPane() {
        HBox field = new HBox();
        TilePane grid = new TilePane();
        grid.setPrefColumns(3);
        grid.setPrefRows(3);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        VBox inventory = createInventoryPane();

        // Create plot slots
        for (int i = 0; i < 9; i++) {
            Button button = new Button(String.format("Plot %d", i));
            EventHandler<ActionEvent> buttonEvent = actionEvent -> {
                if (!field.getChildren().contains(inventory))
                    field.getChildren().add(inventory);
            };
            button.setOnAction(buttonEvent);
            grid.getChildren().add(button);
        }
        field.getChildren().add(0, grid);

        return field;
    }

    // Store
    private VBox createStorePane() {
        // Create layout
        VBox vbox = new VBox();
        HBox hbox = new HBox();

        Button buttonReturn = new Button("Return");

        // Button events
        EventHandler<ActionEvent> buttonReturnEvent = actionEvent -> changeScene(createWorld());
        buttonReturn.setOnAction(buttonReturnEvent);

        // Money label
        Label moneyLabel = new Label(String.format("Money: %d", GameManager.player.getBalance()));

        // List all items
        VBox vboxPlants = new VBox();
        GameManager.store.listPlants().forEach(plant -> {
            Label plantLabel = new Label(plant);

            Button buyButton = new Button("Buy");
            EventHandler<ActionEvent> buttonBuyEvent = actionEvent -> {
                // Split the id from the plant
                GameManager.store.buyPlant(Integer.parseInt(plant.split(" ")[0]), GameManager.player);
                moneyLabel.setText(String.format("Money: %d", GameManager.player.getBalance()));
            };
            buyButton.setOnAction(buttonBuyEvent);

            HBox plantBox = new HBox(25);
            plantBox.getChildren().addAll(plantLabel, buyButton);
            vboxPlants.getChildren().add(plantBox);
        });


        hbox.getChildren().addAll(buttonReturn);
        vbox.getChildren().addAll(moneyLabel, vboxPlants, hbox);

        return vbox;
    }

    private VBox createInventoryPane() {
        VBox vbox = new VBox();

        Button buttonReturn = new Button("Return");
        // Button events
        EventHandler<ActionEvent> buttonReturnEvent = actionEvent -> changeScene(createWorld());
        buttonReturn.setOnAction(buttonReturnEvent);

        VBox itemBox = new VBox();
        GameManager.player.listInventory().forEach(item -> {
            Label itemLabel = new Label(item);
            Button plantButton = new Button("Plant");
            EventHandler<ActionEvent> button = actionEvent -> System.out.println();

            HBox itemInfoBox = new HBox();
            itemInfoBox.getChildren().addAll(itemLabel, plantButton);
            itemBox.getChildren().add(itemInfoBox);
        });
        if (itemBox.getChildren().isEmpty())
            itemBox.getChildren().add(new Label("No items"));

        vbox.getChildren().addAll(itemBox, buttonReturn);

        return vbox;
    }

    private VBox createPlantingPane() {
        return null;
    }
}
