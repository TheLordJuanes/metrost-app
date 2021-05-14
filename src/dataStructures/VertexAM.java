/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

public class VertexAM<V> {

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

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public VertexAM(Color color, V value) {
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
}