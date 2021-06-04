package AdventureGame.Model;

import AdventureGame.Model.MapObjects.MapObject;
import AdventureGame.Model.PhysicBodies.Circle;
import AdventureGame.Model.PhysicBodies.IModel;
import AdventureGame.Model.PhysicBodies.Polygon;
import AdventureGame.Tools.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hero extends MapObject {
    private double m, size;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Vector2 updateRange;//updates visible
    private boolean isCollided;

    public boolean isCollided() {
        return isCollided;
    }
    public void setCollided(boolean collided) {
        isCollided = collided;
    }

    //hero stats
    private double moveSpeed;
    private double rotationSpeed;

    public double getMoveSpeed() {
        return moveSpeed;
    }
    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public enum HeroType{
        ANGRY_TRIANGLE("Kek",
                TextureGenerator.genAngryTrig(new Color(0x20191B)),
                new Polygon(3, Math.toRadians(90)),
                2.2, 5.7
        ),
        ANGRY_RECT("Doc",
                TextureGenerator.genAngryRect(new Color(0x20191B)),
                new Polygon(4, Math.toRadians(0)),
                0.9, 5.3
        ),
        ANGRY_PENTAGON("Bob",
                TextureGenerator.genAngryPentagon(new Color(0x20191B)),
                new Polygon(5, Math.toRadians(90)),
                0.5, 5
        ),
        ANGRY_CIRCLE("Karl",
                TextureGenerator.genAngryOval(new Color(0x20191B)),
                new Circle(),
                1.5, 6.0
        );


        private String name;
        private BufferedImage sprite;
        private IModel body;
        private double rotationSpeed;
        private double speed;

        public String getName() {
            return name;
        }
        public BufferedImage getSprite() {
            return sprite;
        }
        public IModel getBody() {
            return body;
        }
        public double getRotationSpeed() {
            return rotationSpeed;
        }
        public double getSpeed() {
            return speed;
        }

        HeroType(String name, BufferedImage sprite, IModel body, double rotationSpeed, double speed) {
            this.name = name;
            this.sprite = sprite;
            this.body = body;
            this.rotationSpeed = rotationSpeed;
            this.speed = speed;
        }
    }

    public Hero(HeroType type, double m, double size, Vector2 position) {
        super(type.sprite, type.body);
        getBody().init(size, position);
        this.m = m;
        this.size = size;
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
        this.updateRange = new Vector2(0, 0);

        this.moveSpeed = type.speed;
        this.rotationSpeed = type.rotationSpeed;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }
    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }
    public double getM() {
        return m;
    }
    public void setM(double m) {
        this.m = m;
    }
    public Vector2 getPosition() {
        return body.getCenter();
    }
    public void setPosition(Vector2 position) {
        body.setCenter(position);
        body.updatePoints();
    }
    public double getSize() {
        return size;
    }
    public Vector2 getVelocity() {
        return velocity;
    }
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getLookVector(){
        return new Vector2(Math.cos(Math.toRadians(getRotation())), -Math.sin(Math.toRadians(getRotation())));
    }

    public Vector2 getUpdateRange() {
        return updateRange;
    }
    public void setUpdateRange(Vector2 updateRange) {
        this.updateRange = updateRange;
    }
}
