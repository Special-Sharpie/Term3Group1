/*
Daniel Palmer
PROJ-207-A
Workshop 8 - JAVAFX App
2022-05-03
 */

package com.example.term3group1;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


public class ProductsController {
    // Declare Page Controls
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
    private Button btnClear;
    
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
    void initialize() throws ClassNotFoundException {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnAgents != null : "fx:id=\"btnAgents\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'products-view.fxml'.";
        assert btnSuppliers != null : "fx:id=\"btnSuppliers\" was not injected: check your FXML file 'products-view.fxml'.";
        assert tblProducts != null : "fx:id=\"tblProducts\" was not injected: check your FXML file 'products-view.fxml'.";
        assert colProdName != null : "fx:id=\"colProdName\" was not injected: check your FXML file 'products-view.fxml'.";
        assert colProductId != null : "fx:id=\"colProductId\" was not injected: check your FXML file 'products-view.fxml'.";
        assert tbProdName != null : "fx:id=\"tbProdName\" was not injected: check your FXML file 'products-view.fxml'.";
        assert tbProductId != null : "fx:id=\"tbProductId\" was not injected: check your FXML file 'products-view.fxml'.";

        // Load all products from database into table view
        colProductId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("ProductId"));
        colProdName.setCellValueFactory(new PropertyValueFactory<Product, String>("ProdName"));
        tblProducts.setItems(data);
        getProducts();

        // Handles the selection change of the table view, loading with page fields withe selected product data
        tblProducts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                if (tblProducts.getSelectionModel().isSelected(tblProducts.getSelectionModel().getSelectedIndex()))
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            // Enables and disables buttons for ease of use
                            btnAdd.setDisable(true);
                            btnEdit.setDisable(false);
                            btnDelete.setDisable(false);
                            tbProdName.setDisable(true);
                            tbProdName.setText(t1.getProdName());
                            tbProductId.setText(String.valueOf(t1.getProductId()));
                        }
                    });
                }
            });
        // Handles the Add Button on click event, calls addProduct method
        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
					addProduct();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        // Handles the Clear Button on click event, calls clearField method
        btnClear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearFields();
            }
        });

        // Handles the Edit Button on click event, calls editProduct method
        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                editProduct();
            }
        });
        // Handles the Delete Button on click event, calls deleteProduct method
        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
					deleteProduct();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
            }
        });
        // Handles the Save Button on click event, calls saveProductChanges method
        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
					saveProductChanges();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
    // Saves the requested product changes to the database
    private void saveProductChanges() throws ClassNotFoundException {
        Connection con = DB.createConnection();
        String sql = "update `products` set `ProdName`=? where `ProductId`=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tbProdName.getText());
            stmt.setInt(2, Integer.parseInt(tbProductId.getText()));
            int numRows = stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getProducts();
        clearFields();
    }
    // Removes the requested product from the database
    // (likely will not work on existing products due to foreign key constraints that have not been handled)
    private void deleteProduct() throws ClassNotFoundException {
        Connection con = DB.createConnection();
        String sql = "delete from products where `ProductId`=?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(tbProductId.getText()));
            int numRows = stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getProducts();
        clearFields();
    }
    // Alters the page controls to allow for data modification
    private void editProduct() {
        btnAdd.setDisable(true);
        btnEdit.setDisable(true);
        btnSave.setDisable(false);
        tbProdName.setDisable(false);
    }
    // Resets the page, clearing all values from the controls, without saving changes made to products
    private void clearFields() {
        tbProdName.setText("");
        tbProductId.setText("");
        btnEdit.setDisable(true);
        btnSave.setDisable(true);
        btnAdd.setDisable(false);
        tbProdName.setDisable(false);
    }

    // Add a product to the database
    private void addProduct() throws ClassNotFoundException {
        String name = tbProdName.getText();

        Connection con = null;
		try {
			con = DB.createConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
    // Selects all the products from the date base
    private void getProducts() throws ClassNotFoundException {
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
