package api;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MyEdge implements EdgeData{
    private int src;
    private int dest;
    private int tag;
    private double weight;
    private String info;

//    public MyEdge (){   // NEED TO FIX
//         this.src=0;
//         this.dest=0;
//         this.tag=0;
//         this.weight=0;
//         this.info="";
//
//    }

    public MyEdge (int src,int dest, double weight) {   // NEED TO FIX
        this.src = src;
        this.weight = weight;
        this.dest = dest;
        this.tag = -1;
        this.info = "BLACK";

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

class EdgeDataAdapter implements JsonSerializer<EdgeData>, JsonDeserializer<EdgeData> {

    @Override
    public JsonElement serialize(EdgeData edge, Type type, JsonSerializationContext jsonSerializationContext) {
//      write edge to a json
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("src", edge.getSrc());
        jsonObject.addProperty("w", edge.getWeight());
        jsonObject.addProperty("dest", edge.getDest());
        return jsonObject;
    }

    @Override
    public EdgeData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//      read edge from a json
        int src = jsonElement.getAsJsonObject().get("src").getAsInt();
        int dest = jsonElement.getAsJsonObject().get("dest").getAsInt();
        double w = jsonElement.getAsJsonObject().get("w").getAsDouble();
        return new MyEdge(src, dest, w);
    }
}
