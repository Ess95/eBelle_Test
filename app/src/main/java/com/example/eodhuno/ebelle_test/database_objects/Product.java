package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class Product {
    int ProdID, ServID_FK, Price, reorderlevel,Availability ;
    String Prod_Name, prodDescr, prodServices;

    public Product(){}

    public Product(int prodID, int servID_FK, String prod_Name, String prod_descr, int unitPrice, int reorderLevel,
                   int availability) {
        ProdID = prodID;
        ServID_FK = servID_FK;
        Prod_Name = prod_Name;
        prodDescr = prod_descr;
        Price = unitPrice;
        reorderlevel = reorderLevel;
        Availability = availability;
    }

    public int getProdID() {
        return ProdID;
    }

    public int getServID_FK() {
        return ServID_FK;
    }

    public int getPrice() {
        return Price;
    }

    public int getReorderlevel() {
        return reorderlevel;
    }

    public int getAvailability() {
        return Availability;
    }

    public String getProd_Name() {
        return Prod_Name;
    }

    public String getProdDescr() {
        return prodDescr;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "ProdID":
                return String.valueOf(getProdID());
            case "ServID_FK":
                return String.valueOf(getServID_FK());
            case "Prod_Name":
                return getProd_Name();
            case "Price":
                return ""+getPrice();
            case "Availability":
                return String.valueOf(getAvailability());

        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getProdID());
        itemList.add(""+getServID_FK());
        itemList.add(getProd_Name());
        itemList.add(getProdDescr());
        itemList.add(""+getPrice());
        itemList.add(""+getReorderlevel());
        itemList.add(""+getAvailability());

        return itemList;
    }
}
