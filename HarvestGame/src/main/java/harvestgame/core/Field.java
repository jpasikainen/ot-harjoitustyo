package harvestgame.core;

/**
 * Field contains all the plots and plants within.
 * It also exposes the plots and their respective plants to other classes.
 */
public class Field {
    private Plot[] plots;
    private int fieldSize = 9;

    /**
     * Constructor.
     */
    public Field() {
        plots = new Plot[fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            plots[i] = new Plot();
        }
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
        return plots[index].getPlant() == null;
    }

    /**
     * Get the plant at the given index.
     *
     * @param index number of the plot
     * @return {@link Plant} at the given index
     */
    public Plant getPlant(int index) {
        if (validIndex(index)) {
            return plots[index].getPlant();
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
            plots[index].setPlant(null);
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
            plots[index].setPlant(plant);
        }
    }

    /**
     * Removes the plant from the plot and rewards the player.
     *
     * @param index position of the plot
     */
    public void harvest(int index) {
        if (validIndex(index) && getPlant(index) != null) {
            if (getPlant(index).canHarvest()) {
                GameManager.getPlayer().changeBalance(getPlant(index).getPrice() * 2);
                removePlant(index);
            }
        }
    }

    /**
     * Remove all the plants from the field.
     */
    public void clearField() {
        for (int i = 0; i < fieldSize; i++) {
            removePlant(i);
            plots[i].resetPlot();
        }
    }

    /**
     * Check if plot has been bought.
     * @param index index of the plot
     * @return the status of the plot
     */
    public boolean isBought(int index) {
        if (validIndex(index)) {
            return plots[index].isBought();
        }
        return false;
    }

    /**
     * Buy plot.
     * @param index index of the plot
     */
    public void buyPlot(int index) {
        if (validIndex(index)) {
            plots[index].buyPlot(GameManager.getPlayer());
        }
    }
}
