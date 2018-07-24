package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class Employee {
    int EmpID,Emp_Image, Emp_PhoneNo, EmpAccessRight, empRating, Emp_Services,empAvailability;
    String Emp_Fname, Emp_Lname,Emp_Gender,Emp_Email, Emp_ModeOfPay;

    public Employee(){}

    public Employee(int empID, int emp_ProfileImage, String emp_Fname, String emp_Lname, String emp_Gender,int emp_PhoneNo,
                    String emp_Email, String emp_ModeOfPay, int emp_Services, int emp_rating, int empAccessRight, int emp_Availability) {
        EmpID = empID;
        Emp_Image = emp_ProfileImage;
        Emp_PhoneNo = emp_PhoneNo;
        Emp_Fname = emp_Fname;
        Emp_Lname = emp_Lname;
        Emp_Gender = emp_Gender;
        Emp_Email = emp_Email;
        Emp_Services = emp_Services;
        empRating = emp_rating;
        Emp_ModeOfPay = emp_ModeOfPay;
        EmpAccessRight = empAccessRight;
        empAvailability = emp_Availability;
    }

    public int getEmpAvailability() { return empAvailability; }

    public void setEmpAvailability(int empAvailability) { this.empAvailability = empAvailability; }

    public int getEmpRating() { return empRating; }

    public void setEmpRating(int empRating) { this.empRating = empRating; }

    public int getEmpAccessRight() {
        return EmpAccessRight;
    }

    public void setEmpAccessRight(int empAccessRight) {
        EmpAccessRight = empAccessRight;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public void setEmp_PhoneNo(int emp_PhoneNo) {
        Emp_PhoneNo = emp_PhoneNo;
    }

    public void setEmp_Image(int emp_Image) {
        Emp_Image = emp_Image;
    }

    public void setEmp_Fname(String emp_Fname) {
        Emp_Fname = emp_Fname;
    }

    public void setEmp_Lname(String emp_Lname) {
        Emp_Lname = emp_Lname;
    }

    public void setEmp_Gender(String emp_Gender) {
        Emp_Gender = emp_Gender;
    }

    public void setEmp_Email(String emp_Email) {
        Emp_Email = emp_Email;
    }

    public void setEmp_Services(int emp_Services) {
        Emp_Services = emp_Services;
    }

    public void setEmp_ModeOfPay(String emp_ModeOfPay) {
        Emp_ModeOfPay = emp_ModeOfPay;
    }

    public int getEmp_Image() {
        return Emp_Image;
    }

    public int getEmp_Services() {
        return Emp_Services;
    }

    public int getEmpID() {
        return EmpID;
    }

    public int getEmp_PhoneNo() { return Emp_PhoneNo; }

    public String getEmp_Fname() {
        return Emp_Fname;
    }

    public String getEmp_Lname() {
        return Emp_Lname;
    }

    public String getEmp_Gender() {
        return Emp_Gender;
    }

    public String getEmp_Email() {
        return Emp_Email;
    }

    public String getEmp_ModeOfPay() {
        return Emp_ModeOfPay;
    }


    public String getProperty(String propertyName){
        switch (propertyName){
            case "EmpID":
                return ""+getEmpID();
            case "Emp_Image":
                return ""+getEmp_Image();
            case "Emp_Fname":
                return getEmp_Fname();
            case "Emp_Lname":
                return getEmp_Lname();
            case "Emp_Gender":
                return getEmp_Gender();
            case "Emp_PhoneNo":
                return ""+getEmp_PhoneNo();
            case "Emp_Email":
                return getEmp_Email();
            case "Emp_Services":
                return ""+getEmp_Services();
            case "Emp_Rating":
                return ""+getEmpRating();
            case "Emp_ModeOfPay":
                return getEmp_ModeOfPay();
            case "Emp_AccessRight":
                return ""+getEmpAccessRight();
            case "Emp_Availability":
                return ""+getEmpAvailability();
        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getEmpID());
        itemList.add(""+getEmp_Image());
        itemList.add(getEmp_Fname());
        itemList.add(getEmp_Lname());
        itemList.add(getEmp_Gender());
        itemList.add(""+getEmp_PhoneNo());
        itemList.add(getEmp_Email());
        itemList.add(getEmp_ModeOfPay());
        itemList.add(""+getEmpAccessRight());
        itemList.add(""+getEmpAvailability());


        return itemList;
    }
}
