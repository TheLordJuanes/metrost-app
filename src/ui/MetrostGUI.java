/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: June, 1st 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import model.Metrost;
import javafx.stage.Stage;

public class MetrostGUI {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private Stage primaryStage;

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private Metrost metrost;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    public MetrostGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}