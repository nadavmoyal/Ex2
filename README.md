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
##### This interface represents a geo location <x,y,z>, (aka Point3D data).

|          Methods                | Details                             | 
| --------------------------------|:--------------------------------------:| 
| distance(GeoLocation g)         | Returns the distance between 2 points  | 
| MyGeoLocation()                 | An empty constractor of a new GeoLocation.| 
| MyGeoLocation(GeoLocation g)    | A constractor of a new GeoLocation       |   

## class MyNode implements NodeData:
##### This interface represents the set of operations applicable on a node (vertex) in a (directional) weighted graph.

|          Methods                | Details                             | 
| --------------------------------|:--------------------------------------:| 
| getKey()        | Returns the key (id) associated with this node.  | 
| getLocation()                 | Returns the location of this node, if none return null.| 
| setLocation(GeoLocation p)    | Allows changing this node's location.      |  
| getWeight()                 | Returns the weight associated with this node.| 
| SetWeight(double w)           |Allows changing this node's weight.      | 
| getInfo()                      |  Returns the remark (meta data) associated with this node. | 
| setInfo(String s)               | Allows changing the remark (meta data) associated with this node.| 
| getTag()                       | Returns the tag associated with this node.       |   
|   setTag(int t)                 |  Allows setting the "tag" value for temporal marking an node-common practice for marking by algorithms.|


## class MyEdge implements EdgeData:
##### This interface represents the set of operations applicable on a directional edge (src,dest) in a (directional) weighted graph.
                                 
|          Methods                | Details                             | 
| --------------------------------|:--------------------------------------:| 
| getKey()        | Returns the key (id) associated with this node.  | 
| getLocation()                 | Returns the location of this node, if none return null.| 
| setLocation(GeoLocation p)    | Allows changing this node's location.      |  
| getWeight()                 | Returns the weight associated with this node.| 
| SetWeight(double w)           |Allows changing this node's weight.      | 
| getInfo()                      |  Returns the remark (meta data) associated with this node. | 
| setInfo(String s)               | Allows changing the remark (meta data) associated with this node.| 
| getTag()                       | Returns the tag associated with this node.       |   
|   setTag(int t)                 |  Allows setting the "tag" value for temporal marking an node-common practice for marking by algorithms.|




| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |
| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |
| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |
| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |
