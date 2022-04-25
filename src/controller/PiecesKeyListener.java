package controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * key listener used to detect when the player hits the keyboard
 */
public class PiecesKeyListener extends KeyAdapter {

    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case 37 -> GridController.move(-1); // left arrow
            case 39 -> GridController.move(1); // right arrow
            case 40 -> GridController.move(0); // bottom arrow
            case 38 -> GridController.move(2); // upwards arrow
            case 32 -> GridController.move(3); // space bar
        }
    }
}