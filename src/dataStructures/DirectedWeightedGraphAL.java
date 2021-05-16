/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

import java.util.ArrayList;

public class DirectedWeightedGraphAL<V> implements DirectedWeightedGraphInterface{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private ArrayList<VertexAL<V>> vertices;
    private ArrayList<Edge<V>> edges;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public DirectedWeightedGraphAL() {
        vertices = new ArrayList<VertexAL<V>>();
    }

    public ArrayList<VertexAL<V>> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<VertexAL<V>> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Edge<V>> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge<V>> edges) {
        this.edges = edges;
    }

    @Override
    public void dijkstra() {

    }

    @Override
    public void bfs() {

    }
}