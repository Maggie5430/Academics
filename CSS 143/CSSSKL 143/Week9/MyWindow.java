package week9lab;

/**
 * CSSSKL: 162 Winter 2018, Lab 9.
 * 
 * @author Margaret Connor
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

public class MyWindow extends JFrame implements MouseListener {

    ArrayList<Student> myShapes = new ArrayList<Student>();

    /**
     * Default window provided by assignment
     */
    public MyWindow() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addMouseListener(this);
        //todo: add MouseListener methods(see outline below)
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse input detected");
        myShapes.add(new Student("n/a", e.getX() + e.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //mouseClicked(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //mouseClicked(e);
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame app = new MyWindow();
    }
}
