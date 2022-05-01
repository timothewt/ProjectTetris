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

/**
 * Core of the game logic and game loop
 */
public class GameModel {
    public static Grid gameGrid; // Grid of the game with tetrominoes
    public static GameGUI gameGUI; // Game window (grid, next pieces, score, ...)
    public static Tetrominoe[] nextPieces; // Three next pieces
    public static Tetrominoe currentPiece; // Current piece controlled on the grid
    public static volatile boolean hasPickedDifficulty; // Boolean indicating if the user has chosen the difficulty in the menu
    public static int difficulty; // Integer indicating difficulty (easy=1, intermediate=2, hard=3, expert=4)
    private static int pickedDifficulty; // Integer that doesn't change during the game indicating the difficulty chosen at the beginning
    private static int difficultyChange; // Number of difficulty changes throughout the game
    private static boolean hasLost; // True if the player has lost the game false otherwise
    public static Statistics stats; // Statistics of the game (scores)
    public static PiecesKeyListener KL = new PiecesKeyListener(); // Key listeners that gives the controls of the current piece
    public static ButtonsMouseListener BML = new ButtonsMouseListener();  // Mouse listeners to use the buttons pause and restart
    static Clock gameTimer; // Clock used to time the game
    static long msStart; // start of the game in milliseconds
    public static volatile boolean pause; // indicates if the game is paused
    public static boolean hardDrop; // indicates if the player hard drops a piece

    /**
     * Constructor, sets all necessary attributes at base values
     */
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
        pause = false;
        hardDrop = false;
    }

    /**
     * Creates new window where the user picks the difficulty then starts the game
     */
    public void start() {
        MenuGUI menu = new MenuGUI();
        menu.setVisible(true);
        while (!hasPickedDifficulty) {
            Thread.onSpinWait();
        }
        pickedDifficulty = difficulty;
        startGame();
    }

    /**
     * Displays the game window and starts the main game loop
     */
    public void startGame() {
        msStart = gameTimer.millis(); // sets time to 0
        gameGUI = new GameGUI(); // creates an instance of the game window (game GUI)
        loop(); // starts the game loop
    }

    /**
     * Pauses the game by disabling the key controls
     */
    public static void pauseGame() {
        gameGUI.removeKeyListener(KL); // removes the listener so the user can't interact with the piece anymore
        pause = true;
    }

    /**
     * Resumes the game by enabling the key controls
     */
    public static void resumeGame() {
        gameGUI.addKeyListener(KL); // adds the listener so the user can interact with the piece
        pause = false;
    }

    /**
     * Makes a new game by resetting all variables to base values
     */
    public static void restartGame() {
        gameGrid = new Grid(); // New blank grid
        nextPieces = new Tetrominoe[]{PieceGenerator.generate(),PieceGenerator.generate(),PieceGenerator.generate()}; // New set of pieces
        currentPiece = PieceGenerator.generate();
        msStart = gameTimer.millis(); // resets the timer
        stats = new Statistics(); // New statistics
        difficulty = pickedDifficulty; // Resets the difficulty to the on the user chose
        if(pause) { // resumes the game if the game was paused in order to re-able the key listener
            resumeGame();
        }
        if(hasLost) { // If the game was over, creates a new window and re-starts the loop
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

    /**
     * Main game loop that keeps looping until the player looses
     */
    private static void loop() {
        while (!hasLost) {
            if (currentPiece.canPlacePiece(gameGrid)) { // If the piece can be placed keep playing otherwise the player looses
                currentPiece.placePiece(gameGrid); // places the piece then displays new grid
                gameGUI.paintGrid(gameGUI.getGraphics());
                waitMS(1250-250*difficulty); // wait for some time until next move
                while(currentPiece.canMoveDown(gameGrid)) { // while the piece can move down it keep doing down, waits some time between moves
                    currentPiece.moveDown(gameGrid);
                    gameGUI.paintGrid(gameGUI.getGraphics());
                    waitMS(1250-250*difficulty);
                }
                stats.updateScore(-linesCompleted + (linesCompleted += gameGrid.checkLines())); // when the piece reaches the lowest position, checks if lines are complete and updates score
                gameGUI.paintScore(gameGUI.getGraphics()); // update display of lines completed and score
                gameGUI.paintLines(gameGUI.getGraphics());
                currentPiece = nextPieces[0]; // shift pieces in the table NextPieces and generates a new one
                System.arraycopy(nextPieces, 1, nextPieces, 0, 2);
                nextPieces[2] = PieceGenerator.generate();
                gameGUI.paintNext(gameGUI.getGraphics()); // update display of next pieces
            } else {
                hasLost = true;
            }
            increaseDifficulty();
        }
        lostGame();
    }

    /**
     * Increases difficulty if a certain number of lines is reached for the game to be hard enough
     */
    private static void increaseDifficulty() {
        if(isInInterval(linesCompleted,10,19) && difficultyChange == 0) { // if the player reaches more than 10 lines and the difficulty still hasn't changed, increases
            difficulty = min(difficulty+1,4); // If the difficulty is above 4, picks 4
            difficultyChange++;
        } else if(isInInterval(linesCompleted,20,34) && difficultyChange == 1) { // if the player reaches more than 20 lines and the difficulty changed only once, increases
            difficulty = min(difficulty+1,4);
            difficultyChange++;
        } else if(35 <= linesCompleted && difficultyChange == 2) { // if the player reaches more than 10 lines and the difficulty changed only twice, increases
            difficulty = min(difficulty+1,4);
            difficultyChange++;
        }
        gameGUI.paintDifficulty(gameGUI.getGraphics()); // updates difficulty display
    }

    /**
     * Called when the game is lost, displays a window showing the score and the player has to choose between starting a new game and leaving
     */
    private static void lostGame() {
        gameGUI.removeKeyListener(KL); // removes the key controls
        int n;
        if(Statistics.score > bestScore) { // if it's a new high score displays a special window and updates it in the text file highscore.txt
            n = gameGUI.displayMessage("Game Over","You lost<br>New high score : "+Statistics.score+" !","New Game","Quit Game");
            writeBestScore(Statistics.score);
        } else {
            n = gameGUI.displayMessage("Game Over","You lost<br>Score : "+Statistics.score,"New Game","Quit Game");
        }
        if (n==0) { // If the user chose New Game
            restartGame();
        } else { // If the user chose Quit Game
            System.exit(0);
        }
    }

    /**
     * Erases the file and writes score in highscore.txt
     * @param score : score to write in the file
     */
    private static void writeBestScore(int score) {
        try {
            Writer wr = new FileWriter("src/highscore.txt");
            wr.write(valueOf(score));
            wr.close();
        } catch (IOException ignored){}
    }

    /**
     * Waits for a certain time before stopping and checking certain conditions
     * @param ms : time to wait in milliseconds
     */
    static public void waitMS(int ms) {
        long t1 = gameTimer.millis(); // starting time of the loop
        while (gameTimer.millis() - t1 < ms) { // while time isn't reached, loops
            if (getGameTimeMS()%1000 < 20) { // If the game timer reaches a new second, updates the time display
                gameGUI.paintTime(gameGUI.getGraphics());
            }
            if (pause) { // If the game is paused, loops while it's still paused
                long pauseStart = getGameTimeMS();
                while (pause) {
                    Thread.onSpinWait();
                }
                msStart += getGameTimeMS() - pauseStart; // updates time by subtracting the time elapsed during the pause
            }
            if (hardDrop) { // If a piece is hard dropped breaks the loop in order to keep the game running
                hardDrop = false;
                break;
            }
        }
    }

    /**
     * Checks if an interger n is in the interval [min,max]
     * @param n : number to check
     * @param min : minimum bound
     * @param max : maximum  bound
     * @return true if the value is in the interval false otherwise
     */
    public static boolean isInInterval(int n, int min, int max) {
        return n>=min && n<=max;
    }

    /**
     * Gives the time of the game in milliseconds
     * @return the gime elapsed since the beginning of the game in milliseconds
     */
    public static long getGameTimeMS() {
        return gameTimer.millis() - msStart;
    }
    /**
     * Gives the time of the game in seconds
     * @return the gime elapsed since the beginning of the game in seconds
     */
    public static long getGameTimeS() {
        return (gameTimer.millis() - msStart)/1000;
    }
}
