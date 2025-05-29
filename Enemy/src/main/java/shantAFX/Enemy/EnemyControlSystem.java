package shantAFX.Enemy;

import shantAFX.common.bullet.IBulletSPI;
import shantAFX.common.data.Entity;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.services.IEntityProcessingService;
import java.util.List;
import java.util.ServiceLoader;

public class EnemyControlSystem implements IEntityProcessingService{

    private final IBulletSPI bulletSPI;
    private final EnemyAI ai;

    private float spawnTimer = 0f;
    private static final float SPAWN_INTERVAL = 5f;

    public EnemyControlSystem() {
        this.bulletSPI = ServiceLoader.load(IBulletSPI.class).findFirst().orElse(null);
        this.ai = new EnemyAI();
    }

    @Override
    public void process(GameData gameData, World world) {
        float dt = gameData.getDeltaTime();

        spawnTimer += dt;
        if (spawnTimer >= SPAWN_INTERVAL) {
            spawnTimer -= SPAWN_INTERVAL;
            EnemyFactory.spawnEnemy(gameData, world);
        }

        float w = gameData.getDisplayWidth();
        float h = gameData.getDisplayHeight();

        List<Entity> enemies = world.getEntities(Enemy.class);
        for (Entity e : enemies) {
            Enemy enemy = (Enemy)e;
            ai.update(enemy, gameData, world, bulletSPI);

            enemy.setDx(enemy.getDx() * Math.pow(0.99, dt*60));
            enemy.setDy(enemy.getDy() * Math.pow(0.99, dt*60));

            double speed = Math.hypot(enemy.getDx(), enemy.getDy());
            if (speed > Enemy.MAX_SPEED) {
                double scale = Enemy.MAX_SPEED / speed;
                enemy.setDx(enemy.getDx() * scale);
                enemy.setDy(enemy.getDy() * scale);
            }

            double newX = wrap(enemy.getX() + enemy.getDx() * dt, w);
            double newY = wrap(enemy.getY() + enemy.getDy() * dt, h);
            enemy.setX(newX);
            enemy.setY(newY);
        }
    }

    private double wrap(double v, double max) {
        if (v < 0) return v + max;
        if (v > max) return v - max;
        return v;
    }
}
