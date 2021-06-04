package AdventureGame.Model.PhysicBodies;

import AdventureGame.Model.MapObjects.MapObject;
import AdventureGame.Tools.RandomTool;
import AdventureGame.Tools.Vector2;

import java.util.ArrayList;

public interface IModel {
    Vector2 getCenter();
    void setCenter(Vector2 center);
    void updatePoints();
    void setRotation(double rotation);
    double getRotation();
    IModel init(double size, Vector2 center);


    static boolean checkPolygonPolygon(Polygon a, Polygon b){

        for (int i = 0; i < a.getPoints().size(); i++) {
            Vector2 vAxis;
            double min0;
            double max0;
            double min1;
            double max1;

            if(i == a.getPoints().size() - 1)
                vAxis = Vector2.getAxisNormal(a.getPoints().get(i), a.getPoints().get(0));
            else vAxis = Vector2.getAxisNormal(a.getPoints().get(i), a.getPoints().get(i + 1));

            min0 = vAxis.dotProduct(a.getPoints().get(0));
            max0 = min0;

            for (int j = 1; j < a.getPoints().size(); j++) {
                double t = vAxis.dotProduct(a.getPoints().get(j));
                if(t < min0) min0 = t;
                if(t > max0) max0 = t;
            }

            min1 = vAxis.dotProduct(b.getPoints().get(0));
            max1 = min1;

            for (int j = 1; j < b.getPoints().size(); j++) {
                double t = vAxis.dotProduct(b.getPoints().get(j));
                if(t < min1) min1 = t;
                if(t > max1) max1 = t;
            }
            double d0 = min0 - max1;
            double d1 = min1 - max0;

            if(d0 > 0 || d1 > 0){
                return false;
            }
        }
        return true;
    }
    static boolean checkCirclePolygon(Circle a, Polygon b){
        for (int i = 0; i < b.getPoints().size(); i++) {
            Vector2 vAxis;
            double min0;
            double max0;
            double min1;
            double max1;

            if(i == b.getPoints().size() - 1)
                vAxis = Vector2.getAxisNormal(b.getPoints().get(i), b.getPoints().get(0));
            else vAxis = Vector2.getAxisNormal(b.getPoints().get(i), b.getPoints().get(i + 1));

            min0 = vAxis.dotProduct(b.getPoints().get(0));
            max0 = min0;

            for (int j = 1; j < b.getPoints().size(); j++) {
                double t = vAxis.dotProduct(b.getPoints().get(j));
                if (t < min0) min0 = t;
                if (t > max0) max0 = t;
            }

            min1 = vAxis.dotProduct(new Vector2(a.getCenter().getX(), a.getCenter().getY()));
            max1 = min1 + a.getR();
            min1 = min1 - a.getR();

            double d0 = min0 - max1;
            double d1 = min1 - max0;

//            System.out.println(d0 + " " + d1);

            if (d0 > 0 || d1 > 0) {
                return false;
            }
        }

        return true;
    }
    static boolean checkCircleCircle(Circle a, Circle b){
        double dist = Math.pow(b.getCenter().getX() - a.getCenter().getX(), 2) +
                Math.pow(b.getCenter().getY() - a.getCenter().getY(), 2);
        double r = Math.pow(a.getR() + b.getR(), 2);
        return dist < r;
    }

    static boolean isIntersects(IModel a, IModel b){
        if(a instanceof Polygon && b instanceof Polygon)
            return checkPolygonPolygon(((Polygon) a), ((Polygon) b)) &&
                    checkPolygonPolygon(((Polygon) b), ((Polygon) a));
        if(a instanceof Circle && b instanceof Polygon)
            return checkCirclePolygon(((Circle) a), ((Polygon) b));
        if(a instanceof Polygon && b instanceof Circle)
            return checkCirclePolygon(((Circle) b), ((Polygon) a));
        if(a instanceof Circle && b instanceof Circle)
            return checkCircleCircle(((Circle) a), ((Circle) b));
        return false;
    }

    static IModel getRandomObject(){
        int key = RandomTool.getRandInt(0, 6);
        if(key == 0) return new Circle();
        else return new Polygon(key + 2, RandomTool.getRand(Math.toRadians(0), Math.toRadians(359)));
    }
}
