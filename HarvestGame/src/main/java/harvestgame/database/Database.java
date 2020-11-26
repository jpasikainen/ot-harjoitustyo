package harvestgame.database;

import harvestgame.core.Plant;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Connects to the database and fetches data
 */
public class Database {
    public Database(String url) {
        connect(url);
    }

    private Connection connection;

    // Connect to the database
    private void connect(String url) {
        connection = null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean databaseConnected() {
        return connection != null;
    }
    
    // Disconnect from the database
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to the database closed");
            }    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public ArrayList<Plant> getAllPlants() {
        if (!databaseConnected()) {
            return null;
        }
        ArrayList<Plant> plants = new ArrayList<>();
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

        return plants;
    }
}
