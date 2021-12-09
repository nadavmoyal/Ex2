import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public class NewPanel extends JPanel {

    DirectedWeightedGraphAlgorithms graphAlgo;
    //====================================
//    public LinkedList<Point2D> points = new LinkedList<Point2D>();
//    private double[] xPoints;
//    private double[] yPoints;
//    private int nPoints;
    //====================================

    String message;


    public NewPanel(DirectedWeightedGraphAlgorithms graphAlgo) {
        super();
        this.graphAlgo = graphAlgo;
        this.setBackground(new Color(7, 43, 73)); //change color of background
        this.repaint();

    }

/*
    void reset() {
        points = new LinkedList<Point2D>();
        this.repaint();
    }
*/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graph = (Graphics2D) g;
        graph.setStroke(new BasicStroke(2));
        System.out.println("inside paint");
        Point2D prev = null;


        // Here we create two arrayList that will hold the X,Y coordinates so after that we can get the
        // min \ max X,Y so we could scale it
        // if for loop we run on the grapg nodewith node iter and then for each node get that
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
        System.out.println("minX = " + minX);
        System.out.println("maxX = " + maxX);
        System.out.println("minY = " + minY);
        System.out.println("maxY = " + maxY);

        // Here we do scale on each X,Y and draw them
        for (int i = 0; i < this.graphAlgo.getGraph().nodeSize(); i++) {

            System.out.println("old x = " + xPointsToFindMinMax.get(i));
            System.out.println("old y = " + yPointsToFindMinMax.get(i));
            double x = scale(xPointsToFindMinMax.get(i), minX, maxX, 100, 400);
            double y = scale(yPointsToFindMinMax.get(i), minY, maxY, 100, 400);
            System.out.println("new x = " + x);
            System.out.println("new y = " + y);
            g.setColor(Color.RED);
            g.fillOval((int) x-5, (int) y-5, 10, 10);
//            g.fillOval((int) (p.getX()), (int) (p.getY()), 15, 15);
        }
        for (int i = 0; i < this.graphAlgo.getGraph().edgeSize(); i++) {
//            g.drawLine();
        }

        System.out.println(graphAlgo.getGraph().nodeSize());
        System.out.println(graphAlgo.getGraph().edgeSize());
        System.out.println("after all");


        for (Iterator<EdgeData> it = graphAlgo.getGraph().edgeIter(); it.hasNext(); ) {
            EdgeData e = it.next();
            // get the X,Y of e (edge) src and dest
            double xOfESrc = this.graphAlgo.getGraph().getNode(e.getSrc()).getLocation().x();
            double yOfESrc = this.graphAlgo.getGraph().getNode(e.getSrc()).getLocation().y();
            double xOfEDest = this.graphAlgo.getGraph().getNode(e.getDest()).getLocation().x();
            double yOfEDest = this.graphAlgo.getGraph().getNode(e.getDest()).getLocation().y();
            xOfESrc = scale(xOfESrc, minX, maxX, 100, 400);
            yOfESrc = scale(yOfESrc, minY, maxY, 100, 400);
            xOfEDest = scale(xOfEDest, minX, maxX, 100, 400);
            yOfEDest = scale(yOfEDest, minY, maxY, 100, 400);
            g.setColor(Color.WHITE);
            g.drawLine((int)xOfESrc,(int)yOfESrc,(int)xOfEDest,(int)yOfEDest);

        }
//        repaint();
    }


    /**
     * @param data  denote some data to be scaled
     * @param r_min the minimum of the range of your data
     * @param r_max the maximum of the range of your data
     * @param t_min the minimum of the range of your desired target scaling
     * @param t_max the maximum of the range of your desired target scaling
     * @return
     */
    private double scale(double data, double r_min, double r_max,
                         double t_min, double t_max) {
        double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;
        return res;

    }





/*
        for (Point2D p : points) {
            int rand = (int) (Math.random()*200);
            System.out.println("old x = "+ p.getX());
            System.out.println("old y = "+ p.getY());
            double x = scale(p.getX(),35.187594216303474,35.212111165456015,0,height);
            double y = scale(p.getY(),32.10107446554622,32.10788938151261,0,width);
            System.out.println("new x = "+ x);
            System.out.println("new y = "+ y);
            g.setColor(Color.WHITE);
            g.fillOval((int) x, (int) y, 10, 10);
//            g.fillOval((int) (p.getX()), (int) (p.getY()), 15, 15);

            if (prev != null) {
                Double dist = p.distance(prev);
                String distS = dist.toString().substring(0, dist.toString().indexOf(".") + 2);
                g.setColor(Color.RED);
                g.drawLine((int) x, (int) y,
                        (int) prev.getY(), (int) prev.getY());
                g.setFont(new Font("MV Boli", Font.TRUETYPE_FONT, 15)); //set font of text
                g.drawString(distS, (int) ((p.getX() + prev.getX()) / 2), (int) ((p.getY() + prev.getY()) / 2));
            }

            prev = p;
        }

*/


}
