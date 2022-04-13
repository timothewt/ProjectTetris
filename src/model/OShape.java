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

    @Override
    boolean canMoveSide(Grid grid, int direction) {
        return false;
    }

    @Override
    public Grid moveSide(Grid grid, int direction) {
        return null;
    }

    protected boolean canMoveRight(Grid grid) {
        return areCellsFree(grid, new Coordinates(0,2), new Coordinates(1,2));
    }

    public Grid moveRight(Grid grid) {
        if (canMoveRight(grid)) {
            grid = setPieceCells(grid, BLACK, new Coordinates(0,0), new Coordinates(0,1), new Coordinates(1,0), new Coordinates(1,1));
            grid = setPieceCells(grid, color, new Coordinates(0,1), new Coordinates(0,2), new Coordinates(1,1), new Coordinates(1,2));
            position.setX(position.getX() + 1);
        }
        return grid;
    }

    protected boolean canMoveLeft(Grid grid) {
        return areCellsFree(grid, new Coordinates(0,-1), new Coordinates(1,-1));
    }

    public Grid moveLeft(Grid grid) {
        if (canMoveLeft(grid)) {
            grid = setPieceCells(grid, BLACK, new Coordinates(0,0), new Coordinates(0,1), new Coordinates(1,0), new Coordinates(1,1));
            grid = setPieceCells(grid, color, new Coordinates(0,0), new Coordinates(0,-1), new Coordinates(1,0), new Coordinates(1,-1));
            position.setX(position.getX() - 1);
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