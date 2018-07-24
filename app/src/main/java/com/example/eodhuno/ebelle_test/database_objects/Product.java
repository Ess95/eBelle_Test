package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class Product {
    int ProdID, ServID_FK, Price, reorderlevel, prodQty, Availability;
    String productImage, Prod_Name, prodDescr;

    public Product(){}

    public Product(int prodID,String prodImage, String prod_Name, String prod_descr,int prodQuantity,int unitPrice, int reorderLevel,
                   int availability,int servID_FK) {
        ProdID = prodID;
        ServID_FK = servID_FK;
        productImage = prodImage;
        Prod_Name = prod_Name;
        prodDescr = prod_descr;
        prodQty = prodQuantity;
        Price = unitPrice;
        reorderlevel = reorderLevel;
        Availability = availability;

    }

    public String getProductImage() {
        return productImage;
    }

    public int getProdQty() {
        return prodQty;
    }

    public void setProdQty(int prodQty) {
        this.prodQty = prodQty;
    }

    public void setProdID(int prodID) {
        ProdID = prodID;
    }

    public void setServID_FK(int servID_FK) {
        ServID_FK = servID_FK;
    }

    public void setPrice(int unitPrice) {
        Price = unitPrice;
    }

    public void setReorderlevel(int reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public void setAvailability(int availability) {
        Availability = availability;
    }

    public void setProd_Name(String prod_Name) {
        Prod_Name = prod_Name;
    }

    public void setProdDescr(String prodDescr) {
        this.prodDescr = prodDescr;
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
            case "ProdImage":
                return getProductImage() ;
            case "Prod_Name":
                return getProd_Name();
            case "Prod_Descr":
                return getProdDescr();
            case "Prod_Qty":
                return ""+getProdQty();
            case "Price":
                return ""+getPrice();
            case "ReorderLevel":
                return ""+getReorderlevel();
            case "Availability":
                return ""+getAvailability();
            case "ServID_FK":
                return String.valueOf(getServID_FK());

        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getProdID());
        itemList.add(""+getServID_FK());
        itemList.add(getProductImage());
        itemList.add(getProd_Name());
        itemList.add(getProdDescr());
        itemList.add(""+getProdQty());
        itemList.add(""+getPrice());
        itemList.add(""+getReorderlevel());
        itemList.add(""+getAvailability());

        return itemList;
    }
}
