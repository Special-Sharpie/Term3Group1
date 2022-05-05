/*
Gurjeet Kaur Pawar
PROJ-207-A
 */

package com.example.term3group1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
	SimpleIntegerProperty CustomerId;
    SimpleStringProperty CustFirstName;
    SimpleStringProperty CustLastName;
    SimpleStringProperty CustAddress;
    SimpleStringProperty CustCity;
    SimpleStringProperty CustProv;
    SimpleStringProperty CustPostal;
    SimpleStringProperty CustCountry;
    SimpleStringProperty CustHomePhone;
    SimpleStringProperty CustBusPhone;
    SimpleStringProperty CustEmail;
    SimpleIntegerProperty AgentId;

    public int getCustomerId() {
        return CustomerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId.set(customerId);
    }

    public String getCustFirstName() {
        return CustFirstName.get();
    }

    public SimpleStringProperty custFirstNameProperty() {
        return CustFirstName;
    }

    public void setCustLastName(String lastName) {
        this.CustLastName.set(lastName);
    }
    
    public String getCustLastName() {
        return CustLastName.get();
    }

    public SimpleStringProperty custLastNameProperty() {
        return CustLastName;
    }
    
    public String getCustAddress() {
        return CustAddress.get();
    }

    public SimpleStringProperty CustAddressProperty() {
        return CustAddress;
    }

    public void setCustAddress(String address) {
        this.CustAddress.set(address);
    }

    public String getCustCity() {
        return CustCity.get();
    }

    public SimpleStringProperty CustCityProperty() {
        return CustCity;
    }

    public void setCustCity(String custCity) {
        this.CustCity.set(custCity);
    }

    public String getCustProv() {
        return CustProv.get();
    }

    public SimpleStringProperty CustProvProperty() {
        return CustProv;
    }

    public void setCustProv(String custProv) {
        this.CustProv.set(custProv);
    }

    public String getCustPostal() {
        return CustPostal.get();
    }

    public SimpleStringProperty custPostalProperty() {
        return CustPostal;
    }

    public void setCustPostal(String custPostal) {
        this.CustPostal.set(custPostal);
    }

    public String getCustCountry() {
        return CustCountry.get();
    }

    public SimpleStringProperty custCountryProperty() {
        return CustCountry;
    }

    public void setBookingId(String custCountry) {
        this.CustCountry.set(custCountry);
    }

    public String getCustEmail() {
        return CustEmail.get();
    }

    public SimpleStringProperty custEmailProperty() {
        return CustEmail;
    }

    public void setCustEmail(String custEmail) {
        this.CustEmail.set(custEmail);
    }

    public String getCustHomePhone() {
        return CustHomePhone.get();
    }

    public SimpleStringProperty custHomePhoneProperty() {
        return CustHomePhone;
    }

    public void setCustHomePhone(String custHomePhone) {
        this.CustHomePhone.set(custHomePhone);
    }

    public String getCustBusPhone() {
        return CustBusPhone.get();
    }

    public SimpleStringProperty custBusPhoneProperty() {
        return CustBusPhone;
    }

    public void setCustBusPhone(String custBusPhone) {
        this.CustBusPhone.set(custBusPhone);
    }

    public int getAgentId() {
        return AgentId.get();
    }

    public SimpleIntegerProperty agentIdProperty() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        this.AgentId.set(agentId);
    }


    public Customer(int customerId, String custFirstName, String custLastName, String custAddress, String custCity, 
    		String custProv, String custPostal, String custCountry, String custHomePhone, String custBusPhone, String custEmail, int agentId) {
        CustomerId = new SimpleIntegerProperty(customerId);
        CustFirstName = new SimpleStringProperty(custFirstName);
        CustLastName = new SimpleStringProperty(custLastName);
        CustAddress = new SimpleStringProperty(custAddress);
        CustCity = new SimpleStringProperty(custCity);
        CustProv = new SimpleStringProperty(custProv);
        CustPostal = new SimpleStringProperty(custPostal);
        CustCountry = new SimpleStringProperty(custCountry);
        CustHomePhone = new SimpleStringProperty(custHomePhone);
        CustBusPhone = new SimpleStringProperty(custBusPhone);
        CustEmail = new SimpleStringProperty(custEmail);
        AgentId = new SimpleIntegerProperty(agentId);
    }

}
