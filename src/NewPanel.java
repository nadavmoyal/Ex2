import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class NewPanel extends JPanel {

    DirectedWeightedGraphAlgorithms graphAlgo;
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    int HEIGHT = dimension.height;
    int WIDTH = dimension.width;


    public NewPanel(DirectedWeightedGraphAlgorithms graphAlgo) {
        super();
        this.graphAlgo = graphAlgo;
        this.setBackground(new Color(0,206,209));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graph = (Graphics2D) g;
        graph.setStroke(new BasicStroke(2));


        // Here we create two arrayList that will hold the X,Y coordinates so after that we can get the
        // min \ max X,Y so we could scale it
        // if for loop we run on the graph node with node iter and then for each node get that
        // X,Y location
        ArrayList<Double> xPointsToFindMinMax = new ArrayList<Double>();
        ArrayList<Double> yPointsToFindMinMax = new ArrayList<Double>();
        for (Iterator<NodeData> it = graphAlgo.getGraph().nodeIter(); it.hasNext(); ) {
            NodeData p = it.next();
            xPointsToFindMinMax.add(p.getLocation().x());
            yPointsToFindMinMax.add(p.getLocation().y());
        }

        double minX = Collections.min(xPointsToFindMinMax);
        double maxX = Collections.max(xPointsToFindMinMax);
        double minY = Collections.min(yPointsToFindMinMax);
        double maxY = Collections.max(yPointsToFindMinMax);

        // Here we do scale on each X,Y and draw them

        for (Iterator<NodeData> it = graphAlgo.getGraph().nodeIter(); it.hasNext(); ) {
            NodeData p = it.next();
            int IntnodeId = p.getKey();
            String StrNodeId = String.valueOf(IntnodeId);

            double x = scale(p.getLocation().x(), minX, maxX, 100, WIDTH - 200);
            double y = scale(p.getLocation().y(), minY, maxY, 100, HEIGHT - 200);
            g.setColor(Color.RED);
            g.fillOval((int) x - 5, (int) y - 5, 10, 10);
            g.setColor(Color.BLACK);
            g.setFont(new Font("MV Boli", Font.ITALIC, 12));
            g.drawString(StrNodeId, (int) x - 5, (int) y - 5);

        }

        for (
                Iterator<EdgeData> it = graphAlgo.getGraph().edgeIter(); it.hasNext(); ) {
            EdgeData e = it.next();
            // get the X,Y of e (edge) src and dest
            double xOfESrc = this.graphAlgo.getGraph().getNode(e.getSrc()).getLocation().x();
            double yOfESrc = this.graphAlgo.getGraph().getNode(e.getSrc()).getLocation().y();
            double xOfEDest = this.graphAlgo.getGraph().getNode(e.getDest()).getLocation().x();
            double yOfEDest = this.graphAlgo.getGraph().getNode(e.getDest()).getLocation().y();
            xOfESrc = scale(xOfESrc, minX, maxX, 100, WIDTH - 200);
            yOfESrc = scale(yOfESrc, minY, maxY, 100, HEIGHT - 200);
            xOfEDest = scale(xOfEDest, minX, maxX, 100, WIDTH - 200);
            yOfEDest = scale(yOfEDest, minY, maxY, 100, HEIGHT - 200);
            g.setColor(Color.WHITE);
            g.drawLine((int) xOfESrc, (int) yOfESrc, (int) xOfEDest, (int) yOfEDest);

            Double weight = e.getWeight();
            String weightS = weight.toString().substring(0, weight.toString().indexOf(".") + 2);
            g.setFont(new Font("MV Boli", Font.ITALIC, 12));
            if (yOfESrc < yOfEDest) {
                g.drawString(weightS, (int) ((xOfESrc + xOfEDest) / 2 - 17), (int) ((yOfESrc + yOfEDest) / 2 - 17));
            } else g.drawString(weightS, (int) ((xOfESrc + xOfEDest) / 2 + 12), (int) ((yOfESrc + yOfEDest) / 2 + 12));


        }
    }

    private double scale(double data, double r_min, double r_max,
                         double t_min, double t_max) {
        double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;
        return res;

    }


}
