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

        getSuppliers();


        //when the table view is selected

        tvSuppliers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
            @Override
            public void changed(ObservableValue<? extends Supplier> observableValue, Supplier supplier, Supplier t1) {
                if (tvSuppliers.getSelectionModel().isSelected(tvSuppliers.getSelectionModel().getSelectedIndex()))
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            txtSupName.setText(t1.getSupName());
                            txtSupplierId.setText(String.valueOf(t1.getSupplierId()));
                            txtSupplierId.setDisable(true);
                            mode = "edit";


                        }
                    });
            }
        });


        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnSaveClicked(mouseEvent);
            }
        });



        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            btnAdd.setDisable(true);
            btnDelete.setDisable(true);
            txtSupplierId.setDisable(true);

            }
        });

        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                try {
                    Connection conn = DB.createConnection();

                    String sql = "DELETE FROM `suppliers` WHERE SupplierId=?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, Integer.parseInt(txtSupplierId.getText()));
                    int numRows = stmt.executeUpdate();
                    if (numRows == 0) {
                        System.out.println("update failed");
                    }
                    conn.close();

                    Node node = (Node) mouseEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    //get reference to stage and close it
                } catch (SQLIntegrityConstraintViolationException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete failed");
                    alert.setContentText("Agent has customers and cannot be deleted");
                    alert.showAndWait();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                btnDelete.setDisable(true);
                btnEdit.setDisable(true);
                btnSave.setDisable(true);
                addSupplier();
            }
        });

    }

    public void btnSaveClicked(MouseEvent mouseEvent) {

                 try {
                     Connection conn = DB.createConnection();

                     String sql = null;
                     //if mode is "edit", do an update, else, do an insert
                     if (mode.equals("edit")) {
                         sql = "UPDATE `suppliers` SET `SupName`=? WHERE SupplierId=?";
                     }
                     else
                     {
                         sql = "INSERT INTO `suppliers`(`SupplierId`, `SupName`) VALUES (null,?)";
                     }
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     stmt.setString(1, txtSupName.getText());


                     //if we are in "edit" mode there is a second insert to do to set the SupplierId
                     if (mode.equals("edit")) {
                         stmt.setInt(2, Integer.parseInt(txtSupplierId.getText()));
                     }
                     int numRows = stmt.executeUpdate();
                     if (numRows == 0)
                     {
                         System.out.println("update failed");
                     }
                     conn.close();

                     Node node = (Node) mouseEvent.getSource();
                     Stage stage = (Stage) node.getScene().getWindow();
                     stage.close();

                     //get reference to stage and close it
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }




    public void processSupplier(Supplier s) {
        txtSupplierId.setText(s.getSupplierId() + "");
        txtSupName.setText(s.getSupName());
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
        String name = txtSupName.getText();
        //txtSupplierId.setDisable(true);

        Connection conn = DB.createConnection();
        String sql = "INSERT INTO `suppliers`(`SupName`) VALUES (?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            //first column is the Supplier ID, name is the supplier name column
            stmt.setString(1, name);
            int numRows = stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getSuppliers();
        txtSupName.clear();
    }


}

