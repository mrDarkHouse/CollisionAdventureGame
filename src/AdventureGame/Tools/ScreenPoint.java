package AdventureGame.Tools;

public class ScreenPoint {

    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ScreenPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
