package harvestgame.core;

/**
 * Field contains all the plots and plants within.
 * It also exposes the plants to other classes.
 *
 * TODO: Create Plot class to include more properties on the plots
 * such as price, fertilizers, etc.
 */
public class Field {
    private Plant[] plants;
    private int fieldSize = 9;

    /**
     * Constructor.
     */
    public Field() {
        plants = new Plant[fieldSize];
    }

    private boolean validIndex(int index) {
        return (index < fieldSize && index >= 0);
    }

    /**
     * Get field size.
     *
     * @return the size of the field
     */
    public int getFieldSize() {
        return fieldSize;
    }

    /**
     * Check if the plot is empty.
     *
     * @param index number of the plot
     * @return is plot at index empty or not
     */
    public boolean isEmpty(int index) {
        return plants[index] == null;
    }

    /**
     * Get the plant at the given index.
     *
     * @param index number of the plot
     * @return {@link Plant} at the given index
     */
    public Plant getPlant(int index) {
        if (validIndex(index)) {
            return plants[index];
        }
        return null;
    }

    /**
     * Remove plant from the plot at the given index.
     *
     * @param index index of the {@link Plant} to be removed
     */
    public void removePlant(int index) {
        if (validIndex(index)) {
            plants[index] = null;
        }
    }

    /**
     * Plant {@link Plant} at the given plot index.
     *
     * @param plant {@link Plant} to plant
     * @param index position of the plot
     */
    public void plant(Plant plant, int index) {
        if (validIndex(index)) {
            plants[index] = plant;
        }
    }

    /**
     * Removes the plant from the plot and rewards the player.
     *
     * @param index position of the plot
     */
    public void harvest(int index) {
        if (validIndex(index)) {
            if (getPlant(index).canHarvest()) {
                GameManager.getPlayer().changeBalance(getPlant(index).getPrice() * 2);
                removePlant(index);
            }
        }
    }
}
