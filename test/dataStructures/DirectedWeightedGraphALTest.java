package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class DirectedWeightedGraphALTest {

    private DirectedWeightedGraphAL<String> graph;

    public void setup1() {
        graph = new DirectedWeightedGraphAL<String>();
    }

    public void setup2() {
        graph = new DirectedWeightedGraphAL<String>();
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("z");
    }

    public void setup3() {
        graph = new DirectedWeightedGraphAL<String>();
        graph.addVertex("a"); // 0
        graph.addVertex("b"); // 1
        graph.addVertex("c"); // 2
        graph.addVertex("d"); // 3
        graph.addVertex("e"); // 4
        graph.addVertex("z"); // 5
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        graph.addEdge(vertices.get(0), vertices.get(1), 4);
        graph.addEdge(vertices.get(1), vertices.get(0), 4);

        graph.addEdge(vertices.get(0), vertices.get(2), 2);
        graph.addEdge(vertices.get(2), vertices.get(0), 2);

        graph.addEdge(vertices.get(2), vertices.get(1), 1);
        graph.addEdge(vertices.get(1), vertices.get(2), 1);

        graph.addEdge(vertices.get(2), vertices.get(4), 10);
        graph.addEdge(vertices.get(4), vertices.get(2), 10);

        graph.addEdge(vertices.get(2), vertices.get(3), 8);
        graph.addEdge(vertices.get(3), vertices.get(2), 8);

        graph.addEdge(vertices.get(1), vertices.get(3), 5);
        graph.addEdge(vertices.get(3), vertices.get(1), 5);

        graph.addEdge(vertices.get(4), vertices.get(3), 2);
        graph.addEdge(vertices.get(3), vertices.get(4), 2);

        graph.addEdge(vertices.get(3), vertices.get(5), 6);
        graph.addEdge(vertices.get(5), vertices.get(3), 6);

        graph.addEdge(vertices.get(4), vertices.get(5), 3);
        graph.addEdge(vertices.get(5), vertices.get(4), 3);
    }

    @Test
    public void testAddVertex() {
        setup1();
        graph.addVertex("a"); // 0
        graph.addVertex("b"); // 1
        graph.addVertex("c"); // 2
        graph.addVertex("d"); // 3
        graph.addVertex("e"); // 4
        graph.addVertex("z"); // 5
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertEquals(vertices.size(), 6);
        assertEquals(graph.getAdjacencyList().size(), 6);
        assertEquals(graph.getEdges().size(), 0);
        for (int i = 0; i < 5; i++) {
            assertEquals(graph.getAdjacencyList().get(i).size(), 0);
        }
        assertEquals(vertices.get(0).getValue(), "a");
        assertEquals(vertices.get(1).getValue(), "b");
        assertEquals(vertices.get(2).getValue(), "c");
        assertEquals(vertices.get(3).getValue(), "d");
        assertEquals(vertices.get(4).getValue(), "e");
        assertEquals(vertices.get(5).getValue(), "z");
    }

    @Test
    public void testDeleteVertex() {
        setup2();
        assertFalse(graph.deleteVertex("m"));
        assertEquals(graph.getVertices().size(), 6);
        assertEquals(graph.getAdjacencyList().size(), 6);
        assertTrue(graph.deleteVertex("d"));
        assertEquals(graph.getVertices().size(), 5);
        assertEquals(graph.getAdjacencyList().size(), 5);
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertEquals(vertices.get(0).getValue(), "a");
        assertEquals(vertices.get(1).getValue(), "b");
        assertEquals(vertices.get(2).getValue(), "c");
        assertEquals(vertices.get(3).getValue(), "e");
        assertEquals(vertices.get(4).getValue(), "z");
        for (int i = 0; i <= 4; i++) {
            assertEquals(graph.getAdjacencyList().get(i).size(), 0);
        }

        setup3();
        assertTrue(graph.deleteVertex("a"));
        vertices = graph.getVertices();
        assertEquals(vertices.get(0).getValue(), "b");
        assertEquals(vertices.get(1).getValue(), "c");
        assertEquals(vertices.get(2).getValue(), "d");
        assertEquals(vertices.get(3).getValue(), "e");
        assertEquals(vertices.get(4).getValue(), "z");
        assertEquals(graph.getVertices().size(), 5);
        assertEquals(graph.getAdjacencyList().size(), 5);
        assertEquals(14, graph.getEdges().size());
        assertEquals(2, graph.getAdjacencyList().get(0).size());
        for (int i = 0; i < 2; i++) {
            if (graph.getAdjacencyList().get(0).get(i).getValue().compareTo("a") == 0) {
                fail(); // TERMINARRR
            }
        }
        assertEquals(3, graph.getAdjacencyList().get(0).size());
        // TERMINARRR
    }

    @Test
    public void testAddEdge() {

    }

    @Test
    public void testModifyVertex() {

    }

    @Test
    public void testDeleteEdge() {

    }

    @Test
    public void testModifyWeight() {

    }
}
