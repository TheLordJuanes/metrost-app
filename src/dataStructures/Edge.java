/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

public class Edge<V> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private double weight;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private VertexInterface<V> source;
    private VertexInterface<V> destination;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Edge(double weight, VertexInterface<V> source, VertexInterface<V> destination) {
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

    public VertexInterface<V> getSource() {
        return source;
    }

    public void setSource(VertexInterface<V> source) {
        this.source = source;
    }

    public VertexInterface<V> getDestination() {
        return destination;
    }

    public void setDestination(VertexInterface<V> destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Edge From: " + source.getValue().toString() + " to: " + destination.getValue().toString() + " distance: " + weight;
    }
}