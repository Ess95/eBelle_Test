package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class SalonCustomerServicePayment {
    int ServPaymentID, CustHistoryID,TotalCharges;
    String Services, Beautician, ModeOfPayment;

    public SalonCustomerServicePayment(){}

    public SalonCustomerServicePayment(int servPaymentID, int custHistoryID, String services, String beautician, int totalCharges, String modeOfPayment) {
        ServPaymentID = servPaymentID;
        CustHistoryID = custHistoryID;
        TotalCharges = totalCharges;
        Services = services;
        Beautician = beautician;
        ModeOfPayment = modeOfPayment;
    }

    public int getServPaymentID() {
        return ServPaymentID;
    }

    public int getCustHistoryID() {
        return CustHistoryID;
    }

    public int getTotalCharges() {
        return TotalCharges;
    }

    public String getServices() {
        return Services;
    }

    public String getBeautician() {
        return Beautician;
    }

    public String getModeOfPayment() {
        return ModeOfPayment;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "ServPaymentID":
                return ""+getServPaymentID();
            case "CustHistoryID":
                return ""+getCustHistoryID();
            case "TotalCharges":
                return ""+getTotalCharges();
            case "Services":
                return getServices();
            case "Beautician":
                return getBeautician();
            case "ModeOfPayment":
                return getModeOfPayment();
        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getServPaymentID());
        itemList.add(""+getCustHistoryID());
        itemList.add(""+getTotalCharges());
        itemList.add(getServices());
        itemList.add(getBeautician());
        itemList.add(getModeOfPayment());

        return itemList;
    }
}
