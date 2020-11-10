/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

/**
 *
 * @author jpasikainen
 */
public class Plant {
    private int price, soilDryness, growingTime;
    private String name;
    
    public Plant(String name, int price, int soilDryness, int growingTime) {
        this.name = name;
        this.price = price;
        this.soilDryness = soilDryness;
        this.growingTime = growingTime;
    }
    
    public int getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return String.format(
                "%s: costs %d, dries soil every %d days, and takes %d days to grow",
                name, price, soilDryness, growingTime
        );
    }
}
