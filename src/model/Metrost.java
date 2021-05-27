/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    // Constants
    // -----------------------------------------------------------------

    public static final String FILE_NAME = "data/stations.csv";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private double progress;
    private ArrayList<String> stations;
    private ArrayList<String[]> allData;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private DirectedWeightedGraphAM<String> graph;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Metrost() {
        graph = new DirectedWeightedGraphAM<>();
        stations = new ArrayList<String>();
        progress = 0;
    }

    /**
     * @return double return the progress
     */
    public double getProgress() {
        return progress;
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }

    /**
     * @return ArrayList<String[]> return the allData
     */
    public ArrayList<String[]> getAllData() {
        return allData;
    }

    /**
     * @param allData the allData to set
     */
    public void setAllData(ArrayList<String[]> allData) {
        this.allData = allData;
    }

    public void addNetwork(File file) throws IOException, CsvException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
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

    /**
     * @return ArrayList<String> return the stations
     */
    public ArrayList<String> getStations() {
        return stations;
    }

    public boolean addStation(String name) throws IOException, CsvException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        if (graph.addVertex(name) && !stations.contains(name)) {
            if (br.lines().count() == 0) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
                bw.write("Number of stations\n");
                bw.write("1\n");
                bw.write("Stations\n");
                bw.write(name + "\n");
                bw.write("Station,Connected station,Distance between them\n");
                bw.close();
            } else {
                FileReader fr = new FileReader(FILE_NAME);
                CSVReader csvReader = new CSVReaderBuilder(fr).build();
                ArrayList<String[]> allLines = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
                int numStations = Integer.parseInt(allLines.get(1)[0]);
                numStations++;
                allLines.get(1)[0] = String.valueOf(numStations);
                String[] station = { name };
                allLines.add(numStations + 2, station);
                FileWriter fw = new FileWriter(FILE_NAME);
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
            FileReader fr = new FileReader(FILE_NAME);
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
            FileWriter fw = new FileWriter(FILE_NAME);
            CSVWriter csvWriter = new CSVWriter(fw);
            csvWriter.writeAll(allLines, false);
            csvWriter.close();
            return true;
        }
        return false;
    }

    public void deleteStation(String name) throws IOException, CsvException {
        graph.deleteVertex(name);
        FileReader fr = new FileReader(FILE_NAME);
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
        FileWriter fw = new FileWriter(FILE_NAME);
        CSVWriter csvWriter = new CSVWriter(fw);
        csvWriter.writeAll(allLines, false);
        csvWriter.close();
    }

    public boolean addConnection(String fromStation1, String toStation2, String distance) throws IOException, CsvException {
        Vertex<String> source = new Vertex<String>(fromStation1);
        Vertex<String> destination = new Vertex<String>(toStation2);
        double dist = Double.parseDouble(distance);
        if (graph.addEdge(source, destination, dist)) {
            FileReader fr = new FileReader(FILE_NAME);
            CSVReader csvReader = new CSVReaderBuilder(fr).build();
            ArrayList<String[]> allLines = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
            String[] connection = { fromStation1, toStation2, distance };
            allLines.add(connection);
            FileWriter fw = new FileWriter(FILE_NAME);
            CSVWriter csvWriter = new CSVWriter(fw);
            csvWriter.writeAll(allLines, false);
            csvWriter.close();
            return true;
        }
        return false;
    }

    /**
     * @param attribute
     * @param valueD
     * @param valueS
     * @throws IOException
     */
    public boolean modifyStationData(String dataType, String newValue, int station) throws IOException {
        if (allData.get(station) == null) {
            return false;
        }
        Vertex<String> source = new Vertex<String>(allData.get(station)[0]);
        Vertex<String> destination = new Vertex<String>(allData.get(station)[1]);
        switch (dataType) {
            case "Name":
                graph.modifyVertex(allData.get(station)[0], newValue);
                allData.get(station)[0] = newValue;
                break;
            case "Connected with":
                graph.deleteEdge(source, destination);
                allData.get(station)[1] = newValue;
                destination = new Vertex<String>(allData.get(station)[1]);
                graph.addEdge(source, destination, Double.parseDouble(allData.get(station)[2]));
                break;
        }
        FileWriter fw = new FileWriter(FILE_NAME);
        CSVWriter csvWriter = new CSVWriter(fw);
        ArrayList<String[]> allData2 = new ArrayList<>(allData);
        String[] temp = { "Station", "Connected station", "Distance between them" };
        allData2.add(0, temp);
        csvWriter.writeAll(allData2);
        csvWriter.close();
        return true;
    }

    public boolean modifyConnection(String newValue, int station) {
        if (allData.get(station) == null) {
            return false;
        }
        Vertex<String> source = new Vertex<String>(allData.get(station)[0]);
        Vertex<String> destination = new Vertex<String>(allData.get(station)[1]);
        allData.get(station)[2] = newValue;
        graph.modifyWeight(source, destination, Double.valueOf(allData.get(station)[2]));
        return true;
    }

    public void deleteStationData(List<Station> stations) {
        //for (Station station : stations)
            //graph.deleteVertex(station.getName());
    }
}