package dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DirectedWeightedGraphALTest {

    private DirectedWeightedGraphAL<String> graph;

    public void setup1(){
        graph = new DirectedWeightedGraphAL<String>();
    }

    public void setup2(){
        graph = new DirectedWeightedGraphAL<String>();
        graph.addVertex("a"); //0
        graph.addVertex("b"); //1
        graph.addVertex("c"); //2
        graph.addVertex("d"); //3
        graph.addVertex("e"); //4
        graph.addVertex("z"); //5
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
    public void testAddVertex(){

    }

    @Test
    public void testDeleteVertex(){

    }

    @Test
    public void testAddEdge(){

    }

    @Test
    public void testModifyVertex(){

    }

    @Test
    public void testDeleteEdge(){

    }

    @Test
    public void testModifyWeight(){

    }
}
