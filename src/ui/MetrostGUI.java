/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 3rd 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import model.Metrost;
import thread.WelcomeThread;
import java.io.File;
import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.opencsv.exceptions.CsvException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private JFXTextArea taInfo;

    @FXML
    private JFXButton btnFindPaths;

    @FXML
    private JFXButton btnDeleteStation;

    @FXML
    private Label lbAddStation;

    @FXML
    private Label lbModifyStation;

    @FXML
    private Label lbDeleteStation;

    @FXML
    private Label lbAddConnection;

    @FXML
    private JFXButton btnModifyConnection;

    @FXML
    private Label lbModifyConnection1;

    @FXML
    private Label lbModifyConnection2;

    @FXML
    private Label lbDeleteConnection;

    @FXML
    private JFXButton btnCurrentNetwork;

    @FXML
    private JFXButton btnDesignedNetwork;

    @FXML
    private JFXButton btnAddConnection;

    @FXML
    private JFXButton btnDeleteConnection;

    @FXML
    private JFXTextField txtDistance;

    @FXML
    private Label lbWelcome;

    @FXML
    private Label lbSentence1;

    @FXML
    private Label lbSentence2;

    @FXML
    private Label lbSentence3;

    @FXML
    private JFXComboBox<String> cbStations;

    @FXML
    private JFXComboBox<String> cbStations2;

    @FXML
    private JFXComboBox<String> cbStations3;

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

    public Label getLbSentence1() {
        return lbSentence1;
    }

    public Label getLbSentence2() {
        return lbSentence2;
    }

    public Label getLbSentence3() {
        return lbSentence3;
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
                    loadCurrentNetwork(event);
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
        showWarningAlert("Text File Input Format", "The network data must be as follows:", "In the first line, the word \"Number of stations\" is written.\nIn the second line, there is a number n of stations.\nIn the third line, the word \"Stations\" is written.\nThen, n lines follow, each one with the name of a station.\nNext, the title \"Station,Connected station,Distance between them\" where each item is separated by a coma is written.\nFinally, all the connections between two stations with their distance between them are written with the same format of the last title.");
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            try {
                metrost.addDesignedNetwork(file);
                goToMenu(event);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (CsvException ioe) {
                ioe.printStackTrace();
            }
        } else
            showInformationAlert("Missing File", null, "No file was selected");
    }

    @FXML
    public void loadCurrentNetwork(ActionEvent event) {
        File file = new File(metrost.getFileName());
        if (file != null && file.length() != 0) {
            try {
                metrost.addCurrentNetWork(file);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
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
        } catch (InterruptedException ie) {
            ie.printStackTrace();
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
            if (metrost.addStation(txtName.getText()))
                lbAddStation.setText("Station successfully added!");
            else {
                lbAddStation.setText("This station already exists!");
            }
            txtName.setText("");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException csve) {
            csve.printStackTrace();
        }
    }

    @FXML
    public void modifyAStation(ActionEvent event) {
        if (metrost.getStations().isEmpty())
            showInformationAlert("Non-existent stations", null, "There are no stations added to your transportation network design yet.");
        else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify-station.fxml"));
                fxmlLoader.setController(this);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Station modification");
                stage.show();
                ObservableList<String> observableList = FXCollections.observableArrayList(metrost.getStations());
                cbStations.setItems(observableList);
                cbStations.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (cbStations.getValue() != null)
                            btnModifyStation.setDisable(false);
                    }
                });
                btnModifyStation.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (txtName.getText().isEmpty() || cbStations.getValue() == null)
                            showWarningAlert("Missing data", null, "Fill the required data.");
                        else
                            modifyStation(event);
                    }
                });
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    @FXML
    public void modifyStation(ActionEvent event) {
        try {
            if (metrost.modifyStation(cbStations.getValue(), txtName.getText())) {
                lbModifyStation.setText("Done!");
                ObservableList<String> observableList = FXCollections.observableArrayList(metrost.getStations());
                cbStations.setItems(observableList);
            } else {
                showWarningAlert("Modification process interrupted", null, "Another station already has that name.");
            }
            txtName.setText("");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException csve) {
            csve.printStackTrace();
        }
    }

    @FXML
    public void deleteAStation(ActionEvent event) {
        if (metrost.getStations().isEmpty())
            showInformationAlert("Non-existent stations", null, "There are no stations added to your transportation network design yet.");
        else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-station.fxml"));
                fxmlLoader.setController(this);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Station deletion");
                stage.show();
                ObservableList<String> observableList = FXCollections.observableArrayList(metrost.getStations());
                cbStations.setItems(observableList);
                cbStations.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (cbStations.getValue() != null)
                            btnDeleteStation.setDisable(false);
                    }
                });
                btnDeleteStation.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (cbStations.getValue() == null)
                            showWarningAlert("Missing station", null, "You must choose a station!");
                        else
                            deleteStation(event);
                    }
                });
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    @FXML
    public void deleteStation(ActionEvent event) {
        try {
            metrost.deleteStation(cbStations.getValue());
            ObservableList<String> observableList = FXCollections.observableArrayList(metrost.getStations());
            cbStations.setItems(observableList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException ioe) {
            ioe.printStackTrace();
        }
        lbDeleteStation.setText("Station successfully deleted!");
    }

    @FXML
    public void connectionModule(ActionEvent event) {
        if (metrost.getStations().size() < 2)
            showInformationAlert("Insufficient stations", null, "Not enough stations added to your transportation network design to open this module.");
        else {
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
    }

    @FXML
    public void addAConnection(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-connection.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            txtDistance.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("^[0-9]*+([.][0-9]*)?$"))
                        txtDistance.setText(oldValue);
                }
            });
            primaryStage.setTitle("Connection addition");
            primaryStage.show();
            ObservableList<String> observableList = FXCollections.observableArrayList(metrost.getStations());
            cbStations.setItems(observableList);
            cbStations2.setItems(observableList);
            cbStations.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations.getValue() != null)
                        btnAddConnection.setDisable(false);
                }
            });
            cbStations2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations2.getValue() != null)
                        btnAddConnection.setDisable(false);
                }
            });
            btnAddConnection.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations.getValue().equals(cbStations2.getValue()) && !txtDistance.getText().isEmpty())
                        showErrorAlert("Connection failed", null, "A station can't be connected to itself.");
                    else if (txtDistance.getText().isEmpty())
                        showWarningAlert("Missing data", null, "The distance between the two stations must be filled.");
                    else
                        addConnection(event);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void addConnection(ActionEvent event) {
        try {
            if (metrost.addConnection(cbStations.getValue(), cbStations2.getValue(), Double.parseDouble(txtDistance.getText()))) {
                lbAddConnection.setText("Done!");
                txtDistance.setText("");
                cbStations.setValue(null);
                cbStations2.setValue(null);
            } else
                showErrorAlert("Connection addition failed", null, "A connection already exists between these two stations.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException csve) {
            csve.printStackTrace();
        }
    }

    @FXML
    public void modifyAConnection(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify-connection.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            txtDistance.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("^[0-9]*+([.][0-9]*)?$"))
                        txtDistance.setText(oldValue);
                }
            });
            primaryStage.setTitle("Connection modification");
            primaryStage.show();
            ObservableList<String> observableList = FXCollections.observableArrayList(metrost.getStations());
            cbStations.setItems(observableList);
            cbStations2.setItems(observableList);
            cbStations.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations.getValue() != null)
                        btnModifyConnection.setDisable(false);
                }
            });
            cbStations2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations2.getValue() != null)
                        btnModifyConnection.setDisable(false);
                }
            });
            btnModifyConnection.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations.getValue().equals(cbStations2.getValue()) && !txtDistance.getText().isEmpty())
                        showErrorAlert("Connection modification failed", null, "A station can't be connected to itself.");
                    else if (txtDistance.getText().isEmpty())
                        showWarningAlert("Missing data", null, "The distance between the two stations must be filled.");
                    else if (Double.parseDouble(txtDistance.getText()) == 0)
                        showErrorAlert("Connection modification failed", null, "A station can't have distance 0 to another station.");
                    else
                        modifyConnection(event);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void modifyConnection(ActionEvent event) {
        try {
            if (metrost.modifyConnection(cbStations.getValue(), cbStations2.getValue(), Double.parseDouble(txtDistance.getText()))) {
                lbModifyConnection1.setText("Connection distance");
                lbModifyConnection2.setText("successfully modified!");
            } else {
                showErrorAlert("Connection modification failed", null, "There isn't a connection from " + cbStations.getValue() + " to " + cbStations2.getValue() + ".");
            }
            cbStations.setValue(null);
            cbStations2.setValue(null);
            txtDistance.setText("");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException csve) {
            csve.printStackTrace();
        }
    }

    @FXML
    public void deleteAConnection(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-connection.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Connection elimination");
            primaryStage.show();
            ObservableList<String> observableList = FXCollections.observableArrayList(metrost.getStations());
            cbStations.setItems(observableList);
            cbStations2.setItems(observableList);
            cbStations.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations.getValue() != null)
                        btnDeleteConnection.setDisable(false);
                }
            });
            cbStations2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations2.getValue() != null)
                        btnDeleteConnection.setDisable(false);
                }
            });
            btnDeleteConnection.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations.getValue().equals(cbStations2.getValue()))
                        showErrorAlert("Connection deletion failed", null, "A station can't be connected to itself.");
                    else
                        deleteConnection(event);
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void deleteConnection(ActionEvent event) {
        try {
            if (metrost.deleteConnection(cbStations.getValue(), cbStations2.getValue()))
                lbDeleteConnection.setText("Connection successfully deleted!");
            else {
                showErrorAlert("Connection deletion failed", null, "There isn't a connection from " + cbStations.getValue() + " to " + cbStations2.getValue() + ".");
            }
            cbStations.setValue(null);
            cbStations2.setValue(null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (CsvException csve) {
            csve.printStackTrace();
        }
    }

    @FXML
    public void queryModule(ActionEvent event) {
        if (metrost.getStations().isEmpty())
            showInformationAlert("Non-existent stations", null, "There are no stations added to your transportation network design yet.");
        else {
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
    }

    @FXML
    public void findShortestPath(ActionEvent event) {
        if (metrost.getStations().size() < 2)
            showInformationAlert("Insufficient stations", null, "Not enough stations added to your transportation network design to execute this function.");
        else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shortest-path.fxml"));
                fxmlLoader.setController(this);
                Parent root = fxmlLoader.load();
                primaryStage.setScene(new Scene(root));
                primaryStage.setTitle("Shortest path search");
                primaryStage.show();
                ObservableList<String> observableList = FXCollections.observableArrayList(metrost.getStations());
                cbStations3.setItems(observableList);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            cbStations3.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations3.getValue() != null)
                        btnFindPaths.setDisable(false);
                }
            });
            btnFindPaths.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (cbStations3.getValue() == null)
                        taInfo.setText("");
                    else
                        findPaths(event);
                }
            });
        }
    }

    @FXML
    public void findPaths(ActionEvent event) {
        taInfo.setText(metrost.findShortestPath(cbStations3.getValue()));
    }

    @FXML
    public void showNetworkData(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("network.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Network Data");
            primaryStage.show();
            taInfo.setText(metrost.showNetworkData());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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

    public void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}