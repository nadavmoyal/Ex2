package api;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;

class DW_GraphTest {
    private  DirectedWeightedGraph MyGraph;
    NodeData n1,n2,n3,n4;


    public  void Default() {
        this.MyGraph = new DW_Graph();
        this.n1 = new MyNode(new MyGeoLocation(0, 0, 0), 1, 5, -1, "NADAV");
        this.n2 = new MyNode(new MyGeoLocation(1, 1, 0), 2, 3, -1, "MOYAL");
        this.n3 = new MyNode(new MyGeoLocation(3, 3, 0), 3, 2, -1, "YEHONATHAN");
        this.n4 = new MyNode(new MyGeoLocation(5, 7, 0), 4, 6, -1, "BAREL");
        this.MyGraph.addNode(n1);
        this.MyGraph.addNode(n2);
        this.MyGraph.addNode(n3);
        this.MyGraph.addNode(n4);
        this.MyGraph.connect(2,3,4);
        this.MyGraph.connect(1,3,11);
        this.MyGraph.connect(1,4,23);

    }


    @Test
    void getNode() {
        Default();
        assertEquals(n1,this.MyGraph.getNode(n1.getKey()));
        assertNull(MyGraph.getNode(7));
    }

    @Test
    void getAndRemoveEdge() {
        Default();
        assertNull(MyGraph.getEdge(4,3));
        assertNull(MyGraph.getEdge(4,1));

        this.MyGraph.connect(4,3,5);
        this.MyGraph.connect(4,1,17);

        assertNotNull(MyGraph.getEdge(4,3));
        assertNotNull(MyGraph.getEdge(4,1));

        this.MyGraph.removeEdge(4,3);
        this.MyGraph.removeEdge(4,1);

        assertNull(MyGraph.getEdge(4,3));
        assertNull(MyGraph.getEdge(4,1));
    }

    @Test
    void addNode() {
        DirectedWeightedGraph OtherGraph = new DW_Graph();
            assertEquals(0,OtherGraph.nodeSize());
        NodeData curr = new MyNode(new MyGeoLocation(1, 5, 0), 7, 2, 5, "test");
        OtherGraph.addNode(curr);
            assertEquals(1,OtherGraph.nodeSize());
    }

    @Test
    void connectHimself() {
           Default();
        assertEquals(4,MyGraph.nodeSize());
           MyGraph.connect(1,1,2);
           MyGraph.connect(2,2,34);
           MyGraph.connect(3,3,11);
           MyGraph.connect(4,4,1);
        assertEquals(4,MyGraph.nodeSize());

    }
    @Test
    void connectChangeWeight() {
        Default();
        assertEquals(23,MyGraph.getEdge(1,4).getWeight());
        assertEquals(4,MyGraph.getEdge(2,3).getWeight());
        MyGraph.connect(1,4,12);
        MyGraph.connect(2,3,16);
        assertEquals(16,MyGraph.getEdge(2,3).getWeight());
    }


    @Test
    void nodeIter() {
           Default();
            NodeData n;
           assertEquals(-1,MyGraph.getNode(1).getTag());
           assertEquals(-1,MyGraph.getNode(2).getTag());
           assertEquals(-1,MyGraph.getNode(3).getTag());
           assertEquals(-1,MyGraph.getNode(4).getTag());
     Iterator <NodeData> it = MyGraph.nodeIter();
     while(it.hasNext()){
        n=it.next();
        n.setTag(1);
        }
        assertEquals(1,MyGraph.getNode(1).getTag());
        assertEquals(1,MyGraph.getNode(2).getTag());
        assertEquals(1,MyGraph.getNode(3).getTag());
        assertEquals(1,MyGraph.getNode(4).getTag());
    }

    @Test
    void edgeIter() {
        Default();
        EdgeData n;
        assertEquals(-1,MyGraph.getEdge(1,3).getTag());
        assertEquals(-1,MyGraph.getEdge(2,3).getTag());
        assertEquals(-1,MyGraph.getEdge(1,4).getTag());
        assertEquals("BLACK",MyGraph.getEdge(1,4).getInfo());
        assertEquals("BLACK",MyGraph.getEdge(2,3).getInfo());
        assertEquals("BLACK",MyGraph.getEdge(1,3).getInfo());

        Iterator <EdgeData> itE = MyGraph.edgeIter();
        while(itE.hasNext()){
            n = itE.next();
            n.setTag(1);
            n.setInfo("has changed");
        }

        assertEquals(1,MyGraph.getEdge(1,3).getTag());
        assertEquals(1,MyGraph.getEdge(2,3).getTag());
        assertEquals(1,MyGraph.getEdge(1,4).getTag());
        assertEquals("has changed",MyGraph.getEdge(1,4).getInfo());
        assertEquals("has changed",MyGraph.getEdge(2,3).getInfo());
        assertEquals("has changed",MyGraph.getEdge(1,3).getInfo());
    }

    @Test
    void testEdgeIter() {
        Default();
        EdgeData n;

        NodeData node_e = new MyNode(new MyGeoLocation(0, 0, 0), 1, 5, -1, "NADAV");

        assertEquals(-1,MyGraph.getEdge(1,3).getTag());
        assertEquals(-1,MyGraph.getEdge(1,4).getTag());
        assertEquals("BLACK",MyGraph.getEdge(1,3).getInfo());
        assertEquals("BLACK",MyGraph.getEdge(1,4).getInfo());

        Iterator <EdgeData> itE = MyGraph.edgeIter(node_e.getKey());
        while(itE.hasNext()){
            n = itE.next();
            n.setTag(17);
            n.setInfo("nadav and yehonathan");
        }
        assertEquals(17,MyGraph.getEdge(1,3).getTag());
        assertEquals(17,MyGraph.getEdge(1,4).getTag());
        assertEquals("nadav and yehonathan",MyGraph.getEdge(1,3).getInfo());
        assertEquals("nadav and yehonathan",MyGraph.getEdge(1,4).getInfo());
    }

    @Test
    void removeNode() {
        Default();
        assertEquals(4,MyGraph.nodeSize());
        MyGraph.removeNode(n1.getKey());
        MyGraph.removeNode(n2.getKey());
        MyGraph.removeNode(n3.getKey());
        assertEquals(1,MyGraph.nodeSize());



    }

    @Test
    void removeEdge() {
        Default();
        assertEquals(7,MyGraph.getMC());
        assertEquals(3,MyGraph.edgeSize());
        MyGraph.removeEdge(1,4);
        MyGraph.removeEdge(2,3);
        assertEquals(1,MyGraph.edgeSize());
        MyGraph.connect(1,4,5);
        MyGraph.connect(2,3,13);
        assertEquals(11,MyGraph.getMC());

    }
}