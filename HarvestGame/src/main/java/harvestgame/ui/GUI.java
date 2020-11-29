package harvestgame.ui;

import harvestgame.core.GameManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class GUI extends Application {
    private BorderPane root;
    private Label moneyLabel;
    private Map<Integer, Button> fieldButtons;

    @Override
    public void start(Stage stage) throws Exception {
        root = new BorderPane();
        fieldButtons = new HashMap<>();
        GameManager.gui = this;
        createWorldView();

        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> GameManager.exitGame());
        stage.show();

        update();
    }

    // Ticks every seconds
    private void update() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        fieldButtons.forEach((k, v) -> {
                            if (!GameManager.field.isEmpty(k)) {
                                int timeLeft = GameManager.field.getPlant(k).getTimeLeft();
                                GameManager.field.getPlant(k).reduceTime();
                                if (timeLeft == 0) {
                                    v.setText("Harvest");
                                } else {
                                    v.setText(timeLeft + "s");
                                }
                            }
                        });
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1000l);
    }

    private void createWorldView() {
        moneyLabel = new Label();
        updateMoneyLabel();
        root.setTop(moneyLabel);
        root.setCenter(fieldGrid());
        root.setBottom(exitButton());
    }

    public void updateMoneyLabel() {
        String text = "Money: " + Integer.toString(GameManager.player.getBalance());
        moneyLabel.setText(text);
    }

    private Button exitButton() {
        Button button = new Button("Exit Game");
        EventHandler<ActionEvent> buttonEvent = actionEvent -> GameManager.exitGame();
        button.setOnAction(buttonEvent);
        return button;
    }

    private GridPane fieldGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int column = 0;
        int row = 0;

        for (int i = 0; i < GameManager.field.getFieldSize(); i++) {
            String buttonText = GameManager.field.isEmpty(i) ? "Plant" : Integer.toString(GameManager.field.getPlant(i).getTimeLeft()) + "s";
            Button button = new Button(buttonText);
            button.setPrefSize(100, 100);
            button.getStyleClass().add("plot");

            // Add button functionality
            int index = i; // Required for lambda expression
            EventHandler<ActionEvent> buttonEvent = actionEvent -> {
                if (GameManager.field.isEmpty(index)) {
                    openStore(index);
                }
                if (fieldButtons.get(index).getText() == "Harvest") {
                    fieldButtons.get(index).setText("Plant");
                    GameManager.field.harvest(index);
                    updateMoneyLabel();
                }
            };
            button.setOnAction(buttonEvent);
            grid.add(button, column, row);
            fieldButtons.put(i, button);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        return grid;
    }

    public void updateFieldButton(int index, String text) {
        fieldButtons.get(index).setText(text);
    }

    private void openStore(int plotIndex) {
        VBox storeView = new VBox();
        Label storeLabel = new Label("Store");
        storeView.getChildren().add(storeLabel);

        VBox allItems = new VBox();
        GameManager.store.getAllPlants().forEach(plant -> {
            HBox itemView = new HBox();
            String text = String.format(
                    "%s:\tGrowing Time %ds",
                    plant.getName(), plant.getGrowingTime()
            );
            Label plantLabel = new Label(text);
            Button buyButton = new Button("$" + plant.getPrice());

            if (GameManager.player.getBalance() >= plant.getPrice()) {
                buyButton.getStyleClass().add("canBuy");
            } else {
                buyButton.getStyleClass().add("cantBuy");
            }

            EventHandler<ActionEvent> buttonEvent = actionEvent -> {
                if (GameManager.player.getBalance() >= plant.getPrice()) {
                    GameManager.field.plant(
                            GameManager.store.buyPlant(plant.getId(), GameManager.player),
                            plotIndex
                    );
                    updateMoneyLabel();
                    root.setCenter(fieldGrid());    // Change to field view
                }
            };

            buyButton.setOnAction(buttonEvent);
            itemView.getChildren().addAll(plantLabel, buyButton);
            allItems.getChildren().add(itemView);
        });

        ScrollPane itemListing = new ScrollPane();
        itemListing.setContent(allItems);

        Button returnButton = new Button("Return");
        EventHandler<ActionEvent> returnEvent = actionEvent -> {
            root.setCenter(fieldGrid());
        };
        returnButton.setOnAction(returnEvent);

        storeView.getChildren().addAll(itemListing, returnButton);
        root.setCenter(storeView);
    }
}
