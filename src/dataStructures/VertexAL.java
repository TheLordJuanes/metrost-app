package dataStructures;

import java.util.ArrayList;

public class VertexAL<V> {

    public enum Color {
        WHITE, GRAY, BLACK
    }

    private Color color;
    private V value;
    private ArrayList<VertexAL<V>> destinations;

    public VertexAL(Color color, V value) {
        this.color = color;
        this.value = value;
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

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public ArrayList<VertexAL<V>> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<VertexAL<V>> destinations) {
        this.destinations = destinations;
    }
}