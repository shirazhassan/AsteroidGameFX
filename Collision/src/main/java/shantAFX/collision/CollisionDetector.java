package shantAFX.collision;

import shantAFX.asteroids.Asteroid;
import shantAFX.common.asteroids.IAsteroidSplitter;
import shantAFX.common.services.IPostEntityProcessingService;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.data.Entity;
import shantAFX.playersystem.Player;
import shantAFX.Enemy.Enemy;
import shantAFX.bulletsystem.Bullet;

import java.util.*;

public class CollisionDetector implements IPostEntityProcessingService {

    private final IAsteroidSplitter splitter;

    public CollisionDetector() {
        this.splitter = ServiceLoader.load(IAsteroidSplitter.class).findFirst().orElseThrow();
    }

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> snapShot = new ArrayList<>(world.getEntities());
        Set<String> removedIds = new HashSet<>();

        for (int i = 0; i < snapShot.size(); i++ ) {
            Entity e1 = snapShot.get(i);

                if (removedIds.contains(e1.getID())) {
                    continue;
                }

                for (int j = i + 1; j < snapShot.size(); j++) {
                    Entity e2 = snapShot.get(j);
                    if (removedIds.contains(e2.getID())) {
                        continue;
                    }


                    if (!collides(e1, e2)) continue;

                    if(e1 instanceof Bullet && (e2 instanceof Player || e2 instanceof Enemy)) {
                        ((Entity) e2).damage(1);
                        world.removeEntity(e1);
                        removedIds.add(e1.getID());
                        if (e2.isDead()){
                            world.removeEntity(e2);
                            removedIds.add(e2.getID());
                        }
                        break;
                    } else if (e2 instanceof Bullet && (e1 instanceof  Player || e1 instanceof Enemy)) {
                        ((Entity) e1).damage(1);
                        world.removeEntity(e2);
                        removedIds.add(e2.getID());
                        if (e1.isDead()){
                            world.removeEntity(e1);
                            removedIds.add(e1.getID());
                        }
                        break;
                    }


                    if(e1 instanceof Asteroid) {
                        splitter.createSplitAsteroid((Asteroid)e1, world);
                    }
                        if(e2 instanceof Asteroid) {
                            splitter.createSplitAsteroid((Asteroid)e2, world);
                        }
                    world.removeEntity(e1);
                    world.removeEntity(e2);
                    removedIds.add(e1.getID());
                    removedIds.add(e2.getID());
                    break;

            }
        }
    }

    public boolean collides(Entity e1, Entity e2) {
        float dx = (float) e1.getX() - (float) e2.getX();
        float dy = (float) e1.getY() - (float) e2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (e1.getRadius() + e2.getRadius());
    }
}
