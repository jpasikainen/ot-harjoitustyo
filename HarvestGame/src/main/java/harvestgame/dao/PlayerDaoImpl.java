package harvestgame.dao;

public interface PlayerDaoImpl {
    /**
     * Write balance on the database
     */
    void writeBalance();

    /**
     * @return money
     */
    int getBalance();

    /**
     * @param amount negative or positive value that gets added or reduced from the balance
     */
    void changeBalance(int amount);

    /**
     * Reset money on the database to the default value
     */
    void resetData();
}
