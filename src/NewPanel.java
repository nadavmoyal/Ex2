import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;

public class NewPanel extends JPanel{

    DirectedWeightedGraphAlgorithms graphAlgo;
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    int HEIGHT = dimension.height;
    int WIDTH = dimension.width;


    public NewPanel(DirectedWeightedGraphAlgorithms graphAlgo) {
        super();
        this.graphAlgo = graphAlgo;
        this.setBackground(new Color(7, 43, 73)); //change color of background
        repaint();

    }

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
        for (Iterator<NodeData> it = graphAlgo.getGraph().nodeIter(); it.hasNext(); ) {
            NodeData p = it.next();
            int IntnodeId = p.getKey();
            String StrNodeId = String.valueOf(IntnodeId);
            System.out.println("old x = " + p.getLocation().x());
            System.out.println("old y = " + p.getLocation().y());
            double x = scale(p.getLocation().x(), minX, maxX, 100, WIDTH - 200);
            double y = scale(p.getLocation().y(), minY, maxY, 100, HEIGHT - 200);
            System.out.println("new x = " + x);
            System.out.println("new y = " + y);
            g.setColor(Color.RED);
            g.fillOval((int) x - 5, (int) y - 5, 10, 10);
//            g.drawOval((int) x-5, (int) y-5, 30, 30); //99999999999999999999999999
            g.setColor(Color.BLACK);
            g.setFont(new Font("MV Boli", Font.ITALIC, 12)); //set font of text
            g.drawString(StrNodeId, (int) x - 5, (int) y - 5);
            System.out.println("after all");


        }
/*
        for (int i = 0; i < this.graphAlgo.getGraph().nodeSize(); i++) {
            System.out.println(graphAlgo.getGraph().nodeSize() + "-------------------------");
            System.out.println(graphAlgo.getGraph().getNode(17));
            System.out.println(this.graphAlgo.getGraph().getNode(i).getKey());
            int IntnodeId = this.graphAlgo.getGraph().getNode(i).getKey();
            String StrNodeId = String.valueOf(IntnodeId);
            System.out.println("old x = " + xPointsToFindMinMax.get(i));
            System.out.println("old y = " + yPointsToFindMinMax.get(i));
            double x = scale(xPointsToFindMinMax.get(i), minX, maxX, 100, WIDTH - 200);
            double y = scale(yPointsToFindMinMax.get(i), minY, maxY, 100, HEIGHT - 200);
            System.out.println("new x = " + x);
            System.out.println("new y = " + y);
            g.setColor(Color.RED);
            g.fillOval((int) x - 5, (int) y - 5, 10, 10);
//            g.drawOval((int) x-5, (int) y-5, 30, 30); //99999999999999999999999999
            g.setColor(Color.BLACK);
            g.setFont(new Font("MV Boli", Font.ITALIC, 12)); //set font of text
            g.drawString(StrNodeId, (int) x - 5, (int) y - 5);
//            g.fillOval((int) (p.getX()), (int) (p.getY()), 15, 15);
        }
//        for (int i = 0; i < this.graphAlgo.getGraph().edgeSize(); i++) {
////            g.drawLine();
//        }
*/

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
            xOfESrc = scale(xOfESrc, minX, maxX, 100, WIDTH - 200);
            yOfESrc = scale(yOfESrc, minY, maxY, 100, HEIGHT - 200);
            xOfEDest = scale(xOfEDest, minX, maxX, 100, WIDTH - 200);
            yOfEDest = scale(yOfEDest, minY, maxY, 100, HEIGHT - 200);
            g.setColor(Color.WHITE);
            g.drawLine((int) xOfESrc, (int) yOfESrc, (int) xOfEDest, (int) yOfEDest);

            Double weight = e.getWeight();
            String weightS = weight.toString().substring(0, weight.toString().indexOf(".") + 2);
            System.out.println("weightS is = " + weightS);
            g.setFont(new Font("MV Boli", Font.ITALIC, 12)); //set font of text
            if (yOfESrc < yOfEDest) {
                g.drawString(weightS, (int) ((xOfESrc + xOfEDest) / 2 - 17), (int) ((yOfESrc + yOfEDest) / 2 - 17));
            } else g.drawString(weightS, (int) ((xOfESrc + xOfEDest) / 2 + 12), (int) ((yOfESrc + yOfEDest) / 2 + 12));


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


}
