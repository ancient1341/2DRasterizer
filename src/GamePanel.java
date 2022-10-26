import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.ArrayList;


public class GamePanel extends javax.swing.JPanel implements ActionListener {

    ArrayList<Viewable> objects = new ArrayList<Viewable>();
    Player player;
    Timer gameTimer;
    double vertexDistance;
    ViewableFactory viewable = new ViewableFactory(this, "G:\\Java Programs\\2d Fps\\src\\Map.world");
    double sunAngle = Math.toRadians(225);

    public GamePanel() {
        player = new Player(0,0, this);
        objects = viewable.getViewables();
        /*
        for(int i = 0; i < 20; i++) {
            objects.add(new ViewableRect(i*20, 50, 21, 20, this));
        }
        objects.add(new ViewableRect(400, 10, 20, 40, this));
         */

        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){

            @Override
            public void run(){
                player.updatePos();
                repaint();

            }

        }, 0, 16); // 16
    }



    //WHERE STUFF GETS DRAWN
    public void paint(Graphics g){
        super.paint(g);
        Random random = new Random();

        Graphics2D gtd = (Graphics2D) g;
        player.draw(gtd);
        for(int i = 0; i<objects.size(); i++){
            objects.get(i).drawSpace(gtd);
        }


        for(int i = 0; i<this.getHeight()+1; i++){
            vertexDistance = -1;
            for(int j = 0; j<objects.size(); j++){
                objects.get(j).setDistance(vertexDistance);
                objects.get(j).draw(gtd, i);
                if(objects.get(j).getDistance() > vertexDistance){
                    vertexDistance = objects.get(j).getDistance();
                }
            }

            if(vertexDistance == -1){                   //if nothing is drawn Aka skybox
                gtd.setColor(new Color(0, 111, 165));
                if(!player.isFacingRight()) {
                    gtd.drawLine(0, i, getWidth() / 10, i);
                } else {
                    gtd.drawLine(0, getHeight() - i, getWidth() / 10, getHeight() - i);
                }
            }
        }

    }





    // Key Information //

    void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) player.keyLeft = true;
        if(e.getKeyCode() == KeyEvent.VK_D) player.keyRight = true;
        if(e.getKeyCode() == KeyEvent.VK_W) player.keyForward = true;
        if(e.getKeyCode() == KeyEvent.VK_S) player.keyBackward = true;
        if(e.getKeyCode() == KeyEvent.VK_SPACE) player.keyJump = true;
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) player.keySprint = true;
        if(e.getKeyCode() == KeyEvent.VK_UP) player.lookUp = true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) player.lookDown = true;
    }

    void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) player.keyLeft = false;
        if(e.getKeyCode() == KeyEvent.VK_D) player.keyRight = false;
        if(e.getKeyCode() == KeyEvent.VK_W) player.keyForward = false;
        if(e.getKeyCode() == KeyEvent.VK_S) player.keyBackward = false;
        if(e.getKeyCode() == KeyEvent.VK_SPACE) player.keyJump = false;
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) player.keySprint = false;
        if(e.getKeyCode() == KeyEvent.VK_UP) player.lookUp = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) player.lookDown = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
