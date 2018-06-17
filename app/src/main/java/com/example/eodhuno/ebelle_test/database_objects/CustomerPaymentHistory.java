package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class CustomerPaymentHistory {
    int CustHistory_ID, CustomerHistory_TotalCharges;
    String CustomerHistory_DateOfPayment,CustomerHistory_Services,CustomerHistory_Beautician,CustomerHistory_ModeOfPay;

    public CustomerPaymentHistory(){}

    public CustomerPaymentHistory(int custHistory_ID, String customerHistory_DateOfPayment, String customerHistory_Services, String customerHistory_Beautician, int customerHistory_TotalCharges, String customerHistory_ModeOfPay) {
        CustHistory_ID = custHistory_ID;
        CustomerHistory_TotalCharges = customerHistory_TotalCharges;
        CustomerHistory_DateOfPayment = customerHistory_DateOfPayment;
        CustomerHistory_Services = customerHistory_Services;
        CustomerHistory_Beautician = customerHistory_Beautician;
        CustomerHistory_ModeOfPay = customerHistory_ModeOfPay;
    }

    public int getCustHistory_ID() {
        return CustHistory_ID;
    }

    public int getCustomerHistory_TotalCharges() {
        return CustomerHistory_TotalCharges;
    }

    public String getCustomerHistory_DateOfPayment() {
        return CustomerHistory_DateOfPayment;
    }

    public String getCustomerHistory_Services() {
        return CustomerHistory_Services;
    }

    public String getCustomerHistory_Beautician() {
        return CustomerHistory_Beautician;
    }

    public String getCustomerHistory_ModeOfPay() {
        return CustomerHistory_ModeOfPay;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "CustHistory_ID":
                return ""+getCustHistory_ID();
            case "CustomerHistory_TotalCharges":
                return ""+getCustomerHistory_TotalCharges();
            case "CustomerHistory_DateOfPayment":
                return getCustomerHistory_DateOfPayment();
            case "CustomerHistory_Services":
                return getCustomerHistory_Services();
            case "CustomerHistory_Beautician":
                return getCustomerHistory_Beautician();
            case "CustomerHistory_ModeOfPay":
                return getCustomerHistory_ModeOfPay();
        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();

        itemList.add(""+getCustHistory_ID());
        itemList.add(""+getCustomerHistory_TotalCharges());
        itemList.add(getCustomerHistory_DateOfPayment());
        itemList.add(getCustomerHistory_Services());
        itemList.add(getCustomerHistory_Beautician());
        itemList.add(getCustomerHistory_ModeOfPay());

        return itemList;
    }
}
