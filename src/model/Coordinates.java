package model;

public class Coordinates {
    private int y; // line
    private int x; // column

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Coordinates() {
        this.y = 0;
        this.x = 0;
    }

    public Coordinates(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
