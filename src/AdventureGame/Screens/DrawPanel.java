package AdventureGame.Screens;

import AdventureGame.Controls.CameraController;
import AdventureGame.Controls.HeroController;
import AdventureGame.Model.Field;
import AdventureGame.Model.Hero;
import AdventureGame.Model.World;
import AdventureGame.Model.PhysicBodies.Rectangle;
import AdventureGame.Tools.ScreenConverter;
import AdventureGame.Tools.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    public static ScreenConverter converter;
    private World world;
    private long t;
    private Timer physics;
    private Timer graphics;
    private HeroController heroController;
    private CameraController cameraController;


    public DrawPanel(Hero.HeroType type) {
        super();
        converter = new ScreenConverter(-1, 0, 20, 14, 1000, 700);
        Field field = new Field(new Rectangle(0, 0, 100, 100), 0.3, 9.8);
        Hero hero = new Hero(type, 1, 0.5, new Vector2(5, -5));

        heroController = new HeroController(hero);
        cameraController = new CameraController(converter);
        world = new World(field, hero, heroController);

        setFocusable(true);
        /*listeners.*/addKeyListener(heroController);
        /*listeners.*/addKeyListener(cameraController);


        t = System.currentTimeMillis();
        physics = new Timer(10, e -> {
            long nt = System.currentTimeMillis();
            world.update((nt - t)*0.001);
            repaint();
            t = nt;
        });
        physics.start();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, img.getWidth(), img.getHeight());
        world.draw(g2, converter);
        g.drawImage(img, 0, 0, null);
        g2.dispose();
    }
}
