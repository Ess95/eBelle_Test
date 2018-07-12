package com.example.eodhuno.ebelle_test.database_objects;

import java.util.ArrayList;

public class Category {
    int CategotyID, ServCatID_FK;
    String CatImgText, CategoryName,CategoryDescription;

    public Category(){}

    public Category(int categotyID, String catImageText, String categoryName, String categoryDescription) {
        CategotyID = categotyID;
        CatImgText = catImageText;
        CategoryName = categoryName;
        CategoryDescription = categoryDescription;
    }

    public void setCategotyID(int categotyID) {
        CategotyID = categotyID;
    }

    public void setServCatID_FK(int servCatID_FK) {
        ServCatID_FK = servCatID_FK;
    }

    public void setCatImgText(String catImgText) {
        CatImgText = catImgText;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }

    public String getCatImgText() { return CatImgText; }

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
            case "ImgCategoryName":
                return getCatImgText();
            case "CategoryDescription":
                return getCategoryDescription();

        }
        return "";
    }

    public ArrayList<String> getItems(){
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add(""+getCategotyID());
        itemList.add(getCatImgText());
        itemList.add(getCategoryName());
        itemList.add(getCategoryDescription());

        return itemList;
    }
}
