package model;
import static java.awt.Color.*;

/**
 * Class of the pieces with the shape of a S, inheriting of the Tetrominoe class
 * Note : all the other shapes (except O) have the same documentation so see TShape class for doc
 */

public class SShape extends Tetrominoe {

    public SShape() {
        this.color = GREEN;
        this.position = new Coordinates(1,5);
        this.orientation = 0;
        this.type = 2;
    }

    boolean canRotate(Grid grid) {
        return switch (this.orientation % 4) {
            case 0 -> areCellsFree(grid, new Coordinates(0, 1), new Coordinates(1, 1));
            case 1 -> areCellsFree(grid, new Coordinates(1, -1), new Coordinates(1, 0));
            case 2 -> areCellsFree(grid, new Coordinates(0, -1), new Coordinates(-1, -1));
            case 3 -> areCellsFree(grid, new Coordinates(-1, 0), new Coordinates(-1, 1));
            default -> false;
        };
    }

    public void rotate(Grid grid) {
        if (canRotate(grid)) {
            switch (this.orientation % 4) {
                case 0 -> {
                    setPieceCells(grid, BLACK, new Coordinates(0, -1), new Coordinates(0, 0), new Coordinates(-1, 0), new Coordinates(-1, 1));
                    setPieceCells(grid, color, new Coordinates(-1, 0), new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(1, 1));
                }
                case 1 -> {
                    setPieceCells(grid, BLACK, new Coordinates(-1, 0), new Coordinates(0, 0), new Coordinates(0, 1), new Coordinates(1, 1));
                    setPieceCells(grid, color, new Coordinates(0, 1), new Coordinates(0, 0), new Coordinates(1, 0), new Coordinates(1, -1));
                }
                case 2 -> {
                    setPieceCells(grid, BLACK, new Coordinates(0, 1), new Coordinates(0, 0), new Coordinates(1, 0), new Coordinates(1, -1));
                    setPieceCells(grid, color, new Coordinates(-1, -1), new Coordinates(0, -1), new Coordinates(0, 0), new Coordinates(1, 0));
                }
                case 3 -> {
                    setPieceCells(grid, BLACK, new Coordinates(-1, -1), new Coordinates(0, -1), new Coordinates(0, 0), new Coordinates(1, 0));
                    setPieceCells(grid, color, new Coordinates(0, -1), new Coordinates(0, 0), new Coordinates(-1, 0), new Coordinates(-1, 1));
                }
                default -> {
                }
            }
            orientation++;
        }
    }

    public boolean canMoveDown(Grid grid) {
        return switch (this.orientation % 4) {
            case 0 -> areCellsFree(grid, new Coordinates(1, -1), new Coordinates(1, 0), new Coordinates(0, 1));
            case 1 -> areCellsFree(grid, new Coordinates(2, 1), new Coordinates(1, 0));
            case 2 -> areCellsFree(grid, new Coordinates(2, -1), new Coordinates(2, 0), new Coordinates(1, 1));
            case 3 -> areCellsFree(grid, new Coordinates(2, 0), new Coordinates(1, -1));
            default -> false;
        };
    }

    public void moveDown(Grid grid) {
        if (canMoveDown(grid)) {
            moveDownGlobal(grid, new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(-1,0),new Coordinates(-1,1)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,0),new Coordinates(0,1),new Coordinates(1,1)},
                    new Coordinates[]{new Coordinates(1,-1),new Coordinates(1,0),new Coordinates(0,0),new Coordinates(0,1)},
                    new Coordinates[]{new Coordinates(-1,-1),new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(1,0)});
        }
    }

    protected boolean canMoveSide(Grid grid, int direction) {
        if(direction == 1) {
            return switch (this.orientation % 4) {
                case 0 -> areCellsFree(grid, new Coordinates(-1, 2), new Coordinates(0, 1));
                case 1 -> areCellsFree(grid, new Coordinates(-1, 1), new Coordinates(0, 2), new Coordinates(1, 2));
                case 2 -> areCellsFree(grid, new Coordinates(0, 2), new Coordinates(1, 1));
                case 3 -> areCellsFree(grid, new Coordinates(-1, 0), new Coordinates(0, 1), new Coordinates(1, 1));
                default -> false;
            };
        } else {
            return switch (this.orientation % 4) {
                case 0 -> areCellsFree(grid, new Coordinates(-1, -1), new Coordinates(0, -2));
                case 1 -> areCellsFree(grid, new Coordinates(-1, -1), new Coordinates(0, -1), new Coordinates(1, 0));
                case 2 -> areCellsFree(grid, new Coordinates(0, -1), new Coordinates(1, -2));
                case 3 -> areCellsFree(grid, new Coordinates(-1, -2), new Coordinates(0, -2), new Coordinates(1, -1));
                default -> false;
            };
        }
    }

    public void moveSide(Grid grid, int direction) {
        if (canMoveSide(grid, direction)) {
            moveSideGlobal(grid, new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(-1,0),new Coordinates(-1,1)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,0),new Coordinates(0,1),new Coordinates(1,1)},
                    new Coordinates[]{new Coordinates(1,-1),new Coordinates(1,0),new Coordinates(0,0),new Coordinates(0,1)},
                    new Coordinates[]{new Coordinates(-1,-1),new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(1,0)}, direction);
        }
    }

    boolean canPlacePiece(Grid grid) {
        return areCellsFree(grid, new Coordinates(0,-1), new Coordinates(0,0), new Coordinates(-1,0), new Coordinates(-1,1));
    }

    public void placePiece(Grid grid) {
        if (canPlacePiece(grid)) {
            setPieceCells(grid, this.color, new Coordinates(0,-1), new Coordinates(0,0), new Coordinates(-1,0), new Coordinates(-1,1));
        }
    }
}
