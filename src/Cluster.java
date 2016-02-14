import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Антон on 08.02.2016.
 */
public class Cluster {

    private List<Point> points = new ArrayList<Point>();
    private Point centroid = null;
    private Color color;

    public Cluster(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List getPoints() {
        return points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point point){
        this.centroid = point;
    }

    public void clear() {
        points.clear();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setPoints(List<Point> points){
        this.points = points;
    }





}

