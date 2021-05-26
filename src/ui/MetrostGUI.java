/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import model.Metrost;
import model.Station;
import thread.WelcomeThread;
import java.io.File;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MetrostGUI {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    @FXML
    private JFXButton btnCurrentNetwork;

    @FXML
    private JFXButton btnDesignedNetwork;

    @FXML
    private JFXTreeTableView<Station> tvNetwork;

    @FXML
    private JFXButton btnAddConnection;

    @FXML
    private JFXComboBox<String> cbAddConnection1;

    @FXML
    private JFXComboBox<String> cbAddConnection2;

    @FXML
    private JFXTextField txtDistance;

    @FXML
    private Label lbWelcome;

    @FXML
    private JFXComboBox<String> cbStations;

    @FXML
    private JFXButton btnAddStation;

    @FXML
    private JFXButton btnModifyStation;

    @FXML
    private JFXTextField txtName;

    private boolean labelChange;

    private Stage primaryStage;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private Metrost metrost;
    private WelcomeThread welcomeThread;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public MetrostGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        metrost = new Metrost();
        labelChange = true;
        welcomeThread = new WelcomeThread(this);
        welcomeThread.start();
    }

    public boolean getLabelChange() {
        return labelChange;
    }

    public Label getLbWelcome() {
        return lbWelcome;
    }

    public void setLbWelcome(Label lbWelcome) {
        this.lbWelcome = lbWelcome;
    }

    @FXML
    public void networkForm(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("networkForm.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Transportation network");
            stage.show();
            btnDesignedNetwork.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    textFileNetworkAddition(event);
                }
            });
            btnCurrentNetwork.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                    goToMenu(event);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void textFileNetworkAddition(ActionEvent event) {
        // CORREGIR FORMATO
        showWarningAlert("Text Input Format", "The data of the players must be in this order separated by a coma \",\"", "firstName,lastName,team,age,trueShooting,usage,assist,rebound,defensive,blocks");
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            try {
                metrost.addNetwork(file);
                goToMenu(event);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (CsvException ioe) {
                ioe.printStackTrace();
            }
        } else
            showInformationAlert("Missing File", null, "No file was selected ");
    }

    @FXML
    public void goBackToStart(ActionEvent event) {
        try {
            labelChange = true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("metrost.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            welcomeThread = new WelcomeThread(this);
            welcomeThread.start();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Metro Trost");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void goToMenu(ActionEvent event) {
        try {
            labelChange = false;
            welcomeThread.join();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-metrost.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Metrost");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void stationModule(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("station-module.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Station module");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addAStation(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-station.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            btnAddStation.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (txtName.getText().isEmpty())
                        showWarningAlert("Missing station name", null, "The text field must be filled!");
                    else
                        addStation(event);
                }
            });
            stage.setTitle("Station addition");
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addStation(ActionEvent event) {
        try {
            metrost.addStation(txtName.getText());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void modifyAStation(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify-station.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Station modification");
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void modifyStation(ActionEvent event) {

    }

    @FXML
    public void deleteAStation(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-station.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Station deletion");
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void deleteStation(ActionEvent event) {

    }

    @FXML
    public void connectionModule(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("connection-module.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Connection module");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addAConnection(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-connection.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Connection addition");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addConnection(ActionEvent event) {

    }

    @FXML
    public void modifyAConnection(ActionEvent event) {

    }

    @FXML
    public void deleteAConnection(ActionEvent event) {

    }

    @FXML
    public void queryModule(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("query-module.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Query module");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void findShortestPath(ActionEvent event) {

    }

    @FXML
    public void showNetworkData(ActionEvent event) {

    }

    public void showInformationAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void showWarningAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}