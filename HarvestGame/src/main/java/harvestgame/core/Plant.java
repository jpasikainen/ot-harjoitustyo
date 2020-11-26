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
    private String name;
    private int id, price, soilDryness, growingTime;
    private int daysSinceWatering, daysGrown;

    // Constructor
    public Plant(int id, String name, int price, int soilDryness, int growingTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.soilDryness = soilDryness;
        this.growingTime = growingTime;
        plant();
    }

    // Reset plant's values for reuse
    public void plant() {
        this.daysSinceWatering = 0;
        this.daysGrown = 0;
    }

    public void water() {
        daysSinceWatering = 0;
    }

    public boolean canHarvest() {
        return daysGrown == growingTime;
    }

    // Clone self without modified properties
    public Plant harvest() {
        plant();
        return this;
    }

    public boolean survivesDay() {
        if (daysSinceWatering == soilDryness) {
            return false;
        } else {
            daysGrown++;
            daysSinceWatering++;
            return true;
        }
    }

    public String getName() {
        return name;
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
}
