package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class CustomerPaymentHistory {
    int CustHistory_ID, apptID;
    String CustomerHistory_DateOfPayment;

    public CustomerPaymentHistory(){}

    public CustomerPaymentHistory(int custHistory_ID, int apptID, String customerHistory_DateOfPayment) {
        CustHistory_ID = custHistory_ID;
        this.apptID = apptID;
        CustomerHistory_DateOfPayment = customerHistory_DateOfPayment;
    }

    public int getCustHistory_ID() {
        return CustHistory_ID;
    }

    public int getApptID() {
        return apptID;
    }

    public String getCustomerHistory_DateOfPayment() {
        return CustomerHistory_DateOfPayment;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "CustHistory_ID":
                return ""+getCustHistory_ID();
            case "Appointment":
                return ""+getApptID();
            case "CustomerHistory_DateOfPayment":
                return getCustomerHistory_DateOfPayment();
        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();

        itemList.add(""+getCustHistory_ID());
        itemList.add(""+getApptID());
        itemList.add(getCustomerHistory_DateOfPayment());
        return itemList;
    }
}
