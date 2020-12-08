package harvestgame.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class HighScoreDao implements HighScoreDaoImpl {
    private Map<String, Integer> scores;
    private String url;

    public HighScoreDao(String url) {
        this.url = url;
        try {
            Connection connection = DriverManager.getConnection(url);
            writeScoresMap(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeScoresMap(Connection connection) {
        if (connection != null) {
            scores = new HashMap<>();
            // Get top 10 scores on highest first order
            String query = "SELECT * FROM HighScores ORDER BY score DESC LIMIT 10";

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    scores.put(
                            rs.getString("name"),
                            rs.getInt("score")
                    );
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Map<String, Integer> getHighScores() {
        try {
            Connection connection = DriverManager.getConnection(url);
            writeScoresMap(connection);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return scores;
    }

    @Override
    public void writeHighScore(String name, int score) {
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            String query = String.format("INSERT INTO HighScores VALUES (%d, \"%s\")", score, name);
            stmt.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
