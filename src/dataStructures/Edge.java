package dataStructures;

public class Edge<V> {

    private double weight;

    private VertexAL<V> source;
    private VertexAL<V> destination;

    public Edge(double weight, VertexAL<V> source, VertexAL<V> destination) {
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

    public VertexAL<V> getSource() {
        return source;
    }

    public void setSource(VertexAL<V> source) {
        this.source = source;
    }

    public VertexAL<V> getDestination() {
        return destination;
    }

    public void setDestination(VertexAL<V> destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Edge From: " + source.getValue().toString() + " to: " + destination.getValue().toString() + " distance: " + weight;
    }
}