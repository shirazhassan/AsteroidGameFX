package shantAFX.bulletsystem;

import shantAFX.common.data.*;
import shantAFX.common.bullet.IBulletSPI;

public class BulletFactory implements IBulletSPI {
    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        double rot = shooter.getRotation();
        double radius = shooter.getRadius();

        double x = shooter.getX() + Math.cos(Math.toRadians(rot)) * (radius +4);
        double y = shooter.getY() + Math.sin(Math.toRadians(rot)) * (radius +4);

        return new Bullet(x, y, rot);
    }
}
