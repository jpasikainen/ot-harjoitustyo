package harvestgame.dao;

import harvestgame.core.Plant;
import harvestgame.core.Player;

import java.util.ArrayList;

public interface StoreDaoImpl {
    ArrayList<Plant> getPlants();
    Plant buyPlant(int plantId, Player player);
    Plant getPlant(int plantId);
}
