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
        g.drawRect(360,50,80,40);
        g.drawString("Score",365,65);
        g.drawRect(360, 103,80,40);
        g.drawString("Time",365,120);
        g.drawRect(360, 156,80,40);
        g.drawString("High Score",365,171);
        g.drawString(valueOf(Statistics.bestScore),365,186);
        g.drawRect(360, 210,80,40);
        g.drawString("Lines",365,225);
        g.drawRect(260,270,180,55);
        g.drawString("Pause",335,300);
        g.drawRect(260,345,180,55);
        g.drawString("Restart",333,375);
        g.drawRect(260,420,180,30);
        paintGrid(g);
        paintNext(g);
        paintScore(g);
        paintTime(g);
        paintLines(g);
        paintDifficulty(g);
        AffineTransform at = new AffineTransform();
        at.setToRotation(-Math.PI / 2);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setTransform(at);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(g2d.getFont().deriveFont(g2d.getFont().getSize() * 2F));
        g2d.drawString("NEXT", -130, 315);
        at.setToRotation(0);
        g2d.setTransform(at);
        g2d.setFont(g2d.getFont().deriveFont(g2d.getFont().getSize() * 0.5F));

    }

    public void paintDifficulty(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(265,421,175,28);
        g.setColor(Color.LIGHT_GRAY);
        switch (difficulty) {
            case 1 -> g.drawString("Difficulty level : Easy",299,440);
            case 2 -> g.drawString("Difficulty level : Intermediate",280,440);
            case 3 -> g.drawString("Difficulty level : Hard",299,440);
            case 4 -> g.drawString("Difficulty level : Expert",297,440);
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
        g.fillRect(261,271,178,53);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Pause",335,300);
    }
    public void paintPauseReleased(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(261,271,178,53);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Resume",330,300);
    }
    public void paintResumePressed(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(261,271,178,53);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Resume",330,300);
    }
    public void paintResumeReleased(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(261,271,178,53);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Pause",335,300);
    }
    public void paintRestartPressed(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(261,346,178,53);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Restart",333,375);
    }
    public void paintRestartReleased(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(261,346,178,53);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Restart",333,375);
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

    public int displayMessage(String title, String message, String opt1, String opt2) {
        JLabel label = new JLabel("<html><p align=\"center\">"+message);
        label.setHorizontalAlignment(JLabel.CENTER);
        return JOptionPane.showOptionDialog(this,
                label,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,     //do not use a custom Icon
                new String[]{opt1,opt2},  //the titles of buttons
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