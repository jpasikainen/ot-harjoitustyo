package harvestgame.ui;

import harvestgame.core.GameManager;
import harvestgame.core.Plant;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class ActionManager {
    @FXML
    private Pane main;

    @FXML
    private Pane store;

    @FXML
    private Pane idle;

    @FXML
    private Pane scores;

    @FXML
    private VBox scorePane;

    @FXML
    private TextField nameField;

    @FXML
    private VBox itemContainer;

    @FXML
    private Label moneyLabel;

    @FXML
    private GridPane plots;

    @FXML
    private void initialize() {
        GuiManager.setMoneyLabel(moneyLabel);
        updateMoneyLabel();

        plots.getChildren().forEach(button -> {
            Button b = (Button)button;
            b.setText("Buy for $10");
        });
    }

    private void updateMoneyLabel() {
        moneyLabel.setText("$" + GameManager.getPlayer().getBalance());
    }

    @FXML
    protected void exitButton() {
        GameManager.exitGame();
    }

    @FXML
    protected void resetButton() {
        updateMoneyLabel();
        GameManager.getPlayer().resetData();
        GameManager.getField().clearField();
        plots.getChildren().forEach(button -> {
            Button b = (Button)button;
            b.setText("Buy for $10");
        });
        GuiManager.activePlots.clear();
        GuiManager.idleIncome.clear();
        idle.getChildren().forEach(node -> {
            if (node instanceof Button) {
                node.setDisable(false);
            }
        });
    }

    @FXML
    protected void fieldButton(ActionEvent event) {
        Button self = (Button)event.getSource();
        int id = Integer.parseInt(self.getId());
        GuiManager.currentPlot = self;

        if (self.getText() == "Harvest") {
            GameManager.getField().harvest(id);
            updateMoneyLabel();
            self.setText("Plant");
            GuiManager.activePlots.remove(self);
        } else if(self.getText() == "Water") {
            GameManager.getField().getPlant(id).water();
        } else if (GameManager.getField().isEmpty(id)){
            if (GameManager.getField().isBought(id)) {
                main.setVisible(false);
                store.setVisible(true);

                itemContainer.getChildren().clear();
                createStoreItems();
            } else {
                GameManager.getField().buyPlot(id);
                if (GameManager.getField().isBought(id)) {
                    updateMoneyLabel();
                    self.setText("Plant");
                }
            }
        }
    }

    @FXML
    protected void returnButton() {
        store.setVisible(false);
        main.setVisible(true);
    }

    private void createStoreItems() {
        if (GameManager.getStore().getPlants().isEmpty()) {
            GuiManager.displayMessage("Database not found!");
            return;
        }

        int i = 0;
        for (Plant plant : GameManager.getStore().getPlants()) {
            Button button = new Button("$" + plant.getPrice());
            if (GameManager.getPlayer().getBalance() < plant.getPrice()) {
                button.setDisable(true);
            }

            final int finalI = i;
            button.setOnAction(e -> buyButton(finalI));
            BorderPane container = new BorderPane();
            container.setLeft(new Label(plant.getName()));
            container.setCenter(new Label("Grows " + plant.getGrowingTime() + "s"));
            container.setRight(button);
            container.setPadding(new Insets(10));
            itemContainer.getChildren().add(container);
            i++;
        }
    }

    @FXML
    protected void buyButton(int id) {
        Plant plant = GameManager.getStore().buyPlant(id, GameManager.getPlayer());
        int index = Integer.parseInt(GuiManager.currentPlot.getId());
        GameManager.getField().plant(plant, index);

        GuiManager.currentPlot.setText(Integer.toString(plant.getGrowingTime()));
        GuiManager.activePlots.put(index, GuiManager.currentPlot);

        updateMoneyLabel();

        returnButton();
    }

    @FXML
    protected void changeIdleMain() {
        idle.setVisible(!idle.isVisible());
        main.setVisible(!main.isVisible());
    }

    @FXML
    protected void buyBeeHive(ActionEvent event) {
        if (GameManager.getPlayer().getBalance() >= 100) {
            GameManager.getPlayer().changeBalance(-100);
            GuiManager.idleIncome.add(1);
            Button self = (Button)event.getSource();
            self.setDisable(true);
        }
    }

    @FXML
    protected void openScores() {
        main.setVisible(false);
        scores.setVisible(true);

        createScoreListing();
    }

    private void createScoreListing() {
        if (GameManager.getScores().getHighScores().isEmpty()) {
            GuiManager.displayMessage("Database not found!");
            return;
        }

        scorePane.getChildren().clear();
        if (GameManager.getScores().getHighScores().isEmpty()) {
            scorePane.getChildren().add(new Label("No scores"));
        } else {
            GameManager.getScores().getHighScores().forEach((name, score) -> {
                scorePane.getChildren().add(new HBox(
                        new Label(name + ": "),
                        new Label("$" + score)
                ));
            });
        }
    }

    @FXML
    protected void returnFromScores() {
        scores.setVisible(false);
        main.setVisible(true);
    }

    @FXML
    protected void onKeyTyped(Event event) {
        int maxLength = 12;
        if (nameField.getText().length() > maxLength) {
            nameField.setText(nameField.getText().substring(0, maxLength));
            GuiManager.displayMessage("Name too long!");
        }
        if (nameField.getText().contains("\"")) {
            nameField.setText(nameField.getText().substring(0, nameField.getText().length()-1));
            GuiManager.displayMessage("Invalid character!");
        }
    }

    @FXML
    protected void submitScore() {
        if (nameField.getText().length() > 0) {
            if (GameManager.getScores().getHighScores().containsKey(nameField.getText())) {
                GuiManager.displayMessage("Choose another name!");
            } else {
                GameManager.getScores().writeHighScore(nameField.getText(), GameManager.getPlayer().getBalance());
                createScoreListing();
            }
        } else {
            GuiManager.displayMessage("Name not valid!");
        }
    }
}
