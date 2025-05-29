package shantAFX.common.services;

import shantAFX.common.data.GameData;
import shantAFX.common.data.World;

public interface IPostEntityProcessingService {
    void process (GameData gameData, World world);
}
