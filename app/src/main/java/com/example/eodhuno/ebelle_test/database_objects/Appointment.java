package com.example.eodhuno.ebelle_test.database_objects;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class Appointment {
    int apptID, custID_FK,services,ifProductUsed,confirmation, beautician, serviceCharge, productCharge, totalCharges;
    String dateCreated, apptDate, apptTime;

    public Appointment(){}

    public Appointment(int apptID, int custID_FK, String dateCreated, String apptDate, String apptTime, int services, int isProductUsed, int beautician, int confirmation
    ,int serviceCharge, int productCharge, int totalCharges) {
        this.apptID = apptID;
        this.custID_FK = custID_FK;
        this.dateCreated = dateCreated;
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.services = services;
        this.ifProductUsed = isProductUsed;
        this.beautician = beautician;
        this.confirmation = confirmation;
        this.serviceCharge = serviceCharge;
        this.productCharge = productCharge;
        this.totalCharges = totalCharges;
    }

    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public int getCustID_FK() {
        return custID_FK;
    }

    public void setCustID_FK(int custID_FK) {
        this.custID_FK = custID_FK;
    }

    public int getIfProductUsed() {
        return ifProductUsed;
    }

    public void setIfProductUsed(int ifProductUsed) {
        this.ifProductUsed = ifProductUsed;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public int getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(int serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getProductCharge() {
        return productCharge;
    }

    public void setProductCharge(int productCharge) {
        this.productCharge = productCharge;
    }

    public int getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(int totalCharges) {
        this.totalCharges = totalCharges;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }

    public int getServices() {
        return services;
    }

    public void setServices(int services) {
        this.services = services;
    }

    public int getBeautician() {
        return beautician;
    }

    public void setBeautician(int beautician) {
        this.beautician = beautician;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "id":
                return ""+getApptID();
            case "custID_FK":
                return ""+getCustID_FK();
            case "dateCreated":
                return getDateCreated();
            case "apptDate":
                return getApptDate();
            case "apptTime":
                return getApptTime();
            case "services":
                return ""+getServices();
            case "productUsed":
                return ""+getIfProductUsed();
            case "beautician":
                return ""+getBeautician();
            case "confirmation":
                return ""+getConfirmation();
            case "serviceCharge":
                return ""+getServiceCharge();
            case "productCharge":
                return ""+getProductCharge();
            case "totalCharge":
                return ""+getTotalCharges();
        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getApptID());
        itemList.add(""+getCustID_FK());
        itemList.add(getDateCreated());
        itemList.add(getApptDate());
        itemList.add(getApptTime());
        itemList.add(""+getServices());
        itemList.add(""+getIfProductUsed());
        itemList.add(""+getBeautician());
        itemList.add(""+getConfirmation());
        itemList.add(""+getServiceCharge());
        itemList.add(""+getProductCharge());
        itemList.add(""+getTotalCharges());

        return itemList;
    }
}
