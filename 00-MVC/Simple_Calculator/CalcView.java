import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;

public class CalcView extends JFrame{
    private JTextField firstNumber = new JTextField(10);
    private JTextField secondNumber = new JTextField(10);
    private JButton calculateButton = new JButton("Calculate");
    private JTextField calcSolution = new JTextField(10);
    String[] calcStrings = { "+", "-", "x", "/" };
    JComboBox calcListBox = new JComboBox(calcStrings);

    CalcView() {
        JPanel calcPanel = new JPanel();
        this.setTitle("Simple Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 150);
        this.setLocationRelativeTo(null);  // put GUI in the center

        calcPanel.add(firstNumber);
        calcPanel.add(calcListBox);
        calcPanel.add(secondNumber);
        calcPanel.add(calculateButton);
        calcPanel.add(calcSolution);

        this.add(calcPanel);
    }

    public int getFirstNumber() {
        return  Integer.parseInt(firstNumber.getText());
    }

    public int getSecondNumber() {
        return  Integer.parseInt(secondNumber.getText());
    }

    public JComboBox getCalcListBox() {
        return calcListBox;
    }

    public JButton getCalcButton() {
        return  calculateButton;
    }

    public double getCalcSolution() {
        return  Double.parseDouble(calcSolution.getText());
    }

    public void setCalcSolution(double solution) {
        DecimalFormat df = new DecimalFormat("#.##");
        calcSolution.setText(df.format(solution));
    }

    void setCalculationListener(ActionListener listenerForCalcButton) {
        this.calculateButton.addActionListener(listenerForCalcButton);
    }

    void setListBoxListener(ActionListener listenerForListBox) {
        this.calcListBox.addActionListener(listenerForListBox);
    }

    void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
