package AdventureGame.Controls;

import AdventureGame.Tools.ScreenConverter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CameraController implements KeyListener {

    private ScreenConverter converter;

    public CameraController(ScreenConverter converter) {
        this.converter = converter;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            converter.moveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            converter.moveRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            converter.moveUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            converter.moveDown();
        }
        if(e.getKeyCode() == KeyEvent.VK_I){
            converter.zoomIn();
            if(converter.getZoom() <= 0.03f) converter.setLocker(false);
        }
        if(e.getKeyCode() == KeyEvent.VK_O){
            converter.zoomOut();
            converter.setLocker(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
