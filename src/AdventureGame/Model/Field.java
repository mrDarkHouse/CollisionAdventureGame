package AdventureGame.Model;

import AdventureGame.Main;
import AdventureGame.Model.MapObjects.MapObject;
import AdventureGame.Model.MapObjects.StaticObject;
import AdventureGame.Model.PhysicBodies.Circle;
import AdventureGame.Model.PhysicBodies.IModel;
import AdventureGame.Model.PhysicBodies.Polygon;
import AdventureGame.Tools.RandomTool;
import AdventureGame.Model.PhysicBodies.Rectangle;
import AdventureGame.Tools.Vector2;

import java.util.ArrayList;

public class Field {
    private Rectangle rectangle;
    private ArrayList<StaticObject> objects;
    private double mu, g;

    private boolean[][] chuncks;
    private boolean[][] initialized;

    private int chunksExplored;

    public int getChunksExplored() {
        return chunksExplored;
    }
    public int getAllChunks(){
        return chuncks.length * chuncks[0].length;
    }

    public boolean[][] getChuncks() {
        return chuncks;
    }

    public static double CHUNCK_SIZE = 10;

    public ArrayList<StaticObject> getObjects() {
        return objects;
    }

    public Field(Rectangle rectangle, double mu, double g) {
        this.rectangle = rectangle;
        chuncks = new boolean[(int) (rectangle.getHeight()/CHUNCK_SIZE)][(int) (rectangle.getWidth()/CHUNCK_SIZE)];
        initialized = new boolean[chuncks.length][chuncks[0].length];
        this.mu = mu;
        this.g = g;
        generateMap();
    }

    public void generateMap(){
        objects = new ArrayList<>();
    }

    public void updateOpened(Hero hero){
        Vector2[] points = new Vector2[4];
        points[0] = new Vector2(
                hero.getPosition().getX() - hero.getUpdateRange().getX(),
                rectangle.getTop() - hero.getPosition().getY() - hero.getUpdateRange().getY());
        points[1] = new Vector2(
                hero.getPosition().getX() - hero.getUpdateRange().getX(),
                rectangle.getTop() - hero.getPosition().getY() + hero.getUpdateRange().getY());
        points[2] = new Vector2(
                hero.getPosition().getX() + hero.getUpdateRange().getX(),
                rectangle.getTop() - hero.getPosition().getY() - hero.getUpdateRange().getY());
        points[3] = new Vector2(
                hero.getPosition().getX() + hero.getUpdateRange().getX(),
                rectangle.getTop() - hero.getPosition().getY() + hero.getUpdateRange().getY());

        for (Vector2 v:points){
            int i = (int) (v.getY() / CHUNCK_SIZE);
            int j = (int) (v.getX() / CHUNCK_SIZE);
            if(i < chuncks[0].length && j < chuncks.length) {
                if (!chuncks[i][j]) {
                    chuncks[i][j] = true;
                    chunksExplored++;
                    checkWin();
                    generateObjectsInChunk(i, j, hero);
                }
            }
        }
    }

    private void checkWin(){
        if(getChunksExplored() == getAllChunks()){
            Main.main.winGame();
        }
    }

    private Vector2 getChunkCenter(int i, int j){
        return new Vector2(j*CHUNCK_SIZE + CHUNCK_SIZE/2, rectangle.getTop() - (i*CHUNCK_SIZE + CHUNCK_SIZE/2));
    }

    private void generateObjectsInChunk(int i, int j, Hero hero){
        boolean a =                                      !initialized[i][j];
        boolean l = j - 1 >= 0                        && !initialized[i][j - 1];
        boolean r = j + 1 < initialized[0].length     && !initialized[i][j + 1];
        boolean u = i - 1 >= 0                        && !initialized[i - 1][j];
        boolean d = i + 1 < initialized.length        && !initialized[i + 1][j];

        if(a){
//            Vector2 spawnPosition = getChunkCenter(i, j);
//            Vector2 size = new Vector2(2, 2);
//            objects.add(new SolidBlock(
//                    spawnPosition.getX() - size.getX()/2,
//                    spawnPosition.getY() + size.getY()/2,
//                    size.getX(),
//                    size.getY())
//            );
        }

        if(a && r){
            Vector2 spawnPosition = getChunkCenter(i, j).add(new Vector2(CHUNCK_SIZE/2, 0));
            Vector2 dist = hero.getPosition().substract(spawnPosition);
            double yOffset = RandomTool.getRand(
                    dist.length() < 6 ? (dist.getY() < 0 ? 0 : -4) : -4,
                    dist.length() < 6 ? (dist.getY() < 0 ? 4 : 0)  : 4
            );
            generateBlock(spawnPosition.add(new Vector2(0, yOffset)));
        }
        if(a && l){
            Vector2 spawnPosition = getChunkCenter(i, j).add(new Vector2(-CHUNCK_SIZE/2, 0));
            Vector2 dist = hero.getPosition().substract(spawnPosition);
            double yOffset = RandomTool.getRand(
                    dist.length() < 6 ? (dist.getY() < 0 ? 0 : -4) : -4,
                    dist.length() < 6 ? (dist.getY() < 0 ? 4 : 0)  : 4
            );
            generateBlock(spawnPosition.add(new Vector2(0, yOffset)));
        }
        if(a && u){
            Vector2 spawnPosition = getChunkCenter(i, j).add(new Vector2(0, CHUNCK_SIZE/2));
            Vector2 dist = hero.getPosition().substract(spawnPosition);
            double xOffset = RandomTool.getRand(
                    dist.length() < 6 ? (dist.getX() < 0 ? 0 : -4) : -4,
                    dist.length() < 6 ? (dist.getX() < 0 ? 4 : 0)  : 4
            );
            generateBlock(spawnPosition.add(new Vector2(xOffset, 0)));
        }
        if(a && d){
            Vector2 spawnPosition = getChunkCenter(i, j).add(new Vector2(0, -CHUNCK_SIZE/2));
            Vector2 dist = hero.getPosition().substract(spawnPosition);
            double xOffset = RandomTool.getRand(
                    dist.length() < 6 ? (dist.getX() < 0 ? 0 : -4) : -4,
                    dist.length() < 6 ? (dist.getX() < 0 ? 4 : 0)  : 4
            );
            generateBlock(spawnPosition.add(new Vector2(xOffset, 0)));
        }

        initialized[i][j] = true;

    }

    private void generateBlock(Vector2 spawnPosition){
        objects.add(new StaticObject(
                IModel.getRandomObject().init(
                        RandomTool.getRand(1, 1.8),
                        spawnPosition
                ),
                RandomTool.half()
        ));
    }


    public double getG() {
        return g;
    }
    public void setG(double g) {
        this.g = g;
    }

    public double getMu() {
        return mu;
    }
    public void setMu(double mu) {
        this.mu = mu;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public ArrayList<MapObject> getObjectsInRange(Vector2 position, Vector2 range){
        ArrayList<MapObject> current = new ArrayList<>();
//        for (MapObject mo:objects){
//            Rectangle r = new Rectangle(
//                    new Vector2(position.getX() - range.getX(), position.getY() - range.getY()),
//                    new Vector2(position.getX() + range.getX(), position.getY() + range.getY()));
//            if(r.isInside(mo.getCenter())){
//                current.add(mo);
//            }
//        }
        return current;
    }



}
