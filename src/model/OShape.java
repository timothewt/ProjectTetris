package model;

import static java.awt.Color.*;

public class OShape extends Tetrominoe {

    public OShape() {
        this.color = YELLOW;
        this.position = new Coordinates(0,4); // line ; column
        this.orientation = 0;
    }

    protected boolean canRotate(Grid grid) {
        return true;
    }

    public Grid rotate(Grid grid) {
        return grid;
    }

    protected boolean canMoveDown(Grid grid) {
        return areCellsFree(grid, new Coordinates(0,2), new Coordinates(1,2));
    }

    public Grid moveDown(Grid grid) {
        if (canMoveDown(grid)) {
            grid = setPieceCells(grid, BLACK, new Coordinates(0,0), new Coordinates(0,1), new Coordinates(1,0), new Coordinates(1,1));
            grid = setPieceCells(grid, color, new Coordinates(1,0), new Coordinates(1,1), new Coordinates(2,0), new Coordinates(2,1));
            position.setY(position.getY() + 1);
        }
        return grid;
    }
    boolean canMoveSide(Grid grid, int direction) {
        return switch (direction) {
            case 1 -> areCellsFree(grid, new Coordinates(0,2), new Coordinates(1,2));
            case -1 -> areCellsFree(grid, new Coordinates(0,-1), new Coordinates(1,-1));
            default -> false;
        };
    }

    public Grid moveSide(Grid grid, int direction) {
        if (canMoveSide(grid, direction)) {
            grid = setPieceCells(grid, BLACK, new Coordinates(0,0), new Coordinates(0,1), new Coordinates(1,0), new Coordinates(1,1));
            grid = setPieceCells(grid, color, new Coordinates(0,direction), new Coordinates(0,1 + direction), new Coordinates(1,direction), new Coordinates(1,1 + direction));
            position.setX(position.getX() + direction);
        }
        return grid;
    }

    protected boolean canPlacePiece(Grid grid) {
        return areCellsFree(grid, new Coordinates(0,0), new Coordinates(1,0), new Coordinates(0,1), new Coordinates(1,1));
    }

    public Grid placePiece(Grid grid) {
        if (canPlacePiece(grid)) {
            grid = setPieceCells(grid, this.color, new Coordinates(0,0), new Coordinates(1,0), new Coordinates(0,1), new Coordinates(1,1));
        }
        return grid;
    }

}