package api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.Node;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class MyDWGraphAlgo implements DirectedWeightedGraphAlgorithms{
    private DirectedWeightedGraph graph;

    public MyDWGraphAlgo(){
        this.graph= new DW_Graph();


    }
    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph GraphCopy= new DW_Graph(graph);
        //need to return a new deep copy of the graph
        return GraphCopy;
    }

    @Override
    public boolean isConnected() { // need to think how to do that ..
        if (this.graph.nodeSize()==0){
            return true;
        }

        ResetNodeTag(this.graph.nodeIter());
        DfsAlgo(this.graph.nodeIter().next().getKey());
        Iterator <NodeData> nodes = this.graph.nodeIter();
        while (nodes.hasNext()){
            int n=nodes.next().getTag();
            if (n==-1){
                return false;
            }
        }
        //if(there is a node that we didnt visit) so return false
        // than transpose;
        ResetEdgeTag(this.graph.edgeIter());
        getTranspose(this.graph);
        DfsAlgo(this.graph.nodeIter().next().getKey());
        Iterator <NodeData> IsVisited = this.graph.nodeIter();
        while (IsVisited.hasNext()){
            int n=IsVisited.next().getTag();
            if (n==-1){
                getTranspose(this.graph);
                return false;
            }
        }
        getTranspose(this.graph);
        //if(there is a node that we didnt visit) so return false
        // need to change it to specific graph instead of this.graph
        return true;
    }


    private void getTranspose(DirectedWeightedGraph graph) {
        Iterator <EdgeData> EdgeIter= graph.edgeIter();
        while (EdgeIter.hasNext()){
            if(EdgeIter.next().getTag()==-1) {
                System.out.println(EdgeIter.next().getTag());
                EdgeData Edge=EdgeIter.next();
                if(Edge!=null) {
                    int src = Edge.getSrc();
                    int dest = Edge.getDest();
                    double w = Edge.getWeight();
                    graph.connect(dest, src, w);
                    graph.getEdge(dest, src).setTag(1);
                    graph.removeEdge(src, dest);
                }
            }
        }
    }

    private void DfsAlgo(int NodeKey) {
        int dest;
        this.graph.getNode(NodeKey).setTag(1);
        Iterator <EdgeData> EdgeItertor = this.getGraph().edgeIter(NodeKey);
        while(EdgeItertor.hasNext()){
            dest= EdgeItertor.next().getDest();
            if(this.graph.getNode(dest).getTag()==-1){
                DfsAlgo(dest);
            }

        }
    }

    private void ResetEdgeTag (Iterator<EdgeData> EdgeIter) {
        while (EdgeIter.hasNext()){
            EdgeData n=EdgeIter.next();
            n.setTag(-1);
        }
    }

    private void ResetNodeTag(Iterator<NodeData> nodeIter) {
        while (nodeIter.hasNext()){
            NodeData n=nodeIter.next();
            n.setTag(-1);
        }
    }

    private ArrayList<NodeData> dijkstraList(NodeData src, NodeData dest) {
//        ArrayList<NodeData> printNodePath = new ArrayList<NodeData>();99999999999999999999999
//        printNodePath.add(this.graph.Nodes.get(src));999999999999999999999999999999999
        // here we use a PriorityQueue that gets a NodeData and compare them by the node weight
        //***************************************** NEW *********************************
        double shortest = Integer.MAX_VALUE;
        PriorityQueue<NodeData> priorityOnWeight = new PriorityQueue<NodeData>(this.graph.nodeSize(), (Comparator<NodeData>) (node_1, node_2) -> Double.compare(node_1.getWeight(), node_2.getWeight()));
        src.setWeight(0);
        priorityOnWeight.add(src);
        ArrayList<NodeData> prev = new ArrayList<NodeData>();
        prev.add(src);
        while (priorityOnWeight.size() != 0) {
            NodeData tempNode = priorityOnWeight.poll();
            for (Iterator<EdgeData> it = this.graph.edgeIter(tempNode.getKey()); it.hasNext(); ) {
                EdgeData edge = it.next();
                NodeData node = this.graph.getNode(edge.getDest());
                if (node.getInfo().equals("notVisited")) {
                    if (node.getWeight() > tempNode.getWeight() + edge.getWeight()) {
                        prev.add(node);
                        node.setWeight(Math.min(node.getWeight(), tempNode.getWeight() + edge.getWeight()));
                        node.setTag(tempNode.getKey());
                    }
                    priorityOnWeight.add(node);
                }
            }
            tempNode.setInfo("visited");
            if (tempNode.getKey() == dest.getKey()) {
                return prev;
            }
        }
        return prev;
        //===================================================================================================================
//        PriorityQueue<NodeData> priorityOnWeight = new PriorityQueue<NodeData>(this.graph.nodeSize(), (Comparator<NodeData>) (node_1, node_2) -> Double.compare(node_1.getWeight(), node_2.getWeight()));
//        priorityOnWeight.add(src);
//        boolean[] nodeThatHaveBeenVisited = new boolean[this.graph.nodeSize()];
//        Arrays.fill(nodeThatHaveBeenVisited, false);
////        List<NodeData> nodePath = new ArrayList<NodeData>();
//        int[] prev = new int[this.graph.nodeSize()];
//        while (!priorityOnWeight.isEmpty()) {
//            NodeData tempNode = priorityOnWeight.poll();
//            // on this for-each we run on every edges that comes out from the src
//            // in order to do that i change the field variable 'Edges' from private to public
//            for (EdgeData edge : this.graph.Edges.get(tempNode.getKey()).values()) {
//                NodeData node = this.graph.getNode(edge.getDest());
//                if (nodeThatHaveBeenVisited[node.getKey()] == false) {
//                    if (node.getWeight() > tempNode.getWeight() + edge.getWeight()) {
//                        prev[edge.getDest()] = node.getKey();
//                        node.setWeight(Math.min(node.getWeight(), tempNode.getWeight() + edge.getWeight()));
//                        node.setTag(tempNode.getKey());
//                    }
//                }
//                priorityOnWeight.add(node);
//            }
//            nodeThatHaveBeenVisited[tempNode.getKey()] = true;
//            if (tempNode.getKey() == dest.getKey()) {
//                return prev;
//            }
//        }
//        return prev;
        //==============================================================================
    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        resetWeight();
        resetTag();
        resetInfo();
        if (src == dest || dijkstraList(this.graph.getNode(src),this.graph.getNode(dest)).size()==0){
            resetWeight();
            resetTag();
            resetInfo();
            return new ArrayList<NodeData>();
        }
        ArrayList<NodeData> path = dijkstraList(this.graph.getNode(src), this.graph.getNode(dest));
        resetWeight();
        resetTag();
        resetInfo();
        //        if (p[src] == Double.MAX_VALUE) {
//            return null;
//        }
/*        List<NodeData> nodePath = new ArrayList<NodeData>();
        for (int i = 0; i < path.length; i++) {
            nodePath.add(0, this.graph.getNode(path[i])); // from dijkstra we get a list of keys of the nodes so in oredr to find the path of the nodes it self we enter the key into the 'getNode(int key)'
        }*/
//        Collections.reverse(path);
        // nodePath.removeIf(Objects::isNull);

        return path;
    }



    @Override
    public NodeData center() {
        HashMap<NodeData, Double> node = new HashMap<NodeData, Double>();
            /**
             * we will check the dist from every given node (first for loop) to every other nodes
             * (second for loop) calculate the short dist between them with dijkstra algo and add the dist to the sum var.
             * we continue to do so on all the others nodes and sum all the dist weight in the sum var.
             * at the end we will have a hashmap that the key is the 'NodeData' and the value is 'sum' of the dist to all other nodes.
             * finally we return the 'NodeData' with the smallest sum
             */
        double sum = 0;
        Iterator <NodeData> NodeIt = this.graph.nodeIter();
            Iterator <NodeData> NodeIt2= this.graph.nodeIter();

        while(NodeIt.hasNext()){
                NodeData nodeVSOther=NodeIt.next();
                //for (int i = 0; i < this.graph.nodeSize(); i++) {

               // NodeData nodeVSOther = this.graph.getNode(i);
            while(NodeIt2.hasNext()){
                NodeData other = NodeIt2.next();
                sum += dijkstra(nodeVSOther, other);
            }
//                for (int j = i + 1; j < this.graph.nodeSize(); j++) { // here i change to 'i+1' because i dont want to check node to itself because it will be zero and this will show me its like the smallest one
//                    NodeData other = this.graph.getNode(j);
//                    sum += dijkstra(nodeVSOther, other);
//                }
                node.put(nodeVSOther, sum);
                sum = 0;
            }
        //}
        NodeData key = Collections.min(node.entrySet(), Map.Entry.comparingByValue()).getKey();
        return key;
    }


//    private void DFS(int vertex) {
//        visited[vertex] = true;
//        System.out.print(vertex + " ");
//
//        Iterator<Integer> ite = adjLists[vertex].listIterator();
//        while (ite.hasNext()) {
//            int adj = ite.next();
//            if (!visited[adj])
//                DFS(adj);
//        }
//    }

    //=================== BFS ============================

    public List<NodeData> bfs(int src) {
        ResetNodeTag(this.graph.nodeIter());
        List <NodeData> Nodelist = new ArrayList<NodeData>();
        LinkedList<Integer> queue = new LinkedList<Integer>();

        NodeData NodeToList = this.graph.getNode(src);
        NodeToList.setTag(1);
        Nodelist.add(NodeToList);
        queue.add(src);
        while (queue.size()!=0){
            src=queue.poll();
            Iterator<EdgeData> it =this.graph.edgeIter(src);
            while (it.hasNext()) {
                EdgeData temp = it.next();
                NodeData NodeToList2 = this.graph.getNode(temp.getDest());
                int dest = NodeToList2.getTag();
                int key  = temp.getDest();
                if (dest == -1) {
                    this.graph.getNode(key).setTag(1);
                    Nodelist.add(NodeToList2);
                    queue.add(key);
                }
            }
        }
        return Nodelist;
    }


    //==================== BFS =========================
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(this.graph.nodeIter()!=null){
            List<NodeData> TSP =  bfs(this.graph.nodeIter().next().getKey());
            if(TSP.size()==this.graph.nodeSize()){
                return TSP;
            }
        }
        return null;
    }

    /**
     * Saves this weighted directed graph to the given file name.
     * the method save the grafh in json format using {@Gson} class
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
//      save grape in json format
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DW_Graph.class, new GraphAdapter());
        Gson gson = gsonBuilder.create();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(gson.toJson(graph));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * This method load a graph to this graph algorithm.
     * the method load grafh in json format using {@Gson} class
     * if the file was successfully loaded - the underlying graph
     *    of this class will be changed (to the loaded one), in case the
     *    graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
//      load grape from json
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DW_Graph.class, new GraphAdapter());
        Gson gson = gsonBuilder.create();
        try (FileReader reader = new FileReader(file)) {
            init(gson.fromJson(reader, DW_Graph.class));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
//    public class compareWeight implements Comparator<NodeData> {
//        @Override
//        public int compare(NodeData node_1, NodeData node_2) {
//            return Double.compare(node_1.getWeight(), node_2.getWeight());
//
//        }
//    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest) {
            return 0;
        }
        resetInfo();
        resetTag();
        resetWeight();
        double shortPathWeight = dijkstra(this.graph.getNode(src), this.graph.getNode(dest));
        resetInfo();
        resetTag();
        resetWeight();
        if (shortPathWeight == Double.MAX_VALUE) {
            return -1;
        }
        return shortPathWeight;

    }


    private double dijkstra(NodeData src, NodeData dest) {
        double shortest = Integer.MAX_VALUE;
        PriorityQueue<NodeData> priorityOnWeight = new PriorityQueue<NodeData>(this.graph.nodeSize(), (Comparator<NodeData>) (node_1, node_2) -> Double.compare(node_1.getWeight(), node_2.getWeight()));
        src.setWeight(0);
        priorityOnWeight.add(src);
        while (priorityOnWeight.size() != 0) {
            NodeData tempNode = priorityOnWeight.poll();
            for (Iterator<EdgeData> it = this.graph.edgeIter(tempNode.getKey()); it.hasNext(); ) {
                EdgeData edge = it.next();
                NodeData node = this.graph.getNode(edge.getDest());
                if (node.getInfo().equals("notVisited")) {
                    if (node.getWeight() > tempNode.getWeight() + edge.getWeight()) {
                        node.setWeight(Math.min(node.getWeight(), tempNode.getWeight() + edge.getWeight()));
                        node.setTag(tempNode.getKey());
                    }
                    priorityOnWeight.add(node);
                }
            }
            tempNode.setInfo("visited");
            if (tempNode.getKey() == dest.getKey()) {
                return tempNode.getWeight();
            }
        }
        return Integer.MAX_VALUE;
    }

    private void resetTag() {
        for (Iterator<NodeData> it = this.graph.nodeIter(); it.hasNext(); ) {
            NodeData node = it.next();
            node.setTag(-1);
        }
    }

    private void resetWeight() {
        for (Iterator<NodeData> it = this.graph.nodeIter(); it.hasNext(); ) {
            NodeData node = it.next();
            node.setWeight(Integer.MAX_VALUE);
        }
    }


    private void resetInfo() {
        for (Iterator<NodeData> it = this.graph.nodeIter(); it.hasNext(); ) {
            NodeData node = it.next();
            node.setInfo("notVisited");
        }
    }
}
