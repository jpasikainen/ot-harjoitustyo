package harvestgame.dao;

import java.util.Map;

public interface HighScoreDaoImpl {
    Map<String, Integer>  getHighScores();
    void writeHighScore(String name, int score);
}
