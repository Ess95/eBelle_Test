package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class Appointment {
    int ApptID, CustID_FK;
    String DateCreated, ApptDate, ApptTime, Services, Beautician, Confirmation;

    public Appointment(){}

    public Appointment(int apptID, int custID_FK, String dateCreated, String apptDate, String apptTime, String services, String beautician, String confirmation) {
        ApptID = apptID;
        CustID_FK = custID_FK;
        DateCreated = dateCreated;
        ApptDate = apptDate;
        ApptTime = apptTime;
        Services = services;
        Beautician = beautician;
        Confirmation = confirmation;
    }

    public int getApptID() {
        return ApptID;
    }

    public void setApptID(int apptID) {
        ApptID = apptID;
    }

    public int getCustID_FK() {
        return CustID_FK;
    }

    public void setCustID_FK(int custID_FK) {
        CustID_FK = custID_FK;
    }

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }

    public String getApptDate() {
        return ApptDate;
    }

    public void setApptDate(String apptDate) {
        ApptDate = apptDate;
    }

    public String getApptTime() {
        return ApptTime;
    }

    public void setApptTime(String apptTime) {
        ApptTime = apptTime;
    }

    public String getServices() {
        return Services;
    }

    public void setServices(String services) {
        Services = services;
    }

    public String getBeautician() {
        return Beautician;
    }

    public void setBeautician(String beautician) {
        Beautician = beautician;
    }

    public String getConfirmation() {
        return Confirmation;
    }

    public void setConfirmation(String confirmation) {
        Confirmation = confirmation;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "id":
                return ""+getApptID();
            case "CustID_FK":
                return ""+getCustID_FK();
            case "DateCreated":
                return getDateCreated();
            case "ApptDate":
                return getApptDate();
            case "ApptTime":
                return getApptTime();
            case "Services":
                return getServices();
            case "Beautician":
                return getBeautician();
            case "Confirmation":
                return getConfirmation();
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
        itemList.add(getServices());
        itemList.add(getBeautician());
        itemList.add(getConfirmation());

        return itemList;
    }
}
