package view;
import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.parseInt;
import static model.GameModel.*;

/**
 * Menu window of the game where the player picks the difficulty
 */
public class MenuGUI extends JFrame {
    JRadioButton[] radioButtons;// radio buttons of difficulties
    JButton jButton; // button to start the game
    ButtonGroup G1; // button group of radio buttons
    JLabel L1; // "Pick difficulty" label

    public MenuGUI() {
        this.setLayout(null);
        this.setTitle("Tetris"); // title of the window
        this.setIconImage((new ImageIcon("src/logo.png")).getImage()); // window icon

        radioButtons = new JRadioButton[4]; // radio buttons
        G1 = new ButtonGroup(); // Button group of radio buttons

        for (int i = 0; i < 4; i++) { // setting up all the radio buttons
            radioButtons[i] = new JRadioButton();
            radioButtons[i].setActionCommand(String.valueOf(i+1));
            radioButtons[i].setForeground(Color.WHITE); // Setting font color of radio buttons to white
            radioButtons[i].setOpaque(false); // Setting the background transparent for radio buttons
            radioButtons[i].setBounds(50,50+50*i,100,30); // coordinates and size
            G1.add(radioButtons[i]); // adding the radio button to the button group
            this.add(radioButtons[i]); // adding the radio button to the window
        }
        radioButtons[0].setSelected(true); // select easy as the default difficulty
        radioButtons[0].setText("Easy");
        radioButtons[1].setText("Intermediate");
        radioButtons[2].setText("Hard");
        radioButtons[3].setText("Expert");

        jButton = new JButton("Start Game"); // button "Start Game"
        jButton.setBounds(100, 250, 100, 30); // setting coordinates and size of the button
        this.add(jButton); // adding the button to the window

        L1 = new JLabel("Pick difficulty :"); // Label "Pick difficulty"
        L1.setForeground(Color.WHITE); // setting font color of the label
        L1.setBounds(100, 0, 100, 50); // setting coordinates and size of the label
        this.add(L1); // adding the label to the window

        this.setSize(300,350); // setting the size of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when the user leaves the window the program stops

        this.getContentPane().setBackground(Color.BLACK); // sets the background to black

        jButton.addActionListener(e-> {
            this.dispose();
            difficulty = parseInt(G1.getSelection().getActionCommand());
            hasPickedDifficulty = true;
        });
    }

}
