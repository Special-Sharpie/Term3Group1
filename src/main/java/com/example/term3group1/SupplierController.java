package com.example.term3group1;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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

public class SupplierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private TableColumn<Supplier, Integer> colSupplierId;

    @FXML
    private TableColumn<Supplier, String> colsupName;

    @FXML
    private TableView<Supplier> tvSuppliers;

    @FXML
    private TextField txtSupName;

    @FXML
    private TextField txtSupplierId;

    private ObservableList<Supplier> data = FXCollections.observableArrayList();

    private int selectedIndex;


    @FXML
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'supplier-view.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'supplier-view.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'supplier-view.fxml'.";
        assert colSupplierId != null : "fx:id=\"colSupplierId\" was not injected: check your FXML file 'supplier-view.fxml'.";
        assert colsupName != null : "fx:id=\"colsupName\" was not injected: check your FXML file 'supplier-view.fxml'.";
        assert tvSuppliers != null : "fx:id=\"tvSuppliers\" was not injected: check your FXML file 'supplier-view.fxml'.";
        assert txtSupName != null : "fx:id=\"txtSupName\" was not injected: check your FXML file 'supplier-view.fxml'.";
        assert txtSupplierId != null : "fx:id=\"txtSupplierId\" was not injected: check your FXML file 'supplier-view.fxml'.";

        colSupplierId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("SupplierId"));
        colsupName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SupName"));
        tvSuppliers.setItems(data);

        getSuppliers();


        //when the table view is selected

//        tvSuppliers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
//            @Override
//            public void changed(ObservableValue<? extends Supplier> observableValue, Supplier supplier, Supplier t1) {
//                if(tvSuppliers.getSelectionModel().isSelected(tvSuppliers.getSelectionModel().getSelectedIndex()))
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        txtSupName.setText(t1.getSupName());
//                        //txtSupplierId.setText(String.valueOf(t1.getSupplierId()));
//
//
//                    }
//                });
//            }
//        });

        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });

        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });


        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //save data to database


            }
        });


    }

    public void processSupplier(Supplier s) {
        txtSupplierId.setText(s.getSupplierId() + "");
        txtSupName.setText(s.getSupName());
    }


    private void getSuppliers() {
        data.clear();

        //load the agents from the database
        String user = "";
        String password = "";
        String url = "";
        try {
            FileInputStream fis = new FileInputStream("c:\\connection.properties");
            Properties p = new Properties();
            p.load(fis);
            url = (String) p.get("url");
            user = (String) p.get("user");
            password = (String) p.get("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from suppliers");
            while (rs.next())
            {
                data.add(new Supplier(rs.getInt(1), rs.getString(2)));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

