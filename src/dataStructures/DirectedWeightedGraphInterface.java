/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

public interface DirectedWeightedGraphInterface<V> {

    public boolean dijkstra(Vertex<V> s);

    // public boolean mst();

    public boolean bfs(Vertex<V> vertex);

    public boolean dfs();

    public boolean addVertex(V value);

    public boolean deleteVertex(V value);

    public boolean modifyVertex(V oldValue, V newValue);

    public boolean modifyWeight(Vertex<V> source, Vertex<V> destination, double newWeight);

    public boolean addEdge(Vertex<V> source, Vertex<V> destination, double weight);

    public boolean deleteEdge(Vertex<V> source, Vertex<V> destination);
}