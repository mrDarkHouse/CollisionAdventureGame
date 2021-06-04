package AdventureGame.Drawers;

import AdventureGame.Model.MapObjects.MapObject;
import AdventureGame.Model.PhysicBodies.Circle;
import AdventureGame.Model.PhysicBodies.Polygon;
import AdventureGame.Tools.ScreenConverter;
import AdventureGame.Tools.ScreenPoint;
import AdventureGame.Tools.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class AbstractDrawer {

    protected void drawDebug(Graphics2D g2, ScreenConverter converter, MapObject mapObject){
        if(mapObject.getBody() instanceof Polygon) {
            ArrayList<Vector2> points = ((Polygon) mapObject.getBody()).getPoints();
            ArrayList<ScreenPoint> toDraw = new ArrayList<>();
            int[] x = new int[points.size()];
            int[] y = new int[points.size()];

            for (int i = 0; i < points.size(); i++) {
                toDraw.add(converter.r2s(points.get(i)));
                x[i] = toDraw.get(i).getX();
                y[i] = toDraw.get(i).getY();
            }

            g2.setColor(Color.ORANGE);
            g2.drawPolygon(x, y, points.size());
        }else if(mapObject.getBody() instanceof Circle){
            ScreenPoint center = converter.r2s(mapObject.getBody().getCenter());
            int r = converter.r2sDistanceH(((Circle) mapObject.getBody()).getR());

            g2.setColor(Color.ORANGE);
            g2.drawOval(center.getX() - r, center.getY() - r, r*2, r*2);
        }
    }

}
