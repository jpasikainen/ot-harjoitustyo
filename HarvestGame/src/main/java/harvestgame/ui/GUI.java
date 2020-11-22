package harvestgame.ui;

import harvestgame.core.GameManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Tooltip;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {
    private StackPane root;

    @Override
    public void start(Stage stage) throws Exception {
        // Create layout
        root = new StackPane();
        changeScene(createWorld());

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
        // Day counter
        Label dayLabel = new Label(String.format("Day: %d", GameManager.day));

        // Create buttons
        Button buttonExit = new Button("Exit");
        Button buttonStore = new Button("Store");
        Button buttonInventory = new Button("Inventory");
        Button buttonNextDay = new Button("Next day");

        // Add button events
        // Exit
        EventHandler<ActionEvent> buttonExitEvent = actionEvent -> GameManager.exitGame();
        buttonExit.setOnAction(buttonExitEvent);
        // Activate Store
        EventHandler<ActionEvent> buttonStoreEvent = actionEvent -> changeScene(createStorePane());
        buttonStore.setOnAction(buttonStoreEvent);
        // Open inventory
        EventHandler<ActionEvent> buttonInventoryEvent = actionEvent -> changeScene(createInventoryPane(null));
        buttonInventory.setOnAction(buttonInventoryEvent);
        // Next day
        EventHandler<ActionEvent> buttonNextDayEvent = actionEvent -> {
            GameManager.nextDay();
            changeScene(createWorld()); // Refresh the view
        };
        buttonNextDay.setOnAction(buttonNextDayEvent);

        // Create layout
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);


        // Add components to the layout
        vbox.getChildren().addAll(dayLabel, buttonNextDay ,buttonStore, buttonInventory, buttonExit);

        return vbox;
    }

    // Field
    private HBox createFieldPane() {
        HBox field = new HBox();
        TilePane grid = new TilePane();
        grid.setPrefColumns(GameManager.field.getFieldWidth());
        grid.setPrefRows(GameManager.field.getFieldHeight());
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Create plot slots
        for (int i = 0; i < GameManager.field.getFieldSize(); i++) {
            String buttonText = GameManager.field.isPlotFree(i) ? "Empty" : GameManager.field.getPlant(i).getName();

            Button button = new Button(buttonText);
            int index = i; // Required for lambda expression
            EventHandler<ActionEvent> buttonEvent = actionEvent -> {
                if (GameManager.field.isPlotFree(index)) {
                    changeScene(createInventoryPane(index));
                }
            };
            button.setOnAction(buttonEvent);

            String tip = GameManager.field.isPlotFree(i) ? "Click to plant" : GameManager.field.getPlant(i).getName();
            Tooltip tooltip = new Tooltip(tip);
            button.setTooltip(tooltip);

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

    // plotIndex is optional
    private VBox createInventoryPane(int... plotIndex) {
        VBox vbox = new VBox();

        Button buttonReturn = new Button("Return");
        // Button events
        EventHandler<ActionEvent> buttonReturnEvent = actionEvent -> changeScene(createWorld());
        buttonReturn.setOnAction(buttonReturnEvent);

        VBox itemBox = new VBox();
        GameManager.player.getInventory().forEach(item -> {
            HBox itemInfoBox = new HBox();
            Label itemLabel = new Label(item.toString());

            if (plotIndex != null) {
                Button plantButton = new Button("Plant");
                EventHandler<ActionEvent> buttonAction = actionEvent -> {
                    GameManager.field.plant(item, plotIndex[0]);
                    changeScene(createWorld());
                };
                plantButton.setOnAction(buttonAction);
                itemInfoBox.getChildren().addAll(itemLabel, plantButton);
            } else {
                itemInfoBox.getChildren().add(itemLabel);
            }

            itemBox.getChildren().add(itemInfoBox);
        });
        if (itemBox.getChildren().isEmpty())
            itemBox.getChildren().add(new Label("No items"));

        vbox.getChildren().addAll(itemBox, buttonReturn);

        return vbox;
    }
}
