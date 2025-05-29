package shantAFX.asteroids;

import shantAFX.common.data.Entity;
import javafx.scene.paint.Color;
import java.util.Random;

public class Asteroid extends Entity {

    private static final Random rand = new Random();
    private final AsteroidSize size;
    private final float radius;
    private final double dx, dy;

    public Asteroid(AsteroidSize size, double x, double y, double directionDegrees) {
        this.size = size;

        float base = size.getBaseRadius();
        this.radius = base * (0.8f + rand.nextFloat() * 0.4f);
        setRadius(this.radius);

        setPolygonCoordinates(generateShape(this.radius));

        setX(x);
        setY(y);

        double rad = Math.toRadians(directionDegrees);
        double speed = size.getSpeed();
        this.dx = speed * Math.cos(rad);
        this.dy = speed * Math.sin(rad);

        setRotation(directionDegrees);
    }

    private double[] generateShape(float radius) {
        int verts = rand.nextInt(17) + 8;
        double[] coords = new double[verts * 2];
        double step = 2 * Math.PI / verts;

        for (int i = 0; i < verts; i++) {
            double angle = i * step;
            double rr = radius * (0.8 + rand.nextDouble() * 0.4);
            coords[2*i] = Math.cos(angle) * rr;
            coords[2*i+1] = Math.sin(angle) * rr;
        }
        return coords;
    }

    public AsteroidSize getSize() {
        return size;
    }
    public double getDx() {return dx;}
    public double getDy() {return dy;}

    /*
    @Override
    public Color getBaseColor() {
        return switch (size) {
            case GIANT, LARGE -> Color.GRAY.deriveColor(0,1,0.8,1);
            case MEDIUM -> Color.GRAY.deriveColor(0,1,1,1);
            case SMALL -> Color.GRAY.deriveColor(0,1,1.2,1);
        };
    }

     */
}
