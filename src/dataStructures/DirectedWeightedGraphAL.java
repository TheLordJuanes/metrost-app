package dataStructures;

import java.util.ArrayList;

public class DirectedWeightedGraphAL<V> {

    private ArrayList<VertexAL<V>> vertices;
    private ArrayList<Edge<V>> edges;

    public DirectedWeightedGraphAL(){
        vertices= new ArrayList<VertexAL<V>>();
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
}