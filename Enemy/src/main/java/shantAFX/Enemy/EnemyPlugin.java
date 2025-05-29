package shantAFX.Enemy;

import shantAFX.common.services.IGamePluginService;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;

import java.util.List;
import java.util.ArrayList;

public class EnemyPlugin implements IGamePluginService{
    private final List<Enemy> spawned = new ArrayList<>();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 3; i++) {
            spawned.add(EnemyFactory.spawnEnemy(gameData, world));
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Enemy e : spawned) {
            world.removeEntity(e);
        }
        spawned.clear();
    }
}
