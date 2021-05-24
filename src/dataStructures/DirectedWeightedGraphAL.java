/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

import java.util.ArrayList;

public class DirectedWeightedGraphAL<V extends Comparable<V>> extends DirectedWeightedGraph<V> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private ArrayList<ArrayList<Vertex<V>>> adjacencyList;
    private ArrayList<Edge<V>> edges;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public DirectedWeightedGraphAL() {
        super();
        edges = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    public ArrayList<Edge<V>> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge<V>> edges) {
        this.edges = edges;
    }

    public ArrayList<ArrayList<Vertex<V>>> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(ArrayList<ArrayList<Vertex<V>>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    protected Edge<V> searchEdge(Vertex<V> v1, Vertex<V> v2) {
        for (Edge<V> edge : edges) {
            if (edge.getSource().equals(v1) && edge.getDestination().equals(v2))
                return edge;
        }
        return null;
    }

    @Override
    public boolean addVertex(V value) {
        if (value == null)
            return false;
        if (checkValue(value)) {
            return false;
        }
        Vertex<V> newVertex = new Vertex<V>(value);
        getVertices().add(newVertex);
        adjacencyList.add(newVertex.getDestinations());
        return true;
    }

    private boolean checkValue(V value) {
        for (int i = 0; i < getVertices().size(); i++) {
            if (getVertices().get(i).getValue().compareTo(value)==0)
                return true;
        }
        return false;
    }

    @Override
    public boolean deleteVertex(V value) {
        for (int i = 0; i < getVertices().size(); i++) {
            if (getVertices().get(i).getValue().compareTo(value) == 0) {
                deleteEdges(getVertices().get(i));
                adjacencyList.remove(i);
                deleteFromAL(getVertices().get(i));
                getVertices().remove(i);
                return true;
            }
        }
        return false;
    }

    private void deleteEdges(Vertex<V> toDelete) {
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getSource().equals(toDelete) || edges.get(i).getDestination().equals(toDelete)){
                edges.remove(i);
                i--;
            }
        }
    }

    private void deleteFromAL(Vertex<V> toDelete){
        for(int index=0; index<adjacencyList.size(); index++){
            ArrayList<Vertex<V>> destinations = adjacencyList.get(index);
            for(int i=0; i<destinations.size(); i++){
                if(destinations.get(i) == toDelete){
                    destinations.remove(i);
                    break;
                }
            }
        }
    }

    @Override
    public boolean addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        if(source == destination || weight<=0){
            return false;
        }
        if(getIndex(source)==-1 || getIndex(destination)==-1){
            return false;
        }
        Edge<V> found = searchEdge(source, destination);
        if (found == null) {
            Edge<V> edge = new Edge<V>(weight, source, destination);
            edges.add(edge);
            source.getDestinations().add(destination);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyVertex(V oldValue, V newValue) {
        int index = getIndex(newValue);
        if(index != -1){
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
    public boolean deleteEdge(Vertex<V> source, Vertex<V> destination) {
        if (source == null || destination == null) {
            return false;
        }
        int indexSource = getIndex(source);
        int indexDestination = getIndex(destination);
        if (indexSource == -1 || indexDestination == -1) {
            return false;
        }
        Edge<V> toDelete = searchEdge(source, destination);
        if (toDelete == null)
            return false;
        return edges.remove(toDelete) && source.getDestinations().remove(destination);
    }

    @Override
    public boolean modifyWeight(Vertex<V> source, Vertex<V> destination, double newWeight) {
        if (source == null || destination == null || newWeight<=0) {
            return false;
        }
        int indexSource = getIndex(source);
        int indexDestination = getIndex(destination);
        if (indexSource == -1 || indexDestination == -1) {
            return false;
        }
        Edge<V> edge = searchEdge(source, destination);
        if(edge==null)
            return false;
        edge.setWeight(newWeight);
        return true;
    }
}