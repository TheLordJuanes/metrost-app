/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import dataStructures.Vertex.Color;

public class DirectedWeightedGraphAL<V extends Comparable<V>> implements DirectedWeightedGraphInterface<V> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private ArrayList<Vertex<V>> vertices;
    private ArrayList<ArrayList<Vertex<V>>> adjacencyList;
    private ArrayList<Edge<V>> edges;
    private double[] distD;
    private ArrayList<Vertex<V>> prevD;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public DirectedWeightedGraphAL() {
        vertices = new ArrayList<Vertex<V>>();
    }

    public ArrayList<Vertex<V>> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex<V>> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Edge<V>> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge<V>> edges) {
        this.edges = edges;
    }

    public ArrayList<ArrayList<Vertex<V>>> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(ArrayList<ArrayList<Vertex<V>>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    @Override
    public boolean dijkstra(Vertex<V> source) {
        if (vertices.isEmpty()) {
            return false;
        }
        int index = getIndex(source);
        if (index == -1) {
            return false;
        }
        double[] dist = new double[vertices.size()];
        ArrayList<Vertex<V>> prev = new ArrayList<>();
        source = vertices.get(index);
        source.setPriority(0);
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<V> v = vertices.get(i);
            if (v != source) {
                v.setPriority(Double.MAX_VALUE);
            }
            dist[i] = v.getPriority();
            prev.add(null);
            pq.add(v);
        }
        while (!pq.isEmpty()) {
            Vertex<V> u = pq.poll();
            for (Vertex<V> v : u.getDestinations()) {
                Edge<V> edge = searchEdge(u, v);
                double alt = 0;
                if (edge == null)
                    alt = Double.MAX_VALUE;
                else
                    alt = u.getPriority() + edge.getWeight();
                if (alt < v.getPriority()) {
                    v.setPriority(alt);
                    index = getIndex(v);
                    prev.set(index, u);
                    pq.remove(v);
                }
            }
        }
        distD = dist;
        prevD = prev;
        return true;
    }

    private Edge<V> searchEdge(Vertex<V> v1, Vertex<V> v2) {
        for (Edge<V> edge : edges) {
            if (edge.getSource().equals(v1) && edge.getDestination().equals(v2))
                return edge;
        }
        return null;
    }

    private int getIndex(Vertex<V> s) {
        for (int i = 0; i < vertices.size(); i++) {
            if (s.equals(vertices.get(i)))
                return i;
        }
        return -1;
    }

    @Override
    public boolean bfs(Vertex<V> vertex) {
        if (vertices.isEmpty()) {
            return false;
        }
        int index = getIndex(vertex);
        if (index == -1) {
            return false;
        }
        for (Vertex<V> u : vertices) {
            u.setColor(Color.WHITE);
        }
        vertex.setColor(Color.GRAY);
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(vertex);
        while (!queue.isEmpty()) {
            Vertex<V> u = queue.poll();
            for (Vertex<V> v : u.getDestinations()) {
                if (v.getColor() == Color.WHITE) {
                    v.setColor(Color.GRAY);
                    queue.add(v);
                }
            }
            vertex.setColor(Color.BLACK);
        }
        return true;
    }

    @Override
    public void dfs() {

    }

    @Override
    public void addVertex(V value) {

    }

    @Override
    public void deleteVertex(V value) {

    }

    @Override
    public void modifyVertex(V oldValue, V newValue) {

    }

    /**
     * @return double[] return the distD
     */
    public double[] getDistD() {
        return distD;
    }

    /**
     * @param distD the distD to set
     */
    public void setDistD(double[] distD) {
        this.distD = distD;
    }

    /**
     * @return ArrayList<Vertex<V>> return the prevD
     */
    public ArrayList<Vertex<V>> getPrevD() {
        return prevD;
    }

    /**
     * @param prevD the prevD to set
     */
    public void setPrevD(ArrayList<Vertex<V>> prevD) {
        this.prevD = prevD;
    }

}