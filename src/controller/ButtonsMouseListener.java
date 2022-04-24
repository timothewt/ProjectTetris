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
    public void mousePressed(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if (isInInterval(x,260,440) && isInInterval(y,265,320)) {
            gameGUI.paintPause(gameGUI.getGraphics(),true, pause);
        } else if (isInInterval(x,260,440) && isInInterval(y,335,390)) {
            gameGUI.paintRestart(gameGUI.getGraphics(),true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        if (isInInterval(x,260,440) && isInInterval(y,265,320)) {
            gameGUI.paintPause(gameGUI.getGraphics(),false, pause);
        } else if (isInInterval(x,260,440) && isInInterval(y,335,390)) {
            gameGUI.paintRestart(gameGUI.getGraphics(),false);
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
