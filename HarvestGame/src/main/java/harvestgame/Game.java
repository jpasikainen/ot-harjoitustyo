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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Game {
    public static void main(String[] args) {
        connect();
    }
    
    // Connect to the database at the working directory/database/
    public static void connect() {
        Connection connection = null;
        try {
            // Does hardcoding the path work in the jar?
            String url = "jdbc:sqlite:database/Database.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
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
}
