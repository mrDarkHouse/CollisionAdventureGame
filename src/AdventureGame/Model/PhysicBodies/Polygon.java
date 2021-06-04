package AdventureGame.Model.PhysicBodies;

import AdventureGame.Model.MapObjects.StaticObject;
import AdventureGame.Tools.ScreenConverter;
import AdventureGame.Tools.ScreenPoint;
import AdventureGame.Tools.Vector2;

import java.awt.*;
import java.util.ArrayList;

import static AdventureGame.Drawers.FieldDrawer.NON_SAFE_OBJECT_COLOR;
import static AdventureGame.Drawers.FieldDrawer.SAFE_OBJECT_COLOR;

public class Polygon implements IModel{
    private Vector2 center;
    private double size;
    private ArrayList<Vector2> vertex;
    private double baseRotation;
    private double rotation;
    private int n;

    public Polygon(int n, double rotation) {
        this.n = n;
        this.baseRotation = rotation;
    }
    public IModel init(double size, Vector2 center){
        this.size = size;
        this.center = center;
        vertex = new ArrayList<>();
        double angle = Math.PI * 2 / n;
        for (int i = 0; i < n; i++) {
            double curAngle = baseRotation + i*angle + (Math.PI - angle)*0.5f;
            Vector2 p = new Vector2(
                    Math.cos(curAngle)*size + center.getX(),
                    Math.sin(curAngle)*size + center.getY()
            );
            vertex.add(p);
        }
        return this;
    }

    public double getRotation() {
        return rotation;
    }
    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public Vector2 getCenter() {
        return center;
    }

    @Override
    public void setCenter(Vector2 center) {
        this.center = center;
    }

    public void updatePoints(){
        double angle = Math.PI * 2 / vertex.size();
        for (int i = 0; i < vertex.size(); i++) {
            double curAngle = baseRotation + rotation + i*angle + (Math.PI - angle)*0.5f;
            Vector2 p = new Vector2(
                    Math.cos(curAngle)*size + center.getX(),
                    Math.sin(curAngle)*size + center.getY()
            );
            vertex.set(i, p);
        }
    }

//    @Override
    public ArrayList<Vector2> getPoints() {
        return vertex;
    }
}
