package api;
import java.util.*;

public class DW_Graph implements DirectedWeightedGraph {
    public HashMap< Integer, NodeData > Nodes;
    public HashMap<Integer ,HashMap<Integer,EdgeData>> Edges;
    private int ModeCount;
    private int NumberOfNodes;
    private int NumberOfEdges;

    /**
     * Default constructor.
     */
    public DW_Graph(){
         this.Nodes= new HashMap<>();
         this.Edges =new HashMap<>();
         NumberOfNodes=0;
         NumberOfEdges=0;
         ModeCount=0;
    }
    /**
     *  Constructor that doing a deep copy of the graph.
     */
    public DW_Graph(DirectedWeightedGraph newGraph) {
        this.Nodes= new HashMap<>();
        this.Edges =new HashMap<>();
        NodesCopy(newGraph,this.Nodes);
        EdgesCopy(newGraph,this.Edges);
        NumberOfNodes=newGraph.nodeSize();
        NumberOfEdges=newGraph.edgeSize();
    }
    /**
     *   Func that doing a deep copy of the edges.
     */
    private void EdgesCopy(DirectedWeightedGraph newGraph, HashMap<Integer, HashMap<Integer, EdgeData>> edges) {
        HashMap<Integer, HashMap<Integer, EdgeData>> EdgeDup =  edges;
        for (Iterator<EdgeData> it = newGraph.edgeIter(); it.hasNext(); ) {
            EdgeData ed = it.next();
            Integer src = ed.getSrc();
            Integer dest = ed.getDest();
            double weight = ed.getWeight();
            newGraph.connect(src, dest, weight);
        }
    }
    /**
     *   Func that doing a deep copy of the nodes.
     */
    private void NodesCopy(DirectedWeightedGraph newGraph, HashMap<Integer, NodeData> nodes) {
        HashMap<Integer, NodeData> NodeDup = nodes;
        for (Iterator<NodeData> it = newGraph.nodeIter(); it.hasNext(); ) {
            NodeData n = it.next();
            this.addNode(n);
        }
    }
    /**
     *   Returns the node_data by the node_id.
     */
    @Override
    public NodeData getNode(int key) {
        return this.Nodes.get(key);
    }
    /**
     *   Returns the data of the edge (src,dest),
     *   null if none,this method run in O(1) time
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        if(this.Nodes.containsKey(src)){
            if(this.Nodes.containsKey(dest)){
                return this.Edges.get(src).get(dest);
            }
        }
        return null;
    }
    /**
     *  Adds a new node to the graph with
     *  the given node_data,this method run in O(1) time.
     */
    @Override
    public void addNode(NodeData n) {
        if(n!=null){
            if(!Nodes.containsKey(n.getKey())){
                this.Nodes.put(n.getKey(), n);
                this.Edges.put(n.getKey(), new HashMap<>());
                this.NumberOfNodes++;
                this.ModeCount++;
            }
        }
    }
    /**
     *  Connects an edge with weight w between node src to node dest, this method run in O(1) time.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if((src!=dest)&&(w>0)){
          if(Edges.containsKey(Edges.get(src).get(dest))&&(w!=Edges.get(src).get(dest).getWeight())) {
              removeEdge(src,dest);
              this.NumberOfEdges--;
          }
              EdgeData edge = new MyEdge(src,dest,w);
              Edges.get(src).put(dest,edge);
              this.NumberOfEdges++;
              this.ModeCount++;
        }
    }
    /**
     *  Returns an Iterator for the collection representing all the nodes in the graph.
     */
    @Override
    public Iterator<NodeData> nodeIter() {

        return (Iterator<NodeData>) this.Nodes.values().iterator();
    }
    /**
     *   RReturns an Iterator for all the edges in this graph.
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        ArrayList <EdgeData> EdgeArr= new ArrayList<EdgeData>();
        Iterator<Integer> n = this.Edges.keySet().iterator();
        while(n.hasNext()){
            Integer p1 = n.next();
            if(!this.Edges.get(p1).isEmpty()){
                Iterator<EdgeData> EdIter = this.Edges.get(p1).values().iterator();
                while(EdIter.hasNext()){
                    EdgeArr.add(EdIter.next());
                }
            }
        }
        Iterator <EdgeData> FinalIterator =  EdgeArr.iterator();
       return FinalIterator;
    }
    /**
     *   returns an Iterator for edges getting out of the given node.
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return this.Edges.get(node_id).values().iterator();
    }
    /**
     *  Deletes the node (with the given ID) from the graph-and removes all edges which starts or ends at this node.
     */
    @Override
    public NodeData removeNode(int key) {
        if(Nodes.containsKey(key)) {
            int EdgeCounter = this.Edges.get(key).size();
            this.NumberOfEdges = this.NumberOfEdges - EdgeCounter;
            this.ModeCount = this.ModeCount + EdgeCounter;
            this.Edges.remove(key);
            Iterator <Integer> iter = this.Edges.keySet().iterator();
            while(iter.hasNext()){
                Integer temp = iter.next();
                           if( this.Edges.get(temp).containsKey(key)) {
                               this.Edges.get(temp).remove(key);
                               NumberOfEdges--;
                           }
            }
            NodeData HasRemoved = Nodes.get(key);
            Nodes.remove(key);
            if(HasRemoved!=null){
                NumberOfNodes--;
                ModeCount++;
            }
            return HasRemoved;
        }
        return null;
    }
    /**
     *  Deletes the edge from the graph, this method run in O(1) time.
     */
    @Override
    public EdgeData removeEdge(int src, int dest) {
        if(src!=dest) {
            EdgeData edge = Edges.get(src).get(dest);
            Edges.get(src).remove(dest);
            if(edge != null ){
                NumberOfEdges--;
                ModeCount++;
                return edge;
            }
        }
        return null;
    }
    /**
     *   Returns the number of vertices (nodes) in the graph,this method run in O(1) time.
     */
    @Override
    public int nodeSize() { return this.NumberOfNodes;}
    /**
     * Returns the number of edges (assume directional graph),this method run in O(1) time.
     */
    @Override
    public int edgeSize() { return this.NumberOfEdges;}

    /**
     *  Returns the Mode Count - for testing changes in the graph.
     */
    @Override
    public int getMC() { return this.ModeCount;}
}
