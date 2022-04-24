package model;
import controller.ButtonsMouseListener;
import controller.PiecesKeyListener;
import view.GameGUI;
import view.MenuGUI;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Clock;

import static java.lang.Math.min;
import static java.lang.String.valueOf;
import static model.Statistics.bestScore;
import static model.Statistics.linesCompleted;

public class GameModel {

    public static Grid gameGrid;
    public static GameGUI gameGUI;
    public static Tetrominoe[] nextPieces;
    public static volatile boolean hasPickedDifficulty;
    public static Tetrominoe currentPiece;
    public static int difficulty;
    private static int pickedDifficulty;
    private static int difficultyChange;
    private static boolean hasLost;
    public static Statistics stats;
    public static PiecesKeyListener KL = new PiecesKeyListener();
    public static ButtonsMouseListener BML = new ButtonsMouseListener();
    static Clock gameTimer;
    static long msStart;
    public static volatile boolean pause;
    public static boolean hardDrop;


    public GameModel() {
        hasPickedDifficulty = false;
        gameGrid = new Grid();
        nextPieces = new Tetrominoe[]{PieceGenerator.generate(),PieceGenerator.generate(),PieceGenerator.generate()};
        currentPiece = PieceGenerator.generate();
        difficulty = 0;
        difficultyChange = 0;
        hasLost = false;
        stats = new Statistics();
        gameTimer = Clock.systemUTC();
        msStart = gameTimer.millis();
        pause = false;
        hardDrop = false;
    }

    public void start() {
        MenuGUI menu = new MenuGUI();
        menu.setVisible(true);
        while (!hasPickedDifficulty) {
            Thread.onSpinWait();
        }
        pickedDifficulty = difficulty;
        startGame();
    }
    public void startGame() {
        gameGUI = new GameGUI();
        loop();
    }

    public static void pauseGame() {
        gameGUI.removeKeyListener(KL);
        pause = true;
    }
    public static void resumeGame() {
        gameGUI.addKeyListener(KL);
        pause = false;
    }

    public static void restartGame() {
        gameGrid = new Grid();
        nextPieces = new Tetrominoe[]{PieceGenerator.generate(),PieceGenerator.generate(),PieceGenerator.generate()};
        currentPiece = PieceGenerator.generate();
        msStart = gameTimer.millis();
        stats = new Statistics();
        difficulty = pickedDifficulty;
        if(pause) {
            resumeGame();
        }
        if(hasLost) {
            gameGUI.dispose();
            gameGUI = new GameGUI();
            gameGUI.paint(gameGUI.getGraphics());
            pause = false;
            hasLost = false;
            loop();
        } else {
            gameGUI.paint(gameGUI.getGraphics());
        }
    }

    private static void loop() {
        while (!hasLost) {
            if (currentPiece.canPlacePiece(gameGrid)) {
                currentPiece.placePiece(gameGrid);
                gameGUI.paintGrid(gameGUI.getGraphics());
                waitMS(1250-250*difficulty);
                while(currentPiece.canMoveDown(gameGrid)) {
                    currentPiece.moveDown(gameGrid);
                    gameGUI.paintGrid(gameGUI.getGraphics());
                    waitMS(1250-250*difficulty);
                }
                stats.updateScore(-linesCompleted + (linesCompleted += gameGrid.checkLines()));
                gameGUI.paintScore(gameGUI.getGraphics());
                gameGUI.paintLines(gameGUI.getGraphics());
                currentPiece = nextPieces[0];
                System.arraycopy(nextPieces, 1, nextPieces, 0, 2);
                nextPieces[2] = PieceGenerator.generate();
                gameGUI.paintNext(gameGUI.getGraphics());
            } else {
                hasLost = true;
            }
            increaseDifficulty();
        }
        lostGame();
    }

    private static void increaseDifficulty() {
        System.out.println(difficultyChange);
        if(1<=linesCompleted && linesCompleted< 20 && difficultyChange == 0) {
            difficulty = min(difficulty+1,4);
            difficultyChange++;
        } else if(20<=linesCompleted && linesCompleted< 35 && difficultyChange == 1) {
            difficulty = min(difficulty+1,4);
            difficultyChange++;
        } else if(35 <= linesCompleted && difficultyChange == 2) {
            difficulty = min(difficulty+1,4);
            difficultyChange++;
        }
        gameGUI.paintDifficulty(gameGUI.getGraphics());
    }

    private static void lostGame() {
        gameGUI.removeKeyListener(KL);
        int n;
        if(Statistics.score > bestScore) {
            n = gameGUI.displayMessage("You lost<br>New high score : "+Statistics.score+"!");
            writeBestScore(Statistics.score);
        } else {
            n = gameGUI.displayMessage("You lost<br>Score : "+Statistics.score);
        }
        if (n==0) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private static void writeBestScore(int score) {
        try {
            Writer wr = new FileWriter("src/bestscore.txt");
            wr.write(valueOf(score));
            wr.close();
        } catch (IOException ignored){}
    }

    static public void waitMS(int ms) {
        Clock clock = Clock.systemUTC();
        long t1 = clock.millis();
        while (clock.millis() - t1 < ms) {
            if(getGameTimeMS()%1000 < 10) {
                gameGUI.paintTime(gameGUI.getGraphics());
            }
            if (pause) {
                long pauseStart = getGameTimeMS();
                while (pause) {
                    Thread.onSpinWait();
                }
                msStart += getGameTimeMS() - pauseStart;
            }
            if (hardDrop) {
                hardDrop = false;
                break;
            }
        }
    }

    public static long getGameTimeMS() {
        return gameTimer.millis() - msStart;
    }
    public static long getGameTimeS() {
        return (gameTimer.millis() - msStart)/1000;
    }
}
