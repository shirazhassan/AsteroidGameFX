package shantAFX.asteroids;

import shantAFX.common.asteroids.IAsteroidSplitter;
import shantAFX.common.data.*;
import java.util.Random;

public class AsteroidSplitter implements IAsteroidSplitter {

    private static final Random R = new Random();

    @Override
    public void createSplitAsteroid(Entity ent, World world) {
        if (!(ent instanceof Asteroid parent)) return;
        AsteroidSize size = parent.getSize();
        if (!size.canSplit()) return;

        AsteroidSize childSize = size.nextSize();
        int fragments = size.randomFragments();

        double px = parent.getX(), py = parent.getY();
        double pdx = parent.getDx(), pdy = parent.getDy();
        double baseAngle = Math.toDegrees(Math.atan2(pdy, pdx));

        for (int i = 0; i < fragments; i++) {
            double angle = baseAngle + i*(360/fragments) + (R.nextDouble()-0.5)*30;
            double offset = (size.getBaseRadius() + childSize.getBaseRadius())/2;
            double nx = px + Math.cos(Math.toRadians(angle))*offset;
            double ny = py + Math.sin(Math.toRadians(angle))*offset;
            Asteroid c = new Asteroid(childSize, nx, ny, angle);

            // give some inherited velocity + burst
            double speed = childSize.getSpeed()*(0.5 + R.nextDouble());
            c.setDx(Math.cos(Math.toRadians(angle))*speed);
            c.setDy(Math.sin(Math.toRadians(angle))*speed);

            world.addEntity(c);
        }

        world.removeEntity(parent);
    }
}
