package dataStructures;

public class Vertex<V> {

    public enum Color {
        WHITE, GRAY, BLACK
    }

    private Color color;
    private V value;
}