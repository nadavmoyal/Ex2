![This is an image](https://upload.wikimedia.org/wikipedia/commons/e/e2/Ariel_University_Logo.pdf)


# Ex2
>Made by Yehonatan Barel and Nadav Moyal.
>
>GitHub pages: 
>
>https://github.com/nadavmoyal
>
>https://github.com/yehonatanbarel    

## Introduction:
This project is an assignment in an object-oriented course at Ariel University. The project consists of two parts: The first part is an implenentation of directed weighted graph and consist ___ classes, ___ interfaces with __ implementations and another class belonging to the second part of the assignment that we will detail below. 

# Description of the classes:

## class MyGeoLocation implements GeoLocation:
##### This class represents a geo location <x,y,z>, (aka Point3D data).

|          Methods                | Details                             | 
| --------------------------------|:--------------------------------------:| 
|`distance(GeoLocation g)`|Returns the distance between 2 points| 
|`MyGeoLocation()`|An empty constractor of a new GeoLocation.| 
|`MyGeoLocation(GeoLocation g)`|A constractor of a new GeoLocation|   

## class MyNode implements NodeData:
##### This class represents the set of operations applicable on a node (vertex) in a (directional) weighted graph.

|          Methods                | Details                             | 
| --------------------------------|:--------------------------------------:| 
|`getKey()`|Returns the key (id) associated with this node.| 
|`getLocation()`|Returns the location of this node, if none return null.| 
|`setLocation(GeoLocation p)`|Allows changing this node's location.|  
|`getWeight()`|Returns the weight associated with this node.| 
|`SetWeight(double w)`|Allows changing this node's weight.| 
|`getInfo()`|Returns the remark (meta data) associated with this node.| 
|`setInfo(String s)`|Allows changing the remark (meta data) associated with this node.| 
|`getTag()`|Returns the tag associated with this node.|   
|`setTag(int t)`|Allows setting the "tag" value for temporal marking an node-common practice for marking by algorithms.|


## class MyEdge implements EdgeData:
##### This class represents the set of operations applicable on a directional edge (src,dest) in a (directional) weighted graph.
                                 
|          Methods                | Details                             | 
| --------------------------------|:--------------------------------------:| 
|`int getSrc()`|The id of the source node of this edge.| 
|`getDest()`|The id of the destination node of this edge|
|`getWeight()`|return the weight of this edge (positive value).| 
|`getInfo()`|Returns the remark (meta data) associated with this edge.| 
|`setInfo(String s)`|Allows changing the remark (meta data) associated with this edge.| 
|`getTag()`|Returns the tag associated with this edge .|   
|`setTag(int t)`|Allows setting the "tag" value for temporal marking an edge -common practice for marking by algorithms.|

## class  DW_Graph implements DirectedWeightedGraph:
##### This class represents a Directional Weighted Graph.
 
|          Methods                | Details                             | 
| --------------------------------|:--------------------------------------:| 
|`getNode(int key)`|Returns the node_data by the node_id.| 
|`getEdge(int src, int dest)`|Returns the data of the edge (src,dest), null if none,this method run in O(1) time.|
|`addNode(NodeData n)`|Adds a new node to the graph with the given node_data,this method run in O(1) time.| 
|`connect(int src, int dest, double w)`|Connects an edge with weight w between node src to node dest, this method run in O(1) time.| 
|`nodeIter()`|Returns an Iterator for the collection representing all the nodes in the graph.| 
|`edgeIter()`|Returns an Iterator for all the edges in this graph.|   
|`edgeIter(int node_id)`| returns an Iterator for edges getting out of the given node.|
|`removeNode(int key)`|Deletes the node (with the given ID) from the graph-and removes all edges which starts or ends at this node.| 
|`removeEdge(int src, int dest)`|Deletes the edge from the graph, this method run in O(1) time.| 
|`nodeSize()`|Returns the number of vertices (nodes) in the graph,this method run in O(1) time.|   
|`edgeSize()`|Returns the number of edges (assume directional graph),this method run in O(1) time.|
|`getMC()`|Returns the Mode Count - for testing changes in the graph.|


## class MyDWGraphAlgo implements DirectedWeightedGraphAlgorithms:
##### This class represents a Directed (positive) Weighted Graph Theory Algorithms.
 
|          Methods                | Details                             | 
| --------------------------------|:--------------------------------------:| 
|`init(DirectedWeightedGraph g)`|Inits the graph on which this set of algorithms operates on.| 
|`DirectedWeightedGraph getGraph()`|Returns the underlying graph of which this class works.|
|`copy()`|Computes a deep copy of this weighted graph.| 
|`isConnected()`|Returns true if and only if (iff) there is a valid path from each node to each other node.| 
|`shortestPathDist(int src, int dest)`|Computes the length of the shortest path between src to dest.| 
|`shortestPath(int src, int dest)`|Computes the the shortest path between src to dest - as an ordered List of nodes|   
|`center()`|Finds the NodeData which minimizes the max distance to all the other nodes.|
|`tsp(List<NodeData> cities)`|Computes a list of consecutive nodes which go over all the nodes in cities.| 
|`save(String file)`|Saves this weighted (directed) graph to the given file name - in JSON format| 
|`load(String file)`|This method loads a graph to this graph algorithm.|   


   
