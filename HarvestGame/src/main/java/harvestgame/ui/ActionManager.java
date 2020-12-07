package harvestgame.ui;

import harvestgame.core.GameManager;
import harvestgame.core.Plant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private void initialize() {
        updateMoneyLabel();
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
            main.setVisible(false);
            store.setVisible(true);

            itemContainer.getChildren().clear();
            createStoreItems();
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
            Label label = new Label(plant.getName() + ": Grows: " + plant.getGrowingTime() + "s");
            Button button = new Button("$" + plant.getPrice());
            if (GameManager.getPlayer().getBalance() < plant.getPrice()) {
                button.setDisable(true);
            }

            final int finalI = i;
            button.setOnAction(e -> buyButton(finalI));
            BorderPane container = new BorderPane();
            container.setCenter(label);
            container.setRight(button);
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
