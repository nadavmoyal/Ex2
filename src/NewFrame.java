import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.List;
import java.util.*;

public class NewFrame extends JFrame implements ActionListener {

    NewPanel panel;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    JMenu chooseAlgoMenu;
    JMenuItem shortPathDistItem;
    JMenuItem shortPathItem;
    JMenuItem centerItem;
    JMenuItem tspItem;
    JMenuItem isConnectedItem;
    JMenuItem addNodeItem;
    JMenuItem removeNodeItem;
    JMenuItem removeEdgeItem;
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;



    DirectedWeightedGraphAlgorithms graphToLoad;


    private List<Integer> xPoints = new ArrayList<Integer>();
    private List<Integer> yPoints = new ArrayList<Integer>();


    public NewFrame(String json_file) {
        Dimension dimension =  Toolkit.getDefaultToolkit().getScreenSize();
        int HEIGHT = dimension.height;
        int WIDTH = dimension.width;
        this.setSize(WIDTH,HEIGHT);
//        this.setLayout(new BorderLayout());
        this.graphToLoad = new MyDWGraphAlgo();
        this.graphToLoad.load(json_file);
        this.setUndecorated(false);
        this.panel = new NewPanel(this.graphToLoad);
        this.add(this.panel,BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");
        chooseAlgoMenu = new JMenu("chooseAlgo");


        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");


        shortPathDistItem = new JMenuItem("ShortPathDist");
        shortPathItem = new JMenuItem("ShortPath");
        centerItem = new JMenuItem("center");
        tspItem = new JMenuItem("tsp");
        isConnectedItem = new JMenuItem("isConnected");
        addNodeItem = new JMenuItem("addNode");
        removeNodeItem = new JMenuItem("removeNode");
        removeEdgeItem = new JMenuItem("removeEdge");


        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        shortPathDistItem.addActionListener(this);
        shortPathItem.addActionListener(this);
        centerItem.addActionListener(this);
        tspItem.addActionListener(this);
        isConnectedItem.addActionListener(this);
        addNodeItem.addActionListener(this);
        removeNodeItem.addActionListener(this);
        removeEdgeItem.addActionListener(this);


        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        chooseAlgoMenu.add(shortPathDistItem);
        chooseAlgoMenu.add(shortPathItem);
        chooseAlgoMenu.add(centerItem);
        chooseAlgoMenu.add(tspItem);
        chooseAlgoMenu.add(isConnectedItem);
        chooseAlgoMenu.add(addNodeItem);
        chooseAlgoMenu.add(removeNodeItem);
        chooseAlgoMenu.add(removeEdgeItem);



        menuBar.add(fileMenu);
//        menuBar.add(editMenu);
//        menuBar.add(helpMenu);
        menuBar.add(chooseAlgoMenu);


        this.setJMenuBar(menuBar);
        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
//            System.out.println("load");


            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int respone = fileChooser.showOpenDialog(null);

            if (respone == JFileChooser.APPROVE_OPTION) {

                this.graphToLoad.load(fileChooser.getSelectedFile().getAbsolutePath());
                this.panel = new NewPanel(this.graphToLoad);
                this.add(panel);
                this.setVisible(true);
                panel.repaint();


            }
        }
        if (e.getSource() == saveItem) {
//            System.out.println("save");
            this.panel.graphAlgo.save("graphSave.json");
            JOptionPane.showMessageDialog(null,"The file was saved successfully! file name = graphSave.json","graphSave",JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == exitItem) {
            System.exit(0);
        }

        /**
         * shortPathDist get 2 input
         * @input_1 src - start node - from type int
         * @input_2 dest - end (target) node - from type int
         * @return double
         */
        if (e.getSource() == shortPathDistItem) {
            String textInput = JOptionPane.showInputDialog("enter src and dest.\n It is important to note insert the data as follows: src,dest  - without spaces!!!");
            String[] srcDest = textInput.split(",");
            double ans = this.graphToLoad.shortestPathDist(Integer.parseInt(srcDest[0]), Integer.parseInt(srcDest[1]));
            JOptionPane.showMessageDialog(null, String.valueOf(ans), "the shortest path dist is", JOptionPane.PLAIN_MESSAGE);

        }

        /**
         * shortPath get 2 input
         * @input_1 src - start node - from type int
         * @input_2 dest - end (target) node - from type int
         * @return List<NodeData> (in here we represented the path by the node's id)
         */
        if (e.getSource() == shortPathItem) {
            String textInput = JOptionPane.showInputDialog("enter src and dest.\n It is important to note insert the data as follows: src,dest  - without spaces!!!");
            String[] srcDest = textInput.split(",");
            List<NodeData> ans = this.graphToLoad.shortestPath(Integer.parseInt(srcDest[0]), Integer.parseInt(srcDest[1]));
            String[] listOfNodePath = new String[ans.size()];
            for (int i = 0; i < ans.size(); i++) {
                double nodeId = ans.get(i).getKey();
                listOfNodePath[i] = "" + String.valueOf(nodeId) + "->";
            }
            String showPath = Arrays.toString(listOfNodePath);
            showPath = showPath.replaceAll(",","");
            showPath = showPath.substring(1,showPath.length()-3);
            System.out.println(showPath);
            JOptionPane.showMessageDialog(null, showPath, "the shortest path dist is", JOptionPane.PLAIN_MESSAGE);
        }

        /**
         * no input
         * @return the Node data (in here we represent the node by node id)
         */
        if (e.getSource() == centerItem) {
            double ans = this.graphToLoad.center().getKey();
            JOptionPane.showMessageDialog(null, String.valueOf(ans), "the center is", JOptionPane.PLAIN_MESSAGE);
        }

        /**
         * @input - List<NodeData> cities
         * @return - List<NodeData> (in here we represent the path by the node's id)
         */
        if (e.getSource() == tspItem) {
            String textInput = JOptionPane.showInputDialog("enter the nodes id's.\n It is important to note insert the data as follows: node_id_1,node_id_2  - without spaces!!!");
            String[] lst = textInput.split(",");
            List<NodeData> listOfNode = new LinkedList<NodeData>();
            for (int i = 0; i < lst.length; i++) {
                NodeData n = this.graphToLoad.getGraph().getNode(Integer.parseInt(lst[i]));
                listOfNode.add(n);
            }
            List<NodeData> ans = this.graphToLoad.tsp(listOfNode);
            String[] listOfNodeTSp = new String[ans.size()];
            for (int i = 0; i < ans.size(); i++) {
                double nodeId = ans.get(i).getKey();
                listOfNodeTSp[i] = "" + String.valueOf(nodeId) + "->";
            }
            String showPath = Arrays.toString(listOfNodeTSp);
            showPath = showPath.replaceAll(",","");
            showPath = showPath.substring(1,showPath.length()-3);
            System.out.println(showPath);
            JOptionPane.showMessageDialog(null, showPath, "the tsp is", JOptionPane.PLAIN_MESSAGE);
        }

        /**
         * no input
         * @return - boolean
         */
        if (e.getSource() == isConnectedItem) {
            boolean ans = this.graphToLoad.isConnected();
            JOptionPane.showMessageDialog(null, String.valueOf(ans), "the answer to the question of whether the graph is Connected is:", JOptionPane.PLAIN_MESSAGE);

        }
        /**
         * adds a new node to the graph with the given node_data.
         * @input - NodeData n
         * we made a condition do check if the user want to add a new Node and then
         * to connect it with another Node or not.
         * It matters because if he wants to connect it he also has to enter the key of whom he wants to
         * connect to, and then we will have to draw the new line as well
         */
        if (e.getSource() == addNodeItem){
            //******************************************************
            ArrayList<Double> xPointsToFindMinMax = new ArrayList<Double>();
            ArrayList<Double> yPointsToFindMinMax = new ArrayList<Double>();
            for (Iterator<NodeData> it = this.graphToLoad.getGraph().nodeIter(); it.hasNext(); ) {
                NodeData p = it.next();
                xPointsToFindMinMax.add(p.getLocation().x());
                yPointsToFindMinMax.add(p.getLocation().y());
            }

            double minX = Collections.min(xPointsToFindMinMax);
            double maxX = Collections.max(xPointsToFindMinMax);
            double minY = Collections.min(yPointsToFindMinMax);
            double maxY = Collections.max(yPointsToFindMinMax);
            //******************************************************

            int ans = JOptionPane.showConfirmDialog(null,"do you want to connect this Node with another Node?","you can enter a Node Now",JOptionPane.YES_NO_OPTION);
            if (ans == 0){ // if the answer is yes
                String textInput = JOptionPane.showInputDialog("Enter:\nX,Y (THE RANGE OF X YOU CAN ADD IS: "+minX+" - "+maxX+", THE RANGE OF Y YOU CAN ADD IS: "+minY+" - "+maxY+") \nDEST (with which Node do you want to connect this new Node)\nWEIGHT_NEW_EDGE (what will be the weight of the new edge) .\n It is important to note insert the data as follows: X,Y,DEST,WEIGHT_NEW_EDGE  - without spaces!!!");
                String[] lst = textInput.split(",");
                double x = Double.parseDouble(lst[0]);
                double y = Double.parseDouble(lst[1]);


                int dest = Integer.parseInt(lst[2]);
                double weightEdge = Double.parseDouble(lst[3]);
                // Z is always zero
                // in order to determine the new key of the Node i take the size of the Node's in graph
                // and it will be our new key for the new Node.
                // we can do that because the Node's id starts from zero and also when we remove a Node from the graph it won't change
                int keyNode = this.graphToLoad.getGraph().nodeSize();
                MyNode n = new MyNode(new MyGeoLocation(x,y,0.0),keyNode);
                this.graphToLoad.getGraph().addNode(n);
                this.graphToLoad.getGraph().connect(n.getKey(),dest,weightEdge);
                this.panel = new NewPanel(this.graphToLoad);
                this.add(this.panel);
                this.setVisible(true);
                repaint();

            }else{ // if the answer is no
                String textInput = JOptionPane.showInputDialog("Enter:\nX,Y (THE RANGE OF X YOU CAN ADD IS: "+minX+" - "+maxX+", THE RANGE OF Y YOU CAN ADD IS: "+minY+" - "+maxY+")\n It is important to note insert the data as follows: X,Y  - without spaces!!!");
                String[] lst = textInput.split(",");
                double x = Double.parseDouble(lst[0]);
                double y = Double.parseDouble(lst[1]);
                int keyNode = this.graphToLoad.getGraph().nodeSize();
                MyNode n = new MyNode(new MyGeoLocation(x,y,0.0),keyNode);
                this.graphToLoad.getGraph().addNode(n);

                this.panel = new NewPanel(this.graphToLoad);
                this.add(panel);
                this.setVisible(true);
                repaint();
            }

        }
        /**
         * Deletes the node (with the given ID) from the graph -
         * and removes all edges which starts or ends at this node.
         * This method should run in O(k), V.degree=k, as all the edges should be removed.
         * @return the data of the removed node (null if none).
         * @param key
         */
        if (e.getSource() == removeNodeItem){
            String textInput = JOptionPane.showInputDialog("Enter the key Node you want to remove");
            while (this.graphToLoad.getGraph().getNode(Integer.parseInt(textInput)) == null){
                textInput = JOptionPane.showInputDialog("there is no such Node in the graph please enter a new one");
            }
            this.graphToLoad.getGraph().removeNode(Integer.parseInt(textInput));

            this.panel = new NewPanel(this.graphToLoad);
            this.add(panel);
            this.setVisible(true);
            repaint();
        }
        /**
         * Deletes the edge from the graph,
         * Note: this method should run in O(1) time.
         * @param src
         * @param dest
         * @return the data of the removed edge (null if none).
         */
        if (e.getSource() == removeEdgeItem){
            String textInput = JOptionPane.showInputDialog(" Enter src,dest to delete\n It is important to note insert the data as follows: src,dest  - without spaces!!!");
            String[] lst = textInput.split(",");
            int src = Integer.parseInt(lst[0]);
            int dest = Integer.parseInt(lst[1]);
            while(this.graphToLoad.getGraph().getEdge(src,dest) == null){
                textInput = JOptionPane.showInputDialog("there is no such Edge in the graph please enter a new one\n It is important to note insert the data as follows: src,dest  - without spaces!!!");
                lst = textInput.split(",");
                src = Integer.parseInt(lst[0]);
                dest = Integer.parseInt(lst[1]);
            }
            this.graphToLoad.getGraph().removeEdge(src,dest);
            repaint();
        }
    }

}
