package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.*;

public class Statistics {

    public static int score;
    private int tetrisCount;
    public static int linesCompleted;
    public static int bestScore;

    Statistics() {
        this.tetrisCount = 0;
        score = 0;
        linesCompleted = 0;
        bestScore = readHighScore();
    }

    private int readHighScore() {
        try {
            Scanner scanner = new Scanner(new File("src/highscore.txt"));
            return scanner.nextInt();
        } catch (FileNotFoundException ignored) {
            return 0;
        }
    }

    public static int getScore() {
        return score;
    }

    void updateScore(int linesCompleted) {
        if (linesCompleted == 4) {
            score += 800 + 400*max(0,min(1,tetrisCount));
            this.tetrisCount++;
        } else {
            score += linesCompleted * 100;
        }
    }

    public void addToScore(int n) {
        score += n;
    }
}
