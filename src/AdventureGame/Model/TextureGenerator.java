package AdventureGame.Model;

import AdventureGame.Main;
import AdventureGame.Tools.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TextureGenerator {

    public static BufferedImage genAngryTrig(Color color){
        BufferedImage heroTexture = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        int size = 50;
        int[] x = new int[3];
        int[] y = new int[3];

        double angle = Math.PI * 2 / 3;
        for (int i = 0; i < 3; i++) {
            double curAngle = Math.toRadians(90) + i*angle + (Math.PI - angle)*0.5f;
            Vector2 p = new Vector2(
                    Math.cos(curAngle)*size + 50,
                    Math.sin(curAngle)*size + 50
            );
            x[i] = (int) p.getX();
            y[i] = (int) p.getY();
        }
        Graphics2D g2 = heroTexture.createGraphics();
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, heroTexture.getWidth(), heroTexture.getHeight());


        g2.setColor(color);
        g2.fillPolygon(x, y, 3);

        g2.setColor(Color.RED);
        g2.fillOval(heroTexture.getWidth() - 30, y[2] - 5, 10, 10);

        return heroTexture;
    }

    public static BufferedImage genAngryPentagon(Color color){
        BufferedImage heroTexture = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        int size = 50;
        int n = 5;
        int[] x = new int[n];
        int[] y = new int[n];

        double angle = Math.PI * 2 / n;
        for (int i = 0; i < n; i++) {
            double curAngle = Math.toRadians(90) + i*angle + (Math.PI - angle)*0.5f;
            Vector2 p = new Vector2(
                    Math.cos(curAngle)*size + 50,
                    Math.sin(curAngle)*size + 50
            );
            x[i] = (int) p.getX();
            y[i] = (int) p.getY();
        }
        Graphics2D g2 = heroTexture.createGraphics();
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, heroTexture.getWidth(), heroTexture.getHeight());


        g2.setColor(color);
        g2.fillPolygon(x, y, n);

        g2.setColor(Color.RED);
        g2.fillOval(heroTexture.getWidth() - 30, y[3] - 15, 10, 10);
        g2.fillOval(heroTexture.getWidth() - 30, y[3] + 5, 10, 10);

        return heroTexture;
    }

    public static BufferedImage genAngryOval(Color color){
        BufferedImage heroTexture = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = heroTexture.createGraphics();
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, heroTexture.getWidth(), heroTexture.getHeight());
        g2.setColor(color);
        g2.fillOval(0, 0, heroTexture.getWidth(), heroTexture.getHeight());
        g2.setColor(Color.RED);
        g2.fillOval(heroTexture.getWidth() - 10, heroTexture.getHeight()/2 + 10, 10, 10);
        g2.fillOval(heroTexture.getWidth() - 10, heroTexture.getHeight()/2 - 10*2, 10, 10);

        if(Main.DEBUG_TEXTURES) {
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(5));
            g2.drawRect(0, 0, heroTexture.getWidth(), heroTexture.getHeight());
        }

        return heroTexture;
    }

    public static BufferedImage genAngryRect(Color color){
        BufferedImage heroTexture = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        int size = 50;
        int[] x = new int[4];
        int[] y = new int[4];
        double angle = Math.PI * 2 / 4;
        for (int i = 0; i < 4; i++) {
            double curAngle = i*angle + (Math.PI - angle)*0.5f;
            Vector2 p = new Vector2(
                    Math.cos(curAngle)*size + 50,
                    Math.sin(curAngle)*size + 50
            );
            x[i] = (int) p.getX();
            y[i] = (int) p.getY();
        }
        Graphics2D g2 = heroTexture.createGraphics();
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, heroTexture.getWidth(), heroTexture.getHeight());

        g2.setColor(color);
        g2.fillPolygon(x, y, 4);

        g2.setColor(Color.RED);
        g2.fillOval(heroTexture.getWidth() - 30, heroTexture.getHeight()/2 + 10, 10, 10);
        g2.fillOval(heroTexture.getWidth() - 30, heroTexture.getHeight()/2 - 10*2, 10, 10);
        return heroTexture;
    }

    private static BufferedImage genRect(Color color){
        BufferedImage heroTexture = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2 = heroTexture.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, heroTexture.getWidth(), heroTexture.getHeight());
        g2.setColor(color);
        g2.fillRect(1, 1, heroTexture.getWidth() - 2, heroTexture.getHeight() - 2);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(1, 1, heroTexture.getWidth() - 2, heroTexture.getHeight() - 2);

        return heroTexture;
    }
}
