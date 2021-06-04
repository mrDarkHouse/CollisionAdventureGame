package AdventureGame;

import AdventureGame.Model.Hero;
import AdventureGame.Screens.DrawPanel;
import AdventureGame.Screens.EndScreenPanel;
import AdventureGame.Screens.HeroChoosePanel;

import javax.swing.*;

public class Main {

    public final static int FRAME_WIDTH = 1000;
    public final static int FRAME_HEIGHT = 700;

    public final static boolean DEBUG_TEXTURES = false;
    public final static boolean DEBUG_BODIES = true;

    public static Main main;
    private JFrame w;
    private DrawPanel panel;
    private HeroChoosePanel choosePanel;
    private EndScreenPanel endScreenPanel;

    public static void main(String[] args) {
        main = new Main();
    }

    public Main() {
        w = new JFrame();

        choosePanel = new HeroChoosePanel();
        w.add(choosePanel);

        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        w.setVisible(true);
    }

    public void startHeroChoose(){
        choosePanel.setVisible(true);
        w.remove(endScreenPanel);
    }
    public void exitGame(){
        System.exit(0);
    }

    public void startGame(Hero.HeroType type){
        panel = new DrawPanel(type);
        w.add(panel);
    }

    public void looseGame(){
        //w.remove(panel);
        panel.setVisible(false);
        endScreenPanel = new EndScreenPanel(false);
        endScreenPanel.setVisible(true);
        w.add(endScreenPanel);
        w.remove(panel);
    }
    public void winGame(){
        panel.setVisible(false);
        endScreenPanel = new EndScreenPanel(true);
        endScreenPanel.setVisible(true);
        w.add(endScreenPanel);
    }


}
