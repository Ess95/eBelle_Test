package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class Service {
    int ServID, CategoryID_FK, Price, Duration, Availability;

    String Serv_Name, Serv_Description, serviceImage;

    public Service(){}

    public Service(int servID, String serv_image, String serv_Name, int price, int duration, String serv_Description, int availability, int categoryID_FK) {
        ServID = servID;
        serviceImage = serv_image;
        CategoryID_FK = categoryID_FK;
        Price = price;
        Serv_Name = serv_Name;
        Serv_Description = serv_Description;
        Duration = duration;
        Availability = availability;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public int getDuration() {return Duration; }

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

    public int getAvailability() {
        return Availability;
    }

    public void setAvailability(int availability) {
        Availability = availability;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "ServID":
                return ""+getServID();
            case "ServImage":
                return getServiceImage();
            case "CategoryID_FK":
                return ""+getCategoryID_FK();
            case "Price":
                return ""+getPrice();
            case "Time":
                return ""+getDuration();
            case "Serv_Name":
                return getServ_Name();
            case "Serv_Description":
                return getServ_Description();
            case "Availability":
                return ""+getAvailability();

        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getServID());
        itemList.add(""+getCategoryID_FK());
        itemList.add(getServiceImage());
        itemList.add(""+getPrice());
        itemList.add(""+getDuration());
        itemList.add(getServ_Name());
        itemList.add(getServ_Description());
        itemList.add(""+getAvailability());

        return itemList;
    }
}
