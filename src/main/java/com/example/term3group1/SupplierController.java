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
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SupplierController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSave;

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

    private String mode;

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
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'supplier-view.fxml'.";

        colSupplierId.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("SupplierId"));
        colsupName.setCellValueFactory(new PropertyValueFactory<Supplier, String>("SupName"));
        tvSuppliers.setItems(data);
        btnEdit.setDisable(true);
        getSuppliers();


        //when the table view is selected

        tvSuppliers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
            @Override
            public void changed(ObservableValue<? extends Supplier> observableValue, Supplier supplier, Supplier t1) {
                if (tvSuppliers.getSelectionModel().isSelected(tvSuppliers.getSelectionModel().getSelectedIndex()))
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            btnAdd.setDisable(true);
                            btnEdit.setDisable(false);
                            btnDelete.setDisable(false);
                           txtSupName.setDisable(true);


                            txtSupName.setText(t1.getSupName());
                            txtSupplierId.setText(String.valueOf(t1.getSupplierId()));
                            txtSupplierId.setDisable(true);
                         


                        }
                    });
            }
        });


        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                saveSuppliers();
            }
        });



        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            editSuppliers();

            }
        });

        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deleteSuppliers();
            }
        });



        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addSupplier();
            }
        });

    }

    public void saveSuppliers() {

        Connection conn = DB.createConnection();
        String sql = "UPDATE `suppliers` SET `SupName`=? WHERE `SupplierId`=?";

        try {



            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtSupName.getText());
            stmt.setInt(2, Integer.parseInt(txtSupplierId.getText()));
            int numRows = stmt.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        getSuppliers();
        clearFields();
    }



private void editSuppliers(){

    btnAdd.setDisable(true);
    btnEdit.setDisable(true);
    btnSave.setDisable(false);

    txtSupName.setDisable(false);

}




    private void deleteSuppliers(){

        Connection conn = DB.createConnection();


            String sql = "delete from suppliers where `SupplierId`=?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtSupplierId.getText()));
            int numRows = stmt.executeUpdate();

            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        getSuppliers();

        clearFields();
    }



    private void getSuppliers() {
        data.clear();
        Connection conn = DB.createConnection();
        Statement stmt = null;

        try {
            //Connection conn = DB.createConnection();
            stmt = conn.createStatement();
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

    private void addSupplier() {

        Connection conn = DB.createConnection();
        String sql = "INSERT INTO `suppliers`(`SupplierId`,`SupName`) VALUES (null,?)";
//        String sql = "INSERT INTO sup, ps " +
//                "from products_suppliers ps join suppliers sup " +
//                "on sup.SupplierId = ps.ProductId " +
//                "WHERE SupplierId=?";


        PreparedStatement stmt = null;
        try {


            stmt = conn.prepareStatement(sql);
            //first column is the Supplier ID, name is the supplier name column
            stmt.setString(1, txtSupName.getText());
            int numRows = stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getSuppliers();
        txtSupName.clear();
        //clearFields();
    }

    private void clearFields() {
        txtSupName.setText("");
        txtSupplierId.setText("");
        btnEdit.setDisable(false);
        btnSave.setDisable(false);
        btnAdd.setDisable(false);
        btnDelete.setDisable(false);
        txtSupName.setDisable(false);
    }
}

