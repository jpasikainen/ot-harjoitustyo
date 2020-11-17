/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

/**
 *
 * @author jpasikainen
 */

public class Plant {
    private int id, price, soilDryness, growingTime;
    private String name;
    private Timer wateringTimer;
    private Timer harvestingTimer;

    public boolean readyToHarvest;
    public boolean requiresWatering;
    
    public Plant(int id, String name, int price, int soilDryness, int growingTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.soilDryness = soilDryness;
        this.growingTime = growingTime;

        wateringTimer = new Timer();
        harvestingTimer = new Timer();
    }

    public void plant() {
        startHarvestTimer();
        startWateringTimer();
    }

    public void water() {
        if (!readyToHarvest) {
            wateringTimer.purge();
            startWateringTimer();
        }
    }
    
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return String.format(
                "%d %s: costs %d, dries soil every %d days, and takes %d days to grow",
                id, name, price, soilDryness, growingTime
        );
    }

    public Plant harvest() {
        if (readyToHarvest) {
            return this;
        }
        return null;
    }

    private void killTimers() {
        wateringTimer.cancel();
        harvestingTimer.cancel();
    }

    // Time in ms
    private void startHarvestTimer() {
        harvestingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                readyToHarvest = true;
                killTimers();
                System.out.println("Ready to be harvested");
            }
        }, growingTime * 1000);
    }

    // Time in ms
    private void startWateringTimer() {
        wateringTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                requiresWatering = true;
                System.out.println("Requires watering");
                startWateringTimer();
            }
        }, soilDryness * 1000);
    }
}
