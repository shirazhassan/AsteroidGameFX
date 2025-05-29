package shantAFX.bulletsystem;

import shantAFX.common.data.Entity;
import javafx.scene.paint.Color;

public class Bullet extends Entity {
    private static final float SPEED = 800f;
    private final double dx, dy;

    public Bullet(double x, double y, double rotation) {
        setX(x);
        setY(y);
        setRotation(rotation);
        setRadius(3f);

        double[] coords = new double[12];
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            coords[2*i] = Math.cos(angle) * getRadius();
            coords[2*i+1] = Math.sin(angle) * getRadius();
        }
        setPolygonCoordinates(coords);

        double rad = Math.toRadians(rotation);
        this.dx = Math.cos(rad) * SPEED;
        this.dy = Math.sin(rad) * SPEED;
    }

    public double getDx() {return dx;}
    public double getDy() {return dy;}

    public Color getBulletColor() {
        return Color.BLACK;
    }
}
