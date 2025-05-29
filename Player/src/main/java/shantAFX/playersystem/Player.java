package shantAFX.playersystem;

import shantAFX.common.data.*;
import shantAFX.common.bullet.IBulletSPI;
import shantAFX.common.services.IGamePluginService;
import shantAFX.common.services.IEntityProcessingService;

import java.awt.*;
import java.util.ServiceLoader;

public class Player extends Entity {
    public static final double ACCELERATION = 120;
    public static final double MAX_SPEED = 150;
    public static final double ROTATION_SPEED = 200;
    private double dx = 0, dy = 0;

    public Player() {
        setPolygonCoordinates(new double[]{0f, -15f, 10f, 0f, 0f, 15f, -10f, 0f});
        setRadius(12f);
        setRotation(0);
        setColor(Color.BLUE);
    }

    public double getDx() {return dx;}
    public double getDy() {return dy;}
    public void setDx(double dx) {this.dx = dx;}
    public void setDy(double dy) {this.dy = dy;}
}
