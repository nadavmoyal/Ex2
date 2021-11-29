package api;

public class MyNode implements NodeData{
    private int key;
    private double weight;
    private int tag;
    private String info;
    private MyGeoLocation location;
//
//    public  MyNode() {
//        this.key=0;
//        this.weight=0;
//        this.tag=0;
//        this.location=new MyGeoLocation(0,0,0);
//        this.info="";
//    }

        public MyNode (MyGeoLocation location, int key, double weight, int tag, String info ){
            this.location = location;
            this.key = key;
            this.weight = weight;
            this.tag = tag;
            this.info = info;
         //NodeData node
//        this.key=node.getKey();
//        this.weight=node.getWeight();
//        this.tag= node.getTag();
//        this.location=new MyGeoLocation(node.getLocation().x(),node.getLocation().y(),node.getLocation().z());
//        this.info=node.getInfo();
    }



        @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location ;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location=new MyGeoLocation(p.x(),p.y(),p.z());

    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight=w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    @Override
    public int getTag() {
        return this.tag ;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }
}
