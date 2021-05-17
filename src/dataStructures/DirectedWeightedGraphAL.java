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
            if (s.getValue().compareTo((vertices.get(i).getValue())) == 0)
                return i;
        }
        return -1;
    }

    private int getIndex(V value) {
        for (int i = 0; i < vertices.size(); i++) {
            if (value.compareTo((vertices.get(i).getValue())) == 0)
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
        if (index == -1)
            return false;
        for (Vertex<V> u : vertices) {
            u.setColor(Color.WHITE);
            u.setDistance(Integer.MAX_VALUE);
            u.setParent(null);
        }
        vertex.setColor(Color.GRAY);
        Queue<Vertex<V>> queue = new LinkedList<>();
        queue.add(vertex);
        while (!queue.isEmpty()) {
            Vertex<V> u = queue.poll();
            for (Vertex<V> v : u.getDestinations()) {
                if (v.getColor() == Color.WHITE) {
                    v.setColor(Color.GRAY);
                    v.setDistance(u.getDistance() + 1);
                    v.setParent(u);
                    queue.add(v);
                }
            }
            vertex.setColor(Color.BLACK);
        }
        return true;
    }

    @Override
    public boolean dfs() {
        if (vertices.isEmpty())
            return false;
        for (Vertex<V> u : vertices) {
            u.setColor(Color.WHITE);
        }
        int time = 0;
        for (Vertex<V> u : vertices) {
            if (u.getColor() == Color.WHITE)
                dfsVisit(u, time);
        }
        return true;
    }

    private void dfsVisit(Vertex<V> u, int time) {
        time += 1;
        u.setDiscovery(time);
        u.setColor(Color.GRAY);
        for (Vertex<V> v : u.getDestinations()) {
            if (v.getColor() == Color.WHITE) {
                v.setParent(u);
                dfsVisit(v, time);
            }
        }
        u.setColor(Color.BLACK);
        time += 1;
        u.setFinalization(time);
    }

    @Override
    public boolean addVertex(V value) {
        if (value == null)
            return false;
        if (checkValue(value)) {
            return false;
        }
        Vertex<V> newVertex = new Vertex<V>(value);
        vertices.add(newVertex);
        adjacencyList.add(new ArrayList<>());
        return true;
    }

    private boolean checkValue(V value) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(value))
                return true;
        }
        return false;
    }

    @Override
    public boolean deleteVertex(V value) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(value)) {
                deleteEdges(vertices.get(i));
                adjacencyList.remove(i);
                vertices.remove(i);
            }
        }
        return true;
    }

    public void deleteEdges(Vertex<V> toDelete) {
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getSource().equals(toDelete) || edges.get(i).getDestination().equals(toDelete))
                edges.remove(i);
        }
    }

    @Override
    public boolean addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        Edge<V> found = searchEdge(source, destination);
        if (found == null) {
            Edge<V> edge = new Edge<V>(weight, source, destination);
            edges.add(edge);
        } else
            found.setWeight(weight);
        return true;
    }

    @Override
    public boolean modifyVertex(V oldValue, V newValue) {
        int index = getIndex(oldValue);
        if(index == -1){
            return false;
        }
        vertices.get(index).setValue(newValue);;
        return true;
    }

    @Override
    public boolean deleteEdge(Vertex<V> source, Vertex<V> destination) {
        Edge<V> toDelete = searchEdge(source, destination);
        return false;
    }
}