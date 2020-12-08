package harvestgame.ui;

import harvestgame.core.GameManager;
import harvestgame.core.Plant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class ActionManager {
    @FXML
    private Pane main;

    @FXML
    private Pane store;

    @FXML
    private VBox itemContainer;

    @FXML
    private Label moneyLabel;

    @FXML
    private GridPane plots;

    @FXML
    private void initialize() {
        updateMoneyLabel();

        plots.getChildren().forEach(button -> {
            Button b = (Button)button;
            b.setText("Buy for $10");
        });
    }

    public void updateMoneyLabel() {
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
}
