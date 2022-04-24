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

public class CustomerController {

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
    private TableColumn<Customer, Integer> colCustomerId;

    @FXML
    private TableColumn<Customer, String> colCustFirstName;

    @FXML
    private TableColumn<Customer, String> colCustLastName;

    @FXML
    private TableColumn<Customer, String> colCustAddress;

    @FXML
    private TableColumn<Customer, String> colCustCity;

    @FXML
    private TableColumn<Customer, String> colCustProv;

    @FXML
    private TableColumn<Customer, String> colCustPostal;

    @FXML
    private TableColumn<Customer, String> colCustCountry;

    @FXML
    private TableColumn<Customer, String> colCustHomePhone;

    @FXML
    private TableColumn<Customer, String> colCustBusPhone;

    @FXML
    private TableColumn<Customer, String> colCustEmail;

    @FXML
    private TableColumn<Customer, Integer> colAgentId;

    @FXML
    private TableView<Customer> tvCustomers;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustFirstName;

    @FXML
    private TextField txtCustLastName;

    @FXML
    private TextField txtCustAddress;

    @FXML
    private TextField txtCustCity;

    @FXML
    private TextField txtCustProv;

    @FXML
    private TextField txtCustPostal;

    @FXML
    private TextField txtCustCountry;

    @FXML
    private TextField txtCustHomePhone;

    @FXML
    private TextField txtCustBusPhone;

    @FXML
    private TextField txtCustEmail;

    @FXML
    private TextField txtAgentId;

    private ObservableList<Customer> data = FXCollections.observableArrayList();

    private int selectedIndex;

    private String mode;

    @FXML
    void initialize() throws ClassNotFoundException {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustomerId != null : "fx:id=\"colCustomerId\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustFirstName != null : "fx:id=\"colCustFirstName\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustLastName != null : "fx:id=\"colCustLastName\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustAddress != null : "fx:id=\"colCustAddress\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustCity != null : "fx:id=\"colCustCity\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustProv != null : "fx:id=\"colCustProv\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustPostal != null : "fx:id=\"colCustPostal\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustCountry != null : "fx:id=\"colCustCountry\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustHomePhone != null : "fx:id=\"colCustHomePhone\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustBusPhone != null : "fx:id=\"colCustBusPhone\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colCustEmail != null : "fx:id=\"colCustEmail\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert colAgentId != null : "fx:id=\"colAgentId\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert tvCustomers != null : "fx:id=\"tvCustomers\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustomerId != null : "fx:id=\"txtCustomerId\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustFirstName != null : "fx:id=\"txtCustFirstName\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustLastName != null : "fx:id=\"txtCustLastName\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustAddress != null : "fx:id=\"txtCustAddress\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustCity != null : "fx:id=\"txtCustCity\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustProv != null : "fx:id=\"txtCustProv\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustPostal != null : "fx:id=\"txtCustPostal\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustCountry != null : "fx:id=\"txtCustCountry\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustHomePhone != null : "fx:id=\"txtCustHomePhone\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustBusPhone != null : "fx:id=\"txtCustBusPhone\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtCustEmail != null : "fx:id=\"txtCustEmail\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert txtAgentId != null : "fx:id=\"txtAgentId\" was not injected: check your FXML file 'customer-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'customer-view.fxml'.";

        colCustomerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CustomerId"));
        colCustFirstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustFirstName"));
        colCustLastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustLastName"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustAddress"));
        colCustCity.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustCity"));
        colCustProv.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustProv"));
        colCustPostal.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustPostal"));
        colCustCountry.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustCountry"));
        colCustHomePhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustHomePhone"));
        colCustBusPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustBusPhone"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustEmail"));
        colAgentId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("AgentId"));
        tvCustomers.setItems(data);
        btnEdit.setDisable(true);
        getCustomers();


        //when the table view is selected

        tvCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer customer, Customer t1) {
                if (tvCustomers.getSelectionModel().isSelected(tvCustomers.getSelectionModel().getSelectedIndex()))
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            btnAdd.setDisable(true);
                            btnEdit.setDisable(false);
                            btnDelete.setDisable(false);
                            txtCustFirstName.setDisable(true);
                            txtCustLastName.setDisable(true);
                            txtCustAddress.setDisable(true);
                            txtCustCity.setDisable(true);
                            txtCustProv.setDisable(true);
                            txtCustPostal.setDisable(true);
                            txtCustCountry.setDisable(true);
                            txtCustBusPhone.setDisable(true);
                            txtCustHomePhone.setDisable(true);
                            txtCustEmail.setDisable(true);
                            txtAgentId.setDisable(true);
                            
                            txtCustFirstName.setText(t1.getCustFirstName());
                            txtCustLastName.setText(t1.getCustLastName());
                            txtCustAddress.setText(t1.getCustAddress());
                            txtCustCity.setText(t1.getCustCity());
                            txtCustProv.setText(t1.getCustProv());
                            txtCustPostal.setText(t1.getCustPostal());
                            txtCustCountry.setText(t1.getCustCountry());
                            txtCustBusPhone.setText(t1.getCustBusPhone());
                            txtCustHomePhone.setText(t1.getCustHomePhone());
                            txtCustEmail.setText(t1.getCustEmail());
                            txtAgentId.setText(String.valueOf(t1.getAgentId()));
                            txtCustomerId.setText(String.valueOf(t1.getCustomerId()));
                            txtCustomerId.setDisable(true);
                        }
                    });
            }
        });


        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
					saveCustomers();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });



        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            editCustomers();

            }
        });

        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
					deleteCustomers();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });



        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
					addCustomer();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

    }

    public void saveCustomers() throws ClassNotFoundException {

        Connection conn = DB.createConnection();
        String sql = "UPDATE `customers` SET `CustFirstName`=?,`CustLastName`=?,`CustAddress`=?,`CustCity`,`CustProv`=?,`CustPostal`=?,`CustCountry`=?,`CustHomePhone`=?,`CustBusPhone`=?,`CustEmail`=?,`AgentId`=? WHERE `CustomerId`=?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtCustFirstName.getText());
            stmt.setString(2, txtCustLastName.getText());
            stmt.setString(3, txtCustAddress.getText());
            stmt.setString(4, txtCustCity.getText());
            stmt.setString(5, txtCustProv.getText());
            stmt.setString(6, txtCustPostal.getText());
            stmt.setString(7, txtCustCountry.getText());
            stmt.setString(5, txtCustHomePhone.getText());
            stmt.setString(6, txtCustBusPhone.getText());
            stmt.setString(7, txtCustEmail.getText());
            stmt.setInt(11, Integer.parseInt(txtAgentId.getText()));
            stmt.setInt(12, Integer.parseInt(txtCustomerId.getText()));
            int numRows = stmt.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        getCustomers();
        clearFields();
    }



private void editCustomers(){

    btnAdd.setDisable(true);
    btnEdit.setDisable(true);
    btnSave.setDisable(false);

    txtCustFirstName.setDisable(false);
    txtCustLastName.setDisable(false);
    txtCustAddress.setDisable(false);
    txtCustCity.setDisable(false);
    txtCustProv.setDisable(false);
    txtCustPostal.setDisable(false);
    txtCustCountry.setDisable(false);
    txtCustHomePhone.setDisable(false);
    txtCustBusPhone.setDisable(false);
    txtCustEmail.setDisable(false);
    txtAgentId.setDisable(false);
}




    private void deleteCustomers() throws ClassNotFoundException{

        Connection conn = DB.createConnection();


            String sql = "delete from customers where `CustomerId`=?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtCustomerId.getText()));
            int numRows = stmt.executeUpdate();

            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        getCustomers();

        clearFields();
    }



    private void getCustomers() throws ClassNotFoundException {
        data.clear();
        Connection conn = DB.createConnection();
        Statement stmt = null;

        try {
            //Connection conn = DB.createConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customers");
            while (rs.next())
            {
                data.add(new Customer(rs.getInt(1), rs.getString(2),  rs.getString(3),  rs.getString(4),  rs.getString(5),  rs.getString(6),  rs.getString(7),  rs.getString(8),  rs.getString(9),  rs.getString(10),  rs.getString(11), rs.getInt(12)));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addCustomer() throws ClassNotFoundException {

        Connection conn = DB.createConnection();
        String sql = "INSERT INTO `customers`(`CustomerId`,`CustFirstName`,`CustLastName`,`CustAddress`,`CustCity`,`CustProv`,`CustPostal`,,`CustCountry`,`CustHomePhone`,`CustBusPhone`,`CustEmail`,`AgentId`) VALUES (null,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtCustFirstName.getText());
            stmt.setString(2, txtCustLastName.getText());
            stmt.setString(3, txtCustAddress.getText());
            stmt.setString(4, txtCustCity.getText());
            stmt.setString(5, txtCustProv.getText());
            stmt.setString(6, txtCustPostal.getText());
            stmt.setString(7, txtCustCountry.getText());
            stmt.setString(8, txtCustHomePhone.getText());
            stmt.setString(9, txtCustBusPhone.getText());
            stmt.setString(10, txtCustEmail.getText());
            stmt.setInt(11, Integer.parseInt(txtAgentId.getText()));
            int numRows = stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getCustomers();
        clearFields();
    }

    private void clearFields() {
        txtCustFirstName.setText("");
        txtCustLastName.setText("");
        txtCustAddress.setText("");
        txtCustCity.setText("");
        txtCustProv.setText("");
        txtCustPostal.setText("");
        txtCustCountry.setText("");
        txtCustHomePhone.setText("");
        txtCustBusPhone.setText("");
        txtCustEmail.setText("");
        txtAgentId.setText("");
        txtCustomerId.setText("");
        btnEdit.setDisable(false);
        btnSave.setDisable(false);
        btnAdd.setDisable(false);
        btnDelete.setDisable(false);
        txtCustFirstName.setDisable(false);
        txtCustLastName.setDisable(false);
        txtCustAddress.setDisable(false);
        txtCustCity.setDisable(false);
        txtCustProv.setDisable(false);
        txtCustPostal.setDisable(false);
        txtCustCountry.setDisable(false);
        txtCustHomePhone.setDisable(false);
        txtCustBusPhone.setDisable(false);
        txtCustEmail.setDisable(false);
        txtAgentId.setDisable(false);
    }
}

