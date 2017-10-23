import javax.swing.*;

/**
 * Created by Tycho on 20-6-2017.
 */

public class Startscorebord extends JFrame {
    private static JFrame frame;
    public static void main (String[] args){
        frame = new JFrame("Scorebord");
        frame.setContentPane(new Scorebord());
        frame.setSize(500,300);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static JFrame getFrame() {
        return frame;
    }
}
