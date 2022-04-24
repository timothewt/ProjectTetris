package model;

import java.awt.*;
import static java.awt.Color.*;

public abstract class Tetrominoe {
    protected Color color;
    protected Coordinates position;
    protected int orientation;
    public int type;

    public Tetrominoe() {
        this.color = BLACK;
        this.position = new Coordinates();
        this.orientation = 0;
    }
    public Tetrominoe(Color color, Coordinates position, int orientation) {
        this.color = color;
        this.position = position;
        this.orientation = orientation;
    }

    abstract boolean canRotate(Grid grid);

    abstract public void rotate(Grid grid);

    public abstract boolean canMoveDown(Grid grid);

    abstract public void moveDown(Grid grid);

    abstract boolean canMoveSide(Grid grid, int direction);

    abstract public void moveSide(Grid grid, int direction);

    abstract boolean canPlacePiece(Grid grid);

    abstract public void placePiece(Grid grid);

    protected void moveDownGlobal(Grid grid, Coordinates[] position1, Coordinates[] position2, Coordinates[] position3, Coordinates[] position4) { // corresponds to every position according to the orientation
        switch (this.orientation % 4) {
            case 0 -> moveDownInGrid(grid, position1);
            case 1 -> moveDownInGrid(grid, position2);
            case 2 -> moveDownInGrid(grid, position3);
            case 3 -> moveDownInGrid(grid, position4);
            default -> {}
        }
        this.position.setY(this.position.getY() + 1);
    }

    private void moveDownInGrid(Grid grid, Coordinates[] position) {
        setPieceCells(grid, BLACK, new Coordinates(position[0].getY(), position[0].getX()), new Coordinates(position[1].getY(), position[1].getX()), new Coordinates(position[2].getY(), position[2].getX()), new Coordinates(position[3].getY(), position[3].getX()));
        setPieceCells(grid, color, new Coordinates(position[0].getY() + 1, position[0].getX()), new Coordinates(position[1].getY() + 1, position[1].getX()), new Coordinates(position[2].getY() + 1, position[2].getX()), new Coordinates(position[3].getY() + 1, position[3].getX()));
    }

    protected void moveSideGlobal(Grid grid, Coordinates[] position1, Coordinates[] position2, Coordinates[] position3, Coordinates[] position4, int direction) { // corresponds to every position according to the orientation
        switch (this.orientation % 4) {
            case 0 -> moveSideInGrid(grid, position1, direction);
            case 1 -> moveSideInGrid(grid, position2, direction);
            case 2 -> moveSideInGrid(grid, position3, direction);
            case 3 -> moveSideInGrid(grid, position4, direction);
            default -> {}
        }
        this.position.setX(this.position.getX() + direction);
    }

    private void moveSideInGrid(Grid grid, Coordinates[] position, int direction) {
        setPieceCells(grid, BLACK, new Coordinates(position[0].getY(), position[0].getX()), new Coordinates(position[1].getY(), position[1].getX()), new Coordinates(position[2].getY(), position[2].getX()), new Coordinates(position[3].getY(), position[3].getX()));
        setPieceCells(grid, color, new Coordinates(position[0].getY(), position[0].getX() + direction), new Coordinates(position[1].getY(), position[1].getX() + direction), new Coordinates(position[2].getY(), position[2].getX() + direction), new Coordinates(position[3].getY(), position[3].getX() + direction));
    }

    protected boolean areCellsFree(Grid grid, Coordinates firstPos) {
        return grid.isFree(new Coordinates(this.position.getY() + firstPos.getY(),this.position.getX() + firstPos.getX()));
    }
    protected boolean areCellsFree(Grid grid, Coordinates firstPos, Coordinates secondPos) {
        return grid.isFree(new Coordinates(this.position.getY() + firstPos.getY(),this.position.getX() + firstPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + secondPos.getY(),this.position.getX() + secondPos.getX()));
    }
    protected boolean areCellsFree(Grid grid, Coordinates firstPos, Coordinates secondPos, Coordinates thirdPos) {
        return grid.isFree(new Coordinates(this.position.getY() + firstPos.getY(),this.position.getX() + firstPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + secondPos.getY(),this.position.getX() + secondPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + thirdPos.getY(),this.position.getX() + thirdPos.getX()));
    }
    protected boolean areCellsFree(Grid grid, Coordinates firstPos, Coordinates secondPos, Coordinates thirdPos, Coordinates forthPos) {
        return grid.isFree(new Coordinates(this.position.getY() + firstPos.getY(),this.position.getX() + firstPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + secondPos.getY(),this.position.getX() + secondPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + thirdPos.getY(),this.position.getX() + thirdPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + forthPos.getY(),this.position.getX() + forthPos.getX()));
    }

    protected void setPieceCells(Grid grid, Color color,Coordinates firstPos, Coordinates secondPos, Coordinates thirdPos, Coordinates forthPos) {
        grid.setCell(new Coordinates(this.position.getY() + firstPos.getY(), this.position.getX() + firstPos.getX()), color);
        grid.setCell(new Coordinates(this.position.getY() + secondPos.getY(), this.position.getX() + secondPos.getX()), color);
        grid.setCell(new Coordinates(this.position.getY() + thirdPos.getY(), this.position.getX() + thirdPos.getX()), color);
        grid.setCell(new Coordinates(this.position.getY() + forthPos.getY(), this.position.getX() + forthPos.getX()), color);
    }

    public Color getColor() {
        return color;
    }

    public Coordinates getPosition() {
        return position;
    }

    public int getOrientation() {
        return orientation;
    }
}