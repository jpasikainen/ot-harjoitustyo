package harvestgame.dao;

import harvestgame.core.Plant;
import harvestgame.core.Player;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StoreDao implements StoreDaoImpl {
    private ArrayList<Plant> plants;
    private boolean databaseExists = true;

    /**
     * Constructor.
     * @param url address of the database file
     */
    public StoreDao(String url) {
        plants = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url);
            writePlantsArray(connection);
            connection.close();
        } catch (SQLException e) {
            databaseExists = false;
        }
    }

    private void writePlantsArray(Connection connection) {
        if (connection != null && databaseExists) {
            plants = new ArrayList<>();
            String query = "SELECT * FROM Plants";

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    Plant plant = new Plant(
                            rs.getInt("id"), rs.getString("name"), rs.getInt("price"),
                            rs.getInt("soil_dryness"), rs.getInt("growing_time")
                    );

                    plants.add(plant);
                }
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public ArrayList<Plant> getPlants() {
        return plants;
    }

    @Override
    public Plant buyPlant(int plantId, Player player) {
        if (plantId > plants.size() || plantId < 0) {
            return null;
        }

        Plant plant = new Plant(getPlant(plantId));
        int price = plant.getPrice();
        if (player.getBalance() >= price) {
            player.changeBalance(-price);
            return plant;
        }
        return null;
    }

    @Override
    public Plant getPlant(int plantId) {
        if (plantId <= plants.size() && plantId >= 0) {
            return plants.get(plantId);
        }
        return null;
    }
}
