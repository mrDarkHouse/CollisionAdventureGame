package AdventureGame.Tools;

public class Vector2 {
    private double x, y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2(double x, double y) {
        if(Math.abs(x) < 1e-12) this.x = 0;
        else this.x = x;
        if(Math.abs(y) < 1e-12) this.y = 0;
        else this.y = y;
    }

    public Vector2 add(Vector2 other){
        return new Vector2(x + other.x, y + other.y);
    }
    public Vector2 multiply(double n){
        return new Vector2(x*n, y*n);
    }
    public double dotProduct(Vector2 other){
        return x*other.x + y*other.y;
    }
    public static Vector2 getAxisNormal(Vector2 p1, Vector2 p2){
        return new Vector2(-(p2.y-p1.y), p2.x - p1.x).normalized();
    }

    public Vector2 substract(Vector2 other){
        return add(new Vector2(-other.x, -other.y));
    }

    public Vector2 normalized(){
        double d = length();
        if(Math.abs(d) < 1e-15){
            return new Vector2(0, 0);
        }else return new Vector2(x/d, y/d);
    }

    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    //p = p0 + y0t + at^2/2


    @Override
    public String toString() {
        return x + " " + y;
    }
}
