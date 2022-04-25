package model;
import java.awt.*;
import static java.awt.Color.*;

/**
 * Abstract class of all Tetrominoes, common methods are written here and the ones depending on the pieces shapes are written in other classes.
 * Only OShape and TShape are documented because the other pieces have the same documentation as the TShape (OShape is special)
 */
public abstract class Tetrominoe {
    protected Color color; // Color of the piece
    protected Coordinates position; // Current position of the piece (see PiecesRotation.png to know where the coordinates are centered in the piece)
    protected int orientation; // orientation of the piece
    public int type; // type of the piece going from 0 to 6

    /**
     * Constructor of a default piece
     */
    public Tetrominoe() {
        this.color = BLACK;
        this.position = new Coordinates();
        this.orientation = 0;
    }

    /**
     * Checks if the piece can rotate in the grid
     * @param grid : grid in which it rotates
     * @return true if it can false otherwise
     */
    abstract boolean canRotate(Grid grid);

    /**
     * Rotates the piece in the grid
     * @param grid : grid in which it rotates
     */
    abstract public void rotate(Grid grid);

    /**
     * Checks if the piece can move down by 1 line in the grid
     * @param grid : grid in which it moves down
     * @return true if it can false otherwise
     */
    public abstract boolean canMoveDown(Grid grid);

    /**
     * Moves down the piece in the grid
     * @param grid : grid in which it moves down
     */
    abstract public void moveDown(Grid grid);

    /**
     * Checks if the piece can sideways by 1 column in the grid
     * @param grid : grid in which it moves sideways
     * @param direction : -1 if it moves left 1 if it moves right
     * @return true if it can false otherwise
     */
    abstract boolean canMoveSide(Grid grid, int direction);

    /**
     * Moves the piece on the right or the left in the grid
     * @param grid : grid in which it moves sideways
     * @param direction : -1 if it moves left 1 if it moves right
     */
    abstract public void moveSide(Grid grid, int direction);

    /**
     * Checks if the piece can be place at its origin coordinates in the grid
     * @param grid : grid in which we place the piece
     * @return true if it can false otherwise
     */
    abstract boolean canPlacePiece(Grid grid);

    /**
     * Places the piece in the grid
     * @param grid : grid in which we place the piece
     */
    abstract public void placePiece(Grid grid);

    /**
     * Moves the piece down according to its orientation
     * @param grid : grid in which it moves down
     * @param position1 : the 4 cells occupied by the piece with its coordinates center as reference in orientation 0
     * @param position2 : same in orientation 1
     * @param position3 : same in orientation 2
     * @param position4 : same in orientation 3
     */
    protected void moveDownGlobal(Grid grid, Coordinates[] position1, Coordinates[] position2, Coordinates[] position3, Coordinates[] position4) {
        switch (this.orientation % 4) {
            case 0 -> moveDownInGrid(grid, position1);
            case 1 -> moveDownInGrid(grid, position2);
            case 2 -> moveDownInGrid(grid, position3);
            case 3 -> moveDownInGrid(grid, position4);
            default -> {}
        }
        this.position.setY(this.position.getY() + 1); // changes the Y coordinates of the piece
    }

    /**
     * Moves down the piece in the grid by setting black cells at its previous positions and cells of its color at its new position
     * @param grid : grid in which it moves down
     * @param position : cells originally occupied by the piece with the coordinates center as a reference
     */
    private void moveDownInGrid(Grid grid, Coordinates[] position) {
        setPieceCells(grid, BLACK, new Coordinates(position[0].getY(), position[0].getX()), new Coordinates(position[1].getY(), position[1].getX()), new Coordinates(position[2].getY(), position[2].getX()), new Coordinates(position[3].getY(), position[3].getX()));
        setPieceCells(grid, color, new Coordinates(position[0].getY() + 1, position[0].getX()), new Coordinates(position[1].getY() + 1, position[1].getX()), new Coordinates(position[2].getY() + 1, position[2].getX()), new Coordinates(position[3].getY() + 1, position[3].getX()));
    }

    /**
     * Moves the piece to right of left according to its orientation and the direction parameter
     * @param grid : grid in which it moves
     * @param position1 : the 4 cells occupied by the piece with its coordinates center as reference in orientation 0
     * @param position2 : same but orientation 1
     * @param position3 : same but orientation 2
     * @param position4 : same but orientation 3
     * @param direction : -1 if it moves to left 1 if it moves to right
     */
    protected void moveSideGlobal(Grid grid, Coordinates[] position1, Coordinates[] position2, Coordinates[] position3, Coordinates[] position4, int direction) {
        switch (this.orientation % 4) {
            case 0 -> moveSideInGrid(grid, position1, direction);
            case 1 -> moveSideInGrid(grid, position2, direction);
            case 2 -> moveSideInGrid(grid, position3, direction);
            case 3 -> moveSideInGrid(grid, position4, direction);
            default -> {}
        }
        this.position.setX(this.position.getX() + direction); // changes its coordinates according to the left or right movement
    }

    /**
     * Moves sideways the piece in the grid by setting black cells at its previous positions and cells of its color at its new position
     * @param grid : grid in which it moves sideways
     * @param position : cells originally occupied by the piece with the coordinates center as a reference
     * @param direction : -1 if it moves to left 1 if it moves to right
     */
    private void moveSideInGrid(Grid grid, Coordinates[] position, int direction) {
        setPieceCells(grid, BLACK, new Coordinates(position[0].getY(), position[0].getX()), new Coordinates(position[1].getY(), position[1].getX()), new Coordinates(position[2].getY(), position[2].getX()), new Coordinates(position[3].getY(), position[3].getX()));
        setPieceCells(grid, color, new Coordinates(position[0].getY(), position[0].getX() + direction), new Coordinates(position[1].getY(), position[1].getX() + direction), new Coordinates(position[2].getY(), position[2].getX() + direction), new Coordinates(position[3].getY(), position[3].getX() + direction));
    }

    /**
     * For the next 4 functions : checks if a certain number of cells are free using the piece's coordinates center as reference
     * @param grid : grid in which we check if the cells are free
     * @param firstPos, secondPos, thirdPos, fourthPos : positions of the cells to check according to the coordinates of the piece as the origin
     * @return true if the cells are free false otehrwise
     */
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
    protected boolean areCellsFree(Grid grid, Coordinates firstPos, Coordinates secondPos, Coordinates thirdPos, Coordinates fourthPos) {
        return grid.isFree(new Coordinates(this.position.getY() + firstPos.getY(),this.position.getX() + firstPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + secondPos.getY(),this.position.getX() + secondPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + thirdPos.getY(),this.position.getX() + thirdPos.getX()))
                && grid.isFree(new Coordinates(this.position.getY() + fourthPos.getY(),this.position.getX() + fourthPos.getX()));
    }

    /**
     * Sets the cells where the pieces are at to the color of the piece
     * @param grid : grid in which we set the cells
     * @param color : color at which we set the cells
     * @param firstPos, secondPos, thirdPos, forthPos : positions of the piece's cells by taking its coordinates at the origin
     */
    protected void setPieceCells(Grid grid, Color color,Coordinates firstPos, Coordinates secondPos, Coordinates thirdPos, Coordinates forthPos) {
        grid.setCell(new Coordinates(this.position.getY() + firstPos.getY(), this.position.getX() + firstPos.getX()), color);
        grid.setCell(new Coordinates(this.position.getY() + secondPos.getY(), this.position.getX() + secondPos.getX()), color);
        grid.setCell(new Coordinates(this.position.getY() + thirdPos.getY(), this.position.getX() + thirdPos.getX()), color);
        grid.setCell(new Coordinates(this.position.getY() + forthPos.getY(), this.position.getX() + forthPos.getX()), color);
    }

    public Color getColor() {
        return color;
    }
}