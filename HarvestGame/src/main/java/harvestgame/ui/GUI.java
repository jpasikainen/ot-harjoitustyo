package harvestgame.ui;

import harvestgame.core.Field;
import harvestgame.core.GameManager;
import harvestgame.dao.PlayerDao;
import harvestgame.dao.StoreDao;
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
    private static Label moneyLabel;
    private Map<Integer, Button> fieldButtons;

    private StoreDao store;
    private static PlayerDao player;
    private Field field;

    @Override
    public void start(Stage stage) throws Exception {
        initialize();

        root = new BorderPane();
        fieldButtons = new HashMap<>();
        createWorldView();

        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> GameManager.exitGame());
        stage.show();

        update();
    }

    private void initialize() {
        store = GameManager.getStore();
        player = GameManager.getPlayer();
        field = GameManager.getField();
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
                            if (!field.isEmpty(k)) {
                                if (!field.getPlant(k).requiresWatering()) {
                                    int timeLeft = field.getPlant(k).getTimeLeft();
                                    field.getPlant(k).reduceTime();
                                    if (timeLeft == 0) {
                                        v.setText("Harvest");
                                        v.setStyle("-fx-background-color: green;");
                                    } else {
                                        v.setText(timeLeft + "s");
                                    }
                                } else {
                                    v.setText("Water");
                                    v.setStyle("-fx-background-color: lightblue");
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
        root.getChildren().clear();
        moneyLabel = new Label();
        updateMoneyLabel();
        root.setTop(moneyLabel);
        root.setCenter(fieldGrid());
        root.setBottom(bottomView());
    }

    private void createStoreView(int index) {
        root.getChildren().clear();
        root.setTop(moneyLabel);
        root.setCenter(storePane(index));
    }

    public static void updateMoneyLabel() {
        String text = "Money: " + Integer.toString(player.getBalance());
        moneyLabel.setText(text);
    }

    private Button exitButton() {
        Button button = new Button("Exit Game");
        EventHandler<ActionEvent> buttonEvent = actionEvent -> GameManager.exitGame();
        button.setOnAction(buttonEvent);
        return button;
    }

    private HBox bottomView() {
        HBox hb = new HBox();
        Label label = new Label("Harvesting gives 2x buying price");
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> {
            player.resetData();
            updateMoneyLabel();
        });
        hb.getChildren().addAll(exitButton(), label, resetButton);
        return hb;
    }

    private GridPane fieldGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        int column = 0;
        int row = 0;

        for (int i = 0; i < field.getFieldSize(); i++) {
            if (fieldButtons.containsKey(i)) {
                grid.add(fieldButtons.get(i), column, row);
            } else {
                Button button = new Button("Plant");
                button.setPrefSize(200, 100);
                button.getStyleClass().add("plot");

                // Add button functionality
                int index = i; // Required for lambda expression
                EventHandler<ActionEvent> buttonEvent = actionEvent -> {
                    if (field.isEmpty(index)) {
                        createStoreView(index);
                    }
                    if (fieldButtons.get(index).getText() == "Harvest") {
                        fieldButtons.get(index).setStyle("-fx-background-color: sienna");
                        fieldButtons.get(index).setText("Plant");
                        field.harvest(index);
                        updateMoneyLabel();
                    } else if (fieldButtons.get(index).getText() == "Water") {
                        fieldButtons.get(index).setStyle("-fx-background-color: sienna");
                        field.getPlant(index).water();
                    }
                };
                button.setOnAction(buttonEvent);
                grid.add(button, column, row);
                fieldButtons.put(i, button);
            }

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        return grid;
    }

    private VBox storePane(int plotIndex) {
        VBox storeView = new VBox();
        Label storeLabel = new Label("Store");
        storeView.getChildren().add(storeLabel);

        VBox allItems = new VBox();
        store.getPlants().forEach(plant -> {
            HBox itemView = new HBox();
            String text = String.format(
                    "%s:\tGrowing Time %ds",
                    plant.getName(), plant.getGrowingTime()
            );
            Label plantLabel = new Label(text);
            Button buyButton = new Button("$" + plant.getPrice());

            if (player.getBalance() >= plant.getPrice()) {
                buyButton.getStyleClass().add("canBuy");
            } else {
                buyButton.getStyleClass().add("cantBuy");
            }

            EventHandler<ActionEvent> buttonEvent = actionEvent -> {
                if (player.getBalance() >= plant.getPrice()) {
                    field.plant(
                            store.buyPlant(plant.getId(), player),
                            plotIndex
                    );
                    updateMoneyLabel();
                    createWorldView();
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
            createWorldView();
        };
        returnButton.setOnAction(returnEvent);

        storeView.getChildren().addAll(itemListing, returnButton);

        return storeView;
    }
}
