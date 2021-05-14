package dataStructures;

public class VertexAM<V> {

    public enum Color {
        WHITE, GRAY, BLACK
    }

    private Color color;
    private V value;

    public VertexAM(Color color, V value ){
        this.color=color;
        this.value=value;
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

    public boolean isWhite(){
        return color==Color.WHITE;
    }
}