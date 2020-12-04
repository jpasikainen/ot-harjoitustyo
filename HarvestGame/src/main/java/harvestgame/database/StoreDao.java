package harvestgame.database;

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

    public StoreDao(String url) {
        try {
            Connection connection = DriverManager.getConnection(url);
            writePlantsArray(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writePlantsArray(Connection connection) {
        if (connection != null) {
            plants = new ArrayList<>();
            String query = "SELECT * FROM Plants";

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    Plant plant = new Plant(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("soil_dryness"),
                            rs.getInt("growing_time")
                    );

                    plants.add(plant);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public ArrayList<Plant> getPlants() {
        return plants;
    }

    @Override
    public Plant buyPlant(int plantId, Player player) {
        Plant plant = getPlant(plantId);

        if (plant != null) {
            int price = plant.getPrice();
            if (player.getBalance() >= price) {
                player.changeBalance(-price);
                return plant;
            }
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
