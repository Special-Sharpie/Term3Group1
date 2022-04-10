package com.example.term3group1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Supplier {

     SimpleIntegerProperty SupplierId;
     SimpleStringProperty SupName;

//constructor for the supplier class
    public Supplier(Integer supplierId, String supName) {
        SupplierId = new SimpleIntegerProperty(supplierId);
        SupName = new SimpleStringProperty(supName);


    }


    //getters and setters for the supplier class

    public Integer getSupplierId() {
        return SupplierId.get();
    }

    public SimpleIntegerProperty supplierIdProperty() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        this.SupplierId.set(supplierId);
    }

    public String getSupName() {
        return SupName.get();
    }

    public SimpleStringProperty supNameProperty() {
        return SupName;
    }

    public void setSupName(String supName) {
        this.SupName.set(supName);
    }
}
