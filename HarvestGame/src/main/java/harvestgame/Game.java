/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harvestgame;

/**
 *
 * @author jpasikainen
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Game {
    private static Connection connection;
    
    public static void main(String[] args) {
        connect();
        
        if (connection != null)
            createPlant();
        
        disconnect();
    }
    
    public static void createPlant() {
        String query = "SELECT * FROM Plants WHERE id=0";
        
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Plant plant = new Plant(
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("soil_dryness"),
                        rs.getInt("growing_time")
                );
                System.out.println(plant);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Connect to the database at the working directory/database/
    public static void connect() {
        connection = null;
        try {
            // Does hardcoding the path work in the jar?
            String url = "jdbc:sqlite:database/Database.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to the database closed");
            }    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
