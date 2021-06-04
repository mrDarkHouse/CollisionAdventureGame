package AdventureGame.Model.MapObjects;

import AdventureGame.Model.PhysicBodies.IModel;

public class StaticObject {
    private IModel body;
    private boolean safe;

    public IModel getBody() {
        return body;
    }
    public boolean isSafe() {
        return safe;
    }

    public StaticObject(IModel body, boolean safe) {
        this.body = body;
        this.safe = safe;
    }
}
