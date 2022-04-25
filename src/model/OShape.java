package model;
import static java.awt.Color.*;

/**
 * Tetrominoe with the shape of a square, also of an O, inheriting of the Tetrominoe class
 */
public class OShape extends Tetrominoe {

    public OShape() {
        this.color = YELLOW;
        this.position = new Coordinates(0,4); // line ; column, origin coordinates
        this.orientation = 0;
        this.type = 3;
    }

    /**
     * It doesn't rotate, so we say that it can always rotate but it doesn't do anythin
     * @param grid : grid in which it rotates
     * @return true
     */
    protected boolean canRotate(Grid grid) {
        return true;
    }

    /**
     * The rotation does nothing
     * @param grid : grid in which it rotates
     */
    public void rotate(Grid grid) {
    }

    /**
     * checks if it can move down
     * @param grid : grid in which it moves down
     * @return true if yes false otherwise
     */
    public boolean canMoveDown(Grid grid) {
        return areCellsFree(grid, new Coordinates(2,0), new Coordinates(2,1));
    }

    /**
     * Overrides the original function, moves down by 1 line
     * @param grid : grid in which it moves down
     */
    public void moveDown(Grid grid) {
        if (canMoveDown(grid)) {
            setPieceCells(grid, BLACK, new Coordinates(0,0), new Coordinates(0,1), new Coordinates(1,0), new Coordinates(1,1));
            setPieceCells(grid, color, new Coordinates(1,0), new Coordinates(1,1), new Coordinates(2,0), new Coordinates(2,1));
            position.setY(position.getY() + 1);
        }
    }

    /**
     * Overrides the original function, checks if the piece can move sideways by 1 column
     * @param grid : grid in which it moves down
     * @param direction : -1 if moves to left 1 if moves to right
     * @return true if it can false otherwise
     */
    boolean canMoveSide(Grid grid, int direction) {
        return switch (direction) {
            case 1 -> areCellsFree(grid, new Coordinates(0,2), new Coordinates(1,2));
            case -1 -> areCellsFree(grid, new Coordinates(0,-1), new Coordinates(1,-1));
            default -> false;
        };
    }

    /**
     * Overrides the original function, moves sideways by 1 column
     * @param grid : grid in which it moves down
     * @param direction : -1 if moves to left 1 if moves to right
     */
    public void moveSide(Grid grid, int direction) {
        if (canMoveSide(grid, direction)) {
            setPieceCells(grid, BLACK, new Coordinates(0,0), new Coordinates(0,1), new Coordinates(1,0), new Coordinates(1,1));
            setPieceCells(grid, color, new Coordinates(0,direction), new Coordinates(0,1 + direction), new Coordinates(1,direction), new Coordinates(1,1 + direction));
            position.setX(position.getX() + direction);
        }
    }

    /**
     * Checks if the cells where the piece is originally placed are free
     * @param grid : grid in which we place the piece
     * @return true if yes false otherwise
     */
    protected boolean canPlacePiece(Grid grid) {
        return areCellsFree(grid, new Coordinates(0,0), new Coordinates(1,0), new Coordinates(0,1), new Coordinates(1,1));
    }

    /**
     * Places the piece in the grid
     * @param grid : grid in which we place the piece
     */
    public void placePiece(Grid grid) {
        if (canPlacePiece(grid)) {
            setPieceCells(grid, this.color, new Coordinates(0,0), new Coordinates(1,0), new Coordinates(0,1), new Coordinates(1,1));
        }
    }

}