import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Антон on 08.02.2016.
 */
public class KMeans {
    private  int countOfPoints;
    private  int countOfClasses;
    private List<Point> points;
    private static List<Cluster> clusters;
    private Dimension screenSize;

    public KMeans() {
        this.points = new ArrayList<Point>();
        this.clusters = new ArrayList<Cluster>();
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void setCountOfPoints(int countOfPoints){
        this.countOfPoints = countOfPoints;
    }

    public void setCountOfClasses(int countOfClasses){
        this.countOfClasses = countOfClasses;
    }
    //Initializes the process
    public void createPoints() {
        points = Point.createRandomPoints(countOfPoints, screenSize.getWidth(), screenSize.getHeight());

        for (int i = 0; i < countOfClasses; i++) {
            Cluster cluster = new Cluster(getRandomColor());
            Point centroid = Point.createRandomPoint(screenSize.getWidth(), screenSize.getHeight());
            centroid.setClusterColor(cluster.getColor());
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

    }

    private Color getRandomColor() {

        Random random = new Random();

        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }




    public void calculate() {

        boolean finish = false;


        while (!finish) {

            clearClusters();

            List<Point> lastCentroids = getCentroids();

            assignCluster();

            calculateCentroids();

            List<Point> currentCentroids = getCentroids();

            double distance = 0;
            for (int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.distance(lastCentroids.get(i), currentCentroids.get(i));
            }

            if (distance == 0) {
                finish = true;
            }
        }
    }

    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }



    private List<Point> getCentroids() {
        List centroids = new ArrayList(countOfClasses);
        for (Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(), aux.getY());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        Color clusterColor = null;
        double distance = 0.0;
        int cluster = 0;

        for (Point point : points) {
            min = max;
            for (int i = 0; i < countOfClasses; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if (distance < min) {
                    min = distance;
                    clusterColor = c.getColor();
                    cluster = i;
                }
            }

            point.setClusterColor(clusterColor);
            clusters.get(cluster).addPoint(point);
        }
    }


    private void calculateCentroids() {
        List<CentroidThread> listOfThreads = new ArrayList<CentroidThread>(clusters.size());
        for (Cluster cluster : clusters) {
            CentroidThread thread = new CentroidThread(cluster);
            listOfThreads.add(thread);
        }
        for(CentroidThread thread: listOfThreads)
            thread.run();
    }

    public List<Cluster> getClusters(){
        return clusters;
    }

    public void removeAllClasses(){
        clusters.clear();
    }
}

