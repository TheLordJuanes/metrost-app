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
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("z");
    }

    public void setup3() {
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
        for(int row=0; row<6; row++){
            for (int i = 0; i < 6; i++) {
                assertNull(graph.getAdjacencyMatrix().get(row).get(i));
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
        for (int i = 0; i < 5; i++) {
            if (graph.getAdjacencyMatrix().get(i).size() != 5)
                fail();
        }
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertEquals(vertices.get(0).getValue(), "a");
        assertEquals(vertices.get(1).getValue(), "b");
        assertEquals(vertices.get(2).getValue(), "c");
        assertEquals(vertices.get(3).getValue(), "e");
        assertEquals(vertices.get(4).getValue(), "z");

        setup3();
        assertTrue(graph.deleteVertex("a"));
        vertices = graph.getVertices();
        assertEquals(vertices.get(0).getValue(), "b");
        assertEquals(vertices.get(1).getValue(), "c");
        assertEquals(vertices.get(2).getValue(), "d");
        assertEquals(vertices.get(3).getValue(), "e");
        assertEquals(vertices.get(4).getValue(), "z");
        assertEquals(graph.getVertices().size(), 5);
        assertEquals(graph.getAdjacencyMatrix().size(), 5);
        for (int i = 0; i < 5; i++) {
            if (graph.getAdjacencyMatrix().get(i).size() != 5)
                fail();
        }
        int numEdges = 0;
        for(int row=0; row<5; row++){
            for (int i = 0; i < 5; i++) {
                if(graph.getAdjacencyMatrix().get(row).get(i)!=null){
                    numEdges++;
                }
            }
        }
        assertEquals(14, numEdges);
        numEdges = 0;
        for(int i=0; i<5; i++){
            if(graph.getAdjacencyMatrix().get(0).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(2, numEdges);
        numEdges = 0;
        for(int i=0; i<5; i++){
            if(graph.getAdjacencyMatrix().get(1).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(3, numEdges);
        numEdges = 0;
        for(int i=0; i<5; i++){
            if(graph.getAdjacencyMatrix().get(2).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(4, numEdges);
        numEdges = 0;
        for(int i=0; i<5; i++){
            if(graph.getAdjacencyMatrix().get(3).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(3, numEdges);
        numEdges = 0;
        for(int i=0; i<5; i++){
            if(graph.getAdjacencyMatrix().get(4).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(2, numEdges);
    }

    @Test
    public void testAddEdge() {
        setup2();
        Vertex<String> tempV = new Vertex<>("h");
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertFalse(graph.addEdge(vertices.get(0), tempV, 7.9));
        assertFalse(graph.addEdge(vertices.get(0), vertices.get(0), 10));
        assertFalse(graph.addEdge(vertices.get(1), vertices.get(0), -1));
        assertFalse(graph.addEdge(vertices.get(2), vertices.get(1), 0));

        assertTrue(graph.addEdge(vertices.get(0), vertices.get(1), 10));
        assertFalse(graph.addEdge(vertices.get(0), vertices.get(1), 10));

        assertTrue(graph.addEdge(vertices.get(1), vertices.get(2), 5));
        assertTrue(graph.addEdge(vertices.get(2), vertices.get(3), 2));
        assertTrue(graph.addEdge(vertices.get(3), vertices.get(4), 7));
        assertTrue(graph.addEdge(vertices.get(4), vertices.get(5), 12));
        assertTrue(graph.addEdge(vertices.get(5), vertices.get(0), 13));
        assertTrue(graph.addEdge(vertices.get(5), vertices.get(3), 16));

        assertEquals(7, graph.getNumEdges());
        int numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(0).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(1).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(2).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(3).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(4).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(5).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(2, numEdges);

        Edge<String> temp = graph.getAdjacencyMatrix().get(3).get(4);
        assertEquals("d", temp.getSource().getValue());
        assertEquals("e", temp.getDestination().getValue());
        assertEquals(7, temp.getWeight());
    }

    @Test
    public void testModifyVertex() {
        setup2();
        assertFalse(graph.modifyVertex("a", "b"));
        assertTrue(graph.modifyVertex("b", "f"));
        assertTrue(graph.modifyVertex("a", "b"));
        assertEquals("b", graph.getVertices().get(0).getValue());
        assertEquals("f", graph.getVertices().get(1).getValue());

        setup3();
        assertFalse(graph.modifyVertex("z", "c"));
        assertTrue(graph.modifyVertex("z", "f"));
    }

    @Test
    public void testDeleteEdge() {
        setup3();
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertFalse(graph.deleteEdge(vertices.get(0), vertices.get(5)));
        assertFalse(graph.deleteEdge(vertices.get(3), null));
        Vertex<String> temp = new Vertex<>("h");
        assertFalse(graph.deleteEdge(vertices.get(4), temp));
        assertTrue(graph.deleteEdge(vertices.get(0), vertices.get(1)));
        assertFalse(graph.deleteEdge(vertices.get(0), vertices.get(1)));
        assertTrue(graph.deleteEdge(vertices.get(0), vertices.get(2)));
        assertTrue(graph.deleteEdge(vertices.get(1), vertices.get(2)));
        assertEquals(15, graph.getNumEdges());
        int numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(0).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(0, numEdges);
        numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(1).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(2, numEdges);
    }

    @Test
    public void testModifyWeight() {
        setup3();
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertFalse(graph.modifyWeight(vertices.get(0), vertices.get(5), 12.8));
        assertFalse(graph.modifyWeight(null, vertices.get(5), 12.8));
        assertFalse(graph.modifyWeight(vertices.get(0), vertices.get(1), 0));
        assertFalse(graph.modifyWeight(vertices.get(0), vertices.get(2), -1));
        assertTrue(graph.modifyWeight(vertices.get(1), vertices.get(3), 5.7));
        assertTrue(graph.modifyWeight(vertices.get(1), vertices.get(2), 0.8));
        assertTrue(graph.modifyWeight(vertices.get(1), vertices.get(0), 2.7));
        assertEquals(5.7, graph.getAdjacencyMatrix().get(1).get(3).getWeight());
        assertEquals(0.8, graph.getAdjacencyMatrix().get(1).get(2).getWeight());
        assertEquals(2.7, graph.getAdjacencyMatrix().get(1).get(0).getWeight());
        assertEquals(18, graph.getNumEdges());
        int numEdges = 0;
        for(int i=0; i<6; i++){
            if(graph.getAdjacencyMatrix().get(1).get(i)!=null){
                numEdges++;
            }
        }
        assertEquals(3, numEdges);
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