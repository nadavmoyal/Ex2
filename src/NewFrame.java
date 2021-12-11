import api.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

public class NewFrame extends JFrame implements ActionListener {

    NewPanel panel;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu helpMenu;
    JMenu chooseAlgoMenu; //888888888888888888888888888888888
    JMenuItem shortPathDistItem; //888888888888888888888888888888888
    JMenuItem shortPathItem;  //888888888888888888888888888888888
    JMenuItem centerItem;   //888888888888888888888888888888888
    JMenuItem tspItem;   //888888888888888888888888888888888
    JMenuItem isConnectedItem;   //888888888888888888888888888888888
    JMenuItem addNodeItem;   //888888888888888888888888888888888
    JMenuItem removeNodeItem;   //888888888888888888888888888888888
    JMenuItem removeEdgeItem;   //888888888888888888888888888888888
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

//    JTextField textField;
//    JButton button;

    DirectedWeightedGraphAlgorithms graphToLoad;// = new MyDWGraphAlgo();


    private List<Integer> xPoints = new ArrayList<Integer>();
    private List<Integer> yPoints = new ArrayList<Integer>();
    private LinkedList<Point2D> points = new LinkedList<Point2D>();


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

        //=========================================
        //add action for algo
        shortPathDistItem = new JMenuItem("ShortPathDist");
        shortPathItem = new JMenuItem("ShortPath");  //888888888888888888888888888888888
        centerItem = new JMenuItem("center"); //888888888888888888888888888888888
        tspItem = new JMenuItem("tsp"); //888888888888888888888888888888888
        isConnectedItem = new JMenuItem("isConnected"); //88888888888888888888
        addNodeItem = new JMenuItem("addNode"); //88888888888
        removeNodeItem = new JMenuItem("removeNode"); //88888888888
        removeEdgeItem = new JMenuItem("removeEdge"); //88888888888
        //=========================================


        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        //=========================================
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

        //================================
        chooseAlgoMenu.add(shortPathDistItem);
        chooseAlgoMenu.add(shortPathItem);
        chooseAlgoMenu.add(centerItem);
        chooseAlgoMenu.add(tspItem);
        chooseAlgoMenu.add(isConnectedItem);
        chooseAlgoMenu.add(addNodeItem);
        chooseAlgoMenu.add(removeNodeItem);
        chooseAlgoMenu.add(removeEdgeItem);

        //================================


        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        menuBar.add(chooseAlgoMenu);


//        //=====================
//            panel = new NewPanel(graphToLoad);
////            repaint();
//            this.add(panel);

        //================
        this.setJMenuBar(menuBar);
        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            System.out.println("load");

//            DirectedWeightedGraphAlgorithms graphToLoad = new MyDWGraphAlgo();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int respone = fileChooser.showOpenDialog(null);

            if (respone == JFileChooser.APPROVE_OPTION) {

                this.graphToLoad.load(fileChooser.getSelectedFile().getAbsolutePath());
//                System.out.println(points);
                this.panel = new NewPanel(this.graphToLoad);
                this.add(panel);
                this.setVisible(true);
                panel.repaint();


            }
        }
        if (e.getSource() == saveItem) {
            System.out.println("save");
            this.panel.graphAlgo.save("true_save.json");
//                System.out.println(points);
        }
        if (e.getSource() == exitItem) {
            System.exit(0);
        }

/*
        // first if we want to choose an algo to check we need to press on the menu bar to select one algo
        if (e.getSource() == shortPathDistItem ){
//            graphToLoad.shortestPath()
            this.textField = new JTextField();
            this.textField.setPreferredSize(new Dimension(250,40));
            this.button = new JButton();
            this.button.setText("enter src and dest");
            this.button.addActionListener(this);
            this.button.setBounds(100,100,250,100);
            this.button.setFocusable(false);
            this.add(button);
            this.add(textField);
//            this.pack();
            this.panel.add(textField);
            this.panel.add(button);
            repaint();
            this.setVisible(true);
//            JOptionPane.showConfirmDialog(null,this.graphToLoad.)
        }

        // after if
        if (e.getSource() == button){
            String textInput = textField.getText();
            String[] srcDest = textInput.split(",");
            System.out.println(Arrays.toString(srcDest));
            double ans = this.graphToLoad.shortestPathDist(Integer.parseInt(srcDest[0]),Integer.parseInt(srcDest[1]));
            System.out.println("ans is = "+ ans);
            JOptionPane.showMessageDialog(null,String.valueOf(ans) ,"the shortest path dist is", JOptionPane.PLAIN_MESSAGE);
        }
*/
        /**
         * shortPathDist get 2 input
         * @input_1 src - start node - from type int
         * @input_2 dest - end (target) node - from type int
         * @return double
         */
        if (e.getSource() == shortPathDistItem) {
            String textInput = JOptionPane.showInputDialog("enter src and dest.\n It is important to note insert the data as follows: src,dest  - without spaces!!!");
            String[] srcDest = textInput.split(",");
            System.out.println(Arrays.toString(srcDest));
            double ans = this.graphToLoad.shortestPathDist(Integer.parseInt(srcDest[0]), Integer.parseInt(srcDest[1]));
            System.out.println("ans is = " + ans);
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
            JOptionPane.showMessageDialog(null, Arrays.toString(listOfNodePath), "the shortest path dist is", JOptionPane.PLAIN_MESSAGE);
        }

        /**
         * no input
         * @return the Node data (in here we represent the node by node id)
         */
        if (e.getSource() == centerItem) {
            double ans = this.graphToLoad.center().getKey();
            System.out.println("center is = " + ans);
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
            JOptionPane.showMessageDialog(null, Arrays.toString(listOfNodeTSp), "the tsp is", JOptionPane.PLAIN_MESSAGE);
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
            int ans = JOptionPane.showConfirmDialog(null,"do you want to connect this Node with another Node?","you can enter a Node Now",JOptionPane.YES_NO_OPTION);
            if (ans == 0){ // if the answer is yes
                String textInput = JOptionPane.showInputDialog("Enter X,Y,DEST (with which Node do you want to connect this new Node),WEIGHT_NEW_EDGE (what will be the weight of the new edge) .\n It is important to note insert the data as follows: X,Y,DEST,WEIGHT_NEW_EDGE  - without spaces!!!");
                String[] lst = textInput.split(",");
                double x = Double.parseDouble(lst[0]);
                double y = Double.parseDouble(lst[1]);


//                double weightNode = Double.parseDouble(lst[2]); // we dont need NODE WEIGHT!!!
                int dest = Integer.parseInt(lst[2]);
                double weightEdge = Double.parseDouble(lst[3]);
                // Z is always zero
                // in order to determine the new key of the Node i take the size of the Node's in graph
                // and it will be our new key for the new Node.
                // we can do that because the Node's id starts from zero and also when we remove a Node from the graph it won't change
                int keyNode = this.graphToLoad.getGraph().nodeSize();
                MyNode n = new MyNode(new MyGeoLocation(x,y,0.0),keyNode);
//                n.setWeight(weightNode);
                this.graphToLoad.getGraph().addNode(n);
                this.graphToLoad.getGraph().connect(n.getKey(),dest,weightEdge);
                //***************** I ADD THIS *****************
                this.panel = new NewPanel(this.graphToLoad);
                this.add(this.panel);
                this.setVisible(true);
                repaint();
                //*********************************************
            }else{ // if the answer is no
                String textInput = JOptionPane.showInputDialog(" Enter X,Y\n It is important to note insert the data as follows: X,Y  - without spaces!!!");
                String[] lst = textInput.split(",");
                double x = Double.parseDouble(lst[0]);
                double y = Double.parseDouble(lst[1]);
                int keyNode = this.graphToLoad.getGraph().nodeSize();
                MyNode n = new MyNode(new MyGeoLocation(x,y,0.0),keyNode);
//                n.setWeight(weightNode);
                this.graphToLoad.getGraph().addNode(n);
                //**************** I ADD THIS *********************
                this.panel = new NewPanel(this.graphToLoad);
                this.add(panel);
                this.setVisible(true);
                repaint();
                //*********************************************
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
//            String[] lst = textInput.split(",");
            while (this.graphToLoad.getGraph().getNode(Integer.parseInt(textInput)) == null){
                textInput = JOptionPane.showInputDialog("there is no such Node in the graph please enter a new one");
            }
            System.out.println(this.graphToLoad.getGraph().nodeSize()+"..............................");
            this.graphToLoad.getGraph().removeNode(Integer.parseInt(textInput));
            System.out.println(this.graphToLoad.getGraph().nodeSize()+"..............................");

            //            this.panel = new NewPanel(this.graphToLoad);
            //***************** I ADD THIS ********************
            this.panel = new NewPanel(this.graphToLoad);
            this.add(panel);
            this.setVisible(true);
            repaint();
            //*********************************************
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
            this.graphToLoad.getGraph().removeEdge(src,dest);
            repaint();
        }
    }


    private double scale(double data, double r_min, double r_max,
                         double t_min, double t_max) {
        double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;
        return res;

    }


    //============================================================
//    private Map<String, String> readJson(String file) throws FileNotFoundException {
//        String path = file;
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
//
//        Gson gson = new Gson();
//        Map json = gson.fromJson(bufferedReader, Map.class);
//        return json;
//    }
//
//
//    private String[] createStringOfEdgesDataFromJson(Map<String, String> map) {
//        String s = map.values().toArray()[1].toString();
//        s = s.replaceAll("[{}pos=id\\[\\]]", "");
//        String[] s1 = s.split(",");
//        return s1;
//    }


    public static void main(String[] args) {
//        DirectedWeightedGraph g = new DW_Graph();
        new NewFrame("src/G1.json");
    }
}
