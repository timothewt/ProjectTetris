package controller;

import model.GameModel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static model.GameModel.*;

public class ButtonsMouseListener implements MouseListener {
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if (isInInterval(x,260,440) && isInInterval(y,265,320) && !pause) {
            GameModel.pauseGame();
        } else if (isInInterval(x,260,440) && isInInterval(y,265,320) && pause) {
            GameModel.resumeGame();
        } else if (isInInterval(x,260,440) && isInInterval(y,335,390)) {
            GameModel.restartGame();
        }
    }
    private boolean isInInterval(int n, int min, int max) {
        return n>=min && n<=max;
    }
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if (isInInterval(x,260,440) && isInInterval(y,265,320) && !pause) {
            gameGUI.paintPausePressed(gameGUI.getGraphics());
        } else if (isInInterval(x,260,440) && isInInterval(y,265,320) && pause) {
            gameGUI.paintResumePressed(gameGUI.getGraphics());
        } else if (isInInterval(x,260,440) && isInInterval(y,335,390)) {
            gameGUI.paintRestartPressed(gameGUI.getGraphics());
        }
    }

    public void mouseReleased(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if (isInInterval(x,260,440) && isInInterval(y,265,320) && !pause) {
            gameGUI.paintPauseReleased(gameGUI.getGraphics());
        } else if (isInInterval(x,260,440) && isInInterval(y,265,320) && pause) {
            gameGUI.paintResumeReleased(gameGUI.getGraphics());
        } else if (isInInterval(x,260,440) && isInInterval(y,335,390)) {
            gameGUI.paintRestartReleased(gameGUI.getGraphics());
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
