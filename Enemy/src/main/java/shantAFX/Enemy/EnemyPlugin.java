package shantAFX.Enemy;

import shantAFX.common.services.IGamePluginService;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.data.Entity;
import java.util.List;
import java.util.ArrayList;

public class EnemyPlugin implements IGamePluginService{
    private final List<Entity> enemies = new ArrayList<>();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 3; i++) {
            enemies.add(EnemyFactory.spawnEnemy(gameData, world));
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : enemies) {
            world.removeEntity(e);
        }
        enemies.clear();
    }
}
