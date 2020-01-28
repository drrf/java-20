import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcController {
    private CalcView theView;
    private CalcModel theModel;
    private enum Mode {ADD, SUBTRACT, MULTIPLE, DIVIDE }

    public CalcController(CalcView theView, CalcModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.setCalculationListener(new CalcListener());
        this.theView.setListBoxListener(new CalcListener());
    }

    class CalcListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int firstNumber, secondNumber = 0;


            try {
                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();

                if (e.getSource() == theView.getCalcListBox() || e.getSource() == theView.getCalcButton()) {
                    Mode ModeVar = Mode.ADD;

                    if (theView.getCalcListBox().getSelectedItem().equals("+"))
                        ModeVar = Mode.ADD;
                    else if (theView.getCalcListBox().getSelectedItem().equals("-"))
                        ModeVar = Mode.SUBTRACT;
                    else if (theView.getCalcListBox().getSelectedItem().equals("x"))
                        ModeVar = Mode.MULTIPLE;
                    else if (theView.getCalcListBox().getSelectedItem().equals("/"))
                        ModeVar = Mode.DIVIDE;

                    switch(ModeVar) {
                        case ADD: // if add
                            theModel.addTwoNumbers(firstNumber, secondNumber);
                            break;
                        case SUBTRACT: // if subtract
                            theModel.subtractTwoNumbers(firstNumber, secondNumber);
                            break;
                        case MULTIPLE: // if multiple
                            theModel.multipleTwoNumbers(firstNumber, secondNumber);
                            break;
                        case DIVIDE: // if divide
                            theModel.divideTwoNumbers(firstNumber, secondNumber);
                            break;
                        default:
                            // code block
                    }


                }

                theView.setCalcSolution(theModel.getCalculationValue());
            }

            catch (NumberFormatException ex) {
                theView.displayErrorMessage("You Need to Enter 2 Integers");
            }
        }
    }
}
