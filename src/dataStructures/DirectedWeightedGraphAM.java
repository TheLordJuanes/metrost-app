package dataStructures;

import java.util.ArrayList;

public class DirectedWeightedGraphAM<V extends Comparable<V>> implements DirectedWeightedGraphInterface<V> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private ArrayList<Vertex<V>> vertices;
    private ArrayList<ArrayList<Edge<V>>> adjacencyMatrix;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public DirectedWeightedGraphAM() {
        vertices = new ArrayList<>();
        adjacencyMatrix = new ArrayList<ArrayList<Edge<V>>>();
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

    @Override
    public boolean dijkstra(Vertex<V> s) {
        return false;
    }

    @Override
    public boolean bfs(Vertex<V> vertex) {
        return false;
    }

    @Override
    public boolean dfs() {
        return false;
    }

    @Override
    public boolean addVertex(V value) {
        if (getIndex(value) != -1) {
            return false;
        }
        Vertex<V> vertex = new Vertex<V>(value);
        vertices.add(vertex);
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            adjacencyMatrix.get(i).add(null);
        }
        ArrayList<Edge<V>> tempList = new ArrayList<>();
        for (int i = 0; i <= adjacencyMatrix.size(); i++) {
            tempList.add(null);
        }
        adjacencyMatrix.add(tempList);
        return true;
    }

    private int getIndex(V value) {
        for (int i = 0; i < vertices.size(); i++) {
            if (value.compareTo((vertices.get(i).getValue())) == 0)
                return i;
        }
        return -1;
    }

    @Override
    public boolean addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        int sourceIndex = getIndex(source.getValue());
        int destinationIndex = getIndex(destination.getValue());
        if (sourceIndex == -1 || destinationIndex == -1) {
            return false;
        }
        Edge<V> tempEdge = new Edge<V>(weight, source, destination);
        adjacencyMatrix.get(sourceIndex).set(destinationIndex, tempEdge);
        return true;
    }

    @Override
    public boolean deleteVertex(V value) {
        int index = getIndex(value);
        if(index == -1){
            return false;
        }
        vertices.remove(index);
        adjacencyMatrix.remove(index);
        for(int i=0; i<adjacencyMatrix.size(); i++){
            adjacencyMatrix.get(i).remove(index);
        }
        return true;
    }

    @Override
    public boolean modifyVertex(V oldValue, V newValue) {
        int index = getIndex(oldValue);
        if(index == -1){
            return false;
        }
        vertices.get(index).setValue(newValue);;
        return true;
    }

    private Edge<V> searchEdge(Vertex<V> v1, Vertex<V> v2) {
        return null; //CAMBIARRRRR
    }

    @Override
    public boolean deleteEdge(Vertex<V> source, Vertex<V> destination) {
        return false;
    }
}