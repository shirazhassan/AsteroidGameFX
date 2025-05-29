package shantAFX.playersystem;

import shantAFX.common.data.Entity;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = PlayerFactory.spawnPlayer(gameData, world);
    }


    @Override
    public void stop(GameData gameData, World world) {
        if (player != null) {
            world.removeEntity(player);
        }
    }
    }
