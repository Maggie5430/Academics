import javax.swing.JFrame;
public class Window extends JFrame {

  public Window() {
      setSize(400,400);
      setVisible(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

   public static void main (String[] args) {
       Window a = new Window();
    }
}

