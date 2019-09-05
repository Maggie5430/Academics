package shape.inheritance;

import javax.swing.*;
import java.awt.*;

/*
 * Class PolyDemo (is a JFrame) and PolyDemoPanel (is a JPanel)
 * 
 * Author: Rob Nash
 * Edited: Margaret Connor 02/10/18
 */
class PolyDemo extends JFrame {

    public PolyDemo() {
        getContentPane().add(new PolyDemoPanel());
        //just some windowing stuff that must happen for all Frames
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        PolyDemo myApp = new PolyDemo();
    }

    public class PolyDemoPanel extends JPanel {

        Shape[] myShapes = new Shape[4];

        public PolyDemoPanel() {
            myShapes[0] = new Triforce(25, 25, 100);
            myShapes[1] = new MusicNote(150, 25, 100);
            myShapes[2] = new Club(25, 150, 100);
            myShapes[3] = new BtsSymbol(150, 150, 100);

            // Commented out for testing, uncomment for random shape sizes
            /* for (int i = 0; i < myShapes.length; i++) {
             *     myShapes[i] = getRandShape();
             * }
             */
        }


        public void paint(Graphics g) {
            super.paint(g);  
            
            for (int i = 0; i < myShapes.length; i++) {
                myShapes[i].draw(g);
            }
        }

        public int getRandInt() {
            return ((int) (Math.random() * 400));
        }

        public Shape getRandShape() {
            Shape retVal = null;
            final int x = getRandInt();
            final int y = getRandInt();

            switch ((int) (Math.random() * 4)) {
                case 0:
                    retVal = new Triforce(x, y, getRandInt());
                    break;
                case 1:
                    retVal = new MusicNote(x, y, getRandInt());
                    break;
                case 2:
                    retVal = new Club(x, y, getRandInt());
                    break;
                case 3:
                    retVal = new BtsSymbol(x, y, getRandInt());
                    break;
            }

            return retVal;
        }
    }

}
