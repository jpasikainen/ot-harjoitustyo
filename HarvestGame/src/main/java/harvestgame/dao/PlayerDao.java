package harvestgame.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDao implements PlayerDaoImpl {
    private final int startingMoney = 100;
    private int money;
    private String url;

    /**
     * Constructor.
     * @param url The location of the database.
     */
    public PlayerDao(String url) {
        money = startingMoney;
    }

    @Override
    public int getBalance() {
        return money;
    }

    @Override
    public void changeBalance(int amount) {
        money += amount;
        if (money < 0) {
            money = 0;
        }
    }

    @Override
    public void resetData() {
        money = startingMoney;
    }
}
