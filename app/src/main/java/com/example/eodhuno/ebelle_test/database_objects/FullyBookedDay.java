package com.example.eodhuno.ebelle_test.database_objects;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class FullyBookedDay {

    int bookedDayID;
    String strFullyBookedDay, getFullyBookedDay;

    DateTime bookedDay;

    public FullyBookedDay(){}

    public FullyBookedDay(int bookedDayID, String strFullyBookedDay) {
        this.bookedDayID = bookedDayID;
        this.strFullyBookedDay = strFullyBookedDay;

        getFullyBookedDay = ""+bookedDay.getDayOfWeek();
    }

    public void setBookedDayID(int bookedDayID) {
        this.bookedDayID = bookedDayID;
    }

    public void setStrFullyBookedDay(String strFullyBookedDay) {
        this.strFullyBookedDay = strFullyBookedDay;
    }

    public void setGetFullyBookedDay(String getFullyBookedDay) {
        this.getFullyBookedDay = getFullyBookedDay;
    }

    public int getBookedDayID() { return bookedDayID; }

    public String getBookedDay() { return getFullyBookedDay; }

    public String getProperty(String propertyName) {
        switch (propertyName) {
            case "bookedDay_id":
                return "" + getBookedDayID();
            case "bookedDay":
                return "" + getBookedDay();
        }
        return "";
    }

    public ArrayList<String> getItems() {
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add("" + getBookedDayID());
        itemList.add("" + getBookedDay());

        return itemList;
    }

}