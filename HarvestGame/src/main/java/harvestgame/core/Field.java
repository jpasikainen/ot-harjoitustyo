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

    public Plant getPlant(int index) {
        if (validIndex(index)) {
            return plants[index];
        }
        return null;
    }

    public void plant(Plant plant, int index) {
        if (validIndex(index)) {
            plants[index] = plant;
        }
    }

    public void waterPlant(int index) {
        if (validIndex(index)) {
            plants[index].water();
        }
    }

    public boolean canHarvestPlant(int index) {
        if (validIndex(index)) {
            return plants[index].canHarvest();
        }
        return false;
    }

    // Return plant or null
    public Plant harvestPlant(int index) {
        if (validIndex(index)) {
            Plant plant = plants[index].harvest();
            plants[index] = null;
            return plant;
        }
        return null;
    }

    public void advanceDay() {
        // Loop through all plants
        for (int i = 0; i < fieldSize; i++) {
            // Check if plant exists
            if (plants[i] != null) {
                // If plant dies -> destroy it
                if (!plants[i].survivesDay()) {
                    plants[i] = null;
                }
            }
        }
    }
}
