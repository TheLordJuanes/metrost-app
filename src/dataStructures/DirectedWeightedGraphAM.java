package dataStructures;

import java.util.ArrayList;

public class DirectedWeightedGraphAM<V> implements DirectedWeightedGraphInterface<V>{

    private ArrayList<Vertex<V>> vertices;
    private ArrayList<ArrayList<Edge<V>>> adjacencyMatrix;

    public DirectedWeightedGraphAM() {
        adjacencyMatrix = new ArrayList<ArrayList<Edge<V>>>();
    }

    @Override
    public boolean dijkstra(Vertex<V> s) {
        return false;
    }

    @Override
    public boolean bfs(Vertex<V> vertex) {
        return false;
    }

    @Override
    public void dfs() {

    }

    @Override
    public void addVertex(Object value) {

    }

    @Override
    public void deleteVertex(Object value) {

    }

    @Override
    public void modifyVertex(Object oldValue, Object newValue) {

    }

    /**
     * @return ArrayList<Vertex<V>> return the vertices
     */
    public ArrayList<Vertex<V>> getVertices() {
        return vertices;
    }

    /**
     * @param vertices the vertices to set
     */
    public void setVertices(ArrayList<Vertex<V>> vertices) {
        this.vertices = vertices;
    }

    /**
     * @return ArrayList<ArrayList<Edge<V>>> return the adjacencyMatrix
     */
    public ArrayList<ArrayList<Edge<V>>> getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * @param adjacencyMatrix the adjacencyMatrix to set
     */
    public void setAdjacencyMatrix(ArrayList<ArrayList<Edge<V>>> adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

}