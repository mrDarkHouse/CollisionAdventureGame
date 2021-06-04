package AdventureGame.Model.PhysicBodies;

import AdventureGame.Tools.Vector2;

import java.util.ArrayList;

public class Rectangle implements IModel {
    private double left, top, width, height;

    public Rectangle(Vector2 topLeft, Vector2 bottomRight) {
        this(
                Math.min(topLeft.getX(), bottomRight.getX()),
                Math.max(topLeft.getY(), bottomRight.getY()),
                Math.abs(topLeft.getX() - bottomRight.getX()),
                Math.abs(topLeft.getY() - bottomRight.getY())
        );
    }

    public Rectangle(double left, double top, double width, double height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public double getBottom() {
        return top - height;
    }
    public double getLeft() {
        return left;
    }
    public double getRight() {
        return left + width;
    }
    public double getTop() {
        return top;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }

    public boolean isInside(Vector2 p) {
        return getLeft() < p.getX() && p.getX() < getRight() && getBottom() < p.getY() && p.getY() < getTop();
    }
    public Vector2 getCenter() {
        return new Vector2((getLeft() + getRight()) * 0.5, (getTop() + getBottom()) * 0.5);
    }

    @Override
    public void setCenter(Vector2 center) {
        this.left = center.getX() - width/2;
        this.top = center.getY() + height/2;
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

    public Vector2 getSize() {
        return new Vector2(getWidth(), getHeight());
    }
    public Vector2 getTopLeft() {
        return new Vector2(left, top);
    }
    public Vector2 getBottomRight() {
        return new Vector2(left + width, top - height);
    }
    public ArrayList<Vector2> getPoints(){
        return new ArrayList<Vector2>(){{
            add(getTopLeft());
            add(new Vector2(getRight(), getTop()));
            add(getBottomRight());
            add(new Vector2(getLeft(), getBottom()));
        }};
    }

    @Override
    public IModel init(double size, Vector2 center) {
        return null;
    }


    public static boolean isInside(Vector2 center, double a, double b, Vector2 point){
        return point.getX() > center.getX() - a && point.getX() < center.getX() + a &&
               point.getY() > center.getY() - b && point.getY() < center.getY() + b;
    }

    //Separate Axis Theorem
//    public static boolean isIntersects(Rectangle a, Rectangle b){
//        return true;
//    }

    @Override
    public String toString() {
        return "Rect: Left=" + getLeft() + " Top=" + getTop() + " Width=" + width + " Height=" + height;
    }
}
