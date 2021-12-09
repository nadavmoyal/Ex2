import api.DW_Graph;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.MyDWGraphAlgo;
import com.google.gson.Gson;

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
    JMenuItem shortPathItem; //888888888888888888888888888888888
    JMenuItem shortPathListItem;  //888888888888888888888888888888888
    JMenuItem centerItem;   //888888888888888888888888888888888
    JMenuItem tspItem;   //888888888888888888888888888888888
    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    DirectedWeightedGraphAlgorithms graphToLoad;// = new MyDWGraphAlgo();



    private List<Integer> xPoints = new ArrayList<Integer>();
    private List<Integer> yPoints = new ArrayList<Integer>();
    private LinkedList<Point2D> points = new LinkedList<Point2D>();


        public NewFrame(String json_file) {
//        DirectedWeightedGraphAlgorithms graphFromJson = new MyDWGraphAlgo();
//        graphFromJson.load(json_file);
//        this.graphToLoad = graphFromJson;
//        System.out.println(this.graphToLoad.getGraph().nodeSize());
        this.graphToLoad = new MyDWGraphAlgo();
        graphToLoad.load(json_file);

        this.setSize(500, 500);
        this.setLayout(new BorderLayout());

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
        shortPathItem = new JMenuItem("ShortPath");
        shortPathListItem = new JMenuItem("ShortPathList");  //888888888888888888888888888888888
        centerItem = new JMenuItem("center"); //888888888888888888888888888888888
        tspItem  = new JMenuItem("tsp"); //888888888888888888888888888888888
        //=========================================



        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        //=========================================
        shortPathItem.addActionListener(this);
        shortPathListItem.addActionListener(this);
        centerItem.addActionListener(this);
        tspItem.addActionListener(this);


        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        //================================
        chooseAlgoMenu.add(shortPathItem);
        chooseAlgoMenu.add(shortPathListItem);
        chooseAlgoMenu.add(centerItem);
        chooseAlgoMenu.add(tspItem);
        //================================


        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        menuBar.add(chooseAlgoMenu);


//        //=====================
//            panel = new NewPanel(graphToLoad);
////            repaint();
//            this.add(panel);
            panel = new NewPanel(graphToLoad);
            this.add(panel);
            //================
        this.setJMenuBar(menuBar);
        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            System.out.println("load");

//            DirectedWeightedGraphAlgorithms graphToLoad = new MyDWGraphAlgo();
            this.graphToLoad = new MyDWGraphAlgo();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int respone = fileChooser.showOpenDialog(null);

            if (respone == JFileChooser.APPROVE_OPTION) {
//                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
//                System.out.println(file.toString()); //////////
                graphToLoad.load(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(points);
                panel = new NewPanel(graphToLoad);
                repaint();
                this.add(panel);
                this.setVisible(true);
//                repaint();


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
        if (e.getSource() == shortPathItem ){
//            graphToLoad.shortestPath()
        }
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
