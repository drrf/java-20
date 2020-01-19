// Main class represents a LifeGame tester/run.
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args)
    {
        int round = 0;
        JFrame frame = new JFrame("Life GAME");
        JLabel headLine = new JLabel("=== LIFE GAME ===");
        JLabel southText = new JLabel("  Generation: " + round);
        headLine.setHorizontalAlignment(JLabel.CENTER);
        headLine.setVerticalAlignment(JLabel.CENTER);
        frame.add(headLine,BorderLayout.NORTH);
        frame.add(southText,BorderLayout.SOUTH);

        // create life (mat) Panel for the game
        LifeGame matPanel = new LifeGame(10);
        frame.add(matPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setSize(517,575);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // call for wait answer - interaction with the user
        while (matPanel.waitAns() != JOptionPane.NO_OPTION)
            southText.setText("  Generation: " + (++round));
    }
}