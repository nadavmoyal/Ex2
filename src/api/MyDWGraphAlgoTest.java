package api;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyDWGraphAlgoTest {
    private DW_Graph graph;
    private MyDWGraphAlgo graphAlgo;
    NodeData n1,n2,n3,n4;


    void Default(){
        this.graphAlgo= new MyDWGraphAlgo();
        this.graph= new DW_Graph();
        this.n1 = new MyNode(new MyGeoLocation(0, 0, 0), 1, 5, -1, "NADAV");
        this.n2 = new MyNode(new MyGeoLocation(1, 1, 0), 2, 45, -1, "MOYAL");
        this.n3 = new MyNode(new MyGeoLocation(3, 3, 0), 3, 33, -1, "YEHONATHAN");
        this.n4 = new MyNode(new MyGeoLocation(5, 7, 0), 4, 6, -1, "BAREL");

        this.graph.addNode(this.n1);
        this.graph.addNode(this.n2);
        this.graph.addNode(this.n3);
        this.graph.addNode(this.n4);

        this.graph.connect(n1.getKey(), n2.getKey(),1);
        this.graph.connect(n2.getKey(), n3.getKey(),6);
        this.graph.connect(n3.getKey(), n4.getKey(),7);

        this.graphAlgo.init(graph);
        this.graphAlgo.getGraph();
    }
    void DefaultForCenter(){
        this.graphAlgo= new MyDWGraphAlgo();
        this.graph= new DW_Graph();
        this.n1 = new MyNode(new MyGeoLocation(0, 0, 0), 1, 5, -1, "NADAV");
        this.n2 = new MyNode(new MyGeoLocation(1, 1, 0), 2, 45, -1, "MOYAL");
        this.n3 = new MyNode(new MyGeoLocation(3, 3, 0), 3, 33, -1, "YEHONATHAN");
        this.n4 = new MyNode(new MyGeoLocation(5, 7, 0), 4, 6, -1, "BAREL");

        this.graph.addNode(this.n1);
        this.graph.addNode(this.n2);
        this.graph.addNode(this.n3);

        this.graph.connect(n1.getKey(), n2.getKey(),10);
        this.graph.connect(n2.getKey(), n3.getKey(),10);
        this.graph.connect(n3.getKey(), n2.getKey(),1);
        this.graph.connect(n3.getKey(), n1.getKey(),1);

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
        assertEquals(-1,this.graphAlgo.shortestPathDist(4,3));
        assertEquals(7,this.graphAlgo.shortestPathDist(1,3));

    }

    @Test
    void shortestPath() {
        Default();
        assertEquals(4,this.graphAlgo.shortestPath(1,4).size());
        assertEquals(1,this.graphAlgo.shortestPath(1,4).get(0).getKey());
        assertEquals(2,this.graphAlgo.shortestPath(1,4).get(1).getKey());
        assertEquals(3,this.graphAlgo.shortestPath(1,4).get(2).getKey());
        assertEquals(4,this.graphAlgo.shortestPath(1,4).get(3).getKey());


        assertEquals(0,this.graphAlgo.shortestPath(4,1).size());
        assertEquals(0,this.graphAlgo.shortestPath(4,2).size());

        assertEquals(1,this.graphAlgo.shortestPath(1,1).size());
        assertEquals(1,this.graphAlgo.shortestPath(2,2).size());
        assertEquals(1,this.graphAlgo.shortestPath(3,3).size());

    }
    @Test
    void center() {
        DefaultForCenter();
       assertEquals(3,this.graphAlgo.center().getKey());

        Default();
        assertEquals(4,this.graphAlgo.center().getKey());

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

}