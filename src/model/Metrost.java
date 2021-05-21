/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package model;

import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import dataStructures.Vertex;
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
    private ArrayList<String[]> allData;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private DirectedWeightedGraph?<String> graph;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Metrost() {
        graph = new DirectedWeightedGraph?<>();
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

    public void addStationByTextFile(File file) throws InterruptedException, IOException, CsvException {
        FileWriter fw = new FileWriter(FILE_NAME, true);
        CSVWriter csvWriter = new CSVWriter(fw);
        FileReader fr = new FileReader(file);
        CSVReader csvReader = new CSVReaderBuilder(fr).withSkipLines(1).build();
        if (allData.isEmpty()) {
            allData = (ArrayList<String[]>) csvReader.readAll();
            // allData = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
            fw = new FileWriter(FILE_NAME);
            CSVWriter writer = new CSVWriter(fw);
            String[] temp = { "Station", "Connected station", "Distance between them" };
            writer.writeNext(temp);
            for (int i = 0; i < allData.size(); i++) {
                progress = (i + 1) / (double) allData.size();
                writer.writeNext(allData.get(i));
            }
            writer.close();
        } else {
            ArrayList<String[]> newData = (ArrayList<String[]>) csvReader.readAll();
            // ArrayList<String[]> newData = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
            for (int i = 0; i < newData.size(); i++) {
                progress = (i + 1) / (double) newData.size();
                csvWriter.writeNext(newData.get(i));
                allData.add(newData.get(i));
            }
        }
        csvWriter.close();
    }

    public void addStationByPlatform(String name1, String name2, String distance) throws InterruptedException, IOException, CsvException {
        File dataFile = new File(FILE_NAME);
        FileWriter fw = new FileWriter(FILE_NAME, true);
        CSVWriter csvWriter = new CSVWriter(fw);
        if (dataFile.length() == 0) {
            String[] temp = { "Station", "Connected station", "Distance between them" };
            csvWriter.writeNext(temp);
        }
        String[] info = new String[3];
		info[0] = name1;
		info[1] = name2;
		info[2] = distance;
		csvWriter.writeNext(info);
        csvWriter.close();
        FileReader fr = new FileReader(FILE_NAME);
        CSVReader csvReader = new CSVReaderBuilder(fr).withSkipLines(1).build();
        allData = (ArrayList<String[]>) csvReader.readAll();
        // allData = new ArrayList<>((LinkedList<String[]>) csvReader.readAll());
        csvReader.close();
    }

    /**
     * @param attribute
     * @param valueD
     * @param valueS
     * @throws IOException
     */
    public boolean modifyStationData(String dataType, String newValue, int station) throws IOException {
        if (allData.get(station) == null)
            return false;
        switch (dataType) {
            case "Name":
                graph.modifyVertex(allData.get(station)[0], newValue);
                allData.get(station)[0] = newValue;
                break;
            case "Connected with":
                Vertex<String> source = new Vertex<String>(allData.get(station)[0]);
                Vertex<String> destination = new Vertex<String>(allData.get(station)[1]);
                graph.deleteEdge(source, destination);
                allData.get(station)[1] = newValue;
                destination = new Vertex<String>(allData.get(station)[1]);
                graph.addEdge(source, destination, allData.get(station)[2]);
                break;
            case "Distance":
                allData.get(station)[2] = newValue;
                graph.modifyWeight(source, destination, Double.valueOf(allData.get(station)[2]));
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

    public void deleteStation(List<Station> stations) {
        for (Station station : stations)
            graph.deleteVertex(station.getName());
    }
}