package shantAFX.Enemy;

import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import java.util.Random;

public class EnemyFactory {

    public static Enemy spawnEnemy(GameData gameData, World world) {
        Enemy enemy = new Enemy();
        Random random = new Random();
        double x = random.nextDouble() * gameData.getDisplayWidth();
        double y = random.nextDouble() * gameData.getDisplayHeight();
        enemy.setX(x);
        enemy.setY(y);
        enemy.setRotation(random.nextDouble()* 360);
        world.addEntity(enemy);
        return enemy;
    }
}