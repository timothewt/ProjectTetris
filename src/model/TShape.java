package model;

import static java.awt.Color.*;

public class TShape extends Tetrominoe {

    public TShape() {
        this.color = MAGENTA;
        this.position = new Coordinates(1,5);
        this.orientation = 0;
        this.type = 0;
    }

    boolean canRotate(Grid grid) {
        return switch (this.orientation % 4) {
            case 0 -> areCellsFree(grid, new Coordinates(1, 0));
            case 1 -> areCellsFree(grid, new Coordinates(0, -1));
            case 2 -> areCellsFree(grid, new Coordinates(-1, 0));
            case 3 -> areCellsFree(grid, new Coordinates(0, 1));
            default -> false;
        };
    }

    public void rotate(Grid grid) {
        if (canRotate(grid)) {
            switch (this.orientation % 4) {
                case 0 -> {
                    grid.setCell(new Coordinates(this.position.getY(), this.position.getX()-1), BLACK);
                    grid.setCell(new Coordinates(this.position.getY()+1, this.position.getX()), color);
                }
                case 1 -> {
                    grid.setCell(new Coordinates(this.position.getY()-1, this.position.getX()), BLACK);
                    grid.setCell(new Coordinates(this.position.getY(), this.position.getX()-1), color);
                }
                case 2 -> {
                    grid.setCell(new Coordinates(this.position.getY(), this.position.getX()+1), BLACK);
                    grid.setCell(new Coordinates(this.position.getY()-1, this.position.getX()), color);
                }
                case 3 -> {
                    grid.setCell(new Coordinates(this.position.getY()+1, this.position.getX()), BLACK);
                    grid.setCell(new Coordinates(this.position.getY(), this.position.getX()+1), color);
                }
                default -> {
                }
            }
            orientation++;
        }
    }

    public boolean canMoveDown(Grid grid) {
        return switch (this.orientation % 4) {
            case 0 -> areCellsFree(grid, new Coordinates(1, -1), new Coordinates(1, 0), new Coordinates(1, 1));
            case 1 -> areCellsFree(grid, new Coordinates(2, 0), new Coordinates(1, 1));
            case 2 -> areCellsFree(grid, new Coordinates(1, -1), new Coordinates(2, 0), new Coordinates(1, 1));
            case 3 -> areCellsFree(grid, new Coordinates(1, -1), new Coordinates(2, 0));
            default -> false;
        };
    }

    public void moveDown(Grid grid) {
        if (canMoveDown(grid)) {
            moveDownGlobal(grid, new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(0,1),new Coordinates(-1,0)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,1),new Coordinates(0,0),new Coordinates(1,0)},
                    new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(1,0),new Coordinates(0,1)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,0),new Coordinates(0,-1),new Coordinates(1,0)});
        }
    }

    protected boolean canMoveSide(Grid grid, int direction) {
        if(direction == 1) {
            return switch (this.orientation % 4) {
                case 0 -> areCellsFree(grid, new Coordinates(-1, 1), new Coordinates(0, 2));
                case 1 -> areCellsFree(grid, new Coordinates(-1, 1), new Coordinates(0, 2), new Coordinates(1, 1));
                case 2 -> areCellsFree(grid, new Coordinates(0, 2), new Coordinates(1, 1));
                case 3 -> areCellsFree(grid, new Coordinates(-1, 1), new Coordinates(0, 1), new Coordinates(1, 1));
                default -> false;
            };
        } else {
            return switch (this.orientation % 4) {
                case 0 -> areCellsFree(grid, new Coordinates(-1, -1), new Coordinates(0, -2));
                case 1 -> areCellsFree(grid, new Coordinates(-1, -1), new Coordinates(0, -1), new Coordinates(1, -1));
                case 2 -> areCellsFree(grid, new Coordinates(0, -2), new Coordinates(1, -1));
                case 3 -> areCellsFree(grid, new Coordinates(-1, -1), new Coordinates(0, -2), new Coordinates(1, -1));
                default -> false;
            };
        }
    }

    public void moveSide(Grid grid, int direction) {
        if (canMoveSide(grid, direction)) {
            moveSideGlobal(grid, new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(0,1),new Coordinates(-1,0)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,1),new Coordinates(0,0),new Coordinates(1,0)},
                    new Coordinates[]{new Coordinates(0,-1),new Coordinates(0,0),new Coordinates(1,0),new Coordinates(0,1)},
                    new Coordinates[]{new Coordinates(-1,0),new Coordinates(0,0),new Coordinates(0,-1),new Coordinates(1,0)}, direction);
        }
    }

    boolean canPlacePiece(Grid grid) {
        return areCellsFree(grid, new Coordinates(0,-1), new Coordinates(0,0), new Coordinates(-1,0), new Coordinates(0,1));
    }

    public void placePiece(Grid grid) {
        if (canPlacePiece(grid)) {
            setPieceCells(grid, this.color, new Coordinates(0,-1), new Coordinates(0,0), new Coordinates(-1,0), new Coordinates(0,1));
        }
    }

}
