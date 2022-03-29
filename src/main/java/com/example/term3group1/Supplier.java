package com.example.term3group1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Supplier {

    private SimpleIntegerProperty supplierId;
    private SimpleStringProperty supName;

//constructor for the supplier class
    public Supplier(int supplierId, String supName) {
        this.supplierId = new SimpleIntegerProperty(supplierId);
        this.supName = new SimpleStringProperty(supName);
    }


    //getters and setters for the supplier class

    public Integer getSupplierId() {
        return supplierId.get();
    }

    public SimpleIntegerProperty supplierIdProperty() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId.set(supplierId);
    }

    public String getSupName() {
        return supName.get();
    }

    public SimpleStringProperty supNameProperty() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName.set(supName);
    }
}
