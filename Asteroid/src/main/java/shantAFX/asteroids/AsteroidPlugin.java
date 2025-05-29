package shantAFX.asteroids;

import shantAFX.common.data.*;
import shantAFX.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < AsteroidFactory.INITIAL_COUNT; i++) {
            AsteroidFactory.spawn(gameData, world, AsteroidSize.GIANT);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Asteroid.class).forEach(world::removeEntity);
    }
}
