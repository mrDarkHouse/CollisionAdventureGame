package AdventureGame.Drawers;

import AdventureGame.Model.Field;
import AdventureGame.Tools.ScreenConverter;

import java.awt.*;

public class ScoreDrawer {
    private Field field;

    public ScoreDrawer(Field field) {
        this.field = field;
    }

    public void draw(Graphics2D g2, ScreenConverter converter){
        int x = converter.getSw() - 60;
        int y = converter.getSh() - 60;
        g2.setColor(Color.BLACK);
        g2.drawString(field.getChunksExplored() + "/" + field.getAllChunks(), x, y);
    }

}
