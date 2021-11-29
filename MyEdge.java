package api;

public class MyEdge implements EdgeData{
    private int src;
    private int dest;
    private int tag;
    private double weight;
    private String info;

    public MyEdge (){   // NEED TO FIX
         this.src=0;
         this.dest=0;
         this.tag=0;
         this.weight=0;
         this.info="";

    }

    public MyEdge (int src,int dest, double weight) {   // NEED TO FIX
        this.src = src;
        this.weight = weight;
        this.dest = dest;
//        this.tag = ed.getTag();
//        this.info = ed.getInfo();

    }
    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }
}
