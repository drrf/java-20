import javax.swing.*;
import java.awt.event.MouseListener;
import java.util.Vector;

public class BallView extends JFrame {
    private MovingBalls mb;

    BallView() {
        this.setTitle("Moving Balls");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        mb = new MovingBalls();
        this.add(mb);
        this.setLocationRelativeTo(null);  // put GUI in the center
    }

    public void setTitleFrame(int num)
    {
        this.setTitle("Moving Balls (" + num + ")");
    }

    public void setBallListener(MouseListener listenerForPanel)
    {
        this.mb.getPanel().addMouseListener(listenerForPanel);
    }

    public MovingBalls getMovingBalls ()
    {
        return mb;
    }

    public Vector<Ball> getVectorBall ()
    {
        return mb.getVectorBall();
    }

    public void setClear(boolean f)
    {
        mb.setClear(f);
    }
}