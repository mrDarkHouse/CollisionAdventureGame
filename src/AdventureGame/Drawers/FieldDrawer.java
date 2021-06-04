package AdventureGame.Drawers;

import AdventureGame.Model.Field;
import AdventureGame.Model.MapObjects.StaticObject;
import AdventureGame.Model.PhysicBodies.Circle;
import AdventureGame.Model.PhysicBodies.Polygon;
import AdventureGame.Tools.ScreenConverter;
import AdventureGame.Tools.ScreenPoint;

import java.awt.*;
import java.util.ArrayList;

public class FieldDrawer extends AbstractDrawer{
    public static final Color SAFE_OBJECT_COLOR = new Color(0x155F0D);
    public static final Color NON_SAFE_OBJECT_COLOR = new Color(0x910A00);

    private Field field;

    public FieldDrawer(Field field) {
        this.field = field;
    }

    public void draw(Graphics2D g2, ScreenConverter converter){
        ScreenPoint tl = converter.r2s(field.getRectangle().getTopLeft());

        int w = converter.r2sDistanceH(field.getRectangle().getWidth());
        int h = converter.r2sDistanceV(field.getRectangle().getHeight());
        g2.setColor(Color.WHITE);
        g2.fillRect(tl.getX(), tl.getY(), w, h);

        g2.setColor(Color.BLACK);
        g2.drawRect(tl.getX(), tl.getY(), w, h);

    }
    public void drawObjects(Graphics2D g2, ScreenConverter converter){
        for (StaticObject m:field.getObjects()){
            if(m.getBody() instanceof Polygon){
                ArrayList<ScreenPoint> screenPoints = new ArrayList<>();
                for (int i = 0; i < ((Polygon) m.getBody()).getPoints().size(); i++) {
                    screenPoints.add(converter.r2s(((Polygon) m.getBody()).getPoints().get(i)));
                }
                int[] x = new int[screenPoints.size()];
                int[] y = new int[screenPoints.size()];
                for (int i = 0; i < screenPoints.size(); i++) {
                    x[i] = screenPoints.get(i).getX();
                    y[i] = screenPoints.get(i).getY();
                }

                if (m.isSafe()) g2.setColor(SAFE_OBJECT_COLOR);
                else            g2.setColor(NON_SAFE_OBJECT_COLOR);
                g2.fillPolygon(x, y, screenPoints.size());
                g2.setColor(Color.BLACK);
                g2.drawPolygon(x, y, screenPoints.size());
            }else if(m.getBody() instanceof Circle){
                ScreenPoint center = converter.r2s(m.getBody().getCenter());
                int r = converter.r2sDistanceH(((Circle) m.getBody()).getR());

                if (m.isSafe()) g2.setColor(SAFE_OBJECT_COLOR);
                else            g2.setColor(NON_SAFE_OBJECT_COLOR);
                g2.fillOval(center.getX() - r, center.getY() - r, r*2, r*2);
                g2.setColor(Color.BLACK);
                g2.drawOval(center.getX() - r, center.getY() - r, r*2, r*2);
            }

        }
    }
}
