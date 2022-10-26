import java.awt.*;
import java.util.ArrayList;

public abstract class Viewable {
    GamePanel panel;
    ArrayList<Vector> vectors;

    int x;
    int y;
    int width;
    int height;
    double distance;
    Color color;

    ArrayList<Color> genericTexture;

    abstract void drawSpace(Graphics2D gtd);
    abstract void draw(Graphics2D gtd, int direction);
    abstract double getDistance();
    abstract void setDistance(double pixelDistance);
}
