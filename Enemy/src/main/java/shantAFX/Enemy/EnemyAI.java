package shantAFX.Enemy;

import shantAFX.common.bullet.IBulletSPI;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.data.Entity;
import shantAFX.common.data.components.FactionComponent;
import java.util.List;
import java.util.stream.Collectors;


public class EnemyAI {
    private static final double SHOOT_ANGLE_THRESHOLD = 10;
    private static final float SHOOT_COOLDOWN_TIME = 1f;
    private float shootCooldown = 0f;
    private static final double MIN_DISTANCE = 250;

    public void update(Enemy enemy, GameData gameData, World world, IBulletSPI bulletSPI) {
        float dt = gameData.getDeltaTime();
        shootCooldown = Math.max(0, shootCooldown - dt);

        List<Entity> players = world.getEntities()
                .stream()
                .filter(e -> {
                    FactionComponent fc = e.getComponent(FactionComponent.class);
                    return fc != null && fc.getFaction() == FactionComponent.Faction.PLAYER;
                }).collect(Collectors.toList());
        if (players.isEmpty()) {
            return;
        }
        Entity target = players.get(0);

        float dx = (float)(target.getX() - enemy.getX());
        float dy = (float)(target.getY() - enemy.getY());
        double distance = Math.hypot(dx, dy);

        double angleToPlayer = Math.toDegrees(Math.atan2(dy, dx));
        double angleDiff = wrapAngle(angleToPlayer - enemy.getRotation());


        if (angleDiff < -1) {
            enemy.setRotation(enemy.getRotation() - Enemy.ROTATION_SPEED * dt);
        } else if (angleDiff > 1) {
            enemy.setRotation(enemy.getRotation() + Enemy.ROTATION_SPEED * dt);
        }

        if (Math.abs(angleDiff) < 15 && distance > MIN_DISTANCE) {
            double rad = Math.toRadians(enemy.getRotation());
            enemy.setDx(enemy.getDx() + Math.cos(rad) * Enemy.ACCELERATION * dt);
            enemy.setDy(enemy.getDy() + Math.sin(rad) * Enemy.ACCELERATION * dt);
        }

        if (bulletSPI != null && shootCooldown <= 0
                && Math.abs(angleDiff) < SHOOT_ANGLE_THRESHOLD) {
            world.addEntity(bulletSPI.createBullet(enemy, gameData));
            shootCooldown = SHOOT_COOLDOWN_TIME;
        }
    }

    private double wrapAngle(double angle) {
        while (angle < -180) angle += 360;
        while (angle > 180) angle -= 360;
        return angle;
    }
}
