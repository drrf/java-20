import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BallController {
    private BallView theView;
    private boolean pause = false;
    private ExecutorService ex;
    private int threadNumber = 0;


    public BallController(BallView theView)
    {
        this.theView = theView;
        ex = Executors.newCachedThreadPool();
        this.theView.setBallListener(new Listener());
    }

    private boolean changeMode (boolean change)
    {
        if (change)
            change = false;
        else
            change = true;

        return  change;
    }

    private class Listener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if (e.getButton() == MouseEvent.BUTTON1) {
                System.out.println("left click!");
                theView.setClear(false);
                // create new ball click;
                Ball b = new Ball(e.getX(), e.getY(), theView.getMovingBalls()); // "MovingBalls.this" it's reference to class Panel
                theView.getVectorBall().add(b);
                ex.execute(b);  // add to mange the threads
                theView.setTitleFrame(++threadNumber);
            } else if (e.getButton() == MouseEvent.BUTTON2) {
                System.out.println("middle click!");
                if (!theView.getVectorBall().isEmpty())   // change only if t not empty
                    pause = changeMode(pause);

                for(Iterator<Ball> it = theView.getVectorBall().iterator(); it.hasNext();)
                {
                    Ball b = it.next();
                    b.setPause(pause);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                System.out.println("right click!");
                theView.setClear(true);
                pause = false;  // update for next time
                theView.setTitleFrame(threadNumber = 0);
            }
        }
    }
}
