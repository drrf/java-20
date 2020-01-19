// LifeGame class represents a LifeGame playing.
import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

public class LifeGame extends JPanel {
    private boolean _mat[][];       // here are the information about life / dead cell
    private boolean life_check = true;
    private static final SecureRandom randomLife = new SecureRandom();  // for random number in mat 0 or 1

    // constructor get size x return mat[x][x] with boolean values
    public LifeGame(int size)
    {
        this._mat = new boolean[size][size];
        for (int i = 0; i<size; i++)
            for (int j = 0; j<size; j++){
                boolean life = randomLife.nextBoolean();
                this._mat[i][j] = life;
            }
    }

    // return new mat with the update boolean of the next gen
    private boolean[][] nextGen(boolean mat[][])
    {
        int len = mat.length, neighbors;
        boolean newGen[][] = new boolean[len][len];   // the new life gen
        LifeGame nextGen = new LifeGame(len);

        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                switch(neighbors = countNeighbors(mat,row,col)) {
                    case 0:
                        // code block
                    case 1:
                        newGen[row][col] = true;    // Dead here!
                        break;
                    case 2:
                        // code block
                    case 3:
                        if (checkBound(mat,row,col) == life_check) {
                            newGen[row][col] = false;   // LIFE stay life!
                        } else
                            if (neighbors == 3) {
                                newGen[row][col] = false;   // DEAD Become life!
                            } else {
                                newGen[row][col] = true;    // DEAD stay dead!
                            }
                        break;
                    case 4:
                        // code block
                    default:
                        newGen[row][col] = true;    // OVER DEAD!
                }
            }
        }
        return newGen;
    }

    // count how much Neighbors have and return int
    private int countNeighbors(boolean mat[][], int row, int col)
    {
        int neighbors = 0;
        boolean check;

        if (check = checkBound(mat, row, col-1)) neighbors++;
        if (check = checkBound(mat, row, col+1)) neighbors++;
        if (check = checkBound(mat, row-1, col)) neighbors++;
        if (check = checkBound(mat, row+1, col)) neighbors++;
        if (check = checkBound(mat, row-1, col-1)) neighbors++;
        if (check = checkBound(mat, row-1, col+1)) neighbors++;
        if (check = checkBound(mat, row+1, col-1)) neighbors++;
        if (check = checkBound(mat, row+1, col+1)) neighbors++;

        return neighbors;
    }

    // check if now out of bound of the mat
    private boolean checkBound(boolean mat[][], int row, int col) {
        boolean check;
        try {
            check = mat[row][col];
        } catch (ArrayIndexOutOfBoundsException e){
            return false;   // if out of bounds return false
        }
        if (check)
            return false;   // if life not exists return false
        else
            return true;   // if life exists return true
    }

    // overrides method
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        printMatGUI(g);
    }

    // print the mat GUI
    private void printMatGUI(Graphics g)
    {
        int x, y; // for GUI
        y = x = 0;

        int len = this._mat.length;
        for (int row = 0; row < len; row++) {
            for (int col = 0; col < len; col++) {
                if (this._mat[row][col]) {
                    drawRectMat(g,0,x,y);   // dead
                } else {
                    drawRectMat(g,1,x,y);   // life
                }
                x += 50;
            }
            x = 0;
            y += 50;
        }
    }

    // the draw mat method
    private void drawRectMat(Graphics g, int i, int x, int y) {
        Color myBlack = new Color(0,0,0);
        Color myWhite = new Color(255,255,255);
        Color myRed = new Color(255,0,0);
        g.setColor(myRed);
        g.drawRect (x, y, 50, 50);
        if (i == 0)
        {
            g.setColor(myBlack);
            g.fillRect (x+1, y+1, 49, 49);
        } else {
            g.setColor(myWhite);
            g.fillRect (x+1, y+1, 49, 49);
        }
    }

    // wait for answer from interaction with user
    public int waitAns()
    {
        int a=JOptionPane.showConfirmDialog(null,"Want move to next generation?",
                                    "LIFE GAME WAITING FOR ANSWER...", JOptionPane.YES_NO_OPTION);

        if(a==JOptionPane.YES_OPTION) {     // YES!
            _mat = nextGen(this._mat);
            repaint();      // update mat GUI by call to paintComponent()
        } else if(a==JOptionPane.NO_OPTION)   // NO!
            ;
        else    // EXIT!
            System.exit(0);

        return a;
    }
}