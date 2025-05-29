package shantAFX.Enemy;

import shantAFX.common.data.Entity;
public class Enemy extends Entity{
    public static final double ACCELERATION = 120;
    public static final double MAX_SPEED = 150;
    public static final double ROTATION_SPEED = 200;

    private double dx;
    private double dy;

    public double getDx() {return dx;}
    public double getDy() {return dy;}
    public void setDx(double dx) {this.dx = dx;}
    public void setDy(double dy) {this.dy = dy;}
}
