package harvestgame.core;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Player {
    private final int startingMoney = 100;
    private int money;

    /**
     * Constructor.
     */
    public Player() {
        money = startingMoney;
    }

    /**
     * Return player's balance.
     * @return money
     */
    public int getBalance() {
        return money;
    }

    /**
     * Change the amount of money player has.
     * @param amount negative or positive value that gets added or reduced from the balance
     */
    public void changeBalance(int amount) {
        money += amount;
        if (money < 0) {
            money = 0;
        }
    }

    /**
     * Reset money on the database to the default value.
     */
    public void resetData() {
        money = startingMoney;
    }
}
