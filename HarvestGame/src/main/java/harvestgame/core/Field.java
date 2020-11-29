package harvestgame.core;

/**
 * TODO: Create Plot class to include more properties on the plots
 * such as price, fertilizers, etc.
 *
 * Field contains all the plots and plants within.
 * It also exposes the plants to other classes.
 */
public class Field {
    private Plant[] plants;
    private int fieldSize = 9;

    public Field() {
        plants = new Plant[fieldSize];
    }

    private boolean validIndex(int index) {
        return (index < fieldSize && index >= 0);
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public boolean isEmpty(int index) {
        return plants[index] == null;
    }

    public Plant getPlant(int index) {
        if (validIndex(index)) {
            return plants[index];
        }
        return null;
    }

    public void removePlant(int index) {
        if (validIndex(index)) {
            plants[index] = null;
        }
    }

    public void plant(Plant plant, int index) {
        if (validIndex(index)) {
            plants[index] = plant;
        }
    }

    public void harvest(int index) {
        if (validIndex(index)) {
            GameManager.player.changeBalance(getPlant(index).getPrice() * 2);
            removePlant(index);
        }
    }
}
