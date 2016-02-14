import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Антон on 09.02.2016.
 */
public class DrawPanel extends JPanel {

    List<Cluster> clusters = new ArrayList<Cluster>();

    public DrawPanel(List<Cluster> clusters){
        this.clusters = clusters;
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Cluster cluster : clusters) {
            g2d.setColor(cluster.getCentroid().getClusterColor());
            g2d.fillOval((int)cluster.getCentroid().getX(), (int)cluster.getCentroid().getY(), 10, 10);
            List<Point> points = cluster.getPoints();
            for (Point point : points) {
                g2d.setColor(point.getClusterColor());
                g2d.draw(new Ellipse2D.Double(point.getX(), point.getY(), 1, 1));
            }
        }



    }
}
