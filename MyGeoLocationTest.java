package api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyGeoLocationTest {
    private MyGeoLocation GeoArr [] =new MyGeoLocation [4];


    public void DefaultGeo() {
        this.GeoArr[0]= new MyGeoLocation(6, 4, 0);
        this.GeoArr[1]= new MyGeoLocation(4, 8, 0);
        this.GeoArr[2]= new MyGeoLocation(2, 5, 0);
        this.GeoArr[3]= new MyGeoLocation(1, 2, 0);

    }
    public void ChangeGeo() {
        this.GeoArr[0] = new MyGeoLocation(1, 2, 0);
        this.GeoArr[1] = new MyGeoLocation(2, 4, 0);
        this.GeoArr[2] = new MyGeoLocation(3, 6, 0);
        this.GeoArr[3] = new MyGeoLocation(11, 2, 0);
    }
    @Test void GeoData(){
        DefaultGeo();
            assertEquals(6,GeoArr[0].x());
            assertEquals(8,GeoArr[1].y());
            assertEquals(0,GeoArr[2].z());
        ChangeGeo();
            assertEquals(1,GeoArr[0].x());
            assertEquals(4,GeoArr[1].y());
            assertEquals(0,GeoArr[2].z());
    }

    @Test
    void distanceSame() {

        DefaultGeo();
        assertEquals(0,GeoArr[0].distance(GeoArr[0]));
        assertEquals(0,GeoArr[1].distance(GeoArr[1]));
        assertEquals(0,GeoArr[2].distance(GeoArr[2]));

        assertEquals(GeoArr[1].distance(GeoArr[0]),GeoArr[0].distance(GeoArr[1]));
        assertEquals(GeoArr[2].distance(GeoArr[3]),GeoArr[3].distance(GeoArr[2]));
        assertEquals(GeoArr[0].distance(GeoArr[3]),GeoArr[3].distance(GeoArr[0]));

    }
    @Test
    void distance() { // need to check how many num' after that point
        //not working
        DefaultGeo();
        assertEquals(4.47213595499958 ,GeoArr[0].distance(GeoArr[1]));
        assertEquals(3.1622776601683795,GeoArr[3].distance(GeoArr[2]));
        assertEquals(5.385164807134504,GeoArr[3].distance(GeoArr[0]));
    }
}