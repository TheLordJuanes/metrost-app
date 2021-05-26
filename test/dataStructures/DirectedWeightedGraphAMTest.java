package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import dataStructures.Vertex.Color;

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
        for (int row = 0; row < 6; row++) {
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
        for (int row = 0; row < 5; row++) {
            for (int i = 0; i < 5; i++) {
                if (graph.getAdjacencyMatrix().get(row).get(i) != null)
                    numEdges++;
            }
        }
        assertEquals(14, numEdges);
        numEdges = 0;
        for (int i = 0; i < 5; i++) {
            if (graph.getAdjacencyMatrix().get(0).get(i) != null)
                numEdges++;
        }
        assertEquals(2, numEdges);
        numEdges = 0;
        for (int i = 0; i < 5; i++) {
            if (graph.getAdjacencyMatrix().get(1).get(i) != null)
                numEdges++;
        }
        assertEquals(3, numEdges);
        numEdges = 0;
        for (int i = 0; i < 5; i++) {
            if (graph.getAdjacencyMatrix().get(2).get(i) != null)
                numEdges++;
        }
        assertEquals(4, numEdges);
        numEdges = 0;
        for (int i = 0; i < 5; i++) {
            if (graph.getAdjacencyMatrix().get(3).get(i) != null)
                numEdges++;
        }
        assertEquals(3, numEdges);
        numEdges = 0;
        for (int i = 0; i < 5; i++) {
            if (graph.getAdjacencyMatrix().get(4).get(i) != null)
                numEdges++;
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
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(0).get(i) != null)
                numEdges++;
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(1).get(i) != null)
                numEdges++;
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(2).get(i) != null)
                numEdges++;
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(3).get(i) != null)
                numEdges++;
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(4).get(i) != null)
                numEdges++;
        }
        assertEquals(1, numEdges);
        numEdges = 0;
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(5).get(i) != null)
                numEdges++;
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
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(0).get(i) != null)
                numEdges++;
        }
        assertEquals(0, numEdges);
        numEdges = 0;
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(1).get(i) != null)
                numEdges++;
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
        for (int i = 0; i < 6; i++) {
            if (graph.getAdjacencyMatrix().get(1).get(i) != null)
                numEdges++;
        }
        assertEquals(3, numEdges);
    }

    @Test
    public void testDijkstra() {
        setup1();
        Vertex<String> v = new Vertex<>("x");
        assertFalse(graph.dijkstra(v));
        setup3();
        assertFalse(graph.dijkstra(null));
        assertFalse(graph.dijkstra(v));
        graph.addVertex("x");
        assertTrue(graph.dijkstra(graph.getVertices().get(6)));
        double[] distD = graph.getDistD();
        ArrayList<Vertex<String>> prevD = graph.getPrevD();
        assertEquals(0, distD[6]);
        assertNull(prevD.get(6));
        for (int i = 0; i < distD.length - 1; i++) {
            assertEquals(Double.MAX_VALUE, distD[i]);
            assertNull(prevD.get(i));
        }
        setup3();
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertTrue(graph.dijkstra(graph.getVertices().get(0)));
        distD = graph.getDistD();
        prevD = graph.getPrevD();
        assertEquals(0, distD[0]);
        assertNull(prevD.get(0));
        assertEquals(3, distD[1]);
        assertEquals(vertices.get(2), prevD.get(1));
        assertEquals(2, distD[2]);
        assertEquals(vertices.get(0), prevD.get(2));
        assertEquals(8, distD[3]);
        assertEquals(vertices.get(1), prevD.get(3));
        assertEquals(10, distD[4]);
        assertEquals(vertices.get(3), prevD.get(4));
        assertEquals(13, distD[5]);
        assertEquals(vertices.get(4), prevD.get(5));
    }

    @Test
    public void testBfs() {
        setup2();
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        Vertex<String> temp = new Vertex<>("x");
        assertFalse(graph.bfs(temp));
        assertTrue(graph.bfs(vertices.get(0)));
        assertEquals(Color.BLACK, vertices.get(0).getColor());
        for(int i=1; i < vertices.size(); i++){
            assertEquals(Color.WHITE, vertices.get(i).getColor());
        }

        setup2();
        vertices = graph.getVertices();
        graph.addEdge(vertices.get(0), vertices.get(1), 1);
        graph.addEdge(vertices.get(0), vertices.get(2), 2);
        graph.addEdge(vertices.get(5), vertices.get(4), 3);
        graph.addEdge(vertices.get(5), vertices.get(3), 4);

        assertTrue(graph.bfs(vertices.get(0)));
        for(int i=0; i < 3; i++){
            assertEquals(Color.BLACK, vertices.get(i).getColor());
        }
        assertEquals(0, vertices.get(0).getDistance());
        assertEquals(1, vertices.get(1).getDistance());
        assertEquals(1, vertices.get(2).getDistance());
        assertEquals(null, vertices.get(0).getParent());
        assertEquals(vertices.get(0), vertices.get(1).getParent());
        assertEquals(vertices.get(0), vertices.get(2).getParent());
        for(int i=3; i < 6; i++){
            assertEquals(Color.WHITE, vertices.get(i).getColor());
        }
        assertEquals(Integer.MAX_VALUE, vertices.get(3).getDistance());
        assertEquals(Integer.MAX_VALUE, vertices.get(4).getDistance());
        assertEquals(Integer.MAX_VALUE, vertices.get(5).getDistance());
        assertEquals(null, vertices.get(3).getParent());
        assertEquals(null, vertices.get(4).getParent());
        assertEquals(null, vertices.get(5).getParent());

        setup3();
        vertices = graph.getVertices();
        assertTrue(graph.bfs(vertices.get(1)));
        for(int i=0; i < vertices.size(); i++){
            assertEquals(Color.BLACK, vertices.get(i).getColor());
        }
        assertEquals(0, vertices.get(1).getDistance());
        assertEquals(1, vertices.get(0).getDistance());
        assertEquals(1, vertices.get(2).getDistance());
        assertEquals(1, vertices.get(3).getDistance());
        assertEquals(2, vertices.get(4).getDistance());
        assertEquals(2, vertices.get(5).getDistance());

        assertEquals(null, vertices.get(1).getParent());
        assertEquals(vertices.get(1), vertices.get(0).getParent());
        assertEquals(vertices.get(1), vertices.get(2).getParent());
        assertEquals(vertices.get(1), vertices.get(3).getParent());
        assertEquals(vertices.get(2), vertices.get(4).getParent());
        assertEquals(vertices.get(3), vertices.get(5).getParent());
    }

    @Test
    public void testDfs() {
        setup1();
        assertFalse(graph.dfs());

        setup2();
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        assertTrue(graph.dfs());
        assertEquals(1, vertices.get(0).getDiscovery());
        assertEquals(2, vertices.get(0).getFinalization());
        assertEquals(3, vertices.get(1).getDiscovery());
        assertEquals(4, vertices.get(1).getFinalization());
        assertEquals(5, vertices.get(2).getDiscovery());
        assertEquals(6, vertices.get(2).getFinalization());
        assertEquals(7, vertices.get(3).getDiscovery());
        assertEquals(8, vertices.get(3).getFinalization());
        assertEquals(9, vertices.get(4).getDiscovery());
        assertEquals(10, vertices.get(4).getFinalization());
        assertEquals(11, vertices.get(5).getDiscovery());
        assertEquals(12, vertices.get(5).getFinalization());
        assertEquals(Color.BLACK, vertices.get(0).getColor());
        assertEquals(Color.BLACK, vertices.get(1).getColor());
        assertEquals(Color.BLACK, vertices.get(2).getColor());
        assertEquals(Color.BLACK, vertices.get(3).getColor());
        assertEquals(Color.BLACK, vertices.get(4).getColor());
        assertEquals(Color.BLACK, vertices.get(5).getColor());

        setup3();
        vertices = graph.getVertices();
        assertTrue(graph.dfs());
        assertEquals(1, vertices.get(0).getDiscovery());
        assertEquals(12, vertices.get(0).getFinalization());
        assertEquals(2, vertices.get(1).getDiscovery());
        assertEquals(11, vertices.get(1).getFinalization());
        assertEquals(3, vertices.get(2).getDiscovery());
        assertEquals(10, vertices.get(2).getFinalization());
        assertEquals(5, vertices.get(3).getDiscovery());
        assertEquals(8, vertices.get(3).getFinalization());
        assertEquals(4, vertices.get(4).getDiscovery());
        assertEquals(9, vertices.get(4).getFinalization());
        assertEquals(6, vertices.get(5).getDiscovery());
        assertEquals(7, vertices.get(5).getFinalization());
        assertEquals(Color.BLACK, vertices.get(0).getColor());
        assertEquals(Color.BLACK, vertices.get(1).getColor());
        assertEquals(Color.BLACK, vertices.get(2).getColor());
        assertEquals(Color.BLACK, vertices.get(3).getColor());
        assertEquals(Color.BLACK, vertices.get(4).getColor());
        assertEquals(Color.BLACK, vertices.get(5).getColor());
    }

    @Test
    public void testfloydWarshall() {
        setup2();
        graph.floydWarshall();
        double[][] matrix = graph.getMinDistances();
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                if(i==j){
                    assertEquals(0, matrix[i][j]);
                }else{
                    if(matrix[i][j]!=Double.MAX_VALUE){
                        fail();
                    }
                }
            }
        }
        setup3();
        graph.floydWarshall();
        matrix = graph.getMinDistances();
        ArrayList<Vertex<String>> vertices = graph.getVertices();
        for(int i=0; i<vertices.size(); i++){
            graph.dijkstra(vertices.get(i));
            double[] row = graph.getDistD();
            for(int j=0; j<vertices.size(); j++){
                assertEquals(row[j], matrix[i][j]);
            }
        }
    }
}