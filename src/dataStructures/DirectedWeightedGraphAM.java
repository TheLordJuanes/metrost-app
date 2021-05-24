package dataStructures;

import java.util.ArrayList;

public class DirectedWeightedGraphAM<V extends Comparable<V>> extends DirectedWeightedGraph<V> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private int numEdges;
    private ArrayList<ArrayList<Edge<V>>> adjacencyMatrix;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public DirectedWeightedGraphAM() {
        super();
        adjacencyMatrix = new ArrayList<ArrayList<Edge<V>>>();
        numEdges = 0;
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
    public boolean addVertex(V value) {
        if (value == null)
            return false;
        if (getIndex(value) != -1) {
            return false;
        }
        Vertex<V> vertex = new Vertex<V>(value);
        getVertices().add(vertex);
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

    @Override
    public boolean addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        if (source == null || destination == null || source == destination || weight <= 0) {
            return false;
        }
        int sourceIndex = getIndex(source.getValue());
        int destinationIndex = getIndex(destination.getValue());
        if (sourceIndex == -1 || destinationIndex == -1)
            return false;
        if (adjacencyMatrix.get(sourceIndex).get(destinationIndex) != null) {
            return false;
        }
        Edge<V> tempEdge = new Edge<V>(weight, source, destination);
        adjacencyMatrix.get(sourceIndex).set(destinationIndex, tempEdge);
        numEdges++;
        return true;
    }

    @Override
    public boolean deleteVertex(V value) {
        if (value == null) {
            return false;
        }
        int index = getIndex(value);
        if (index == -1) {
            return false;
        }
        getVertices().remove(index);
        adjacencyMatrix.remove(index);
        for (int i = 0; i < adjacencyMatrix.size(); i++)
            adjacencyMatrix.get(i).remove(index);
        return true;
    }

    @Override
    public boolean modifyVertex(V oldValue, V newValue) {
        if (oldValue == null || newValue == null) {
            return false;
        }
        int index = getIndex(newValue);
        if (index != -1) {
            return false;
        }
        index = getIndex(oldValue);
        if (index == -1) {
            return false;
        }
        getVertices().get(index).setValue(newValue);
        return true;
    }

    @Override
    protected Edge<V> searchEdge(Vertex<V> source, Vertex<V> destination) {
        if (source == null || destination == null) {
            return null;
        }
        int indexSource = getIndex(source.getValue());
        int indexDestination = getIndex(destination.getValue());
        if (indexSource == -1 || indexDestination == -1)
            return null;
        return adjacencyMatrix.get(indexSource).get(indexDestination);
    }

    @Override
    public boolean deleteEdge(Vertex<V> source, Vertex<V> destination) {
        if (source == null || destination == null) {
            return false;
        }
        int indexSource = getIndex(source.getValue());
        int indexDestination = getIndex(destination.getValue());
        if (indexSource == -1 || indexDestination == -1)
            return false;
        if (adjacencyMatrix.get(indexSource).get(indexDestination) == null) {
            return false;
        }
        adjacencyMatrix.get(indexSource).set(indexDestination, null);
        numEdges--;
        return true;
    }

    @Override
    public boolean modifyWeight(Vertex<V> source, Vertex<V> destination, double newWeight) {
        if (source == null || destination == null || newWeight <= 0) {
            return false;
        }
        int indexSource = getIndex(source.getValue());
        int indexDestination = getIndex(destination.getValue());
        if (indexSource == -1 || indexDestination == -1)
            return false;
        if (adjacencyMatrix.get(indexSource).get(indexDestination) == null) {
            return false;
        }
        adjacencyMatrix.get(indexSource).get(indexDestination).setWeight(newWeight);
        return true;
    }

    protected void fillMatrix() {
        for (int i = 0; i < adjacencyMatrix.size(); i++) {
            for (int j = 0; j < adjacencyMatrix.size(); j++) {
                if (adjacencyMatrix.get(i).get(j) != null)
                    getMinDistances()[i][j] = adjacencyMatrix.get(i).get(j).getWeight();
            }
        }
    }

    public int getNumEdges() {
        return numEdges;
    }
}