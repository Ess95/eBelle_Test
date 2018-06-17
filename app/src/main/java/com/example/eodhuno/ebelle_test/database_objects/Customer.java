package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    int CustID,Contact;
    String FirstName, LastName, Gender, Email, Password;

    public Customer() {}

    public Customer(int custID, String firstName, String lastName, String gender, int contact, String email, String password) {
        CustID = custID;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Gender = gender;
        this.Contact = contact;
        this.Email = email;
        this.Password = password;
    }

    public int getCustID() {
        return CustID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getGender() {
        return Gender;
    }

    public int getContact() {
        return Contact;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "id":
                return ""+getCustID();
            case "fname":
                return getFirstName();
            case "lname":
                return getLastName();
            case "gender":
                return getGender();
            case "contact":
                return String.valueOf(getContact());
            case "email":
                return getEmail();
            case "password":
                return getPassword();
        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getCustID());
        itemList.add(getFirstName());
        itemList.add(getLastName());
        itemList.add(getGender());
        itemList.add(""+getContact());
        itemList.add(getEmail());
        itemList.add(getPassword());

        return itemList;
    }
}
