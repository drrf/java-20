public class Main {
    public static void main(String[] args) {
        CalcView theView = new CalcView();

        CalcModel theModel = new CalcModel();

        CalcController theController = new CalcController(theView,theModel);

        theView.setVisible(true);
    }
}
