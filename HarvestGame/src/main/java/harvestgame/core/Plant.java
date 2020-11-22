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
    private int id, price, soilDryness, growingTime;
    private String name;
    private int timeGrown = 0;

    public boolean readyToHarvest;
    public boolean requiresWatering;

    public Plant(int id, String name, int price, int soilDryness, int growingTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.soilDryness = soilDryness;
        this.growingTime = growingTime;
    }

    // Clone
    // TODO: Remove cloning eventually
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

    // Kill plant if not watered every day
    public boolean newDay() {
        if (requiresWatering) {
            return false;
        } else {
            requiresWatering = true;
            timeGrown++;

            if (timeGrown == growingTime) {
                readyToHarvest = true;
            }
            return true;
        }
    }

    public void water() {
        requiresWatering = false;
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
