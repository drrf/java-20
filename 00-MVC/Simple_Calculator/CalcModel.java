public class CalcModel {
    private double calculationValue;

    public void addTwoNumbers(int firstNumber, int secondNumber) {
        calculationValue = firstNumber + secondNumber;
    }

    public void subtractTwoNumbers(int firstNumber, int secondNumber) {
        calculationValue = firstNumber - secondNumber;
    }

    public void multipleTwoNumbers(int firstNumber, int secondNumber) {
        calculationValue = firstNumber * secondNumber;
    }

    public void divideTwoNumbers(int firstNumber, int secondNumber) {
        calculationValue = (double)firstNumber / (double)secondNumber;
    }

    public double getCalculationValue() {
        return  calculationValue;
    }
}
