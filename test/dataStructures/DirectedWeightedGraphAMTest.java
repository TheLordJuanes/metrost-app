package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DirectedWeightedGraphAMTest {

    private DirectedWeightedGraphAM<String> graph;

    public void setup1() {
        graph = new DirectedWeightedGraphAM<String>();
    }

    public void setup2() {
        graph = new DirectedWeightedGraphAM<String>();
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
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("z");
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertEquals(vertices.size(), 6);
        assertEquals(graph.getAdjacencyMatrix().size(), 6);
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(i).size() != 6)
                fail();
        }
        int row = 0;
        for (int i = 0; row < 6; i++) {
            assertNull(graph.getAdjacencyMatrix().get(row).get(i));
            if (i == 5) {
                row++;
                i = -1;
            }
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
        assertEquals(graph.getAdjacencyMatrix().size(), 6);
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(i).size() != 6)
                fail();
        }

        setup2();
        assertTrue(graph.deleteVertex("d"));
        assertEquals(graph.getVertices().size(), 5);
        assertEquals(graph.getAdjacencyMatrix().size(), 5);
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(i).size() != 5)
                fail();
        }
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertEquals(vertices.get(0).getValue(), "a");
        assertEquals(vertices.get(1).getValue(), "b");
        assertEquals(vertices.get(2).getValue(), "c");
        assertEquals(vertices.get(3).getValue(), "e");
        assertEquals(vertices.get(4).getValue(), "z");

        //setup3();
        assertTrue(graph.deleteVertex("a"));
        vertices = graph.getVertices();
        assertEquals(vertices.get(0).getValue(), "b");
        assertEquals(vertices.get(1).getValue(), "c");
        assertEquals(vertices.get(2).getValue(), "d");
        assertEquals(vertices.get(3).getValue(), "e");
        assertEquals(vertices.get(4).getValue(), "z");
        assertEquals(graph.getVertices().size(), 5);
        assertEquals(graph.getAdjacencyMatrix().size(), 5);
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(i).size() != 5)
                fail();
        }
        /*int numEdges = 0;
        for(int row=0; row<6; row++){
            for (int i = 0; i < 6; i++) {
            if(graph.getAdjacencyMatrix().get(row).get(i)!=null){
                numEdges++;
            }
        }
        }
        //COMPLETA

        assertEquals(numEdges, graph.getEdges().size());
        assertEquals(2, graph.getAdjacencyList().get(0).size());
        for (int i = 0; i < 2; i++) {
            if (graph.getAdjacencyList().get(0).get(i).getValue().compareTo("a") == 0) {
                fail();
            }
        }
        assertEquals(3, graph.getAdjacencyList().get(1).size());
        for (int i = 0; i < 3; i++) {
            if (graph.getAdjacencyList().get(1).get(i).getValue().compareTo("a") == 0) {
                fail();
            }
        }
        assertEquals(4, graph.getAdjacencyList().get(2).size());
        assertEquals(3, graph.getAdjacencyList().get(3).size());
        assertEquals(2, graph.getAdjacencyList().get(4).size());*/
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

    @Test
    public void testDijkstra() {

    }

    @Test
    public void testBfs() {

    }

    @Test
    public void testDfs() {

    }
}