package shantAFX.common.services;

import shantAFX.common.data.GameData;
import shantAFX.common.data.World;

public interface IEntityProcessingService {

    /**
     *
     *
     * @param gameData
     * @param world
     * @throws
     */
    void process(GameData gameData, World world);
}
