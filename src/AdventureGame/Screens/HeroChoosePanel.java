package AdventureGame.Screens;

import AdventureGame.Main;
import AdventureGame.Model.Hero;
import AdventureGame.Model.Hero.*;
import AdventureGame.Model.MapObjects.MapObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HeroChoosePanel extends JPanel {

    private HeroChooseActor current;
    private HeroChooseActor[] actors;

    private class HeroChooseActor extends JComponent {
        private class HeroImage extends JComponent{
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(hero.getSprite(), 0, 0, getWidth(), getHeight(), null);
                g.setColor(Color.ORANGE);
                if(selected) {
                    g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                    g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
                    g.drawRect(2, 2, getWidth() - 5, getHeight() - 5);
                }
            }
        }
        private class HeroStat extends JComponent{
            public void init(){
                JLabel name = new JLabel(hero.getName());
                JLabel speed = new JLabel("Speed:");
                JLabel rotation = new JLabel("Handling:");
                JProgressBar moveSpeedBar = new JProgressBar();
                moveSpeedBar.setMinimum(45);
                moveSpeedBar.setMaximum(60);
                moveSpeedBar.setBounds(0, getHeight()/5*2, getWidth(), getHeight()/5);
                moveSpeedBar.setValue((int) (hero.getSpeed()*10));
                JProgressBar rotationSpeedBar = new JProgressBar();
                rotationSpeedBar.setMinimum(3);
                rotationSpeedBar.setMaximum(25);
                rotationSpeedBar.setBounds(0, getHeight()/5*4, getWidth(), getHeight()/5);
                rotationSpeedBar.setValue((int) (hero.getRotationSpeed()*10));
                name.setFont(new Font("Arial", Font.BOLD, 28));
                speed.setFont(new Font("Arial", Font.PLAIN, 20));
                rotation.setFont(new Font("Arial", Font.PLAIN, 20));
                name.setHorizontalAlignment(SwingConstants.CENTER);
                speed.setHorizontalAlignment(SwingConstants.CENTER);
                rotation.setHorizontalAlignment(SwingConstants.CENTER);
                name.setBounds(0, 0, getWidth(), getHeight()/5);
                speed.setBounds(0, getHeight()/5, getWidth(), getHeight()/5);
                rotation.setBounds(0, getHeight()/5*3, getWidth(), getHeight()/5);
                add(name);
                add(speed);
                add(moveSpeedBar);
                add(rotation);
                add(rotationSpeedBar);
            }
        }


        private Hero.HeroType hero;
        private boolean selected;
        public HeroType getHero() {
            return hero;
        }

        public HeroChooseActor(Hero.HeroType hero) {
            this.hero = hero;
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    select();
                }
            });
        }
        public void init(){
            HeroImage img = new HeroImage();
            img.setBounds(0, 0, getWidth(), getWidth());
            add(img);
            HeroStat stat = new HeroStat();
            stat.setBounds(0, getWidth(), getWidth(), getHeight() - getWidth());
            stat.init();
            add(stat);
        }

        public void select(){
            selected = true;
            current = HeroChooseActor.this;
            cancelAll(HeroChooseActor.this);
        }
        public void cancel(){
            selected = false;
        }
    }

    public void cancelAll(HeroChooseActor nonCancel){
        for (HeroChooseActor a:actors){
            if(a != nonCancel) a.cancel();
        }
        repaint();
    }

    public HeroChoosePanel() {
        super();
        setLayout(null);

        actors = new HeroChooseActor[4];

        actors[0] = new HeroChooseActor(HeroType.ANGRY_PENTAGON);
        actors[1] = new HeroChooseActor(HeroType.ANGRY_RECT);
        actors[2] = new HeroChooseActor(HeroType.ANGRY_CIRCLE);
        actors[3] = new HeroChooseActor(HeroType.ANGRY_TRIANGLE);

        JLabel chooseHero = new JLabel("CHOOSE YOUR HERO");
        chooseHero.setFont(new Font("Arial", Font.BOLD, 36));
        chooseHero.setBounds(Main.FRAME_WIDTH/2 - 200, 30, 400, 50);
        chooseHero.setHorizontalAlignment(SwingConstants.CENTER);
        JButton startGame = new JButton("Start Game");
        startGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main.main.startGame(current.hero);
                HeroChoosePanel.this.setVisible(false);
            }
        });
        startGame.setBounds(Main.FRAME_WIDTH - 150, Main.FRAME_HEIGHT - 100, 120, 50);
        add(startGame);
        add(chooseHero);

        int t = (Main.FRAME_WIDTH - 200)/actors.length;
        for (int i = 0; i < actors.length; i++) {
            actors[i].setLocation(150 + i*t, 200);
            actors[i].setSize(100, 300);
            actors[i].init();
            add(actors[i]);
        }
        actors[0].select();

        setVisible(true);
    }
}
