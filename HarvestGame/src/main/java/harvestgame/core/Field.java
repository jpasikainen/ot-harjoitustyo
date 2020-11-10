package harvestgame.core;

import java.util.ArrayList;

public class Field {
    private int fieldSize; // How many plants can be planted
    private ArrayList<Plant> plants;

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
        plants = new ArrayList<>();
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    // Plant if free space on the field
    public void plant(Plant plant) {
        if (plants.size() < fieldSize) {
            plants.add(plant);
            Game.getPlayer().removeItem(plant);
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
