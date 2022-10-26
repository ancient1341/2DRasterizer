import java.awt.*;
import java.util.ArrayList;

public class Grop extends Viewable{

    GamePanel panel;
    ArrayList<Vector> vectors;

    int x;
    int y;
    int width;
    int height;
    double distance;
    Color color;

    ArrayList<Color> genericTexture;

    public Grop(int x, int y, int width, int height, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        genericTexture = new ArrayList<Color>();
        ArrayList<Color> top = new ArrayList<Color>();

        for(int i = 0; i<6; i++){
            if(i%2 == 0){
                genericTexture.add(new Color(82, 45, 26));
            } else {
                genericTexture.add(new Color(75, 58, 56));
            }
        }

        for(int i = 0; i<20; i++){
            if(i%2 == 0){
                top.add(new Color(21, 88, 0));
            } else {
                top.add(new Color(12, 75, 0));
            }
        }

        vectors = new ArrayList<Vector>();

        vectors.add(new Vector(x, y, x+width, y, top));
        vectors.add(new Vector(x+width, y+height, x+width, y, genericTexture));
        vectors.add(new Vector(x+width, y+height, x, y+height, top));
        vectors.add(new Vector(x, y+height, x, y, genericTexture));

    }
    public void drawSpace(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.fillRect(x+(panel.getWidth()/2)-panel.player.getX()+(panel.getWidth()/10)-55, y+(panel.getHeight()/2)-panel.player.getY(), width, height);
    }

    public void draw(Graphics2D gtd, int direction) {
        double viewAngle = ((double)direction/panel.getHeight())*90+panel.player.getViewDirection()-45;// Calculates the angle of the current pixel
        if(viewAngle > 360){
            viewAngle -= 360;
        } else if(viewAngle < 0){
            viewAngle += 360;
        }
        viewAngle = Math.toRadians(viewAngle);

        for(int i = 0; i < vectors.size(); i++){

            vectors.get(i).rasterize(panel.player.getX(), panel.player.getY(), viewAngle, panel.player.facingRight);

            if(vectors.get(i).getDistance() != -1){
                if(distance == -1){                                     //first pixel to be drawn
                    distance = vectors.get(i).getDistance();
                    color = vectors.get(i).Shade(panel.sunAngle);
                    gtd.setColor(color);
                    if (!panel.player.isFacingRight()) {
                        gtd.drawLine(0, direction, panel.getWidth() / 10, direction);
                    } else {
                        gtd.drawLine(0, panel.getHeight() - direction, panel.getWidth() / 10, panel.getHeight() - direction);
                    }
                } else if(distance > vectors.get(i).getDistance()){     //any other pixel
                    distance = vectors.get(i).getDistance();
                    color = vectors.get(i).Shade(panel.sunAngle);
                    gtd.setColor(color);
                    if (!panel.player.isFacingRight()) {
                        gtd.drawLine(0, direction, panel.getWidth() / 10, direction);
                    } else {
                        gtd.drawLine(0, panel.getHeight() - direction, panel.getWidth() / 10, panel.getHeight() - direction);
                    }
                }
            }
        }
    }



    double getDistance(){
        return distance;
    }
    void setDistance(double pixelDistance){ distance = pixelDistance; }
}
