import java.awt.*;

public class Player {
    GamePanel panel;
    int x;
    int y;
    double viewDirection = 0;
    double rotation = 0;
    int width;
    int height;
    int fov = 90;

    double xVel;
    double yVel;

    boolean keyLeft;
    boolean keyRight;
    boolean keyForward;
    boolean keyBackward;
    boolean keyJump;
    boolean keySprint;
    boolean touchedGround = true;
    boolean lookUp;
    boolean lookDown;
    boolean facingRight;


    public Player(int x, int y, GamePanel panel){

        this.panel = panel;
        this.x = x;
        this.y = y;

        width = 50;
        height = 100;
    }

    public void updatePos(){
        updateVel();
        viewDirection += rotation;
        x += xVel;
        y += yVel;
    }

    int getX(){
        return x;
    }
    int getY(){
        return y;
    }
    boolean isFacingRight(){ return facingRight; }
    double getViewDirection(){
        return viewDirection;
    }
    int getFov(){ return fov;}



    private void updateVel(){
        //if(keyRight == true) xVel = 5;
        //if(keyLeft == true) xVel = -5;
        yVel+=0.3;
        if(y >= 0){
            y=0;
            yVel = 0;
            touchedGround = true;
        }

        if(keyJump == true && yVel >=0 && touchedGround == true){
            yVel = -10;
            touchedGround = false;
        }

        //view rotation
        if(lookUp == lookDown) {
            rotation = 0;
        } else if(lookUp && !lookDown) rotation = 3;
          else if(lookDown && !lookUp) rotation = -3;


        if(keyLeft == keyRight) {
            xVel = xVel * 0.8;
        }
        else if(keyRight && !keyLeft) xVel++;
        else if(keyLeft && !keyRight) xVel--;

        // X-Axis Movement
        if(viewDirection <= 90 || viewDirection >= 270){
            facingRight = true;
            if(keyForward == keyBackward) {
                xVel = xVel * 0.8;
            }
            else if(keyForward) xVel++;
            else if(keyBackward) xVel--;

        }else {
            facingRight =  false;
            if(keyForward == keyBackward) {
                xVel = xVel * 0.8;
            }
            else if(keyForward && !keyBackward) xVel--;
            else if(keyBackward && !keyForward) xVel++;
        }

        if(viewDirection >= 360) viewDirection -= 360;
        if(viewDirection < 0) viewDirection += 360;

        //Speed Limits
        if(xVel < 0.75 && xVel > 0) xVel = 0;
        if(xVel > -0.75 && xVel < 0) xVel = 0;

        if(yVel > 15) yVel = 15;

        if(keySprint){
            if(xVel > 12) xVel = 12;
            if(xVel < -12) xVel = -12;
        }else {
            if(xVel > 5) xVel = 5;
            if(xVel < -5) xVel = -5;
        }

        if(viewDirection > 90 && viewDirection < 270){
            facingRight = false;
        } else {
            facingRight = true;
        }
    }



    public void draw(Graphics2D gtd){

        //Player
        gtd.setColor(Color.BLUE);
        gtd.fillRect((int)(panel.getWidth()/1.8)-width/2, panel.getHeight()/2-height/2, width, height);

        //View Lines
        gtd.setColor(Color.BLACK);
        gtd.drawLine((int)(panel.getWidth()/1.8),
                panel.getHeight()/2,
                (int)(panel.getWidth()/1.8)+(int)(panel.getWidth()*Math.cos(Math.toRadians(viewDirection+fov/2))),
                panel.getHeight()/2-(int)(panel.getWidth()*Math.sin(Math.toRadians(viewDirection+fov/2))));
        gtd.drawLine((int)(panel.getWidth()/1.8),
                panel.getHeight()/2,
                (int)(panel.getWidth()/1.8)+(int)(panel.getWidth()*Math.cos(Math.toRadians(viewDirection-fov/2))),
                panel.getHeight()/2-(int)(panel.getWidth()*Math.sin(Math.toRadians(viewDirection-fov/2))));


    }

}
