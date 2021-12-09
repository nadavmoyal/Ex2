package api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MyNodeTest {
    private NodeData n;

    public void DefaultNode() {
        this.n = new MyNode(new MyGeoLocation(3, 5, 0), 1, 10, 6, "NADAV");
    }
    public void ChangeNode(){
        this.n = new MyNode(new MyGeoLocation(4,6,0),2,12,5,"YEHONATHAN");
    }
    @org.junit.jupiter.api.Test
    void get() {
    DefaultNode();
        assertEquals(1,n.getKey());
        assertEquals(10,n.getWeight());
        assertEquals(6,n.getTag());
        assertEquals("NADAV",n.getInfo());
        assertEquals(3,n.getLocation().x());
        assertEquals(5,n.getLocation().y());
    ChangeNode();
        assertEquals(2,n.getKey());
        assertEquals(12,n.getWeight());
        assertEquals(5,n.getTag());
        assertEquals("YEHONATHAN",n.getInfo());
        assertEquals(4,n.getLocation().x());
        assertEquals(6,n.getLocation().y());

    }

    @org.junit.jupiter.api.Test
    void set() {
        DefaultNode();
            assertEquals(1,n.getKey());
            assertEquals(10,n.getWeight());
            assertEquals(6,n.getTag());
            assertEquals("NADAV",n.getInfo());
            assertEquals(3,n.getLocation().x());
            assertEquals(5,n.getLocation().y());

            this.n.setWeight(13);
            this.n.setTag(2);
            this.n.setInfo("HAS CHANGED");
            this.n.setLocation(new MyGeoLocation(13,35,0));


            assertNotEquals(10,n.getWeight());
            assertNotEquals(6,n.getTag());
            assertNotEquals("NADAV",n.getInfo());
            assertNotEquals(3,n.getLocation().x());
            assertNotEquals(5,n.getLocation().y());


    }


}