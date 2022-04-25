package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Math.*;

/**
 * Class that contains the statistics of the game (score, highscore, number of completed lines,..)
 */
public class Statistics {

    public static int score; // score of the game
    private int tetrisCount; // number of 'tetris' (4 lines completed with a single piece)
    public static int linesCompleted; // number of lines completed
    public static long bestScore; // highest score of all time

    /**
     * Constructor setting all the stats to 0 and the best score to the highest score contained in a file
     */
    Statistics() {
        this.tetrisCount = 0;
        score = 0;
        linesCompleted = 0;
        bestScore = readHighScore();
    }

    /**
     * Reads the highest score in the file highscore.txt
     * @return the highscore
     */
    private long readHighScore() {
        try { // if the files is not found returns 0
            Scanner scanner = new Scanner(new File("src/highscore.txt")); // scans the file for the single integer it contains
            return scanner.nextLong();
        } catch (FileNotFoundException ignored) {
            return 0;
        }
    }

    /**
     * Score getter
     * @return the score of the game
     */
    public static int getScore() {
        return score;
    }

    /**
     * Updates the score as a function of the completed lines (1:+100,2:+200,3:+300,4:+800 or +1200 if 4 has already been done (tetris))
     * @param linesCompleted : number of lines completed with a single piece
     */
    void updateScore(int linesCompleted) {
        if (linesCompleted == 4) { // if it is a tetris (4 lines)
            score += 800 + 400*max(0,min(1,tetrisCount)); // adds 800 if no tetris had been done before otherwise adds 1200
            this.tetrisCount++; // adds 1 to tetris count
        } else {
            score += linesCompleted * 100; // adds 100 200 or 300
        }
    }

    /**
     * adds a certain value to the score
     * @param n : value to add to the score
     */
    public void addToScore(int n) {
        score += n;
    }
}
