package api;

public class MyGeoLocation implements GeoLocation{
    private double x;
    private double y;
    private double z;

    public MyGeoLocation(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public MyGeoLocation(GeoLocation g) {
        this.x=g.x();
        this.y=g.y();
        this.z=g.z();
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(GeoLocation g) {
        double X= Math.pow(this.x-g.x(),2);
        double Y = Math.pow(this.y-g.y(),2);
        double Z = Math.pow(this.z-g.z(),2);
        double dist = Math.sqrt(X+Y+Z);
        return dist;

    }
}
