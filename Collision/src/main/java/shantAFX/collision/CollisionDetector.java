package shantAFX.collision;

import shantAFX.common.services.IPostEntityProcessingService;
import shantAFX.common.data.GameData;
import shantAFX.common.data.World;
import shantAFX.common.data.Entity;

public class CollisionDetector implements IPostEntityProcessingService {
    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {

                if (e1.getID().equals(e2.getID())) {
                    continue;
                }

                if (this.collides(e1, e2)) {
                    world.removeEntity(e1);
                    world.removeEntity(e2);
                }
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
