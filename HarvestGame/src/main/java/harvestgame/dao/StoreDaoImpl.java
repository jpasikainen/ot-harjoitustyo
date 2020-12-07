package harvestgame.dao;

import harvestgame.core.Plant;

import java.util.ArrayList;

public interface StoreDaoImpl {
    /**
     * Get an array of the plants that exist in the database.
     * @return list of plants
     */
    ArrayList<Plant> getPlants();

    /**
     * Get the plant with the given id and reduce player's balance.
     * @param plantId id of the plant being bought
     * @param player location of the wallet
     * @return clone of the plant from database with the same id
     */
    Plant buyPlant(int plantId, PlayerDao player);

    /**
     * Get values of a plant from the database.
     * @param plantId id of the plant being bought
     * @return plant from the database, not a clone
     */
    Plant getPlant(int plantId);
}
