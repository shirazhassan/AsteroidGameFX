package shantAFX.Enemy;

import shantAFX.common.data.Entity;
import java.awt.*;

public class Enemy extends Entity{
    public static final double ACCELERATION = 120;
    public static final double MAX_SPEED = 150;
    public static final double ROTATION_SPEED = 140;

    private double dx;
    private double dy;

    public Enemy() {
        setPolygonCoordinates(new double[]{
                15, 0, -10, -10, -5, 0, -10, 10});
        setRadius(8f);
        setRotation(0);
        setHealth(2);
        setColor(Color.RED);
    }

    public double getDx() {return dx;}
    public double getDy() {return dy;}
    public void setDx(double dx) {this.dx = dx;}
    public void setDy(double dy) {this.dy = dy;}
}
