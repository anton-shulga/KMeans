import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Антон on 14.02.2016.
 */
public class GUI extends JFrame {

    private JButton startButton;
    private JTextField pointsText;
    private JTextField classesText;
    private DrawPanel drawPanel;
    private List<Cluster> clusters;
    private KMeans kMeans;
    private JPanel guiPanel;
    private JPanel container;

    public GUI() {
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        guiPanel = new JPanel();
        guiPanel.setSize(500, 100);
        kMeans = new KMeans();
        this.setContentPane(container);
        this.setSize(500,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        pointsText = new JTextField("Count of points");
        pointsText.setSize(150, 30);
        pointsText.setLocation(5,5);

        classesText = new JTextField("Count of classes");
        classesText.setSize(150, 30);
        classesText.setLocation(310, 5);

        startButton = new JButton("Start!");
        startButton.setSize(150, 30);
        startButton.setLocation(610, 5);

        guiPanel.add(pointsText);
        guiPanel.add(classesText);
        guiPanel.add(startButton);

        container.add(guiPanel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int countOfPoints = Integer.parseInt(pointsText.getText());
                int countOfClasses = Integer.parseInt(classesText.getText());
                kMeans.removeAllClasses();
                kMeans.setCountOfPoints(countOfPoints);
                kMeans.setCountOfClasses(countOfClasses);
                kMeans.createPoints();
                kMeans.calculate();
                JFrame pointsFrame = new JFrame("Result");
                pointsFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                pointsFrame.add(new DrawPanel(kMeans.getClusters()));
                pointsFrame.setVisible(true);
            }
        });


        this.setVisible(true);
    }


}
