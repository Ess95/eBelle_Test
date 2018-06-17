package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class ServiceCategory {
    int CategotyID, ServCatID_FK;
    String CategoryName,CategoryDescription;

    public ServiceCategory(){}

    public ServiceCategory(int categotyID, String categoryName, String categoryDescription) {
        CategotyID = categotyID;
        CategoryName = categoryName;
        CategoryDescription = categoryDescription;
    }

    public int getCategotyID() {
        return CategotyID;
    }

    public int getServCatID_FK() {
        return ServCatID_FK;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public String getProperty(String propertyName){
        switch (propertyName){
            case "CategotyID":
                return ""+getCategotyID();
            case "CategoryName":
                return getCategoryName();
            case "CategoryDescription":
                return getCategoryDescription();

        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getCategotyID());
        itemList.add(getCategoryName());
        itemList.add(getCategoryDescription());

        return itemList;
    }
}
