package harvestgame.dao;

import harvestgame.core.Plant;

import java.util.ArrayList;

public interface StoreDaoImpl {
    ArrayList<Plant> getPlants();
    Plant buyPlant(int plantId, PlayerDao player);
    Plant getPlant(int plantId);
}
