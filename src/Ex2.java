import api.DirectedWeightedGraphAlgorithms;
import api.DirectedWeightedGraph;
import api.*;

import java.util.Arrays;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph graph = null;
        DirectedWeightedGraphAlgorithms GraphAlgo = new MyDWGraphAlgo();
        GraphAlgo.load(json_file);
        graph = GraphAlgo.getGraph();
        return graph;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraph graph = getGrapg(json_file);
        DirectedWeightedGraphAlgorithms GraphAlgo = new MyDWGraphAlgo();
        GraphAlgo.init(graph);
        return GraphAlgo;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new NewFrame(json_file);
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            runGUI(args[0]);
        } else {
            System.out.println("No json file entered");
    }
}
}