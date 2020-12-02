package harvestgame.core;

import java.util.Random;

/**
 * Object which is created from the data of the database
 */
public class Plant {
    private String name;
    private int id, price, soilDryness, growingTime;
    private int timeLeft;
    private boolean hasWater = true;

    /**
     * Constructor
     *
     * @param id plant id
     * @param name plant name
     * @param price plant price
     * @param soilDryness chance of requiring watering every second
     * @param growingTime growing time in seconds
     */
    public Plant(int id, String name, int price, int soilDryness, int growingTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.soilDryness = soilDryness;
        this.growingTime = growingTime;
        this.timeLeft = growingTime;
    }

    /**
     * Alternative constructor for cloning
     *
     * @param plant {@link Plant} to create clone of
     */
    public Plant(Plant plant) {
        this.id = plant.id;
        this.name = plant.name;
        this.price = plant.price;
        this.soilDryness = plant.soilDryness;
        this.growingTime = plant.growingTime;
        this.timeLeft = growingTime;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getGrowingTime() {
        return growingTime;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Reduces the time plant has left by 1 second.
     * Also chooses if watering is required
     */
    public void reduceTime() {
        if (timeLeft > 0) {
            timeLeft -= 1;

            Random rn = new Random();
            if (rn.nextInt(10 + 1) <= soilDryness) {
                hasWater = false;
            }
        }
    }

    public boolean requiresWatering() {
        return !hasWater;
    }

    public void water() {
        hasWater = true;
    }

    public boolean canHarvest() {
        return timeLeft == 0;
    }
}
