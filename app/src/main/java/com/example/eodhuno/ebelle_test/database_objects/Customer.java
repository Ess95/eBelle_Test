package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Customer {
    int CustID,Contact,customerImage;
    String FirstName, LastName, Gender, Email, Password, ConfirmPassword;

    public Customer() {}

    public Customer(int custID,int custImage, String firstName, String lastName, String gender, int contact, String email, String password, String confirmPassword) {
        CustID = custID;
        this.customerImage = custImage;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Gender = gender;
        this.Contact = contact;
        this.Email = email;
        this.Password = password;
        this.ConfirmPassword = confirmPassword;
    }

    public int getCustomerImage() { return customerImage; }

    public void setCustID(int custID) {
        CustID = custID;
    }

    public void setContact(int contact) {
        Contact = contact;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getConfirmPassword() { return ConfirmPassword; }

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
            case "custIMG":
                return ""+getCustomerImage();
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
            case "confirm_password":
                return getConfirmPassword();
        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getCustID());
        itemList.add(""+getCustomerImage());
        itemList.add(getFirstName());
        itemList.add(getLastName());
        itemList.add(getGender());
        itemList.add(""+getContact());
        itemList.add(getEmail());
        itemList.add(getPassword());
        itemList.add(getConfirmPassword());

        return itemList;
    }
}
