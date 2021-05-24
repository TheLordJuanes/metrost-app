/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import model.Metrost;
import thread.WelcomeThread;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MetrostGUI {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

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

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Label getLbWelcome() {
        return lbWelcome;
    }

    public void setLbWelcome(Label lbWelcome) {
        this.lbWelcome = lbWelcome;
    }

    public MetrostGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
        labelChange = true;
        new WelcomeThread(this).start();
    }

    public boolean getLabelChange() {
        return labelChange;
    }

    @FXML
    public void goBackToStart(ActionEvent event) {
        try {
            labelChange = true;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("metrost.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-metrost.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Metrost");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void stationModule(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("station-module.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Metrost");
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
            stage.setTitle("Station addition");
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addStation(ActionEvent event) {

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
            primaryStage.setTitle("Metrost");
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addAConnection(ActionEvent event) {

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
            primaryStage.setTitle("Metrost");
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
}