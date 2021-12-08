package api;
import java.util.*;

public class DW_Graph implements DirectedWeightedGraph {
    public HashMap< Integer, NodeData > Nodes;
    public HashMap<Integer ,HashMap<Integer,EdgeData>> Edges;
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

    public DW_Graph(DirectedWeightedGraph newGraph) {
        this.Nodes= new HashMap<>();
        this.Edges =new HashMap<>();
        NodesCopy(newGraph,this.Nodes);
        EdgesCopy(newGraph,this.Edges);
        NumberOfNodes=newGraph.nodeSize();
        NumberOfEdges=newGraph.edgeSize();
    }

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

    private void NodesCopy(DirectedWeightedGraph newGraph, HashMap<Integer, NodeData> nodes) {
        HashMap<Integer, NodeData> NodeDup = nodes;
        for (Iterator<NodeData> it = newGraph.nodeIter(); it.hasNext(); ) {
            NodeData n = it.next();
            this.addNode(n);
        }
    }
//        EdgeData TempEdge;
//        this.Nodes= new HashMap<>();
//        Iterator <NodeData> NodeIt = this.nodeIter();
//        while(NodeIt.hasNext()){
//            newGraph.addNode(NodeIt.next());
//        }
//      //  this.Edges= new HashMap<>();
//        if(Edges!=null){
//        Iterator <EdgeData> EdgeIt = this.edgeIter();
//        while(EdgeIt.hasNext()) {
//            TempEdge = EdgeIt.next();
//            Integer src = TempEdge.getSrc();
//            Integer dest = TempEdge.getDest();
//            double weight = TempEdge.getWeight();
//            newGraph.connect(src, dest, weight);
//        }
//
//        NumberOfNodes = this.Nodes.size();
//        NumberOfEdges = this.Edges.size();
//        }
//    }
//        while(NodeIt.hasNext()) {
//            NodeData temp = NodeIt.next();
//            this.addNode(temp);
//        }
//        while(NodeIt.hasNext()) {
//            NodeData temp = NodeIt.next();
//            k=temp.getKey();
//            while (EdgeIt.hasNext()){
//                EdgeData temp2=EdgeIt.next();
//                Integer src = temp2.getSrc();
//                Integer dest = temp2.getDest();
//                double weight = temp2.getWeight();
//                EdgeData EdgeTemp=new MyEdge(src,dest,weight);
//                this.Edges.get(EdgeTemp.getSrc()).put(dest,temp2);
//            }
//        }
//        NumberOfNodes=Nodes.size();
//        NumberOfEdges=Edges.size();


    @Override
    public NodeData getNode(int key) {
        return this.Nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if(this.Nodes.containsKey(src)){
            if(this.Nodes.containsKey(dest)){
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
    //* Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
    @Override
    public Iterator<NodeData> nodeIter() {

        return (Iterator<NodeData>) this.Nodes.values().iterator();
    }

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
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {

        return this.Edges.get(node_id).values().iterator();
    }

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



    @Override
    public EdgeData removeEdge(int src, int dest) { // check again
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
        return this.NumberOfNodes;
    }

    @Override
    public int edgeSize() {
        return this.NumberOfEdges;
    }


    @Override
    public int getMC() {
        return this.ModeCount;
    }
}
