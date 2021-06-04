package AdventureGame.Tools;

public class ScreenConverter {
    private double rx, ry, rw, rh;
    private int sw, sh;
    private double zoom /*= 1*/;
    private boolean locker;

    public ScreenPoint r2s(Vector2 p){
        int x=(int)(sw*(p.getX()-rx)/rw);
        int y=(int)(sh*(ry-p.getY())/rh);
        return new ScreenPoint(x,y);
    }

    public Vector2 s2r(ScreenPoint p){
        double x=rx+p.getX()*rw/sw;
        double y=ry-p.getY()*rh/sh;
        return new Vector2(x,y);
    }

    public int r2sDistanceH(double d) {
        return r2s(new Vector2(d, 0)).getX() - r2s(new Vector2(0, 0)).getX();
    }

    public int r2sDistanceV(double d) {
        return r2s(new Vector2(0, 0)).getY() - r2s(new Vector2(0, d)).getY();
    }

    public ScreenConverter(double rx, double ry, double rw, double rh, int sw, int sh) {
        this.rx = rx;
        this.ry = ry;
        this.rw = rw;
        this.rh = rh;
        this.sw = sw;
        this.sh = sh;
        zoom = rw/sw;
//        System.out.println(zoom);
    }

    public double getZoom() {
        return zoom;
    }

    public double getRx() {
        return rx;
    }

    public double getRy() {
        return ry;
    }

    public double getRw() {
        return rw;
    }

    public double getRh() {
        return rh;
    }

    public int getSw() {
        return sw;
    }

    public int getSh() {
        return sh;
    }

    public void setRx(double rx) {
        this.rx = rx;
    }

    public void setRy(double ry) {
        this.ry = ry;
    }

    public void setRw(double rw) {
        if(rw <= 0) return;
        this.rw = rw;
    }

    public void setRh(double rh) {
        if(rh <= 0) return;
        this.rh = rh;
    }

    public void moveRight(){
        setRx(getRx() + 1);
    }
    public void moveHorizontal(double d){
        setRx(getRx() + d);
    }

    public void moveVertical(double d){
        setRy(getRy() + d);
    }

    public void moveLeft(){
        setRx(getRx() - 1);
    }
    public void moveLeft(double d){
        setRx(getRx() - d);
    }
    public void moveUp(){
        setRy(getRy() + 1);
    }
    public void moveUp(double d){
        setRy(getRy() + d);
    }
    public void moveDown(){
        setRy(getRy() - 1);
    }
    public void moveDown(double d){
        setRy(getRy() - d);
    }

    public boolean isLocker() {
        return locker;
    }
    public void setLocker(boolean locker) {
        this.locker = locker;
    }

    public void resetZoom(){
        setRw(getSw() * zoom);
        setRh(getSh() * zoom);
    }

    public void setZoom(double k, double x, double y){
//        double lastZoom = zoom;
        zoom = k;
//        double lastRw = getRw();
//        double lastRh = getRh();
        setRw(getSw()*zoom);
        setRh(getSh()*zoom);

//        setRx(getRx() + getRw()*(zoom)/2);
//        setRy(getRy() - getRh()*(zoom)/2);

//        Vector2 v = s2r(new ScreenPoint(sw/2, sh/2));
//        setRx(getRw() - v.getX()/2);
//        setRy(getRh() + v.getY()/2);
//        centerOverPoint(getRw()/2, -getRh()/2);
//        centerOverPoint(x, y);
//        centerOverPoint((getRw() - lastRw)/2, (getRh() - lastRh)/2);
    }
    public void centerOverPoint(double x, double y){
        setRx(x - getRw()/2);
        setRy(y + getRh()/2);
    }


    public void zoomIn(){
        setZoom(getZoom()*0.9, 0, 0);
//        if(getRw() <= 2 || getRh() <= 2) return;
////        zoom *= 0.9;
//        double offsetX = getRw()*0.05;
//        double offsetY = getRh()*0.05;
//        setRw(getRw()*0.9);
//        setRh(getRh()*0.9);
//        setRx(getRx() + offsetX);
//        setRy(getRy() - offsetY);
//
//        zoom = rw/sw;
    }
    public void zoomOut(){
        setZoom(getZoom()*1.1, 0, 0);
//        if(getRw() >= 30000 || getRh() >= 30000)return;
////        zoom *= 1.1;
//        double offsetX = getRw()*0.05;
//        double offsetY = getRh()*0.05;
//        setRw(getRw()*1.1);
//        setRh(getRh()*1.1);
//        setRx(getRx() - offsetX);
//        setRy(getRy() + offsetY);
//
//        zoom = rw/sw;
    }


    public void setSw(int sw) {
        this.sw = sw;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }
}
