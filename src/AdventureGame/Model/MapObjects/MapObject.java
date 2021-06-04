package AdventureGame.Model.MapObjects;

import AdventureGame.Model.PhysicBodies.IModel;
import AdventureGame.Model.PhysicBodies.Rectangle;
import AdventureGame.Tools.Vector2;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class MapObject {

    private BufferedImage texture;
    protected IModel body;

    public IModel getBody() {
        return body;
    }

    private double rotation;

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        if (rotation > 360) {
            this.rotation = rotation - 360;
        } else if (rotation < 0) {
            this.rotation = 360 + rotation;
        } else this.rotation = rotation;
        body.setRotation(Math.toRadians(-rotation));
        body.updatePoints();
    }

    public BufferedImage getTexture() {
        return texture;
    }


    public MapObject(BufferedImage texture, IModel body) {
        this.texture = texture;
        this.body = body;
    }
}
