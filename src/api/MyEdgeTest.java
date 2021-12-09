package api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyEdgeTest {
    private EdgeData e;
    private NodeData n1;
    private NodeData n2;

    public void DefaultEdge() {
        this.n1 = new MyNode(new MyGeoLocation(3, 5, 0), 1, 10, 6, "NADAV");
        this.n2 = new MyNode(new MyGeoLocation(8, 10, 0), 2, 7, 2, "YEHONATHAN");
        this.e = new MyEdge(n1.getKey(),n2.getKey(),12);
    }

    public void ChangeEdge(){
        this.n1 = new MyNode(new MyGeoLocation(2, 2, 0), 4, 1, 2, "NADAVI");
        this.n2 = new MyNode(new MyGeoLocation(4, 5, 0), 5, 3, 1, "YEHONATHANI");
        this.e = new MyEdge(n1.getKey(),n2.getKey(),13);
   }
    private void ChangeTagAndInfo() {
        this.e.setTag(20);
        this.e.setInfo("HAS CHANGED");
    }

    @Test
    void get() {
        DefaultEdge();
            assertEquals(1,e.getSrc());
            assertEquals(2,e.getDest());
            assertEquals(12,e.getWeight());
            assertEquals(-1,e.getTag());
            assertEquals("BLACK",e.getInfo());
        ChangeEdge();
            assertEquals(4,e.getSrc());
            assertEquals(5,e.getDest());
            assertEquals(13,e.getWeight());
            assertEquals(-1,e.getTag());
            assertEquals("BLACK",e.getInfo());
    }

    @Test
    void set() {
        DefaultEdge();
            assertEquals(-1,e.getTag());
            assertEquals("BLACK",e.getInfo());
        ChangeTagAndInfo();
            assertNotEquals(-1,e.getTag());
            assertNotEquals("BLACK",e.getInfo());
            assertEquals(20,e.getTag());
            assertEquals("HAS CHANGED",e.getInfo());
    }


}