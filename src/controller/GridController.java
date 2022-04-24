package controller;

import model.GameModel;

import java.awt.*;

import static model.GameModel.*;

public class GridController {

    public static void move(int dir) {
        switch (dir) {
            case -1, 1 -> currentPiece.moveSide(gameGrid, dir);
            case 0 -> {
                currentPiece.moveDown(gameGrid);
                if (currentPiece.canMoveDown(gameGrid)) {
                    GameModel.stats.addToScore(1);
                    gameGUI.paintScore(gameGUI.getGraphics());
                }
            }
            case 2 -> currentPiece.rotate(gameGrid);
            case 3 -> {
                while (currentPiece.canMoveDown(gameGrid)) {
                    currentPiece.moveDown(gameGrid);
                    GameModel.stats.addToScore(2);
                    gameGUI.paintScore(gameGUI.getGraphics());
                }
                hardDrop = true;
            }
        }
        gameGUI.paintGrid(gameGUI.getGraphics());
    }

}
