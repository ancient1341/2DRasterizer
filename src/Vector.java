import java.awt.*;
import java.util.ArrayList;

public class Vector {
    int x1;
    int y1;
    int x2;
    int y2;

    private double length;
    private double vectorAngle;
    private double point1Angle;
    private double point2Angle;
    private double l1;
    private double l2;
    private double l2Angle;
    private double newAngle;
    private ArrayList<Color> texture;
    private Color color;

    private float shade;


    private double distance;
    private double positionOnVector;

    //boolean facingPlayer;

    public Vector(int x1, int y1, int x2, int y2, ArrayList<Color> texture){
            this.x1 = x2;
            this.y1 = y2;
            this.x2 = x1;
            this.y2 = y1;

        this.texture = texture;

        // Sin(y) = x/c
        // tan(A/B) = Map.world

        length = Math.sqrt(Math.pow(this.x1-this.x2, 2) + Math.pow(this.y1-this.y2, 2));
        vectorAngle = Math.atan2(this.y2-this.y1, this.x2-this.x1);
    }

    void rasterize(int playerX, int playerY, double angle, boolean right) {
        point1Angle = calcAngle(playerY - this.y1, this.x1 - playerX);
        point2Angle = calcAngle(playerY - this.y2, this.x2 - playerX);
        if(point1Angle < Math.PI/2 && point2Angle > Math.PI*3/4){       // If the angles cross the 0-360 degree barrier
            point1Angle += 2*Math.PI;
            if(angle < Math.PI/2){
                angle += 2*Math.PI;
            }
        }
        if(point2Angle < Math.PI/2 && point1Angle > Math.PI*3/4){
            point2Angle += 2*Math.PI;
            if(angle < Math.PI/2){
                angle += 2*Math.PI;
            }
        }

        if(point1Angle > point2Angle){                                  // Assures that point1Angle is always the lower one
            point1Angle = point2Angle + point1Angle;
            point2Angle = point1Angle - point2Angle;
            point1Angle = point1Angle - point2Angle;
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }


        if(angle > point1Angle && angle < point2Angle) {                //Checks that angle is in the bounds of the vector
                                                                        //And if so, it calculates the distance to the vector
            if(point1Angle > 2*Math.PI){
                point1Angle -= 2*Math.PI;
            }
            if(angle > 2*Math.PI){
                angle -= 2*Math.PI;
            }

            l1 = Math.sqrt(Math.pow(this.y1 - playerY, 2) + Math.pow(this.x1 - playerX, 2));
            l2 = Math.sqrt(Math.pow(this.y2 - playerY, 2) + Math.pow(this.x2 - playerX, 2));
            newAngle = angle - point1Angle;
            if(newAngle < 0){ newAngle += 2*Math.PI; }

            l2Angle = Math.acos(((length * length) + (l1 * l1) - (l2 * l2)) / (2 * length * l1));
            distance = (l1 * Math.sin(l2Angle)) / Math.sin(Math.PI-(l2Angle+newAngle));                    //calculates the distance to the point on the vector
            positionOnVector = ((distance * Math.sin(newAngle)) / Math.sin(l2Angle));                     //calculates how far up the vector the angle goes
            if(positionOnVector > length ){
                positionOnVector = length;
            } else if(positionOnVector < 0){
                positionOnVector = 0;
            }
            color = texture.get((int)((texture.size()-1)*(positionOnVector/length)));


        } else {
            distance = -1;
        }

    }


    private double calcAngle(double y, double x){ //calculates the angle between two points by their difference in x and y values
        double temp = Math.atan2(y, x);
        if(temp < 0){
            temp += 2*Math.PI;
        }
        return temp;
    }

    public Color Shade(double sunAngle){
        double darkness = (vectorAngle-sunAngle)/(Math.PI*2);
        int r = (int) (color.getRed()-color.getRed()*darkness);
        int g = (int) (color.getGreen()-color.getGreen()*darkness);
        int b = (int) (color.getBlue()-color.getBlue()*darkness);
        return new Color(r, g, b);
    }


    // Getters
    public double getDistance() { return distance; }
    public double getL1() { return l1; }
    public double getL2() { return l2; }
    public double getL2Angle() { return l2Angle; }
    public double getLength() { return length; }
    public double getNewAngle() { return newAngle; }
    public double getPoint1Angle() { return point1Angle; }
    public double getPoint2Angle() { return point2Angle; }
    public double getPositionOnVector() { return positionOnVector; }
    public double getVectorAngle() { return vectorAngle; }
    public Color getColor(){ return color; }

}
