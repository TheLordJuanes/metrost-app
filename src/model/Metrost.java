/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 3rd 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import dataStructures.DirectedWeightedGraphAM;
import dataStructures.Vertex;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Metrost {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private ArrayList<String> stations;
    private String fileName = "data/stations.csv";

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private DirectedWeightedGraphAM<String> graph;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public DirectedWeightedGraphAM<String> getGraph() {
        return graph;
    }

    public void setGraph(DirectedWeightedGraphAM<String> graph) {
        this.graph = graph;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Metrost() {
        graph = new DirectedWeightedGraphAM<String>();
        stations = new ArrayList<String>();
    }

    public Metrost(String file) {
        graph = new DirectedWeightedGraphAM<String>();
        stations = new ArrayList<String>();
        fileName = file;
    }

    /**
     * @return ArrayList<String> return the stations
    */
    public ArrayList<String> getStations() {
        return stations;
    }

    public void addDesignedNetwork(File file) throws IOException, CsvException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String line = br.readLine();
        while (line != null) {
            bw.write(line + "\n");
            line = br.readLine();
        }
        bw.close();
        br.close();
        br = new BufferedReader(new FileReader(file));
        line = br.readLine();
        line = br.readLine();
        line = br.readLine();
        line = br.readLine();
        while (!line.equals("Station,Connected station,Distance between them")) {
            graph.addVertex(line);
            stations.add(line);
            line = br.readLine();
        }
        line = br.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            Vertex<String> source = new Vertex<String>(parts[0]);
            Vertex<String> destination = new Vertex<String>(parts[1]);
            double weight = Double.parseDouble(parts[2]);
            graph.addEdge(source, destination, weight);
            line = br.readLine();
        }
    }

    public void addCurrentNetWork(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        line = br.readLine();
        line = br.readLine();
        line = br.readLine();
        while (!line.equals("Station,Connected station,Distance between them")) {
            graph.addVertex(line);
            stations.add(line);
            line = br.readLine();
        }
        line = br.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            Vertex<String> source = new Vertex<String>(parts[0]);
            Vertex<String> destination = new Vertex<String>(parts[1]);
            double weight = Double.parseDouble(parts[2]);
            graph.addEdge(source, destination, weight);
            line = br.readLine();
        }
        br.close();
    }

    public boolean addStation(String name) throws IOException, CsvException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        if (graph.addVertex(name) && !stations.contains(name)) {
            if (br.lines().count() == 0) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                bw.write("Number of stations\n");
                bw.write("1\n");
                bw.write("Stations\n");
                bw.write(name + "\n");
                bw.write("Station,Connected station,Distance between them\n");
                bw.close();
            } else {
                FileReader fr = new FileReader(fileName);
                CSVReader csvReader = new CSVReaderBuilder(fr).build();
                ArrayList<String[]> allLines = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
                int numStations = Integer.parseInt(allLines.get(1)[0]);
                numStations++;
                allLines.get(1)[0] = String.valueOf(numStations);
                String[] station = { name };
                allLines.add(numStations + 2, station);
                FileWriter fw = new FileWriter(fileName);
                CSVWriter csvWriter = new CSVWriter(fw);
                csvWriter.writeAll(allLines, false);
                csvWriter.close();
            }
            stations.add(name);
            br.close();
            return true;
        }
        br.close();
        return false;
    }

    public boolean modifyStation(String oldName, String newName) throws IOException, CsvException {
        if (graph.modifyVertex(oldName, newName)) {
            for (int i = 0; i < stations.size(); i++) {
                if (stations.get(i).equals(oldName)) {
                    stations.set(i, newName);
                    break;
                }
            }
            FileReader fr = new FileReader(fileName);
            CSVReader csvReader = new CSVReaderBuilder(fr).build();
            ArrayList<String[]> allLines = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
            for (String[] line : allLines) {
                if (line[0].equals(oldName) && line.length != 3)
                    line[0] = newName;
                if (line.length == 3) {
                    if (line[0].equals(oldName))
                        line[0] = newName;
                    else if (line[1].equals(oldName))
                        line[1] = newName;
                }
            }
            FileWriter fw = new FileWriter(fileName);
            CSVWriter csvWriter = new CSVWriter(fw);
            csvWriter.writeAll(allLines, false);
            csvWriter.close();
            return true;
        }
        return false;
    }

    public void deleteStation(String name) throws IOException, CsvException {
        graph.deleteVertex(name);
        FileReader fr = new FileReader(fileName);
        CSVReader csvReader = new CSVReaderBuilder(fr).build();
        ArrayList<String[]> allLines = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
        int numStations = Integer.parseInt(allLines.get(1)[0]);
        numStations--;
        allLines.get(1)[0] = String.valueOf(numStations);
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i)[0].equals(name) && allLines.get(i).length != 3) {
                allLines.remove(i);
                stations.remove(name);
            }
            if (allLines.get(i).length == 3) {
                if (allLines.get(i)[0].equals(name) || allLines.get(i)[1].equals(name)) {
                    Vertex<String> source = new Vertex<String>(allLines.get(i)[0]);
                    Vertex<String> destination = new Vertex<String>(allLines.get(i)[1]);
                    graph.deleteEdge(source, destination);
                    allLines.remove(i);
                    i--;
                }
            }
        }
        FileWriter fw = new FileWriter(fileName);
        CSVWriter csvWriter = new CSVWriter(fw);
        csvWriter.writeAll(allLines, false);
        csvWriter.close();
    }

    public boolean addConnection(String fromStation1, String toStation2, double distance) throws IOException, CsvException {
        Vertex<String> source = new Vertex<String>(fromStation1);
        Vertex<String> destination = new Vertex<String>(toStation2);
        if (graph.addEdge(source, destination, distance)) {
            FileReader fr = new FileReader(fileName);
            CSVReader csvReader = new CSVReaderBuilder(fr).build();
            ArrayList<String[]> allLines = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
            String[] connection = { fromStation1, toStation2, String.valueOf(distance) };
            allLines.add(connection);
            FileWriter fw = new FileWriter(fileName);
            CSVWriter csvWriter = new CSVWriter(fw);
            csvWriter.writeAll(allLines, false);
            csvWriter.close();
            return true;
        }
        return false;
    }

    public boolean modifyConnection(String fromStation1, String toStation2, double newDistance) throws IOException, CsvException {
        Vertex<String> source = new Vertex<String>(fromStation1);
        Vertex<String> destination = new Vertex<String>(toStation2);
        if (graph.modifyWeight(source, destination, newDistance)) {
            FileReader fr = new FileReader(fileName);
            CSVReader csvReader = new CSVReaderBuilder(fr).build();
            ArrayList<String[]> allLines = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
            for (String[] line : allLines) {
                if (line.length == 3) {
                    if (line[0].equals(fromStation1) && line[1].equals(toStation2)) {
                        line[2] = String.valueOf(newDistance);
                        break;
                    }
                }
            }
            FileWriter fw = new FileWriter(fileName);
            CSVWriter csvWriter = new CSVWriter(fw);
            csvWriter.writeAll(allLines, false);
            csvWriter.close();
            return true;
        }
        return false;
    }

    public boolean deleteConnection(String fromStation1, String toStation2) throws IOException, CsvException {
        Vertex<String> source = new Vertex<String>(fromStation1);
        Vertex<String> destination = new Vertex<String>(toStation2);
        if (graph.deleteEdge(source, destination)) {
            FileReader fr = new FileReader(fileName);
            CSVReader csvReader = new CSVReaderBuilder(fr).build();
            ArrayList<String[]> allLines = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
            for (String[] line : allLines) {
                if (line.length == 3) {
                    if (line[0].equals(fromStation1) && line[1].equals(toStation2)) {
                        allLines.remove(line);
                        break;
                    }
                }
            }
            FileWriter fw = new FileWriter(fileName);
            CSVWriter csvWriter = new CSVWriter(fw);
            csvWriter.writeAll(allLines, false);
            csvWriter.close();
            return true;
        }
        return false;
    }

    public String findShortestPath(String fromStation) {
        graph.dijkstra(graph.getVertices().get(graph.getIndex(fromStation)));
        String info = "";
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i) != fromStation) {
                Vertex<String> target = new Vertex<String>(stations.get(i));
                ArrayList<Vertex<String>> path = getPath(target);
                if (graph.getDistD()[i] == Double.MAX_VALUE)
                    info += "\nTo " + stations.get(i) + ": \tDistance: " + "N/A" + "\t\tPath: ";
                else
                    info += "\nTo " + stations.get(i) + ": \tDistance: " + graph.getDistD()[i] + "\t\tPath: ";
                if (path != null) {
                    for (Vertex<String> station : path) {
                        info += station.getValue() + "->";
                    }
                    info = info.substring(0, info.length() - 2);
                } else
                    info += "Non-existent";
            }
        }
        info = info.substring(1, info.length());
        return info;
    }

    private ArrayList<Vertex<String>> getPath(Vertex<String> target) {
        ArrayList<Vertex<String>> path = new ArrayList<Vertex<String>>();
        Vertex<String> step = target;
        if (graph.getPrevD().get(graph.getIndex(step.getValue())) == null) {
            return null;
        }
        path.add(step);
        while (graph.getPrevD().get(graph.getIndex(step.getValue())) != null) {
            step = graph.getPrevD().get(graph.getIndex(step.getValue()));
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    public String showNetworkData() throws IOException {
        String network = "";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        while (line != null) {
            network += line + "\n";
            line = br.readLine();
        }
        br.close();
        return network;
    }
}