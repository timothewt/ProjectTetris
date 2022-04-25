package controller;
import model.GameModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static model.GameModel.*;

/**
 * Mouse listeners used to detect when the players clicks on the buttons 'Restart' and 'Pause'
 */
public class ButtonsMouseListener implements MouseListener {
    /**
     * when the users uses right or left click, compares the coordinates of the click with the positions of the buttons
     * @param e : event of the mouse being clicked
     */
    public void mouseClicked(MouseEvent e) {
        int x=e.getX(); // Retrieving click coordinates
        int y=e.getY();
        if (isInInterval(x,260,440) && isInInterval(y,265,320) && !pause) { // if the game is not paused and the user clicks on the 'Pause' button
            GameModel.pauseGame(); // pauses the game
        } else if (isInInterval(x,260,440) && isInInterval(y,265,320) && pause) { // if the game is paused and the user clicks on the 'Resume' button
            GameModel.resumeGame(); // resumes (unpause) the game
        } else if (isInInterval(x,260,440) && isInInterval(y,335,390)) { // the user clicks on the 'Restart' button
            GameModel.restartGame(); // restarts the game
        }
    }

    /**
     * When the user presses his mouse, makes the button gray in order to indicate to the player that he is clicking on the button
     * @param e : event of the mouse being pressed
     */
    public void mousePressed(MouseEvent e) {
        int x=e.getX(); // Retrieving click coordinates
        int y=e.getY();
        if (isInInterval(x,260,440) && isInInterval(y,265,320)) { // if the user clicks on the 'Pause'/'Resume' button
            gameGUI.paintPause(gameGUI.getGraphics(),true, pause); // changes the button to a gray background
        } else if (isInInterval(x,260,440) && isInInterval(y,335,390)) { // if the user clicks on the 'Restart' button
            gameGUI.paintRestart(gameGUI.getGraphics(),true); // changes the button to gray background
        }
    }

    /**
     * When the mouse is released after pressing it on a button, puts back the button background to black
     * @param e : event of the mouse being released
     */
    public void mouseReleased(MouseEvent e) {
        int x=e.getX(); // Retrieving coordinates
        int y=e.getY();
        if (isInInterval(x,260,440) && isInInterval(y,265,320)) { // if the click is released on the 'Pause'/'Resume' button
            gameGUI.paintPause(gameGUI.getGraphics(),false, pause); // changes the button background to black and changes the text ('Pause'->'Resume' or 'Resume'->'Pause')
        } else if (isInInterval(x,260,440) && isInInterval(y,335,390)) { // if the click is released on the 'Restart' button
            gameGUI.paintRestart(gameGUI.getGraphics(),false); // changes back the button to a black background
        }
    }

    /**
     * Mouse events not used but they have to be written
     * @param e : event
     */
    public void mouseEntered(MouseEvent e) {    }
    public void mouseExited(MouseEvent e) {    }
}
