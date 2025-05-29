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

    public EnemyControlSystem() {
        bulletSPI = ServiceLoader.load(IBulletSPI.class).findFirst().orElse(null);
        ai = new EnemyAI();
    }

    @Override
    public void process(GameData gameData, World world) {
        float dt = gameData.getDeltaTime();
        List<Entity> enemies = world.getEntities(Enemy.class);
        for (Entity enemy : enemies) {
            ai.update((Enemy)enemy, gameData, world, bulletSPI);

            enemy.setDx(enemy.getDx() * Math.pow(0.99, dt*60));
            enemy.setDy(enemy.getDy() * Math.pow(0.99, dt*60));

            double speed = Math.hypot(enemy.getDx(), enemy.getDy());
            if (speed > Enemy.MAX_SPEED) {
                double scale = Enemy.MAX_SPEED / speed;
                enemy.setDx(enemy.getDx() * scale);
                enemy.setDy(enemy.getDy() * scale);
            }

            double newX = enemy.getX() + enemy.getDx() * dt;
            double newY = enemy.getY() + enemy.getDy() * dt;
            enemy.setX(wrap(newX, gameData.getDisplayWidth()));
            enemy.setY(wrap(newY, gameData.getDisplayHeight()));
        }
    }

    private double wrap(double v, double max) {
        if (v < 0) return v + max;
        if (v > max) return v - max;
        return v;
    }
}
