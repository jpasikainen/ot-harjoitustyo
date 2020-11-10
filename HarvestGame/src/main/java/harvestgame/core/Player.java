/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

import java.util.ArrayList;

/**
 *
 * @author jpasikainen
 */
public class Player {
    private int money;
    private ArrayList<Plant> inventory;
    
    public Player(int money) {
        this.money = money;
        this.inventory = new ArrayList<>();
    }
    
    public int getBalance() {
        return money;
    }
    
    public void changeBalance(int amount) {
        money -= amount;
    }
    
    public void addItem(Plant plant) {
        inventory.add(plant);
    }
}