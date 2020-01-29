import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BallView theView = new BallView();

        BallController theController = new BallController(theView);

        theView.setVisible(true);
    }
}