package api;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MyEdge implements EdgeData{
    private int src;
    private int dest;
    private int tag;
    private double weight;
    private String info;


    public MyEdge (int src,int dest, double weight) {
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

class EdgeDataRead implements JsonSerializer<EdgeData>, JsonDeserializer<EdgeData> {

    @Override
    public JsonElement serialize(EdgeData edge, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("src", edge.getSrc());
        jsonObject.addProperty("w", edge.getWeight());
        jsonObject.addProperty("dest", edge.getDest());
        return jsonObject;
    }

    @Override
    public EdgeData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        int src = jsonElement.getAsJsonObject().get("src").getAsInt();
        int dest = jsonElement.getAsJsonObject().get("dest").getAsInt();
        double w = jsonElement.getAsJsonObject().get("w").getAsDouble();
        return new MyEdge(src, dest, w);
    }
}
