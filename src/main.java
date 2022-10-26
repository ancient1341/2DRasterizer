import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class main {

    public static void main(String[] args) {
        int displayWidth = 1280;
        int displayHeight = 720;

        MainFrame frame = new MainFrame();

        frame.setSize(displayWidth, displayHeight);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
        frame.setResizable(false);
        frame.setTitle("Welcome to the 2nd Dimension");
        frame.setVisible(true);
        //System.out.println(Math.toDegrees(Math.atan2(-4, 4)));

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
