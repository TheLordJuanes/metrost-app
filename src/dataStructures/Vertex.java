/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

import java.util.ArrayList;

public class Vertex<V> implements Comparable<Vertex<V>>{

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
    private ArrayList<Vertex<V>> destinations;
    private double priority;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Vertex(Color color, V value) {
        this.color = color;
        this.value = value;
        priority=Double.MAX_VALUE;
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
        this.priority= priority;
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