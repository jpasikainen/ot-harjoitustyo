package harvestgame.core;

/**
 * Manages the inventory and money
 */
public class Player {
    private int money;

    /**
     * Constructor
     *
     * @param money set starting money
     */
    public Player(int money) {
        this.money = money;
    }
    
    public int getBalance() {
        return money;
    }

    /**
     * Changes balance and checks if the amount is meant to add or subtract balance
     *
     * @param amount the value which is either negative or positive
     */
    public void changeBalance(int amount) {
        if (amount > 0) {
            money += amount;
        } else if (getBalance() >= Math.abs(amount)) {
            money += amount;
            if (money < 0) {
                money = 0;
            }
        }
    }
}
