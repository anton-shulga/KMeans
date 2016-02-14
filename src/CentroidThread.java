import java.util.List;

/**
 * Created by Антон on 14.02.2016.
 */
public class CentroidThread implements Runnable{
    Cluster cluster = null;
    public CentroidThread(Cluster cluster){
        this.cluster = cluster;
    }

    @Override
    public void run() {

        double sumX = 0;
        double sumY = 0;

        List<Point> listOfPoints = cluster.getPoints();

        int countOfPointsInCluster = listOfPoints.size();

        for (Point point : listOfPoints) {
            sumX += point.getX();
            sumY += point.getY();
        }

        Point centroid = cluster.getCentroid();
        if (countOfPointsInCluster > 0) {
            double newX = sumX / countOfPointsInCluster;
            double newY = sumY / countOfPointsInCluster;
            centroid.setX(newX);
            centroid.setY(newY);
        }
    }
}
