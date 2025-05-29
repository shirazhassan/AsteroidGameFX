package shantAFX.common.services;

import shantAFX.common.data.GameData;
import shantAFX.common.data.World;

public interface IGamePluginService {

    void start(GameData gameData, World world);
    void stop(GameData gameData, World world);
}
