package model;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;
import dataStructures.Edge;
import dataStructures.Vertex;

public class MetrostTest {

	private Metrost metrost;

	public void setup1(){
		File file = new File("test/stations.csv");
		file.delete();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		metrost = new Metrost("test/stations.csv");
	}

	public void setup2(){
		metrost = new Metrost("test/stations.csv");
		File file = new File("test/inputExample.csv");
		try {
			metrost.addNetwork(file);
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddNetwork() {
		setup1();
		File file = new File("test/inputExample.csv");
		try {
			metrost.addNetwork(file);
			assertEquals(7, metrost.getStations().size());
			assertEquals("a", metrost.getStations().get(0));
			assertEquals("b", metrost.getStations().get(1));
			assertEquals("c", metrost.getStations().get(2));
			assertEquals("d", metrost.getStations().get(3));
			assertEquals("e", metrost.getStations().get(4));
			assertEquals("f", metrost.getStations().get(5));
			assertEquals("g", metrost.getStations().get(6));

			ArrayList<ArrayList<Edge<String>>> adjacencyMatrix = metrost.getGraph().getAdjacencyMatrix();
			ArrayList<Vertex<String>> vertices = metrost.getGraph().getVertices();

			assertEquals(vertices.size(), 7);
			assertEquals(adjacencyMatrix.size(), 7);
			for (int i = 0; i < 6; i++) {
				if (adjacencyMatrix.get(i).size() != 7)
					fail();
			}

			assertEquals(vertices.get(0).getValue(), "a");
			assertEquals(vertices.get(1).getValue(), "b");
			assertEquals(vertices.get(2).getValue(), "c");
			assertEquals(vertices.get(3).getValue(), "d");
			assertEquals(vertices.get(4).getValue(), "e");
			assertEquals(vertices.get(5).getValue(), "f");
			assertEquals(vertices.get(6).getValue(), "g");

			assertEquals(3.0, adjacencyMatrix.get(0).get(1).getWeight());
			assertEquals(6.0, adjacencyMatrix.get(0).get(2).getWeight());
			assertEquals(5.0, adjacencyMatrix.get(1).get(0).getWeight());
			assertEquals(4.0, adjacencyMatrix.get(0).get(3).getWeight());

			for(int i=0; i<7; i++){
				for(int j=0; j<7; j++){
					if(i==0){
						if(j==1 || j==2 || j==3){
							continue;
						}
					}
					if(i==1){
						if(j==0){
							continue;
						}
					}
					assertNull(adjacencyMatrix.get(i).get(j));
				}
			}
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		} catch (CsvException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void testAddStation() {
		setup1();
		try {
			metrost.addStation("Prueba");
			assertEquals(1, metrost.getStations().size());
			assertEquals("Prueba", metrost.getStations().get(0));
			BufferedReader br = new BufferedReader(new FileReader(metrost.getFileName()));
			assertEquals("Number of stations",br.readLine());
			assertEquals("1",br.readLine());
			assertEquals("Stations",br.readLine());
			assertEquals("Prueba",br.readLine());
			assertEquals("Station,Connected station,Distance between them",br.readLine());
			assertNull(br.readLine());
			br.close();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			fail();
		}
		setup2();
		try {
			metrost.addStation("Prueba");
			assertEquals(8, metrost.getStations().size());
			assertEquals("a", metrost.getStations().get(0));
			assertEquals("b", metrost.getStations().get(1));
			assertEquals("c", metrost.getStations().get(2));
			assertEquals("d", metrost.getStations().get(3));
			assertEquals("e", metrost.getStations().get(4));
			assertEquals("f", metrost.getStations().get(5));
			assertEquals("g", metrost.getStations().get(6));
			assertEquals("Prueba", metrost.getStations().get(7));
			BufferedReader br = new BufferedReader(new FileReader(metrost.getFileName()));
			assertEquals("Number of stations",br.readLine());
			assertEquals("8",br.readLine());
			assertEquals("Stations",br.readLine());
			assertEquals("a",br.readLine());
			assertEquals("b",br.readLine());
			assertEquals("c",br.readLine());
			assertEquals("d",br.readLine());
			assertEquals("e",br.readLine());
			assertEquals("f",br.readLine());
			assertEquals("g",br.readLine());
			assertEquals("Prueba",br.readLine());
			assertEquals("Station,Connected station,Distance between them",br.readLine());
			assertEquals("a,b,3.0",br.readLine());
			assertEquals("a,c,6.0",br.readLine());
			assertEquals("b,a,5.0",br.readLine());
			assertEquals("a,d,4.0",br.readLine());
			assertNull(br.readLine());
			br.close();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testModifyStation() {
		setup2();
		try {
			assertFalse(metrost.modifyStation("a", "b"));
			assertTrue(metrost.modifyStation("b", "h"));
			assertTrue(metrost.modifyStation("a", "b"));
			ArrayList<Vertex<String>> vertices = metrost.getGraph().getVertices();

			assertEquals("b", metrost.getStations().get(0));
			assertEquals("h", metrost.getStations().get(1));

			assertEquals("b", vertices.get(0).getValue());
			assertEquals("h", vertices.get(1).getValue());

			BufferedReader br = new BufferedReader(new FileReader("test/stations.csv"));
			for (int i = 0; i < 3; i++) {
				br.readLine();
			}
			assertEquals("b", br.readLine());
			assertEquals("h", br.readLine());
			for (int i = 0; i < 6; i++) {
				br.readLine();
			}
			String[] temp = br.readLine().split(",");
			assertEquals("b", temp[0]);
			assertEquals("h", temp[1]);
			assertEquals("b", br.readLine().split(",")[0]);
			temp = br.readLine().split(",");
			assertEquals("h", temp[0]);
			assertEquals("b", temp[1]);
			assertEquals("b", br.readLine().split(",")[0]);
			br.close();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteStation() {
		setup2();
		try {
			metrost.deleteStation("a");
			assertEquals(6, metrost.getStations().size());
			assertEquals("b", metrost.getStations().get(0));
			assertEquals("c", metrost.getStations().get(1));
			assertEquals("d", metrost.getStations().get(2));
			assertEquals("e", metrost.getStations().get(3));
			assertEquals("f", metrost.getStations().get(4));
			assertEquals("g", metrost.getStations().get(5));
			BufferedReader br = new BufferedReader(new FileReader(metrost.getFileName()));
			assertEquals("Number of stations",br.readLine());
			assertEquals("6",br.readLine());
			assertEquals("Stations",br.readLine());
			assertEquals("b",br.readLine());
			assertEquals("c",br.readLine());
			assertEquals("d",br.readLine());
			assertEquals("e",br.readLine());
			assertEquals("f",br.readLine());
			assertEquals("g",br.readLine());
			assertEquals("Station,Connected station,Distance between them",br.readLine());
			assertNull(br.readLine());
			br.close();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddConnection() {
		setup2();
		try {
			assertFalse(metrost.addConnection("a", "a", 12.0));
			assertFalse(metrost.addConnection("a", "b", 11.0));
			assertFalse(metrost.addConnection("a", "f", 0));
			assertTrue(metrost.addConnection("a", "f", 7.5));
			assertTrue(metrost.addConnection("f", "g", 10.2));

			BufferedReader br = new BufferedReader(new FileReader("test/stations.csv"));

			for(int i=0; i<15; i++){
				br.readLine();
			}
			String[] temp = br.readLine().split(",");
			assertEquals("a", temp[0]);
			assertEquals("f", temp[1]);
			assertEquals("7.5", temp[2]);

			temp = br.readLine().split(",");
			assertEquals("f", temp[0]);
			assertEquals("g", temp[1]);
			assertEquals("10.2", temp[2]);

			Edge<String> tempE = metrost.getGraph().getAdjacencyMatrix().get(0).get(5);
			assertEquals("a", tempE.getSource().getValue());
			assertEquals("f", tempE.getDestination().getValue());
			assertEquals(7.5, tempE.getWeight());

			tempE = metrost.getGraph().getAdjacencyMatrix().get(5).get(6);
			assertEquals("f", tempE.getSource().getValue());
			assertEquals("g", tempE.getDestination().getValue());
			assertEquals(10.2, tempE.getWeight());

			br.close();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testModifyConnection() {
		setup2();
		try {
			assertFalse(metrost.modifyConnection("e", "f", 15));
			assertTrue(metrost.modifyConnection("a", "b", 3.5));
			BufferedReader br = new BufferedReader(new FileReader("test/stations.csv"));

			for(int i=0; i<11; i++){
				br.readLine();
			}
			String[] temp = br.readLine().split(",");
			assertEquals("a", temp[0]);
			assertEquals("b", temp[1]);
			assertEquals("3.5", temp[2]);

			Edge<String> tempE = metrost.getGraph().getAdjacencyMatrix().get(0).get(1);
			assertEquals("a", tempE.getSource().getValue());
			assertEquals("b", tempE.getDestination().getValue());
			assertEquals(3.5, tempE.getWeight());
			br.close();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDeleteConnection() {
		setup2();
		try {
			assertFalse(metrost.deleteConnection("a", "f"));
			assertTrue(metrost.deleteConnection("a", "b"));
			assertEquals(7, metrost.getStations().size());
			assertEquals("a", metrost.getStations().get(0));
			assertEquals("b", metrost.getStations().get(1));
			assertEquals("c", metrost.getStations().get(2));
			assertEquals("d", metrost.getStations().get(3));
			assertEquals("e", metrost.getStations().get(4));
			assertEquals("f", metrost.getStations().get(5));
			assertEquals("g", metrost.getStations().get(6));
			BufferedReader br = new BufferedReader(new FileReader(metrost.getFileName()));
			assertEquals("Number of stations",br.readLine());
			assertEquals("7",br.readLine());
			assertEquals("Stations",br.readLine());
			assertEquals("a",br.readLine());
			assertEquals("b",br.readLine());
			assertEquals("c",br.readLine());
			assertEquals("d",br.readLine());
			assertEquals("e",br.readLine());
			assertEquals("f",br.readLine());
			assertEquals("g",br.readLine());
			assertEquals("Station,Connected station,Distance between them",br.readLine());
			assertEquals("a,c,6.0",br.readLine());
			assertEquals("b,a,5.0",br.readLine());
			assertEquals("a,d,4.0",br.readLine());
			assertNull(br.readLine());
			br.close();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testFindShortestPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckNetworkData() {
		fail("Not yet implemented");
	}
}