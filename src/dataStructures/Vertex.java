/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 3rd 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

import java.util.ArrayList;

public class Vertex<V> implements Comparable<Vertex<V>> {

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public enum Color {
        WHITE, GRAY, BLACK
    }

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private Color color;
    private V value;
    private double priority;
    private int distance;
    private int discovery;
    private int finalization;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private ArrayList<Vertex<V>> destinations;
    private Vertex<V> parent;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Vertex(V value) {
        color = Color.WHITE;
        this.value = value;
        priority = Double.MAX_VALUE;
        destinations = new ArrayList<Vertex<V>>();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDiscovery() {
        return discovery;
    }

    public void setDiscovery(int discovery) {
        this.discovery = discovery;
    }

    public int getFinalization() {
        return finalization;
    }

    public void setFinalization(int finalization) {
        this.finalization = finalization;
    }

    public Vertex<V> getParent() {
        return parent;
    }

    public void setParent(Vertex<V> parent) {
        this.parent = parent;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public ArrayList<Vertex<V>> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<Vertex<V>> destinations) {
        this.destinations = destinations;
    }

    @Override
    public int compareTo(Vertex<V> o) {
        return (int) (priority - o.getPriority());
    }
}