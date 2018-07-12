package com.example.eodhuno.ebelle_test.database_objects;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class FullyBookedHours {
    int bookedHourID, apptID_FK;
    String strBookedDay, strStartTime, strEndTime, getBookedDay, getBookedStart, getBookedEnd;

    DateTime bookedDay, startTime, endTime;

    public FullyBookedHours(){}

    public FullyBookedHours(int bookedHourID, int apptID_FK, String strBookedDay, String strStartTime, String strEndTime) {
        this.bookedHourID = bookedHourID;
        this.apptID_FK = apptID_FK;
        this.strBookedDay = strBookedDay;
        this.strStartTime = strStartTime;
        this.strEndTime = strEndTime;

        getBookedDay = ""+bookedDay.getDayOfWeek();
        getBookedStart = ""+startTime.getHourOfDay()+ ":" + startTime.getMinuteOfHour();
        getBookedEnd = ""+endTime.getHourOfDay()+ ":" + endTime.getMinuteOfHour();

    }

    public int getBookedHourID() {
        return bookedHourID;
    }

    public int getApptID_FK() {
        return apptID_FK;
    }

    public String getBookedDay() {
        return getBookedDay;
    }

    public String getBookedStart() {
        return getBookedStart;
    }

    public String getBookedEnd() {
        return getBookedEnd;
    }


    public String getProperty(String propertyName) {
        switch (propertyName) {
            case "bookedHour_id":
                return "" + getBookedHourID();
            case "appt_ID":
                return "" + getApptID_FK();
            case "booked_day":
                return getBookedDay();
            case "start":
                return getBookedStart();
            case "end":
                return getBookedEnd();
        }
        return "";
    }
    public ArrayList<String> getItems() {
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add("" + getBookedHourID());
        itemList.add("" + getApptID_FK());
        itemList.add(getBookedDay());
        itemList.add(getBookedStart());
        itemList.add(getBookedEnd());
        return itemList;
    }
}
