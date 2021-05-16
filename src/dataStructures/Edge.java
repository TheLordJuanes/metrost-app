/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

public class Edge<V> implements Comparable<Edge<V>> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private double weight;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private Vertex<V> source;
    private Vertex<V> destination;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Edge(double weight, Vertex<V> source, Vertex<V> destination) {
        this.weight = weight;
        this.source = source;
        this.destination = destination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex<V> getSource() {
        return source;
    }

    public void setSource(Vertex<V> source) {
        this.source = source;
    }

    public Vertex<V> getDestination() {
        return destination;
    }

    public void setDestination(Vertex<V> destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Edge From: " + source.getValue().toString() + " to: " + destination.getValue().toString() + " distance: " + weight;
    }

    @Override
    public int compareTo(Edge<V> o) {
        return (int) weight - (int) o.getWeight();
    }
}