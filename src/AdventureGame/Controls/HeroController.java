package AdventureGame.Controls;

import AdventureGame.Model.Hero;
import AdventureGame.Tools.Vector2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HeroController implements KeyListener {
    private Hero hero;
    private Vector2 movement;

    private int moveType;
    private boolean leftRotation, rightRotation;

    public int getMoveType() {
        return moveType;
    }
    public boolean isLeftRotation() {
        return leftRotation;
    }
    public boolean isRightRotation() {
        return rightRotation;
    }

    private double curPower = 0;
    private double curTime = 0;

    public Vector2 getMovement() {
        return movement;
    }

    public HeroController(Hero hero) {
        this.hero = hero;
        movement = new Vector2(0, 0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            if(moveType != 1){
                curTime = 0;
                moveType = 1;
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_S) {
            if(moveType != 0){
                curTime = 0;
                moveType = 0;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_A) leftRotation = true;
        if(e.getKeyCode() == KeyEvent.VK_D) rightRotation = true;
    }

    public void update(double dt){
        if(moveType == 1)       movement = hero.getLookVector().multiply(getPower());
        else if(moveType == 2)  movement = hero.getLookVector().multiply(getPower());
//        else if(moveType == 0)  movement = hero.getLookVector().multiply(getPower());
        else                    movement = new Vector2(0, 0);

//        if(moveType == 0) curTime = 0;
        /*else*/ curTime += dt;

        if(leftRotation)  {
            hero.setRotation(hero.getRotation() - getRotateSpeed());
//            if(moveType == 2) hero.setRotation(hero.getRotation() - getRotateSpeed());
//            else hero.setRotation(hero.getRotation() - ROTATE_SPEED);
        }
        if(rightRotation) {
            hero.setRotation(hero.getRotation() + getRotateSpeed());
//            if(moveType == 2) hero.setRotation(hero.getRotation() + getRotateSpeed());
//            else hero.setRotation(hero.getRotation() + ROTATE_SPEED);
        }
    }

    private double getRotateSpeed(){
//        double v = -Math.pow(0.5, hero.getVelocity().length()) + 1;
//        System.out.println(hero.getAcceleration().length());
//        double v = Math.pow(hero.getVelocity().length(), 0.6);
        double v = (1.0 + hero.getRotationSpeed())/(hero.getVelocity().length() + 1) + hero.getRotationSpeed();
//        System.out.println(v);
        return v;
//        return ROTATE_SPEED * 0.5;
    }

    private double getPower(){
        //curPower = -Math.log(curTime/2 + 1) + MAX_POWER;
        if(moveType == 1) curPower = -Math.atan(curTime/3) + hero.getMoveSpeed();
        if(moveType == 2) curPower = -Math.log(5*curTime + 1) + 5;
//        if(moveType == 0) curPower = -Math.log(5*curTime + 1) + 3;
//        if(curPower >= MAX_POWER) curPower = MAX_POWER;
//        System.out.println(curPower);
        return curPower;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) moveType = 2;
        if(e.getKeyCode() == KeyEvent.VK_S) moveType = 0;
        if(e.getKeyCode() == KeyEvent.VK_A) leftRotation = false;
        if(e.getKeyCode() == KeyEvent.VK_D) rightRotation = false;
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S){
            curTime = 0;
        }

//        if(moveType == 0){
////            movement.setX(0);
////            movement.setY(0);
//        }
    }
}
