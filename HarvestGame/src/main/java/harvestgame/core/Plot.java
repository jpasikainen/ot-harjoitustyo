package harvestgame.core;

public class Plot {
    private boolean bought;
    private Plant plant;
    private int price = 10;

    /**
     * Constructor.
     */
    public Plot() {
        plant = null;
    }

    public boolean isBought() {
        return bought;
    }

    /**
     * Reset the plot bought status.
     */
    public void resetPlot() {
        bought = false;
    }

    /**
     * Buy the plot.
     * @param player player which is paying
     */
    public void buyPlot(Player player) {
        if (player.getBalance() >= price) {
            player.changeBalance(-price);
            bought = true;
        }
    }

    /**
     * Set plant to the plot.
     * @param plant Plant you wish to plant.
     */
    public void setPlant(Plant plant) {
        if (bought) {
            this.plant = plant;
        }
    }

    public Plant getPlant() {
        return plant;
    }
}
