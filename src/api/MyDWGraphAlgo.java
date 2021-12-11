package api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.Node;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class MyDWGraphAlgo implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;

    public MyDWGraphAlgo() {this.graph = new DW_Graph();}

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
        DirectedWeightedGraph GraphCopy = new DW_Graph(graph);
        //need to return a new deep copy of the graph
        return GraphCopy;
    }

    @Override
    public boolean isConnected() {
        if (this.graph.nodeSize() == 0) {
            return true;
        }

        DirectedWeightedGraph CopiedGraph =new DW_Graph(graph);
        resetTag();
        DfsAlgo(CopiedGraph.nodeIter().next().getKey());
        Iterator<NodeData> nodes = CopiedGraph.nodeIter();
        while (nodes.hasNext()) {
            int n = nodes.next().getTag();
            if (n == -1) {
                return false;
            }
        }
        ResetEdgeTag(CopiedGraph.edgeIter());
        getTranspose(CopiedGraph);
        DfsAlgo(CopiedGraph.nodeIter().next().getKey());
        Iterator<NodeData> IsVisited = CopiedGraph.nodeIter();
        while (IsVisited.hasNext()) {
            int n = IsVisited.next().getTag();
            if (n == -1) {
                getTranspose(CopiedGraph);
                return false;
            }
        }
        return true;
    }

    private void getTranspose(DirectedWeightedGraph graph) {
        Iterator<EdgeData> EdgeIter = graph.edgeIter();
        while (EdgeIter.hasNext()) {
            EdgeData Edge = EdgeIter.next();
            if (Edge.getTag() == -1) {
                if (Edge != null) {
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
        Iterator<EdgeData> EdgeItertor = this.getGraph().edgeIter(NodeKey);
        while (EdgeItertor.hasNext()) {
            dest = EdgeItertor.next().getDest();
            if (this.graph.getNode(dest).getTag() == -1) {
                DfsAlgo(dest);
            }

        }
    }

    private void ResetEdgeTag(Iterator<EdgeData> EdgeIter) {
        while (EdgeIter.hasNext()) {
            EdgeData n = EdgeIter.next();
            n.setTag(-1);
        }
    }

    private ArrayList<NodeData> dijkstraList(NodeData src, NodeData dest) {
        HashMap<NodeData,NodeData> Prev = new HashMap<>();
        HashMap<NodeData, Double> DistMap = new HashMap<>();
        Queue<NodeData> PQ = new PriorityQueue<>(Comparator.comparingDouble(DistMap::get));
        boolean IsDest = false;
        NodeData NeighborNode;
        NodeData Current;
        DistMap.put(src, 0.0);
        PQ.add(src);
        while ((!IsDest) && (!PQ.isEmpty())) {
            Current = PQ.poll();
            if (Current.getKey() == dest.getKey()) {
                IsDest = true;
            }
            Iterator<EdgeData> NeiIter = this.graph.edgeIter(Current.getKey());
            while (NeiIter.hasNext()) {
                EdgeData CurrEdge = NeiIter.next();
                NeighborNode = this.graph.getNode(CurrEdge.getDest());
                if (NeighborNode.getTag() == -1) {
                    DistMap.put(NeighborNode, DistMap.get(Current) + CurrEdge.getWeight());
                    PQ.add(NeighborNode);
                    Prev.put(NeighborNode, Current);
                    NeighborNode.setTag(1);
                } else {
                    double t = Math.min(DistMap.get(NeighborNode), DistMap.get(Current) + CurrEdge.getWeight());
                    if (t != DistMap.get(NeighborNode)) {
                        DistMap.put(NeighborNode, t);
                        PQ.add(NeighborNode);
                        Prev.put(NeighborNode, Current);
                    }
                }
            }
        }
        if (!IsDest) {
            return new ArrayList<>();
        }
        ArrayList <NodeData> FinalPath = new ArrayList<>();
        int temp= dest.getKey();
        FinalPath.add(dest);
        dest=Prev.get(dest);
            while ((dest != null ) && (dest.getKey() != temp) ) {
                FinalPath.add(0, dest);
                dest = Prev.get(dest);
            }

        return FinalPath;

    }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        NodeData Source = this.graph.getNode(src);
        NodeData Destination = this.graph.getNode(dest);
       if(Source==null) return null;
       if(Destination==null) return null;
        if (src == dest) {
            ArrayList <NodeData> OneNodeList =new ArrayList<NodeData>();
            OneNodeList.add(Source);
            return OneNodeList;
        }
        resetTag();
        ArrayList <NodeData> Ans =new ArrayList<NodeData>();
        Ans = dijkstraList(Source,Destination);
        return Ans;
    }

    @Override
    public NodeData center() {
        if(!isConnected()){
            return null;
        }
        Iterator<EdgeData> NeiIterr = this.graph.edgeIter(3);
        while (NeiIterr.hasNext()) {
            EdgeData CurrEdge = NeiIterr.next();
            System.out.println(CurrEdge.getDest());
        }
        int MinKey = -1;
        HashMap<NodeData, Double> NodeMap = new HashMap<NodeData, Double>();
        double minOfAll = Double.MAX_VALUE;

        Iterator<NodeData> NodeIter = this.graph.nodeIter();
        while (NodeIter.hasNext()) {
            double max = 0;
            NodeData Nd = NodeIter.next();
            Iterator<NodeData> EachNodeIter = this.graph.nodeIter();
            while (EachNodeIter.hasNext()) {
                NodeData EachNd = EachNodeIter.next();
                if (EachNd.getKey() != Nd.getKey()) {
                    System.out.println("from :" +Nd.getKey()+ ", to" + EachNd.getKey());
                    double k = dijkstra(Nd, EachNd);
                    System.out.println("is :"+k);
                    if ((max < k)&&(k!=Double.MAX_VALUE)) {
                        max = k;
                    }
                }
            }
            NodeMap.put(Nd, max);
        }
        Iterator<NodeData> NodeIter3 = NodeMap.keySet().iterator();
        while (NodeIter3.hasNext()) {
            NodeData Nd3 = NodeIter3.next();

            if (NodeMap.get(Nd3) < minOfAll) {
                minOfAll = NodeMap.get(Nd3);
           System.out.println("the current value is ====="+NodeMap.get(Nd3));
           MinKey = Nd3.getKey();
            }
        }
        return this.graph.getNode(MinKey);
    }

    //=================== BFS ============================
    public List<NodeData> bfs(int src) {
        resetTag();
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

    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest) {
            return 0;
        }
        resetTag();
        double shortPathWeight = dijkstra(this.graph.getNode(src), this.graph.getNode(dest));
        resetTag();
        if (shortPathWeight == Double.MAX_VALUE) {
            return -1;
        }
        return shortPathWeight;
    }

    private double dijkstra(NodeData src, NodeData dest) {
        HashMap<NodeData, Double> DistMap = new HashMap<>();
        Queue<NodeData> PQ = new PriorityQueue<>(Comparator.comparingDouble(DistMap::get));
        resetTag();
        boolean IsDest = false;
        NodeData NeighborNode;
        NodeData Current;
        DistMap.put(src, 0.0);
        PQ.add(src);
        while ((!IsDest) && (!PQ.isEmpty())) {
            Current = PQ.poll();
            if (Current.getKey() == dest.getKey()) {
                IsDest = true;
            }
                Iterator<EdgeData> NeiIter = this.graph.edgeIter(Current.getKey());
                while (NeiIter.hasNext()) {
                    EdgeData CurrEdge = NeiIter.next();
                    NeighborNode = this.graph.getNode(CurrEdge.getDest());
                    if (NeighborNode.getTag() == -1) {
                        DistMap.put(NeighborNode, DistMap.get(Current) + CurrEdge.getWeight());
                        PQ.add(NeighborNode);
                        NeighborNode.setTag(1);
                    } else {
                        double t = Math.min(DistMap.get(NeighborNode), DistMap.get(Current) + CurrEdge.getWeight());
                        if (t != DistMap.get(NeighborNode)) {
                            DistMap.put(NeighborNode, t);
                            PQ.add(NeighborNode);
                        }
                    }
                }
            }
            if (!IsDest) {
                return Double.MAX_VALUE;
            }
            return DistMap.get(this.graph.getNode(dest.getKey()));
        }

;    private void resetTag() {
        for (Iterator<NodeData> it = this.graph.nodeIter(); it.hasNext(); ) {
            NodeData node = it.next();
            node.setTag(-1);
        }
    }

}
