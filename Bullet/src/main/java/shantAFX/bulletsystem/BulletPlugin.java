package shantAFX.bulletsystem;

import shantAFX.common.data.*;
import shantAFX.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {}

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities().stream().filter(e -> e instanceof Bullet).forEach(world::removeEntity);
    }

}
