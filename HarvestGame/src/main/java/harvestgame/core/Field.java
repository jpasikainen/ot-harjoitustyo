package harvestgame.core;

import java.util.ArrayList;
import harvestgame.core.Player;

public class Field {
    private int fieldSize; // How many plants can be planted
    private ArrayList<Plant> plants;
    private Player player;

    public Field(int fieldSize, Player player) {
        this.player = player;
        this.fieldSize = fieldSize;
        plants = new ArrayList<>();
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void water(Plant plant) {
        plant.water();
    }

    public Plant getPlant(int id) {
        return plants.get(id);
    }

    public void harvest() {
        plants.forEach(plant -> {
            player.addItem(plant.harvest());
        });
    }

    // Plant if free space on the field
    public void plant(Plant plant) {
        if (plant == null)
            return;

        if (plants.size() < fieldSize) {
            plants.add(plant);
            plant.plant();
            player.removeItem(plant);
            System.out.println("Item planted!");
        }
        else {
            System.out.println("No space on the field");
        }
    }

    public void viewField() {
        for (Plant plant : plants) {
            System.out.println(plant.toString());
        }
    }
}
