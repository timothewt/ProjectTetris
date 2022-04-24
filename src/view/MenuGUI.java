package view;
import javax.swing.*;
import java.awt.*;
import static java.lang.Integer.parseInt;
import static model.GameModel.*;

public class MenuGUI extends JFrame {
    JRadioButton jRadioButton1;
    JRadioButton jRadioButton2;
    JRadioButton jRadioButton3;
    JRadioButton jRadioButton4;
    JButton jButton;
    ButtonGroup G1;
    JLabel L1;

    public MenuGUI() {
        this.setLayout(null);
        this.setTitle("Tetris");

        jRadioButton1 = new JRadioButton();
        jRadioButton1.setActionCommand("1");
        jRadioButton2 = new JRadioButton();
        jRadioButton2.setActionCommand("2");
        jRadioButton3 = new JRadioButton();
        jRadioButton3.setActionCommand("3");
        jRadioButton4 = new JRadioButton();
        jRadioButton4.setActionCommand("4");

        jButton = new JButton("Start Game");

        G1 = new ButtonGroup();

        L1 = new JLabel("Pick difficulty :");
        L1.setForeground(Color.WHITE);

        jRadioButton1.setText("Easy");
        jRadioButton2.setText("Intermediate");
        jRadioButton3.setText("Hard");
        jRadioButton4.setText("Expert");
        jRadioButton1.setForeground(Color.WHITE);
        jRadioButton2.setForeground(Color.WHITE);
        jRadioButton3.setForeground(Color.WHITE);
        jRadioButton4.setForeground(Color.WHITE);
        jRadioButton1.setOpaque(false);
        jRadioButton2.setOpaque(false);
        jRadioButton3.setOpaque(false);
        jRadioButton4.setOpaque(false);

        jRadioButton1.setBounds(50, 50, 100, 30);
        jRadioButton2.setBounds(50, 100, 100, 30);
        jRadioButton3.setBounds(50, 150, 100, 30);
        jRadioButton4.setBounds(50, 200, 100, 30);
        jButton.setBounds(100, 250, 100, 30);

        L1.setBounds(100, 0, 100, 50);

        this.add(jRadioButton1);
        this.add(jRadioButton2);
        this.add(jRadioButton3);
        this.add(jRadioButton4);
        this.add(jButton);
        this.add(L1);

        jRadioButton1.setSelected(true);
        G1.add(jRadioButton1);
        G1.add(jRadioButton2);
        G1.add(jRadioButton3);
        G1.add(jRadioButton4);

        this.setSize(300,350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setBackground(Color.BLACK);

        jButton.addActionListener(e -> {
            dispose();
            difficulty = parseInt(G1.getSelection().getActionCommand());
            hasPickedDifficulty = true;
        });
    }

}
