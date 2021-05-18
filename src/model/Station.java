package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Station extends RecursiveTreeObject<Station> {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private String name;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}