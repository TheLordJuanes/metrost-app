/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

public interface DirectedWeightedGraphInterface<V>  {

    public boolean dijkstra(Vertex<V> s);

    public boolean bfs(Vertex<V> vertex);

    public void dfs();

    public void addVertex(V value);

    public void deleteVertex(V value);

    public void modifyVertex(V oldValue, V newValue);
}