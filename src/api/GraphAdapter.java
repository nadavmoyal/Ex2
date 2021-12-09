package api;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Iterator;

public class GraphAdapter implements JsonSerializer<DirectedWeightedGraph>, JsonDeserializer<DirectedWeightedGraph> {
    NodeDataAdapter nodeDataAdapter = new NodeDataAdapter();
    EdgeDataAdapter edgeDataAdapter = new EdgeDataAdapter();

    @Override
    public JsonElement serialize(DirectedWeightedGraph graph, Type type, JsonSerializationContext jsonSerializationContext) {
//      write graph to a json
        JsonArray edgesArray = new JsonArray();
        JsonArray nodesArray = new JsonArray();
        for (Iterator<NodeData> it = graph.nodeIter(); it.hasNext(); ) {
            NodeData node = it.next();
            nodesArray.add(nodeDataAdapter.serialize(node, type, jsonSerializationContext));
            for (Iterator<EdgeData> iter = graph.edgeIter(node.getKey()); iter.hasNext(); ) {
                EdgeData edge = iter.next();
                edgesArray.add(edgeDataAdapter.serialize(edge, type, jsonSerializationContext));
            }
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("Edges", edgesArray);
        jsonObject.add("Nodes", nodesArray);
        return jsonObject;
    }

    @Override
    public DirectedWeightedGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//      read graph to a json
        DirectedWeightedGraph graph = new DW_Graph();
        JsonArray nodesArray = jsonElement.getAsJsonObject().getAsJsonArray("Nodes");
        JsonArray edgesArray = jsonElement.getAsJsonObject().getAsJsonArray("Edges");
        for (JsonElement na : nodesArray) {
            graph.addNode(nodeDataAdapter.deserialize(na, type, jsonDeserializationContext));
        }
        for (JsonElement ea : edgesArray) {
            int src = ea.getAsJsonObject().get("src").getAsInt();
            int dest = ea.getAsJsonObject().get("dest").getAsInt();
            double w = ea.getAsJsonObject().get("w").getAsDouble();
            graph.connect(src, dest, w);
        }
        return graph;
    }
}