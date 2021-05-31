/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 3rd 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

import java.util.ArrayList;

public interface DirectedWeightedGraphInterface<V> {

    public boolean dijkstra(Vertex<V> s);

    public void floydWarshall();

    public boolean bfs(Vertex<V> vertex);

    public boolean dfs();

    public boolean prim(Vertex<V> r);

    public ArrayList<Edge<V>> kruskal();

    public boolean addVertex(V value);

    public boolean deleteVertex(V value);

    public boolean modifyVertex(V oldValue, V newValue);

    public boolean modifyWeight(Vertex<V> source, Vertex<V> destination, double newWeight);

    public boolean addEdge(Vertex<V> source, Vertex<V> destination, double weight);

    public boolean deleteEdge(Vertex<V> source, Vertex<V> destination);
}