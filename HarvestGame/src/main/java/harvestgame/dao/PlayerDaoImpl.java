package harvestgame.dao;

public interface PlayerDaoImpl {
    void writeBalance();
    int getBalance();
    void changeBalance(int amount);
    void resetData();
}
