/*
Gurjeet Kaur Pawar
PROJ-207-A
 */

package com.example.term3group1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Booking {
    SimpleIntegerProperty BookingId;
    SimpleStringProperty BookingDate;
    SimpleStringProperty BookingNo;
    SimpleDoubleProperty TravelerCount;
    SimpleIntegerProperty CustomerId;
    SimpleStringProperty TripTypeId;
    SimpleIntegerProperty PackageId;

    //Booking ID
    public int getBookingId() {
        return BookingId.get();
    }

    public SimpleIntegerProperty bookingIdProperty() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        this.BookingId.set(bookingId);
    }
    
    //Booking Date
    public String getBookingDate() {
        return BookingDate.get();
    }

    public SimpleStringProperty bookingDateProperty() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.BookingDate.set(bookingDate);
    }
    
    //Booking No
    public String getBookingNo() {
        return BookingNo.get();
    }

    public SimpleStringProperty bookingNoProperty() {
        return BookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.BookingNo.set(bookingNo);
    }
    
    //Traveller Count
    public double getTravelerCount() {
        return TravelerCount.get();
    }

    public SimpleDoubleProperty travelerCountProperty() {
        return TravelerCount;
    }

    public void setTravelerCount(double travelerCount) {
        this.TravelerCount.set(travelerCount);
    }
    
    //Customer ID
    public int getCustomerId() {
        return CustomerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId.set(customerId);
    }
    
    //Trip Type ID
    public String getTripTypeId() {
        return TripTypeId.get();
    }

    public SimpleStringProperty tripTypeIdProperty() {
        return TripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.TripTypeId.set(tripTypeId);
    }
    
    //Package ID
    public int getPackageId() {
        return PackageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        this.BookingId.set(packageId);
    }
    
    public Booking(Integer bookingId, String bookingDate, String bookingNo, Double travelerCount, 
    		Integer customerId, String tripTypeId, Integer packageId) {
        BookingId = new SimpleIntegerProperty(bookingId);
        BookingDate = new SimpleStringProperty(bookingDate);
        BookingNo = new SimpleStringProperty(bookingNo);
        TravelerCount = new SimpleDoubleProperty(travelerCount);
        CustomerId = new SimpleIntegerProperty(customerId);
        TripTypeId = new SimpleStringProperty(tripTypeId);
        PackageId = new SimpleIntegerProperty(packageId);
    }

}
