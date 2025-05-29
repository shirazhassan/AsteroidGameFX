package shantAFX.Enemy;

import shantAFX.common.bullet.IBulletSPI;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.data.Entity;
import java.util.List;

public class EnemyAI {
    private static final double SHOOT_ANGLE_THRESHOLD = 10;
    private static final float SHOOT_COOLDOWN_TIME = 1f;
    private float shootCooldown = 0f;

    public void update(Enemy enemy, GameData gameData, World world, IBulletSPI bulletSPI) {
        float dt = gameData.getDeltaTime();
        shootCooldown -= dt;

        List<Entity> players = world.getEntities(shantAFX.playersystem.Player.class);
        if (players.isEmpty()) {
            return;
        }
        Entity player = players.get(0);

        double dx = player.getX() - enemy.getX();
        double dy = player.getY() - enemy.getY();
        double desiredAngle = Math.toDegrees(Math.atan2(dy, dx));
        desiredAngle = (desiredAngle + 360) % 360;
        double currentRotation = (enemy.getRotation() + 360) % 360;
        double angleDiff = desiredAngle - currentRotation;
        if (angleDiff > 180) angleDiff -= 360;
        if (angleDiff < -180) angleDiff += 360;

        if (angleDiff < -1) {
            enemy.setRotation(enemy.getRotation() - Enemy.ROTATION_SPEED * dt);
        } else if (angleDiff > 1) {
            enemy.setRotation(enemy.getRotation() + Enemy.ROTATION_SPEED * dt);
        }

        if (Math.abs(angleDiff) < 15) {
            double rad = Math.toRadians(enemy.getRotation());
            enemy.setDx(enemy.getDx() + Math.cos(rad) * Enemy.ACCELERATION * dt);
            enemy.setDy(enemy.getDy() + Math.sin(rad) * Enemy.ACCELERATION * dt);
        }

        if (bulletSPI != null && shootCooldown <= 0 && Math.abs(angleDiff) < SHOOT_ANGLE_THRESHOLD) {
            world.addEntity(bulletSPI.createBullet(enemy, gameData));
            shootCooldown = SHOOT_COOLDOWN_TIME;
        }
    }
}
