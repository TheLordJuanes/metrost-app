/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 3rd 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package dataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import dataStructures.Vertex.Color;

public abstract class DirectedWeightedGraph<V extends Comparable<V>> implements DirectedWeightedGraphInterface<V> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private int time;
    private double[] distD;
    private double[][] minDistances;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private Edge<V> minEdge;
    private ArrayList<Vertex<V>> vertices;
    private ArrayList<Vertex<V>> prevD;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public DirectedWeightedGraph() {
        vertices = new ArrayList<Vertex<V>>();
    }

    public double[] getDistD() {
        return distD;
    }

    public double[][] getMinDistances() {
        return minDistances;
    }

    public ArrayList<Vertex<V>> getVertices() {
        return vertices;
    }

    public ArrayList<Vertex<V>> getPrevD() {
        return prevD;
    }

    public Edge<V> getMinEdge() {
        return minEdge;
    }

    public void setMinEdge(Edge<V> minEdge) {
        this.minEdge = minEdge;
    }

    @Override
    public void floydWarshall() {
        int size = vertices.size();
        minDistances = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                minDistances[i][j] = Double.MAX_VALUE;
        }
        for (int i = 0; i < size; i++) {
            minDistances[i][i] = 0;
        }
        fillMatrix();
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (minDistances[i][j] > minDistances[i][k] + minDistances[k][j])
                        minDistances[i][j] = minDistances[i][k] + minDistances[k][j];
                }
            }
        }
    }

    @Override
    public boolean dijkstra(Vertex<V> source) {
        if (vertices.isEmpty() || source == null) {
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
                    dist[index] = alt;
                    prev.set(index, u);
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
        distD = dist;
        prevD = prev;
        return true;
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
        vertex.setDistance(0);
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
            u.setColor(Color.BLACK);
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
        time = 0;
        for (Vertex<V> u : vertices) {
            if (u.getColor() == Color.WHITE)
                dfsVisit(u);
        }
        return true;
    }

    private void dfsVisit(Vertex<V> u) {
        time += 1;
        u.setDiscovery(time);
        u.setColor(Color.GRAY);
        for (Vertex<V> v : u.getDestinations()) {
            if (v.getColor() == Color.WHITE) {
                v.setParent(u);
                dfsVisit(v);
            }
        }
        u.setColor(Color.BLACK);
        time += 1;
        u.setFinalization(time);
    }

    @Override
    public boolean prim(Vertex<V> r) {
        if (!bfs(vertices.get(0)))
            return false;
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<V> v = vertices.get(i);
            if (v.isWhite())
                return false;
            else {
                v.setColor(Color.WHITE);
                v.setPriority(Double.MAX_VALUE);
            }
        }
        r.setPriority(0);
        r.setParent(null);
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(vertices);
        while (!queue.isEmpty()) {
            Vertex<V> u = queue.poll();
            for (Vertex<V> v : u.getDestinations()) {
                double wei = searchEdge(u, v).getWeight();
                if (v.isWhite() && wei < v.getPriority()) {
                    v.setPriority(wei);
                    queue.remove(v);
                    queue.add(v);
                    v.setParent(u);
                }
            }
            u.setColor(Color.BLACK);
        }
        return true;
    }

    @Override
    public ArrayList<Edge<V>> kruskal() {
        ArrayList<Edge<V>> a = new ArrayList<>();
        Uf uf = new Uf(vertices.size());
        ArrayList<Edge<V>> edges = getEdges();
        Collections.sort(edges);
        for (int i = 0; i < vertices.size(); i++)
            uf.makeSet(i);
        for (Edge<V> edge : edges) {
            Vertex<V> source = edge.getSource();
            Vertex<V> dest = edge.getDestination();
            int u = getIndex(source);
            int v = getIndex(dest);
            if (uf.find(u) != uf.find(v)) {
                a.add(edge);
                uf.union(u, v);
            }
        }
        return a;
    }

    public int getIndex(V value) {
        for (int i = 0; i < getVertices().size(); i++) {
            if (value.compareTo((getVertices().get(i).getValue())) == 0)
                return i;
        }
        return -1;
    }

    protected int getIndex(Vertex<V> s) {
        V value = s.getValue();
        for (int i = 0; i < vertices.size(); i++) {
            if (value.compareTo((vertices.get(i).getValue())) == 0)
                return i;
        }
        return -1;
    }

    public abstract ArrayList<Edge<V>> getEdges();

    static class Uf {

        ArrayList<LinkedList<Integer>> sets;
        int[] reps;

        public Uf(int vs) {
            sets = new ArrayList<>();
            reps = new int[vs];
        }

        public void makeSet(int x) {
            LinkedList<Integer> newSet = new LinkedList<>();
            newSet.add(x);
            sets.add(x, newSet);
            reps[x] = x;
        }

        public int find(int i) {
            while (reps[i] != i)
                i = reps[i];
            return i;
        }

        public void union(int x, int y) {
            int a = find(x);
            int b = find(y);
            reps[a] = b;
        }
    }

    protected abstract Edge<V> searchEdge(Vertex<V> v1, Vertex<V> v2);

    protected abstract void fillMatrix();
}