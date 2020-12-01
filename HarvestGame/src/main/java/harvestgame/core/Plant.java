package harvestgame.core;

import javafx.application.Platform;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Plant {
    private String name;
    private int id, price, soilDryness, growingTime;
    private int timeLeft;
    private boolean hasWater = true;

    // Constructor
    public Plant(int id, String name, int price, int soilDryness, int growingTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.soilDryness = soilDryness;
        this.growingTime = growingTime;
        this.timeLeft = growingTime;
    }

    // Clone
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
