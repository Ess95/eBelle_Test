package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class Service {
    int ServID, CategoryID_FK, Price;

    String Serv_Name, Duration, Serv_Description, Availability;

    public Service(){}

    public Service(int servID, int categoryID_FK, String serv_Name, String serv_Description, int price, String time, String availability) {
        ServID = servID;
        CategoryID_FK = categoryID_FK;
        Price = price;
        Serv_Name = serv_Name;
        Serv_Description = serv_Description;
        Duration = time;
        Availability = availability;
    }

    public String getDuration() {return Duration; }

    public int getServID() {
        return ServID;
    }

    public void setServID(int servID) {
        ServID = servID;
    }

    public int getCategoryID_FK() {
        return CategoryID_FK;
    }

    public void setCategoryID_FK(int categoryID_FK) {
        CategoryID_FK = categoryID_FK;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getServ_Name() {
        return Serv_Name;
    }

    public void setServ_Name(String serv_Name) {
        Serv_Name = serv_Name;
    }

    public String getServ_Description() {
        return Serv_Description;
    }

    public void setServ_Description(String serv_Description) { Serv_Description = serv_Description; }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "ServID":
                return ""+getServID();
            case "CategoryID_FK":
                return ""+getCategoryID_FK();
            case "Price":
                return ""+getPrice();
            case "Time":
                return getDuration();
            case "Serv_Name":
                return getServ_Name();
            case "Serv_Description":
                return getServ_Description();
            case "Availability":
                return getAvailability();

        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getServID());
        itemList.add(""+getCategoryID_FK());
        itemList.add(""+getPrice());
        itemList.add(getDuration());
        itemList.add(getServ_Name());
        itemList.add(getServ_Description());
        itemList.add(getAvailability());

        return itemList;
    }
}
