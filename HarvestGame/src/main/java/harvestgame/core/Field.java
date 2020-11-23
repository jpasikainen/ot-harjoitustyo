package harvestgame.core;

/**
 * TODO: Create Plot class to include more properties on the plots
 * such as price, fertilizers, etc.
 */
public class Field {
    private Plant[] plots;
    private int fieldWidth, fieldHeight;

    public Field(int fieldWidth, int fieldHeight) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        plots = new Plant[getFieldSize()];
    }

    public boolean isPlotFree(int plotIndex) {
        return plots[plotIndex] == null;
    }

    public Plant getPlant(int plotIndex) {
        return isPlotFree(plotIndex) ? null : plots[plotIndex];
    }

    public void plant(Plant plant, int plotIndex) {
        if (GameManager.player.hasItem(plant)) {
            plots[plotIndex] = plant;
            GameManager.player.removeItem(plant);
        }
    }

    public void harvest(int plotIndex) {
        GameManager.player.addItem(plots[plotIndex]);
        plots[plotIndex] = null;
    }

    public void water(int plotIndex) {
        plots[plotIndex].water();
    }

    public void waterAll() {
        for (int i = 0; i < getFieldSize(); i++) {
            if (!isPlotFree(i)) {
                water(i);
            }
        }
    }

    public void newDay() {
        for (int i = 0; i < getFieldSize(); i++) {
            if (!isPlotFree(i)) {
                if (!plots[i].newDay()) {
                    plots[i] = null; // Remove the plant if not watered
                }
            }
        }
    }

    public int getFieldSize() {
        return fieldWidth * fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }
}
