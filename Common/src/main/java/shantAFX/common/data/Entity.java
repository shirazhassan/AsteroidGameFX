package shantAFX.common.data;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Entity implements Serializable {


    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double rotation;
    private float radius;
    private String type = getClass().getSimpleName();
    private final Map<Class<?>, Object> components = new HashMap<>();
    private Color color = Color.BLUE;

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }


    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }


    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public <T> void addComponent(T component) {
        components.put(component.getClass(), component);
    }
    public <T> T getComponent(Class<T> componentClass) {
        return (T) components.get(componentClass);
    }
    public <T> void removeComponent(Class<T> componentClass) {
        components.remove(componentClass);
    }
    public <T> boolean hasComponent(Class<T> c) {
        return components.containsKey(c);
    }

    public Color getBaseColor() {
        return color;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}

