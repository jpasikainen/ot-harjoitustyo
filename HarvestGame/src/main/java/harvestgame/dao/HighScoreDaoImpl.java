package harvestgame.dao;

import java.util.Map;

public interface HighScoreDaoImpl {
    /**
     * Get top 10 scores from the database.
     * @return top 10 scores as string-integer pairs
     */
    Map<String, Integer>  getHighScores();

    /**
     * Insert score to the database.
     * @param name name of the player
     * @param score score of the player
     */
    void writeHighScore(String name, int score);
}
