/*
Gurjeet Kaur Pawar
PROJ-207-A
 */

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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

public class BookingController {

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
    private TableColumn<Booking, Integer> colBookingId;

    @FXML
    private TableColumn<Booking, String> colBookingDate;

    @FXML
    private TableColumn<Booking, String> colBookingNo;

    @FXML
    private TableColumn<Booking, Double> colTravelerCount;

    @FXML
    private TableColumn<Booking, Integer> colCustomerId;

    @FXML
    private TableColumn<Booking, String> colTripTypeId;

    @FXML
    private TableColumn<Booking, Integer> colPackageId;
    
    @FXML
    private TableView<Booking> tvBookings;

    @FXML
    private TextField txtBookingId;
    
    @FXML
    private TextField txtBookingDate;
    
    @FXML
    private TextField txtBookingNo;

    @FXML
    private TextField txtTravelerCount;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtTripTypeId;

    @FXML
    private TextField txtPackageId;
    
    private ObservableList<Booking> data = FXCollections.observableArrayList();

    private int selectedIndex;

    private String mode;

    @FXML
    void initialize() throws ClassNotFoundException {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert colBookingId != null : "fx:id=\"colBookingId\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert colBookingDate != null : "fx:id=\"colBookingDate\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert colBookingNo != null : "fx:id=\"colBookingNo\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert colTravelerCount != null : "fx:id=\"colTravelerCount\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert colCustomerId != null : "fx:id=\"colCustomerId\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert colTripTypeId != null : "fx:id=\"colTripTypeId\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert colPackageId != null : "fx:id=\"colPackageId\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert tvBookings != null : "fx:id=\"tvBookings\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert txtBookingId != null : "fx:id=\"txtBookingId\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert txtBookingDate != null : "fx:id=\"txtBookingDate\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert txtBookingNo != null : "fx:id=\"txtBookingNo\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert txtTravelerCount != null : "fx:id=\"txtTravelerCount\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert txtCustomerId != null : "fx:id=\"txtCustomerId\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert txtPackageId != null : "fx:id=\"txtPackageId\" was not injected: check your FXML file 'booking-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'booking-view.fxml'.";

        colBookingId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("BookingId"));
        colBookingDate.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingDate"));
        colBookingNo.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingNo"));
        colTravelerCount.setCellValueFactory(new PropertyValueFactory<Booking, Double>("TravelerCount"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("CustomerId"));
        colTripTypeId.setCellValueFactory(new PropertyValueFactory<Booking, String>("TripTypeId"));
        colPackageId.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("PackageId"));
        tvBookings.setItems(data);
        btnEdit.setDisable(true);
        getBookings();


        //when the table view is selected

        tvBookings.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Booking>() {
            @Override
            public void changed(ObservableValue<? extends Booking> observableValue, Booking booking, Booking t1) {
                if (tvBookings.getSelectionModel().isSelected(tvBookings.getSelectionModel().getSelectedIndex()))
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            btnAdd.setDisable(true);
                            btnEdit.setDisable(false);
                            btnDelete.setDisable(false);
                            txtBookingDate.setDisable(true);
                            txtBookingNo.setDisable(true);
                            txtTravelerCount.setDisable(true);
                            txtCustomerId.setDisable(true);
                            txtTripTypeId.setDisable(true);
                            txtPackageId.setDisable(true);

                            txtBookingDate.setText(t1.getBookingDate());
                            txtBookingNo.setText(t1.getBookingDate());
                            txtTravelerCount.setText(String.valueOf(t1.getTravelerCount()));
                            txtCustomerId.setText(String.valueOf(t1.getBookingDate()));
                            txtPackageId.setText(String.valueOf(t1.getBookingDate()));
                            txtTripTypeId.setText(t1.getTripTypeId());
                            txtBookingId.setText(String.valueOf(t1.getBookingId()));
                            txtBookingId.setDisable(true);
                         
                        }
                    });
            }
        });


        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
					saveBookings();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });



        btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            editBookings();

            }
        });

        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
					deleteBookings();
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
					addBooking();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

    }

    public void saveBookings() throws ClassNotFoundException {

        Connection conn = DB.createConnection();
        String sql = "UPDATE `bookings` SET `BookingDate`=?,`BookingNo`=?,`TravelerCount`=?,`CustomerId`=?,`TripTypeId`=?,`PackageId`=? WHERE `BookingId`=?";

        try {



            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtBookingDate.getText());
            stmt.setString(2, txtBookingNo.getText());
            stmt.setDouble(3, Double.parseDouble(txtTravelerCount.getText()));
            stmt.setInt(4, Integer.parseInt(txtCustomerId.getText()));
            stmt.setString(5, txtTripTypeId.getText());
            stmt.setInt(6, Integer.parseInt(txtPackageId.getText()));
            int numRows = stmt.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        getBookings();
        clearFields();
    }



private void editBookings(){

    btnAdd.setDisable(true);
    btnEdit.setDisable(true);
    btnSave.setDisable(false);

    txtBookingDate.setDisable(false);
    txtBookingNo.setDisable(false);
    txtTravelerCount.setDisable(false);
    txtCustomerId.setDisable(false);
    txtTripTypeId.setDisable(false);
    txtPackageId.setDisable(false);

}




    private void deleteBookings() throws ClassNotFoundException{

        Connection conn = DB.createConnection();


            String sql = "delete from bookings where `BookingId`=?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtBookingId.getText()));
            int numRows = stmt.executeUpdate();

            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        getBookings();
        clearFields();
    }



    private void getBookings() throws ClassNotFoundException {
        data.clear();
        Connection conn = DB.createConnection();
        Statement stmt = null;

        try {
            //Connection conn = DB.createConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bookings");
            while (rs.next())
            {
                data.add(new Booking(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6),rs.getInt(7)));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addBooking() throws ClassNotFoundException {

        Connection conn = DB.createConnection();
        String sql = "INSERT INTO `bookings`(`BookingId`,`BookingDate`,`BookingNo`,`TravelerCount`,`CustomerId`,`TripTypeId`,`PackageId`) VALUES (null,?,?,?,?,?,?)";

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtBookingDate.getText());
            stmt.setString(2, txtBookingNo.getText());
            stmt.setDouble(3, Double.parseDouble(txtTravelerCount.getText()));
            stmt.setInt(4, Integer.parseInt(txtCustomerId.getText()));
            stmt.setString(5, txtTripTypeId.getText());
            int packageID = Integer.parseInt("0"+txtPackageId.getText());
            if(packageID > 0) {
            	stmt.setInt(6, packageID);            	
            }else {
            	stmt.setNull(6,0);
            }
            int numRows = stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getBookings();
        txtBookingDate.clear();
        txtBookingNo.clear();
        txtTravelerCount.clear();
        txtCustomerId.clear();
        txtTripTypeId.clear();
        txtPackageId.clear();
        //clearFields();
    }

    private void clearFields() {
    	txtBookingDate.setText("");
        txtBookingNo.setText("");
        txtTravelerCount.setText("");
        txtCustomerId.setText("");
        txtPackageId.setText("");
        txtTripTypeId.setText("");
        txtBookingId.setText("");
        btnEdit.setDisable(false);
        btnSave.setDisable(false);
        btnAdd.setDisable(false);
        btnDelete.setDisable(false);
    }
}

