package view;
import model.Statistics;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import static java.lang.String.valueOf;
import static model.GameModel.*;

/**
 * Class representing the game window where the players plays the actual game, with the grid, all the pieces, score and so on
 */
public class GameGUI extends JFrame {
    /**
     * Paints the base of the window (grid outerlines, score, time, lines rectangles, buttons)
     * @param g : graphics of the frame
     */
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,465,475); // puts a black background
        g.setColor(Color.GRAY);
        for (int i = 1; i < 10; i++) { // paints the grid lines
            g.drawLine(30+20*i,50, 30+20*i,450);
        }
        for (int i = 1; i < 20; i++) {
            g.drawLine(30,50+20*i, 230,50+20*i);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(30,50,200,400); // grid outlines
        g.drawRect(260,50,80,200); // next pieces outlines
        for (int i = 0; i < 4; i++) { // score, time, lines, highscore rectangles in which they are written
            g.drawRect(360,50+53*i,80,40);
        }
        g.drawString("Score",365,65); // labels inside the rectangles
        g.drawString("Time",365,120);
        g.drawString("High Score",365,171);
        g.drawString(valueOf(Statistics.bestScore),365,186); // writes the highscore because it doesnt change throughout the game
        g.drawString("Lines",365,225);
        g.drawRect(260,270,180,55); // buttons 'Pause' and 'Restart' outlines
        g.drawRect(260,345,180,55);
        g.drawRect(260,420,180,30); // difficulty level outlines
        g.drawString("Pause",334,300); // pause and restart labels
        g.drawString("Restart",333,375);
        paintGrid(g); // paints the inside of the grid (pieces)
        paintNext(g); // paints the 3 next tetrominoes
        paintScore(g); // paints the score inside the score rectangle
        paintTime(g); // paints the time inside the time rectangle
        paintLines(g); // paints the lines inside the lines rectangle
        paintDifficulty(g); // paints the difficulty inside the difficulty rectangle
        paintNextLabel((Graphics2D) g); // paints the 'Next' label rotated of -90°

    }

    /**
     * Paints the 'Next' label rotated by 90° next to the next tetrominoes
     * @param g : 2d graphics of the frame
     */
    private void paintNextLabel(Graphics2D g) {
        AffineTransform at = new AffineTransform(); // new transformation, here it's a -90 degrees rotation
        at.setToRotation(-Math.PI / 2);
        g.setTransform(at);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(g.getFont().deriveFont(g.getFont().getSize() * 2F)); // makes the font 2 times bigger
        g.drawString("NEXT", -130, 315); // writes NEXT
        at.setToRotation(0); // sets back rotation to 0
        g.setTransform(at);
        g.setFont(g.getFont().deriveFont(g.getFont().getSize() * 0.5F)); // set back the font to the original size
    }

    /**
     * Paints the difficulty
     * @param g : graphics of the frame
     */
    public void paintDifficulty(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(265,421,175,28); // paints a black rectangle on the previous difficulty to erase it
        g.setColor(Color.LIGHT_GRAY);
        switch (difficulty) { // writes the difficulty in the difficulty rectangle
            case 1 -> g.drawString("Difficulty level : Easy",299,440);
            case 2 -> g.drawString("Difficulty level : Intermediate",280,440);
            case 3 -> g.drawString("Difficulty level : Hard",299,440);
            case 4 -> g.drawString("Difficulty level : Expert",297,440);
        }
    }

    /**
     * Writes the time elapsed since the start of the game in the 'Time' rectangle
     * @param g : graphics of the frame
     */
    public void paintTime(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(365,123,70,13); // paints a black rectangle on the previous time to erase it
        g.setColor(Color.WHITE);
        String timeSeparator = getGameTimeS()%60<10? ":0":":"; // (ternary operator) if the seconds are below ten we have to add a 0 otherwise it will be '4:8' instead of '4:08' (for 4min8sec)
        g.drawString((getGameTimeS() - getGameTimeS() % 60) / 60 +timeSeparator+ getGameTimeS()%60,365,134); // (getGameTimeS() - getGameTimeS() % 60) / 60 gives the minutes and getGameTimeS()%60 the seconds
    }

    /**
     * Write the score of the current game in the 'Score' rectangle
     * @param g : graphics of the frame
     */
    public void paintScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(365,69,70,13); // paints a black rectangle on the previous score to erase it
        g.setColor(Color.WHITE);
        g.drawString(valueOf(Statistics.getScore()),365,80); // writes
    }

    /**
     * Write the lines completed during the current game in the 'Lines' rectangle
     * @param g : graphics of the frame
     */
    public void paintLines(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(365,233,70,13); // paints a black rectangle on the previous score to erase it
        g.setColor(Color.WHITE);
        g.drawString(valueOf(Statistics.linesCompleted),365,244); // writes
    }

    /**
     * Paints all the cells of the grid of the current game on the grid
     * @param g : graphics of the frame
     */
    public void paintGrid(Graphics g) {
        for (int i = 0; i < 20; i++) { // loops through the whole grid
            for (int j = 0; j < 10; j++) {
                g.setColor(gameGrid.getGrid()[i][j]); // uses the color of the current cell
                g.fillRect(31+20*j,51+20*i,17,17); // paints a rectangle on the cell
            }
        }
    }

    /**
     * Paints the pause button when the user presses it. Its background becomes gray.
     * The label on the button changes (when the user pauses the game it goes to 'Resume' and when he resumes it, it goes to 'Pause' again)
     * @param g : graphics of the frame
     * @param pressed : is the button pressed (true) or released (false)
     * @param isPaused : is the game paused (true) or not (false)
     */
    public void paintPause(Graphics g, boolean pressed, boolean isPaused) {
        String s1 = isPaused ? "Resume" : "Pause"; // If the game is paused we go from 'Resume' to 'Pause' otherwise from 'Pause' to 'Resume'
        String s2 = isPaused ? "Pause" : "Resume";
        int padding = isPaused ? 0:-5; // If we write 'Resume' we have to write it some pixels to the left for it to be centered
        Color btnColor = pressed ? Color.GRAY : Color.BLACK; // If the button is pressed the background will be gray, black if released
        String str = pressed ? s1:s2; // is the button is pressed chooses the first string otherwise the second
        g.setColor(btnColor); // draws a rect of the color chosen above
        g.fillRect(261,271,178,53);
        g.setColor(Color.LIGHT_GRAY); // writes the String on the button
        g.drawString(str,334+padding,300);
    }
    public void paintRestart(Graphics g, boolean pressed) {
        Color btnColor = pressed ? Color.GRAY:Color.BLACK; // If the button is pressed the background will be gray, black if released
        g.setColor(btnColor); // paints the background of the button
        g.fillRect(261,346,178,53);
        g.setColor(Color.LIGHT_GRAY); //paints the Restart label because it was erased by the filled rectangle painted 2 lines above
        g.drawString("Restart",333,375);
    }

    /**
     * Paints the 3 next pieces in the 'NEXT' rectangle
     * @param g : graphics of the frame
     */
    public void paintNext(Graphics g) {
        g.setColor(Color.BLACK); // paints a black rectangle to erase the 3 previous pieces
        g.fillRect(261,51,79,199);
        for (int i = 0; i < 3; i++) { // loops through the next pieces
            switch (nextPieces[i].type) { // paints the tetrominoe depending on the type
                case 0 -> { // T
                    g.setColor(Color.MAGENTA);
                    g.fillRect(290,70+60*i,20,20);
                    g.fillRect(270,90+60*i,60,20);
                }
                case 1 -> { // Z
                    g.setColor(Color.RED);
                    g.fillRect(270,70+60*i,40,20);
                    g.fillRect(290,90+60*i,40,20);
                }
                case 2 -> { // S
                    g.setColor(Color.GREEN);
                    g.fillRect(290,70+60*i,40,20);
                    g.fillRect(270,90+60*i,40,20);
                }
                case 3 -> { // O
                    g.setColor(Color.YELLOW);
                    g.fillRect(280,70+60*i,40,40);
                }
                case 4 -> { // J
                    g.setColor(Color.ORANGE);
                    g.fillRect(310,70+60*i,20,20);
                    g.fillRect(270,90+60*i,60,20);
                }
                case 5 -> { // L
                    g.setColor(Color.BLUE);
                    g.fillRect(270, 70+60*i,20,20);
                    g.fillRect(270,90+60*i,60,20);
                }
                case 6 -> { // I
                    g.setColor(Color.CYAN);
                    g.fillRect(264,81+60*i,72,18);
                }
            }
        }
    }

    /**
     * Displays a message with two buttons choices
     * @param title : title of the message window
     * @param message : message to display, can be written in html
     * @param opt1 : message written on button 1
     * @param opt2 : message written on button 2
     * @return 0 if the player chose the first button 1 if he chose the second
     */
    public int displayMessage(String title, String message, String opt1, String opt2) {
        JLabel label = new JLabel("<html><p align=\"center\">"+message); // aligns the message in the center
        label.setHorizontalAlignment(JLabel.CENTER); //
        return JOptionPane.showOptionDialog(this, // displays the window
                label,
                title,
                JOptionPane.YES_NO_OPTION, // type of the message displayed
                JOptionPane.PLAIN_MESSAGE, // no icon in the window
                null,     //do not use a custom Icon
                new String[]{opt1,opt2},  //the titles of buttons
                ""); //default button title
    }

    public GameGUI() {
        this.setLayout(null);
        this.setTitle("Tetris"); // sets the title of the window
        this.setIconImage((new ImageIcon("src/logo.png")).getImage()); // window icon
        this.setSize(465,480); // sets the size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the program stops when the user closes the window
        this.setVisible(true); // Sets the window visible
        this.addKeyListener(KL); // adds the key controls
        this.addMouseListener(BML); // adds the buttons controls
    }
}