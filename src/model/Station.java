package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Station extends RecursiveTreeObject<Station> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private StringProperty name;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Station(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }
}