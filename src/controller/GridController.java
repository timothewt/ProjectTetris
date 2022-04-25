package controller;
import model.GameModel;
import static model.GameModel.*;

/**
 * Movements of the pieces when the players hits the keys
 */
public class GridController {

    public static void move(int dir) {
        switch (dir) {
            case -1, 1 -> currentPiece.moveSide(gameGrid, dir); // moves to left or right when hitting side arrows keys
            case 0 -> { // moves down if the player hits bottom arrow key
                if (currentPiece.canMoveDown(gameGrid)) { // if the piece can move down adds a point to the score
                    GameModel.stats.addToScore(1);
                    gameGUI.paintScore(gameGUI.getGraphics());
                }
                currentPiece.moveDown(gameGrid);
            }
            case 2 -> currentPiece.rotate(gameGrid); // rotates the piece if the player hits upwards arrow key
            case 3 -> { // hard drops (loops on moving down while the piece can) when player hits space bar
                while (currentPiece.canMoveDown(gameGrid)) {
                    currentPiece.moveDown(gameGrid);
                    GameModel.stats.addToScore(2); // adds 2 of score for each movement
                    gameGUI.paintScore(gameGUI.getGraphics());
                }
                hardDrop = true;
            }
        }
        gameGUI.paintGrid(gameGUI.getGraphics()); // Grid display updates after the movement
    }

}
