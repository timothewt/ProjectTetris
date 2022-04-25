package model;
import java.awt.*;
import static java.awt.Color.*;
import static model.GameModel.isInInterval;

/**
 * Grid class, it is the grid of the game containing the Tetrominoes
 */
public class Grid {
    private final Color[][] grid; // Two dimensions array of Colors containing the pieces (black indicates no pieces and not black is where a piece is there)

    /**
     * Constructor, sets all the cells to BLACK (no pieces)
     */
    public Grid() {
        this.grid = new Color[20][10]; // new array of 20 lines by 10 columns
        for (int i = 0; i < 20; i++) { // loops through all the cells
            for (int j = 0; j < 10; j++) {
                this.grid[i][j] = BLACK; // sets cells to BLACK
            }
        }
    }

    /**
     * Constructor if a simple two dimensions grid is already defined
     * @param grid : two dimensions Color array
     */
    public Grid(Color[][] grid) {
        this.grid = grid;
    }

    /**
     * Checks if a cell is free
     * @param coordinates : coordinates of the cells (line,column)
     * @return true if the cell is free false otherwise
     */
    public boolean isFree(Coordinates coordinates) {
        if (!isInInterval(coordinates.getY(), 0, 19) || !isInInterval(coordinates.getX(), 0, 9)) { // if the coordinates are out of the grid, returns false
            return false;
        } else { // else checks the coordinates
            return this.grid[coordinates.getY()][coordinates.getX()] == BLACK; // checks if the cell of coordinates (line:Y, column:X) is BLACK therefore free
        }
    }

    /**
     * Shifts all the lines above lineNb down by 1 line, therefore deletes the lineNb
     * @param lineNb : the line to delete starting by 0 at the top of the grid
     */
    public void clearLine(int lineNb) {
        for (int i = lineNb; i>0; i--) {
            System.arraycopy(this.grid[i - 1], 0, this.grid[i], 0, 10);
        }
    }

    /**
     * Checks for completed lines (all columns of the line are filled)
     * @return the number of lines completed
     */
    public int checkLines() {
        int completeLines = 0;
        for (int i=0; i<20; i++) { // loops through all the lines
            int isComplete = 1; // we assume that the line is full
            for (int j=0; j<10; j++) { // loops through all the columns
                if (this.grid[i][j] == BLACK) { // if a cell is not filled we change isComplete to say that it is not
                    isComplete = 0;
                    break; // breaks the loop because it is not necessary to go further we know it's not full
                }
            }
            if(isComplete == 1) { // if the line is completed clears it
                clearLine(i);
            }
            completeLines += isComplete; // if the line is complete adds it to the returned value
        }
        return completeLines;
    }

    /**
     * gives the two dimensions array grid
     * @return the grid
     */
    public Color[][] getGrid() {
        return grid;
    }

    /**
     * Changes the value of a cell at certain coordinates
     * @param coordinates : coordinates of the cell to change
     * @param value : at which color we have to change it
     */
    public void setCell(Coordinates coordinates, Color value) {
        this.grid[coordinates.getY()][coordinates.getX()] = value;
    }
}