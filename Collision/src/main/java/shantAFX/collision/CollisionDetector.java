package shantAFX.collision;

import shantAFX.common.asteroids.IAsteroidSplitter;
import shantAFX.common.services.IPostEntityProcessingService;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.data.Entity;

import java.util.*;

public class CollisionDetector implements IPostEntityProcessingService {

    private final IAsteroidSplitter splitter;

    public CollisionDetector() {
        this.splitter = ServiceLoader.load(IAsteroidSplitter.class).findFirst().orElse(new IAsteroidSplitter() {
            @Override
            public void createSplitAsteroid(Entity e, World w) {

            }
        });
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


                    if (!collides(e1, e2)) {
                        continue;
                    }

                    String type1 = e1.getType().toLowerCase(Locale.ROOT);
                    String type2 = e2.getType().toLowerCase(Locale.ROOT);

                    if(type1.equals("bullet") && (type2.equals("player") || type2.equals("enemy"))) {
                        e2.damage(1);
                        world.removeEntity(e1);
                        removedIds.add(e1.getID());
                        if (e2.isDead()){
                            world.removeEntity(e2);
                            removedIds.add(e2.getID());
                        }
                        break;
                    }
                    if (type2.equals("bullet") && (type1.equals("player") || type1.equals("enemy"))) {
                        e1.damage(1);
                        world.removeEntity(e2);
                        removedIds.add(e2.getID());
                        if (e1.isDead()){
                            world.removeEntity(e1);
                            removedIds.add(e1.getID());
                        }
                        break;
                    }


                    if(type1.equals("asteroid")) {
                        splitter.createSplitAsteroid(e1, world);
                    }
                        if(type2.equals("asteroid")) {
                            splitter.createSplitAsteroid(e2, world);
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
        double dx =  e1.getX() -  e2.getX();
        double dy =  e1.getY() - e2.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        float sumRadii = e1.getRadius() + e2.getRadius();

        return distance <= sumRadii;
    }
}
