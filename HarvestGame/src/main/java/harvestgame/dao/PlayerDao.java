package harvestgame.dao;

import harvestgame.ui.GUI;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDao implements PlayerDaoImpl {
    private final int startingMoney = 100;
    private int money;
    private String url;

    public PlayerDao(String url) {
        this.url = url;
        try {
            Connection connection = DriverManager.getConnection(url);
            readBalance(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readBalance(Connection connection) {
        if (connection != null) {
            String query = "SELECT money FROM Player";

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                money = rs.getInt("money");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void writeBalance() {
        setValue("UPDATE Player SET money = " + money);
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
        setValue("UPDATE Player SET money = " + startingMoney);
        money = startingMoney;
        GUI.updateMoneyLabel();
    }

    private void setValue(String query) {
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
