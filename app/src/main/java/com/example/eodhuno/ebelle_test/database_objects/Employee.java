package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class Employee {
    int EmpID, Emp_PhoneNo;
    String Emp_Fname, Emp_Lname, Emp_Gender,Emp_Email, Emp_Services, Emp_ModeOfPay;

    public Employee(){}

    public Employee(int empID,String emp_Fname, String emp_Lname, String emp_Gender,int emp_PhoneNo, String emp_Email, String emp_Services, String emp_ModeOfPay) {
        EmpID = empID;
        Emp_PhoneNo = emp_PhoneNo;
        Emp_Fname = emp_Fname;
        Emp_Lname = emp_Lname;
        Emp_Gender = emp_Gender;
        Emp_Email = emp_Email;
        Emp_Services = emp_Services;
        Emp_ModeOfPay = emp_ModeOfPay;
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public int getEmp_PhoneNo() {
        return Emp_PhoneNo;
    }

    public void setEmp_PhoneNo(int emp_PhoneNo) {
        Emp_PhoneNo = emp_PhoneNo;
    }

    public String getEmp_Fname() {
        return Emp_Fname;
    }

    public void setEmp_Fname(String emp_Fname) {
        Emp_Fname = emp_Fname;
    }

    public String getEmp_Lname() {
        return Emp_Lname;
    }

    public void setEmp_Lname(String emp_Lname) {
        Emp_Lname = emp_Lname;
    }

    public String getEmp_Gender() {
        return Emp_Gender;
    }

    public void setEmp_Gender(String emp_Gender) {
        Emp_Gender = emp_Gender;
    }

    public String getEmp_Email() {
        return Emp_Email;
    }

    public void setEmp_Email(String emp_Email) {
        Emp_Email = emp_Email;
    }

    public String getEmp_Services() {
        return Emp_Services;
    }

    public void setEmp_Services(String emp_Services) {
        Emp_Services = emp_Services;
    }

    public String getEmp_ModeOfPay() {
        return Emp_ModeOfPay;
    }

    public void setEmp_ModeOfPay(String emp_ModeOfPay) {
        Emp_ModeOfPay = emp_ModeOfPay;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "EmpID":
                return ""+getEmpID();
            case "Emp_Fname":
                return getEmp_Fname();
            case "Emp_Lname":
                return getEmp_Lname();
            case "Emp_Gender":
                return getEmp_Gender();
            case "Emp_PhoneNo":
                return String.valueOf(getEmp_PhoneNo());
            case "Emp_Email":
                return getEmp_Email();
            case "Emp_ModeOfPay":
                return getEmp_ModeOfPay();
        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getEmpID());
        itemList.add(getEmp_Fname());
        itemList.add(getEmp_Lname());
        itemList.add(getEmp_Gender());
        itemList.add(""+getEmp_PhoneNo());
        itemList.add(getEmp_Email());
        itemList.add(getEmp_ModeOfPay());

        return itemList;
    }
}
