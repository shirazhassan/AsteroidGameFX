package shantAFX.asteroids;

import shantAFX.common.data.GameData;
import shantAFX.common.data.World;

public class AsteroidFactory {
    public static final int INITIAL_COUNT = 10;
    public static final int MAX_ASTEROIDS = 120;
    public static final double SPAWN_BUFFER = 200.0;

    public static void spawn(GameData gameData, World world, AsteroidSize size) {
        long existing = world.getEntities(Asteroid.class).size();
        if (existing >= MAX_ASTEROIDS) return;

        double[] pos = randomEdge(gameData, size.getBaseRadius());

        double dx2 = pos[0]*pos[0] + pos[1]*pos[1];
    if (dx2 < SPAWN_BUFFER*SPAWN_BUFFER) {
        double d = Math.sqrt(dx2);
        double factor = (SPAWN_BUFFER + size.getBaseRadius()) / (d==0?1:d);
        pos[0] *= factor;
        pos[1] *= factor;
    }
    double dir = Math.random()*360;
    Asteroid a = new Asteroid(size, pos[0], pos[1], dir);
    world.addEntity(a);
    }

    private static double[] randomEdge(GameData gameData, double radius) {
        double w = gameData.getDisplayWidth();
        double h = gameData.getDisplayHeight();
        int edge = (int)(Math.random()*4);
        return switch(edge) {
            case 0 -> new double[]{Math.random()*w, -radius};
            case 1 -> new double[]{w+radius, Math.random()*h};
            case 2 -> new double[]{Math.random()*w, h + radius};
            default -> new double[]{-radius, Math.random()*h};
        };
    }

    public static AsteroidSize getRandomSize() {
        double r = Math.random();
        if (r < 0.2)    return AsteroidSize.GIANT;
        else if (r < 0.8) return AsteroidSize.LARGE;
        else if (r < 0.8) return AsteroidSize.MEDIUM;
        else            return AsteroidSize.SMALL;
    }
}
