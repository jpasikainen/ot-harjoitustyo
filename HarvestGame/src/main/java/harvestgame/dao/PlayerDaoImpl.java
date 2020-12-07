package harvestgame.dao;

public interface PlayerDaoImpl {
    /**
     * Write balance on the database.
     */
    void writeBalance();

    /**
     * Return player's balance.
     * @return money
     */
    int getBalance();

    /**
     * Change the amount of money player has.
     * @param amount negative or positive value that gets added or reduced from the balance
     */
    void changeBalance(int amount);

    /**
     * Reset money on the database to the default value.
     */
    void resetData();
}
