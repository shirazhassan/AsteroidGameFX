package shantAFX.playersystem;

import shantAFX.common.bullet.IBulletSPI;
import shantAFX.common.data.*;
import shantAFX.common.services.IEntityProcessingService;

import java.util.ServiceLoader;

public class PlayerControlSystem implements IEntityProcessingService {

    private final IBulletSPI bulletSPI;
    private double shootCooldown = 0f;
private static final double DEACCELERATION_FACTOR = 0.99;
    private static final float FIRE_RATE = 0.1f;

    public PlayerControlSystem() {
        this.bulletSPI = ServiceLoader.load(IBulletSPI.class).stream().map(ServiceLoader.Provider::get).findFirst().orElse(null);
    }
    @Override
    public void process(GameData gameData, World world) {

        double dt = gameData.getDeltaTime();
        GameKeys keys = gameData.getKeys();
        for (Entity playerE : world.getEntities()) {
            if (!(playerE instanceof Player)) {
                continue;
            }

            Player p = (Player) playerE;

            if (keys.isDown(GameKeys.LEFT)) {
                p.setRotation(p.getRotation() - Player.ROTATION_SPEED * dt);
            }
            if (keys.isDown(GameKeys.RIGHT)) {
                p.setRotation(p.getRotation() + Player.ROTATION_SPEED * dt);
            }
            if (keys.isDown(GameKeys.UP)) {
                double rad = Math.toRadians(p.getRotation());
                p.setDx(p.getDx() + Math.cos(rad) * Player.ACCELERATION * dt);
                p.setDy(p.getDy() + Math.sin(rad) * Player.ACCELERATION * dt);

                double speed = Math.hypot(p.getDx(), p.getDy());
                if (speed > Player.MAX_SPEED) {
                    double scale = Player.MAX_SPEED / speed;
                    p.setDx(p.getDx() * scale);
                    p.setDy(p.getDy() * scale);
                }
            } else {
                p.setDx(p.getDx() * Math.pow(DEACCELERATION_FACTOR, dt*60));
                p.setDy(p.getDy() * Math.pow(DEACCELERATION_FACTOR, dt*60));
            }

            double nx = p.getX() + p.getDx() * dt;
            double ny = p.getY() + p.getDy() * dt;
            nx = wrap(nx, gameData.getDisplayWidth());
            ny = wrap(ny, gameData.getDisplayHeight());
            p.setX(nx);
            p.setY(ny);

            shootCooldown -= dt;
            if (bulletSPI != null && keys.isDown(GameKeys.SPACE) && shootCooldown <= 0f) {
                world.addEntity(bulletSPI.createBullet(p, gameData));
                shootCooldown = FIRE_RATE;
            }
        }

        keys.update();
    }
    private double wrap(double v, double max) {
        if (v < 0) return v + max;
        if (v > max) return v - max;
        return v;
    }
}
