package AdventureGame.Model;

import AdventureGame.Controls.HeroController;
import AdventureGame.Drawers.FieldDrawer;
import AdventureGame.Drawers.HeroDrawer;
import AdventureGame.Drawers.ScoreDrawer;
import AdventureGame.Main;
import AdventureGame.Model.MapObjects.MapObject;
import AdventureGame.Model.MapObjects.StaticObject;
import AdventureGame.Model.PhysicBodies.IModel;
import AdventureGame.Tools.ScreenConverter;
import AdventureGame.Tools.ScreenPoint;
import AdventureGame.Tools.Vector2;

import java.awt.*;

public class World {
    private static final boolean CHANGE_FOV = true;
    private static final double CAMERA_RANGE = 4d;
    private static final Vector2 UPDATE_RANGE = new Vector2(1, 1);

    private Field field;
    private Hero hero;
    private HeroController controller;

    private FieldDrawer fieldDrawer;
    private HeroDrawer heroDrawer;
    private ScoreDrawer scoreDrawer;

    public World(Field field, Hero hero, HeroController controller) {
        this.field = field;
        this.fieldDrawer = new FieldDrawer(field);
        this.hero = hero;
        this.controller = controller;
        this.heroDrawer = new HeroDrawer(hero);
        this.scoreDrawer = new ScoreDrawer(field);
    }

    public void update(double dt){
        Vector2 np = hero.getPosition()
                .add(hero.getVelocity().multiply(dt))
                .add(hero.getAcceleration().multiply(dt*dt*0.5));
        Vector2 nv = hero.getVelocity()
                .add(hero.getAcceleration().multiply(dt));

        double vx = nv.getX(), vy = nv.getY();
        boolean reset = false;
        if (np.getX() - hero.getSize() < field.getRectangle().getLeft()
                || np.getX() + hero.getSize() > field.getRectangle().getRight()) {
            vx = -vx;
            reset = true;
        }
        if (np.getY() - hero.getSize() < field.getRectangle().getBottom()
                || np.getY() + hero.getSize() > field.getRectangle().getTop()) {
            vy = -vy;
            reset = true;
        }

        if(!reset) {
            if(checkCollision(hero)){
                if(!hero.isCollided()){
                    hero.setCollided(true);
                    vx = -hero.getLookVector().getX() * hero.getVelocity().length();
                    vy = -hero.getLookVector().getY() * hero.getVelocity().length();
                    reset = true;
                }
            }else hero.setCollided(false);
        }

        nv = new Vector2(vx, vy);
        if (nv.length() < 1e-10)
            nv = new Vector2(0, 0);

        if (reset) {
            np = hero.getPosition();
        }

        controller.update(dt);
        Vector2 Fvn = controller.getMovement();
        Vector2 Ftr = hero.getVelocity().normalized().multiply(-field.getMu()*hero.getM()*field.getG());
        Vector2 F = Ftr.add(Fvn);

        hero.setAcceleration(F.multiply(1 / hero.getM()));
        hero.setVelocity(nv);
        hero.setPosition(np);

    }

    public boolean checkCollision(Hero hero){
        for (StaticObject model:field.getObjects()){
            if(IModel.isIntersects(hero.getBody(), model.getBody())){
                if(!model.isSafe()) Main.main.looseGame();
                return true;
            }
        }
        return false;
    }

    private void updateCameraPosition(ScreenConverter converter){
        double cameraRange = CAMERA_RANGE;

        if(hero.getPosition().getX() + hero.getSize() + cameraRange > converter.getRx() + converter.getRw()){
            double dx = hero.getPosition().getX() + hero.getSize() + cameraRange - (converter.getRx() + converter.getRw());
            converter.moveHorizontal(dx);
        }
        if(hero.getPosition().getX() - hero.getSize() - cameraRange < converter.getRx()){
            double dx = hero.getPosition().getX() - hero.getSize() - cameraRange - converter.getRx();
            converter.moveHorizontal(dx);
        }
        if(hero.getPosition().getY() + hero.getSize() + cameraRange > converter.getRy()){
            double dy = hero.getPosition().getY() + hero.getSize() + cameraRange - (converter.getRy());
            converter.moveVertical(dy);
        }

        if(hero.getPosition().getY() - hero.getSize() - cameraRange < converter.getRy() - converter.getRh()){
            double dy = hero.getPosition().getY() - hero.getSize() - cameraRange - (converter.getRy() - converter.getRh());
            converter.moveVertical(dy);
        }

        if(CHANGE_FOV && !converter.isLocker()) {
            int minSpeedCameraZoom = 3;
            int maxSpeedCameraZoom = 15;

            if (hero.getVelocity().length() > minSpeedCameraZoom) {
                if (hero.getVelocity().length() > maxSpeedCameraZoom) {
                    converter.setZoom(0.02 + 0.001 * maxSpeedCameraZoom - 0.003, hero.getPosition().getX(), hero.getPosition().getY());
                } else {
                    converter.setZoom(0.02 + 0.001 * hero.getVelocity().length() - 0.003, hero.getPosition().getX(), hero.getPosition().getY());
                }
            } else {
                converter.resetZoom();
            }
        }

        hero.setUpdateRange(UPDATE_RANGE);
        field.updateOpened(hero);
    }

    public void draw(Graphics2D g2, ScreenConverter converter){
        fieldDrawer.draw(g2, converter);
        updateCameraPosition(converter);
        drawOpenedRange(g2, converter);
        fieldDrawer.drawObjects(g2, converter);
        heroDrawer.draw(g2, converter);
        scoreDrawer.draw(g2, converter);
    }
    private void drawOpenedRange(Graphics2D g2, ScreenConverter converter){
        for (int i = 0; i < field.getChuncks().length; i++) {
            for (int j = 0; j < field.getChuncks()[0].length; j++) {
                if(field.getChuncks()[i][j]){
                    ScreenPoint v = converter.r2s(new Vector2(
                            field.getRectangle().getTopLeft().getX() + j*Field.CHUNCK_SIZE,
                            field.getRectangle().getTopLeft().getY() - i*Field.CHUNCK_SIZE
                    ));
                    int w = converter.r2sDistanceH(Field.CHUNCK_SIZE);
                    int h = converter.r2sDistanceV(Field.CHUNCK_SIZE);
                    g2.setColor(new Color(0, 0, 255, 60));
                    g2.fillRect(v.getX(), v.getY(), w, h);
                }
            }
        }
    }
}
