package shantAFX.playersystem;

import shantAFX.common.data.*;
import java.awt.*;

public class Player extends Entity {
    public static final double ACCELERATION = 120;
    public static final double MAX_SPEED = 150;
    public static final double ROTATION_SPEED = 140;
    private double dx = 0, dy = 0;

    public Player() {
        setPolygonCoordinates(new double[]{
                15, 0, -10, -10, -5, 0, -10, 10});
        setRadius(8f);
        setRotation(0);
        setHealth(5);
        setColor(Color.GREEN);
    }

    public double getDx() {return dx;}
    public double getDy() {return dy;}
    public void setDx(double dx) {this.dx = dx;}
    public void setDy(double dy) {this.dy = dy;}
}
