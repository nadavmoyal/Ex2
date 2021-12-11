package api;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MyNode implements NodeData{
    private int key;
    private double weight;
    private int tag;
    private String info;
    private MyGeoLocation location;

    public MyNode(MyGeoLocation location, int key) {
        this.location = location;
        this.key = key;
        this.weight = 0;
        this.tag = 0;
        this.info = "";
    }

        public MyNode (MyGeoLocation location, int key, double weight, int tag, String info ){
            this.location = location;
            this.key = key;
            this.weight = weight;
            this.tag = tag;
            this.info = info;

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

class NodeDataRead implements JsonSerializer<NodeData>, JsonDeserializer<NodeData> {
    @Override
    public JsonElement serialize(NodeData node, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pos", node.getLocation().toString());
        jsonObject.addProperty("id", node.getKey());
        return jsonObject;
    }

    @Override
    public NodeData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        int id = jsonElement.getAsJsonObject().get("id").getAsInt();
        MyGeoLocation p = null;
        if (jsonElement.getAsJsonObject().get("pos") != null) {
            String pos = jsonElement.getAsJsonObject().get("pos").getAsString();
            String[] location = pos.split(",");
            p = new MyGeoLocation(Double.parseDouble(location[0]),
                    Double.parseDouble(location[1]), Double.parseDouble(location[2]));
        }
        return new MyNode(p, id);
    }
}
