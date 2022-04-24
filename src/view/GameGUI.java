package view;

import model.Statistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import static java.lang.String.valueOf;
import static model.GameModel.*;

public class GameGUI extends JFrame {

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,465,475);
        g.setColor(Color.GRAY);
        for (int i = 1; i < 10; i++) {
            g.drawLine(30+20*i,50, 30+20*i,450);
        }
        for (int i = 1; i < 20; i++) {
            g.drawLine(30,50+20*i, 230,50+20*i);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(30,50,200,400);
        g.drawRect(260,50,80,200);
        paintGrid(g);
        paintNext(g);
        paintScore(g);
        paintTime(g);
        paintLines(g);
        paintDifficulty(g);
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform at = new AffineTransform();
        at.setToRotation(-Math.PI / 2);
        g2d.setTransform(at);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(g2d.getFont().deriveFont(g2d.getFont().getSize() * 2F));
        g2d.drawString("NEXT", -130, 315);
        at.setToRotation(0);
        g2d.setTransform(at);
        g2d.setFont(g2d.getFont().deriveFont(g2d.getFont().getSize() * 0.7F));
        g2d.drawRect(450,62,100,50);
        g2d.drawString("Score",455,82);
        g2d.drawRect(450, 129,100,50);
        g2d.drawString("Time",455,149);
        g2d.drawRect(450,196,100,50);
        g2d.drawString("Best Score",455,216);
        g2d.drawString(valueOf(Statistics.bestScore),455,241);
        g2d.drawRect(450,262,100,50);
        g2d.drawString("Lines",455,282);
        g2d.drawRect(325,330,225,70);
        g2d.drawString("Pause",415,370);
        g2d.drawRect(325,420,225,70);
        g2d.drawString("Restart",413,460);
        g2d.drawRect(325,510,225,50);

    }

    public void paintDifficulty(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(265,410,175,34);
        g.setColor(Color.LIGHT_GRAY);
        switch (difficulty) {
            case 1 -> g.drawString("Difficulty level : Easy",299,432);
            case 2 -> g.drawString("Difficulty level : Intermediate",280,432);
            case 3 -> g.drawString("Difficulty level : Hard",299,432);
            case 4 -> g.drawString("Difficulty level : Expert",297,432);
        }
    }

    public void paintTime(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(365,123,70,13);
        g.setColor(Color.WHITE);
        if (getGameTimeS()%60<10) {
            g.drawString((getGameTimeS() - getGameTimeS() % 60) / 60 +":0"+ getGameTimeS()%60,365,134);
        } else {
            g.drawString((getGameTimeS() - getGameTimeS() % 60) / 60 + ":" + getGameTimeS() % 60, 365, 134);
        }
    }

    public void paintScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(365,69,70,13);
        g.setColor(Color.WHITE);
        g.drawString(valueOf(Statistics.getScore()),365,80);
    }

    public void paintLines(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(365,233,70,13);
        g.setColor(Color.WHITE);
        g.drawString(valueOf(Statistics.linesCompleted),365,244);
    }

    public void paintGrid(Graphics g) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                g.setColor(gameGrid.getGrid()[i][j]);
                g.fillRect(31+20*j,51+20*i,17,17);
            }
        }
    }

    public void paintPausePressed(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(261,265,179,55);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Pause",335,295);
    }
    public void paintPauseReleased(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(261,265,179,55);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Resume",330,295);
    }
    public void paintResumePressed(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(261,265,179,55);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Resume",330,295);
    }
    public void paintResumeReleased(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(261,265,179,55);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Pause",335,295);
    }
    public void paintRestartPressed(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(261,337,179,55);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Restart",331,368);
    }
    public void paintRestartReleased(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(261,337,179,55);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Restart",331,368);
    }

    public void paintNext(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(261,51,79,199);
        for (int i = 0; i < 3; i++) {
            switch (nextPieces[i].type) {
                case 0 -> {
                    g.setColor(Color.MAGENTA);
                    g.fillRect(290,70+60*i,20,20);
                    g.fillRect(270,90+60*i,60,20);
                }
                case 1 -> {
                    g.setColor(Color.RED);
                    g.fillRect(270,70+60*i,40,20);
                    g.fillRect(290,90+60*i,40,20);
                }
                case 2 -> {
                    g.setColor(Color.GREEN);
                    g.fillRect(290,70+60*i,40,20);
                    g.fillRect(270,90+60*i,40,20);
                }
                case 3 -> {
                    g.setColor(Color.YELLOW);
                    g.fillRect(280,70+60*i,40,40);
                }
                case 4 -> {
                    g.setColor(Color.ORANGE);
                    g.fillRect(310,70+60*i,20,20);
                    g.fillRect(270,90+60*i,60,20);
                }
                case 5 -> {
                    g.setColor(Color.BLUE);
                    g.fillRect(270, 70+60*i,20,20);
                    g.fillRect(270,90+60*i,60,20);
                }
                case 6 -> {
                    g.setColor(Color.CYAN);
                    g.fillRect(264,81+60*i,72,18);
                }
            }
        }
    }

    public int displayMessage(String message) {
        JLabel label = new JLabel("<html><p align=\"center\">"+message);
        label.setHorizontalAlignment(JLabel.CENTER);
        return JOptionPane.showOptionDialog(this,
                label,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,     //do not use a custom Icon
                new String[]{"New Game","Quit"},  //the titles of buttons
                ""); //default button title
    }

    public GameGUI() {
        this.setLayout(null);
        this.setTitle("Tetris");
        this.setSize(465,480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(KL);
        this.addMouseListener(BML);
    }
}