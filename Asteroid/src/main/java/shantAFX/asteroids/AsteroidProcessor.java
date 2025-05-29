package shantAFX.asteroids;

import shantAFX.common.data.*;
import shantAFX.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {
    private float timer = 0.0f;
    private static final float SPAWN_INTERVAL = 5f;

    @Override
    public void process(GameData gameData, World world) {
        float dt = gameData.getDeltaTime();

        for (Entity e : world.getEntities(Asteroid.class)) {
            Asteroid a = (Asteroid) e;
            a.setX(a.getX() + a.getDx()*dt);
            a.setY(a.getY() + a.getDy()*dt);

            double w = gameData.getDisplayWidth(), h = gameData.getDisplayHeight(), r = a.getRadius();
            if (a.getX() < -r) a.setX(w + r);
            if (a.getX() > w + r) a.setX(-r);
            if (a.getY() < -r) a.setY(h + r);
            if (a.getY() > h + r) a.setY(-r);
        }

        timer += dt;
        if (timer >= SPAWN_INTERVAL) {
            timer =0f;
            AsteroidFactory.spawn(gameData, world, AsteroidFactory.getRandomSize());
        }
    }
}
