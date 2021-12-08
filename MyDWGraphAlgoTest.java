package api;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyDWGraphAlgoTest {
    private DW_Graph graph;
    private MyDWGraphAlgo graphAlgo;
   // private DirectedWeightedGraph graph;
    NodeData n1,n2,n3,n4,n5;


    void Default(){
        this.graphAlgo= new MyDWGraphAlgo();
        this.graph= new DW_Graph();
        this.n1 = new MyNode(new MyGeoLocation(0, 0, 0), 1, 5, -1, "NADAV");
        this.n2 = new MyNode(new MyGeoLocation(1, 1, 0), 2, 45, -1, "MOYAL");
        this.n3 = new MyNode(new MyGeoLocation(3, 3, 0), 3, 33, -1, "YEHONATHAN");
        this.n4 = new MyNode(new MyGeoLocation(5, 7, 0), 4, 6, -1, "BAREL");
        this.n5 = new MyNode(new MyGeoLocation(2, 7, 0), 5, 3, -1, "BAREL");
        this.graph.addNode(this.n1);
        this.graph.addNode(this.n2);
        this.graph.addNode(this.n3);
        this.graph.addNode(this.n4);
        this.graph.addNode(this.n5);
        this.graph.connect(n1.getKey(), n2.getKey(),1);
        this.graph.connect(n2.getKey(), n3.getKey(),6);
        this.graph.connect(n3.getKey(), n4.getKey(),7);
        this.graph.connect(n4.getKey(), n5.getKey(),8);

//        this.graph.connect(n2.getKey(), n1.getKey(),7);

        this.graphAlgo.init(graph);
        this.graphAlgo.getGraph();
    }
    void ChangeTheGraph() {
        this.graph.removeEdge(n3.getKey(),n4.getKey());
        this.graph.removeEdge(n2.getKey(),n1.getKey());
        this.graph.removeEdge(n4.getKey(),n2.getKey());
    }

    @Test
    void copy() {
     Default();
     DW_Graph GraphDup= new DW_Graph();
     GraphDup= (DW_Graph) this.graphAlgo.copy();
     assertEquals(this.graph.nodeSize(),GraphDup.nodeSize());
     assertEquals(this.graph.edgeSize(),GraphDup.edgeSize());
     assertEquals(this.graph.getNode(1).getWeight(),GraphDup.getNode(1).getWeight());
        }

    @Test
    void isConnected() {
        Default();
        assertTrue(this.graphAlgo.isConnected());
        ChangeTheGraph();
        assertFalse(this.graphAlgo.isConnected());
    }

    @Test
    void shortestPathDist() {
        Default();
        assertEquals(1,this.graphAlgo.shortestPathDist(1,2));
        assertEquals(Integer.MAX_VALUE,this.graphAlgo.shortestPathDist(4,3));
        assertEquals(7,this.graphAlgo.shortestPathDist(1,3));

    }

    @Test
    void shortestPath() {
        Default();
        assertEquals(7,this.graphAlgo.shortestPath(1,5));

    }

    @Test
    void center() {

    }

    @Test
    void tsp() {
        Default();
        List<NodeData> nodePath = new LinkedList<NodeData>();
        nodePath.add(this.n1);
        nodePath.add(this.n2);
        nodePath.add(this.n3);
        nodePath.add(this.n4);
        assertEquals(4,this.graphAlgo.tsp(nodePath).size());
        this.graph.removeEdge(1,2);
        this.graph.removeEdge(4,2);
        assertNull(this.graphAlgo.tsp(nodePath));
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }


}