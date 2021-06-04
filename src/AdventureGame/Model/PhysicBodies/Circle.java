package AdventureGame.Model.PhysicBodies;

import AdventureGame.Tools.Vector2;

import java.util.ArrayList;

public class Circle implements IModel{
    private Vector2 center;
    private double r;

    public Circle() {
    }

    @Override
    public Vector2 getCenter() {
        return center;
    }

    public double getR() {
        return r;
    }

    @Override
    public void setCenter(Vector2 center) {
        this.center = center;
    }

    @Override
    public void updatePoints() {

    }

    @Override
    public void setRotation(double rotation) {

    }

    @Override
    public double getRotation() {
        return 0;
    }

    @Override
    public IModel init(double size, Vector2 center) {
        this.center = center;
        this.r = size;
        return this;
    }
}
