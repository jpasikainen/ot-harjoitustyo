/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jpasikainen
 */

public class Plant {
    public boolean readyToHarvest;

    private String name;
    private int id, price, soilDryness, growingTime;
    private int timeGrown = 0;
    private int daysSinceWatering = 0;

    // Main constructor
    public Plant(int id, String name, int price, int soilDryness, int growingTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.soilDryness = soilDryness;
        this.growingTime = growingTime;
    }

    // Clone constructor
    public Plant(Plant plant) {
        this.id = plant.id;
        this.name = plant.name;
        this.price = plant.price;
        this.soilDryness = plant.soilDryness;
        this.growingTime = plant.growingTime;
    }

    public void harvest() {
        if (readyToHarvest) {
            GameManager.player.addItem(this);
        }
    }

    public boolean canHarvest() {
        return readyToHarvest;
    }

    // Kill plant if not watered every day = return false
    public boolean newDay() {
        daysSinceWatering++;
        if (daysSinceWatering > soilDryness) {
            return false;
        } else {
            timeGrown++;

            if (timeGrown == growingTime) {
                readyToHarvest = true;
            }
            return true;
        }
    }

    public void water() {
        daysSinceWatering = 0;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format(
                "%d %s: costs %d, dries soil every %d days, and takes %d days to grow",
                id, name, price, soilDryness, growingTime
        );
    }
}
