package com.example.term3group1;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    SimpleIntegerProperty ProductId;
    SimpleStringProperty ProdName;

    public int getProductId() {
        return ProductId.get();
    }

    public SimpleIntegerProperty productIdProperty() {
        return ProductId;
    }

    public void setProductId(int productId) {
        this.ProductId.set(productId);
    }

    public String getProdName() {
        return ProdName.get();
    }

    public SimpleStringProperty prodNameProperty() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        this.ProdName.set(prodName);
    }

    public Product(Integer productId, String prodName) {
        ProductId = new SimpleIntegerProperty(productId);
        ProdName = new SimpleStringProperty(prodName);
    }

}
