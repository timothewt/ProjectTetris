package model;
import static java.awt.Color.*;

/**
 * Class of the pieces with the shape of a T, inheriting of the Tetrominoe class
 * Note : all the other shapes (except O) have the same documentation so only this one is written
 */
public class TShape extends Tetrominoe {

    public TShape() {
        this.color = MAGENTA; // color of the piece
        this.position = new Coordinates(1,5); // origin coordinates
        this.orientation = 0;
        this.type = 0;
    }

    /**
     * Checks if the piece can rotate
     * @param grid : grid in which it rotates
     * @return true if yes no otherwise
     */
    boolean canRotate(Grid grid) {
        return switch (this.orientation % 4) { // returns a value according to its orientation
            case 0 -> areCellsFree(grid, new Coordinates(1, 0));
            case 1 -> areCellsFree(grid, new Coordinates(0, -1));
            case 2 -> areCellsFree(grid, new Coordinates(-1, 0));
            case 3 -> areCellsFree(grid, new Coordinates(0, 1));
            default -> false;
        };
    }

    /**
     * Rotates the piece in the grid
     * @param grid : grid in which it rotates
     */
    public void rotate(Grid grid) {
        if (canRotate(grid)) { // only executes if the piece can rotate
            switch (this.orientation % 4) { // rotation depends on the orientation
                case 0 -> {
                    grid.setCell(new Coordinates(this.position.getY(), this.position.getX()-1), BLACK); // sets the 'old' cells to black
                    grid.setCell(new Coordinates(this.position.getY()+1, this.position.getX()), color); // sets the 'new' cells to the piece color
                }
                case 1 -> {
                    grid.setCell(new Coordinates(this.position.getY()-1, this.position.getX()), BLACK); // same as above
                    grid.setCell(new Coordinates(this.position.getY(), this.position.getX()-1), color);
                }
                case 2 -> {
                    grid.setCell(new Coordinates(this.position.getY(), this.position.getX()+1), BLACK); //
                    grid.setCell(new Coordinates(this.position.getY()-1, this.position.getX()), color);
                }
                case 3 -> {
                    grid.setCell(new Coordinates(this.position.getY()+1, this.position.getX()), BLACK); //
                    grid.setCell(new Coordinates(this.position.getY(), this.position.getX()+1), color);
                }
                default -> {
                }
            }
            orientation++; // orientation increased by one
        }
    }

    /**
     * Checks if the piece can move down
     * @param grid : grid in which it moves down
     * @return true if yes false otherwise
     */
    public boolean canMoveDown(Grid grid) {
        return switch (this.orientation % 4) { // checks according to the orientation
            case 0 -> areCellsFree(grid, new Coordinates(1, -1), new Coordinates(1, 0), new Coordinates(1, 1)); // checks if the cells under the piece are free, taking its coordinates center as origin
            case 1 -> areCellsFree(grid, new Coordinates(2, 0), new Coordinates(1, 1)); // same as above
            case 2 -> areCellsFree(grid, new Coordinates(1, -1), new Coordinates(2, 0), new Coordinates(1, 1)); //
            case 3 -> areCellsFree(grid, new Coordinates(1, -1), new Coordinates(2, 0)); //
            default -> false;
        };
    }

    /**
     * Moves the piece down in the grid
     * @param grid : grid in which it moves down
     */
    public void moveDown(Grid grid) {
        if (canMoveDown(grid)) { // if it can move down, calls the 'global' function of the Tetrominoe class
            moveDownGlobal(grid, new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(0,1),new Coordinates(-1,0)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,1),new Coordinates(0,0),new Coordinates(1,0)},
                    new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(1,0),new Coordinates(0,1)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,0),new Coordinates(0,-1),new Coordinates(1,0)});
        }
    }

    /**
     * Checks if the piece can move to the side
     * @param grid : grid in which it moves sideways
     * @param direction : -1 if it moves left 1 if it moves right
     * @return true if yes false otherwise
     */
    protected boolean canMoveSide(Grid grid, int direction) {
        if(direction == 1) { // if it goes to the right
            return switch (this.orientation % 4) { // checks if the cells to the right of the piece are free according to the orientation, taking the coordinates as the origin
                case 0 -> areCellsFree(grid, new Coordinates(-1, 1), new Coordinates(0, 2));
                case 1 -> areCellsFree(grid, new Coordinates(-1, 1), new Coordinates(0, 2), new Coordinates(1, 1));
                case 2 -> areCellsFree(grid, new Coordinates(0, 2), new Coordinates(1, 1));
                case 3 -> areCellsFree(grid, new Coordinates(-1, 1), new Coordinates(0, 1), new Coordinates(1, 1));
                default -> false;
            };
        } else { // if it goes to the left
            return switch (this.orientation % 4) { // checks if the cells to the left of the pieces are free according to the orientation, taking the coordinates as the origin
                case 0 -> areCellsFree(grid, new Coordinates(-1, -1), new Coordinates(0, -2));
                case 1 -> areCellsFree(grid, new Coordinates(-1, -1), new Coordinates(0, -1), new Coordinates(1, -1));
                case 2 -> areCellsFree(grid, new Coordinates(0, -2), new Coordinates(1, -1));
                case 3 -> areCellsFree(grid, new Coordinates(-1, -1), new Coordinates(0, -2), new Coordinates(1, -1));
                default -> false;
            };
        }
    }

    /**
     * Moves the piece to the side in the grid
     * @param grid : grid in which it moves sideways
     * @param direction : -1 if it moves left 1 if it moves right
     */
    public void moveSide(Grid grid, int direction) {
        if (canMoveSide(grid, direction)) { // if it can move to the side calls the 'global' function in the Tetrominoe class
            moveSideGlobal(grid, new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(0,1),new Coordinates(-1,0)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,1),new Coordinates(0,0),new Coordinates(1,0)},
                    new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(1,0),new Coordinates(0,1)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,0),new Coordinates(0,-1),new Coordinates(1,0)}, direction);
        }
    }

    /**
     * Checks if the cells where the piece is placed are free
     * @param grid : grid in which we place the piece
     * @return true if yes false otherwise
     */
    boolean canPlacePiece(Grid grid) {
        return areCellsFree(grid, new Coordinates(0,-1), new Coordinates(0,0), new Coordinates(-1,0), new Coordinates(0,1));
    }

    /**
     * Places the piece in the grid according to its coordinates
     * @param grid : grid in which we place the piece
     */
    public void placePiece(Grid grid) {
        if (canPlacePiece(grid)) { // if the piece can be placed, sets the cells in the grid with the color of the piece and taking its coordinates as the origin
            setPieceCells(grid, this.color, new Coordinates(0,-1), new Coordinates(0,0), new Coordinates(-1,0), new Coordinates(0,1));
        }
    }

}
