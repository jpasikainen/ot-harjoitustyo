package harvestgame.dao;

import harvestgame.core.Plant;

import java.util.ArrayList;

public interface StoreDaoImpl {
    /**
     * @return list of plants
     */
    ArrayList<Plant> getPlants();

    /**
     * @param plantId id of the plant being bought
     * @param player location of the wallet
     * @return clone of the plant from database with the same id
     */
    Plant buyPlant(int plantId, PlayerDao player);

    /**
     * @param plantId id of the plant being bought
     * @return plant from the database, not a clone
     */
    Plant getPlant(int plantId);
}
