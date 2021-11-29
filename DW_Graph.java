package api;
import java.security.Key;
import java.util.*;

public class DW_Graph implements DirectedWeightedGraph {
    private HashMap< Integer, NodeData > Nodes;
    private HashMap<Integer ,HashMap<Integer,EdgeData>> Edges;
    private int ModeCount;
    private int NumberOfNodes;
    private int NumberOfEdges;


    public DW_Graph(){
         this.Nodes= new HashMap<>();
         this.Edges =new HashMap<>();
         NumberOfNodes=0;
         NumberOfEdges=0;
         ModeCount=0;
    }

    public DW_Graph(DirectedWeightedGraph newGraph){
        this.Nodes= new HashMap<>();
        this.Edges =new HashMap<>();
        ///
        ///
        NumberOfNodes=Nodes.size();
        NumberOfEdges=Edges.size();
    }



    @Override
    public NodeData getNode(int key) {
        return this.Nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if(this.Nodes.containsKey(src)){
            if(this.Edges.containsKey(dest)){
                return this.Edges.get(src).get(dest);
            }
        }
            return null;
    }

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

    @Override
    public void connect(int src, int dest, double w) {
        if((src!=dest)&&(w>0)){
            // check case when there is already this edge??
            EdgeData edge = new MyEdge(src,dest,w);
            Edges.get(src).put(dest,edge);
        }
    }

    //* Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
    @Override
    public Iterator<NodeData> nodeIter() {

        return (Iterator<NodeData>) this.Nodes.values();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return (Iterator<EdgeData>) this.Edges.values();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {

        return (Iterator<EdgeData>) this.Edges.get(node_id).values();
    }

    @Override
    public NodeData removeNode(int key) {
        if(Nodes.containsKey(key)) {
            int EdgeCounter = Edges.get(key).size();
            NumberOfEdges = NumberOfEdges - EdgeCounter;
            ModeCount = ModeCount + EdgeCounter;
            Edges.remove(key);
            Iterator <Integer> iter = Edges.keySet().iterator();
            while(iter.hasNext()){
                if(iter.next()!=null){
                    Integer i = iter.next();
                    if(Edges.get(i).containsKey(key)){
                        Edges.get(i).remove(key);
                        NumberOfEdges--;
                        ModeCount++;
                    }
                    }
            }
            NodeData node = Nodes.get(key);
            Nodes.remove(key);
            if(node!=null){
                NumberOfNodes--;
                ModeCount++;
            }
            return node;
        }
        return null;
    }

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

    @Override
    public int nodeSize() {
        return Nodes.size();
    }

    @Override
    public int edgeSize() {
        return Edges.size();
    }

    @Override
    public int getMC() {
        return ModeCount;
    }
}
