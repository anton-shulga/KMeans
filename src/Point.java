import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Антон on 08.02.2016.
 */
public class Point {
    private double x;
    private double y;
    private Color clusterColor;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getClusterColor() {
        return clusterColor;
    }

    public void setClusterColor(Color color) {
        this.clusterColor = color;
    }

    public static Point createRandomPoint(double width, double height){


        Random r = new Random();
        double x = Math.abs(r.nextInt((int)width));
        double y = Math.abs(r.nextInt((int)height));
        return  new Point(x, y);
    }

    public static List createRandomPoints( int number, double width, double height) {
        List<Point> points = new ArrayList<Point>(number);
        for(int i = 0; i < number; i++) {
            points.add(createRandomPoint(width, height));
        }
        return points;
    }


    protected static double distance(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
    }

}
