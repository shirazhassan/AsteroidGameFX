package shantAFX.Enemy;

import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.data.components.FactionComponent;

import java.util.Random;

public class EnemyFactory {
    private static final Random RANDOM = new Random();

    public static Enemy spawnEnemy(GameData gameData, World world) {
        Enemy enemy = new Enemy();
        enemy.setX(RANDOM.nextDouble() * gameData.getDisplayWidth());
        enemy.setY(RANDOM.nextDouble() * gameData.getDisplayHeight());
        enemy.setRotation(RANDOM.nextDouble() * 360);

        enemy.addComponent(
                new FactionComponent(FactionComponent.Faction.ENEMY)
        );
        world.addEntity(enemy);
        return enemy;
    }
}