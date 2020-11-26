package harvestgame.ui;

import harvestgame.core.Game;
import harvestgame.core.GameManager;
import harvestgame.core.Plant;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * TODO: Create local variables for
 * Graphical User Interface, GUI
 * Excluded from tests and style check
 */
public class GUI extends Application {
    private StackPane root;

    @Override
    public void start(Stage stage) throws Exception {
        // Create layout
        root = new StackPane();
        changeScene(createWorld());

        // Create scene and change stage settings
        Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
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
        Button buttonHelp = new Button("Help");

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
        // Help
        EventHandler<ActionEvent> buttonHelpEvent = actionEvent -> changeScene(createHelpPane());
        buttonHelp.setOnAction(buttonHelpEvent);

        // Create layout
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);


        // Add components to the layout
        vbox.getChildren().addAll(dayLabel, buttonNextDay, buttonStore, buttonInventory, buttonHelp, buttonExit);

        return vbox;
    }

    // Field
    private TilePane createFieldPane() {
        TilePane field = new TilePane();
        field.setPrefColumns(3);
        field.setPrefRows(3);
        field.setPadding(new Insets(10));
        field.setHgap(10);
        field.setVgap(10);

        // Create plot slots
        for (int i = 0; i < GameManager.field.getFieldSize(); i++) {
            // Initialize button
            String buttonText = GameManager.field.getPlant(i) == null ? "Empty" : GameManager.field.getPlant(i).getName();
            Button button = new Button(buttonText);
            button.getStyleClass().add("plot");

            // Add button functionality
            int index = i; // Required for lambda expression
            EventHandler<ActionEvent> buttonEvent = actionEvent -> {
                // If empty -> go to inventory, else -> water / harvest
                if (GameManager.field.getPlant(index) == null) {
                    changeScene(createInventoryPane(index));
                } else {
                    if (GameManager.field.canHarvestPlant(index)) {
                        GameManager.player.addItem(GameManager.field.harvestPlant(index));
                        changeScene(createWorld()); // Refresh view
                    } else {
                        GameManager.field.waterPlant(index);
                    }
                }
            };
            button.setOnAction(buttonEvent);

            // Set tooltip
            String tip;
            if (GameManager.field.getPlant(index) == null) {
                tip = "Click to plant";
            } else {
                if (GameManager.field.getPlant(index).canHarvest()) {
                    tip = "Click to harvest";
                } else {
                    tip = "Click to water";
                }
            }

            Tooltip tooltip = new Tooltip(tip);
            tooltip.setShowDelay(Duration.seconds(0));
            button.setTooltip(tooltip);

            field.getChildren().add(button);
        }

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
        for (Plant item : GameManager.player.getItems()) {
            HBox itemInfoBox = new HBox();
            Label itemLabel = new Label(item.toString());

            // Enable planting mechanics
            if (plotIndex != null) {
                Button plantButton = new Button("Plant");
                EventHandler<ActionEvent> buttonAction = actionEvent -> {
                    GameManager.field.plant(item, plotIndex[0]);
                    GameManager.player.removeItem(item);
                    changeScene(createWorld());
                };
                plantButton.setOnAction(buttonAction);
                itemInfoBox.getChildren().addAll(itemLabel, plantButton);
            } else {
                itemInfoBox.getChildren().add(itemLabel);
            }

            itemBox.getChildren().add(itemInfoBox);
        }
        if (itemBox.getChildren().isEmpty())
            itemBox.getChildren().add(new Label("Use Store to buy plants"));

        ScrollPane sp = new ScrollPane();
        sp.setContent(itemBox);
        vbox.getChildren().addAll(sp, buttonReturn);

        return vbox;
    }

    private VBox createHelpPane() {
        VBox vbox = new VBox();

        Label label = new Label("1. Open the Store and buy a plant\n" +
                "2. Go to the main view and click on Empty plot\n" +
                "3. Click Plant next to the plant description\n" +
                "4. Water plants when needed and click Next Day to proceed\n" +
                "5. After x days you can harvest the plant");

        Button button = new Button("Return");
        button.setOnAction(ActionEvent -> changeScene(createWorld()));

        vbox.getChildren().addAll(label, button);

        return vbox;
    }
}
