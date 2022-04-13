package model;

import java.awt.*;

import static java.awt.Color.*;

public class Grid {
    private Color[][] grid;
    private int[] size;

    public Grid() {
        this.grid = new Color[20][10];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                this.grid[i][j] = BLACK;
            }
        }
    }
    public Grid(Color[][] grid) {
        this.grid = grid;
    }

    public boolean isFree(Coordinates coordinates) {
        if (isOutOfBounds(coordinates.getY(), 0, 19) || isOutOfBounds(coordinates.getX(), 0, 9)) {
            return false;
        } else {
            return this.grid[coordinates.getY()][coordinates.getX()] == BLACK;
        }
    }

    public boolean isOutOfBounds(int nb, int inf, int sup) {
        return nb > sup || nb < inf;
    }

    public void clearLine(int lineNb) {
        for (int i = 0; i<10; i++) {
            this.grid[lineNb][10] = BLACK;
        }
    }

    public int checkLines() {
        int completeLines = 0;
        for (int i=0; i<20; i++) {
            int isComplete = 1;
            for (int j=0; j<10; j++) {
                if (this.grid[i][j] == BLACK) {
                    isComplete = 0;
                    break;
                }
            }
            completeLines += isComplete;
        }
        return completeLines;
    }

    public void displayInConsole() {
        for (int i = 0; i<20; i++) {
            for (int j = 0; j<10; j++) {
                if (BLACK.equals(this.grid[i][j])) {
                    System.out.print("|0");
                } else {
                    System.out.print("|1");
                }
            }
            System.out.print("|\n");
        }
    }

    public Color[][] getGrid() {
        return grid;
    }

    public void setCells(Coordinates coordinates, Color value) {
        this.grid[coordinates.getY()][coordinates.getX()] = value;
    }

    public void setGrid(Color[][] grid) {
        this.grid = grid;
    }
}