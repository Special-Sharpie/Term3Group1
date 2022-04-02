package com.example.term3group1;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.crypto.spec.PSource;

public class ProductsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAgents;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSuppliers;

    @FXML
    private TextField tbProdName;

    @FXML
    private TextField tbProductId;

    @FXML
    private TableColumn<Product, String> colProdName;

    @FXML
    private TableColumn<Product, Integer> colProductId;

    @FXML
    private TableView<Product> tblProducts;

    ObservableList<Product> data = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnAgents != null : "fx:id=\"btnAgents\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnSuppliers != null : "fx:id=\"btnSuppliers\" was not injected: check your FXML file 'products-view.fxml'.";
        assert tblProducts != null : "fx:id=\"tblProducts\" was not injected: check your FXML file 'products-view.fxml'.";
        assert colProdName != null : "fx:id=\"colProdName\" was not injected: check your FXML file 'products-view.fxml'.";
        assert colProductId != null : "fx:id=\"colProductId\" was not injected: check your FXML file 'products-view.fxml'.";
        assert tbProdName != null : "fx:id=\"tbProdName\" was not injected: check your FXML file 'products-view.fxml'.";
        assert tbProductId != null : "fx:id=\"tbProductId\" was not injected: check your FXML file 'products-view.fxml'.";


        colProductId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("ProductId"));
        colProdName.setCellValueFactory(new PropertyValueFactory<Product, String>("ProdName"));
        tblProducts.setItems(data);
        getProducts();

        //tblProducts.getSelectionModel().selectedItemProperty().addListener();

        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addProduct();
            }
        });
    }

    private void addProduct() {
        String name = tbProdName.getText();

        Connection con = DB.createConnection();
        String sql = "INSERT INTO `products`(`ProdName`) VALUES (?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            int numRows = stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getProducts();
        tbProdName.clear();
    }

    private void getProducts() {
        data.clear();
        Connection con = DB.createConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");
            while (rs.next()){
                data.add(new Product(rs.getInt(1), rs.getString(2)));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
