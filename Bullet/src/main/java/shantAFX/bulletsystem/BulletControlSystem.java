package shantAFX.bulletsystem;

import shantAFX.common.data.*;
import shantAFX.common.services. IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        float dt = gameData.getDeltaTime();
        for (Entity e : world.getEntities()) {
            if (!(e instanceof Bullet))
            continue;
            Bullet b = (Bullet) e;

            b.setX(b.getX() + b.getDx() * dt);
            b.setY(b.getY() + b.getDy() * dt);

            if (b.getX() < -b.getRadius()
                    || b.getX() > gameData.getDisplayWidth()  + b.getRadius()
                    || b.getY() < -b.getRadius()
                    || b.getY() > gameData.getDisplayHeight() + b.getRadius()) {
                world.removeEntity(b);
            }
        }
    }
}
