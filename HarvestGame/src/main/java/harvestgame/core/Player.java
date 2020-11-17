/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame.core;

import java.lang.reflect.Array;
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
        if (amount > 0) {
            money += amount;
        } else if (getBalance() >= Math.abs(amount)) {
            money += amount;
        }
    }
    
    public void addItem(Plant plant) {
        if (plant != null) {
            inventory.add(plant);
        }
    }

    public void removeItem(Plant plant) {
        inventory.remove(plant);
    }

    public ArrayList<Plant> getInventory() {
        return inventory;
    }

    public ArrayList<String> listInventory() {
        ArrayList<String> itemList = new ArrayList<>();
        inventory.forEach(item -> itemList.add(item.toString()));
        return itemList;
    }

    public Plant getItem(int id) {
        if (inventory.size() > id) {
            return inventory.get(id);
        }
        return null;
    }
}
