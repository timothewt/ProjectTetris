package controller;

import model.GameModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PiecesKeyListener extends KeyAdapter {

    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case 37 -> GridController.move(-1);
            case 39 -> GridController.move(1);
            case 40 -> GridController.move(0);
            case 38 -> GridController.move(2);
            case 32 -> GridController.move(3);
        }
    }
}