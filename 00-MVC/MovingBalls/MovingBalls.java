import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovingBalls extends JPanel {
    private Vector<Ball> t;
    private ExecutorService ex;
    private boolean clear = false;
    private boolean pause = false;

    public MovingBalls()
    {
        t = new Vector<Ball>();
        ex = Executors.newCachedThreadPool();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);    // clean the screen
        if (clear == false) {
            for(Iterator<Ball> it = t.iterator(); it.hasNext();)
            {
                Ball b = it.next();
                g.fillOval(b.getX(),b.getY(),b.getSize(), b.getSize());
                g.setColor(b.getColor());
            }
        } else {
            stopBall(t);
            t.clear();
        }
    }

    private void stopBall (Vector <Ball> temp)
    {
        for(Iterator<Ball> it = temp.iterator(); it.hasNext();)
        {
            Ball b = it.next();
            b.setClear(clear);
        }
    }

    public JPanel getPanel()
    {
        return this;
    }

    public Vector<Ball> getVectorBall ()
    {
        return t;
    }

    public void setClear(boolean f)
    {
        this.clear = f;
    }
}
