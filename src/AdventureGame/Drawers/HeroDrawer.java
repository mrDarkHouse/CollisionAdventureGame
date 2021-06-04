package AdventureGame.Drawers;

import AdventureGame.Controls.HeroController;
import AdventureGame.Main;
import AdventureGame.Model.Hero;
import AdventureGame.Tools.ScreenConverter;
import AdventureGame.Tools.ScreenPoint;
import AdventureGame.Tools.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class HeroDrawer extends AbstractDrawer{
    private Hero hero;

    public HeroDrawer(Hero hero) {
        this.hero = hero;
    }

    public void draw(Graphics2D g2, ScreenConverter converter){
        ScreenPoint pc = converter.r2s(hero.getPosition());
        int rh = converter.r2sDistanceH(hero.getSize());
        int rv = converter.r2sDistanceV(hero.getSize());
        g2.setColor(Color.BLACK);
        AffineTransform old = g2.getTransform();
        ScreenPoint pos = converter.r2s(hero.getPosition());
        g2.rotate(Math.toRadians(hero.getRotation()), pos.getX(), pos.getY());
        g2.drawImage(hero.getTexture(), pc.getX() - rh, pc.getY() - rv,
                rh + rh, rv + rv, null);
        g2.setTransform(old);

        if(Main.DEBUG_BODIES) {
            drawDebug(g2, converter, hero);
        }
    }
}
