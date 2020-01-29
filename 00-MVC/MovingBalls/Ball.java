import java.awt.*;

public class Ball implements Runnable {

    private int x, y, size, delay;
    private Color c;
    private int xInterval, yInterval;
    private MovingBalls frame;
    private final int INTERVAL = 10;
    private boolean clear = false;
    private boolean pause = false;

    // constructor
    public Ball()
    {

    }

    // constructor
    public Ball(int x, int y, MovingBalls m)
    {
        this.x = x;
        this.y = y;
        this.size = (int)(Math.random()*50);
        this.delay = (int)(Math.random()*100);

        int R = (int)(Math.random()*256);
        int G = (int)(Math.random()*256);
        int B = (int)(Math.random()*256);
        this.c = new Color(R, G, B);

        frame = m;
        xInterval = (int)(Math.random()*10) % 2 == 0 ? INTERVAL : -1 * INTERVAL;
        yInterval = (int)(Math.random()*10) % 2 == 0 ? INTERVAL : -1 * INTERVAL;
    }

    public void run()
    {
        while (clear == false) {
            if (pause == false) {
                Thread.interrupted();
                x += xInterval;
                y += yInterval;
                if (x + 10 >= frame.getWidth() || x <= 0)
                    xInterval *= -1;
                if (y + 20 >= frame.getWidth() || y <= 10)
                    yInterval *= -1;
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) { }
            } else {
                try {
                    Thread.sleep(5);    // go to sleep forever!
                } catch (InterruptedException e) { }
            }

            if (!clear) {
                frame.repaint();    // this one for GUI print all the balls
            }
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }
    public Color getColor() { return c; }

    public void setPause(boolean f)
    {
        this.pause = f;
        System.out.println("setPause: " + pause);
    }

    public void setClear(boolean f)
    {
        this.clear = f;
        System.out.println("setClear: " + clear);
    }
}
