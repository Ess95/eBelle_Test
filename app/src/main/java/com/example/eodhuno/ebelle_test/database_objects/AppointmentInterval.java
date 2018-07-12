package com.example.eodhuno.ebelle_test.database_objects;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class AppointmentInterval {

    public AppointmentInterval(){}

    int interval_ID;
    String strBookedDay, strStartTime, strEndTime, getBookedDay, getBookedStart, getBookedEnd;

    DateTime bookedDay, startTime, endTime;

    public AppointmentInterval(int interval_ID, String strBookedDay, String strStartTime, String strEndTime) {
        this.interval_ID = interval_ID;
        this.strBookedDay = strBookedDay;
        this.strStartTime = strStartTime;
        this.strEndTime = strEndTime;

        getBookedDay = ""+bookedDay.getHourOfDay()+ ":" + bookedDay.getMinuteOfHour();
        getBookedStart = ""+startTime.getHourOfDay() + ":" + startTime.getMinuteOfHour();
        getBookedEnd = ""+endTime.getHourOfDay() + ":" + endTime.getMinuteOfHour();
    }

    public void setInterval_ID(int interval_ID) {
        this.interval_ID = interval_ID;
    }

    public void setStrBookedDay(String strBookedDay) {
        this.strBookedDay = strBookedDay;
    }

    public void setStrStartTime(String strStartTime) {
        this.strStartTime = strStartTime;
    }

    public void setStrEndTime(String strEndTime) {
        this.strEndTime = strEndTime;
    }

    public void setGetBookedDay(String getBookedDay) {
        this.getBookedDay = getBookedDay;
    }

    public void setGetBookedStart(String getBookedStart) {
        this.getBookedStart = getBookedStart;
    }

    public void setGetBookedEnd(String getBookedEnd) {
        this.getBookedEnd = getBookedEnd;
    }


    public int getInterval_ID() {
        return interval_ID;
    }

    public String getStrBookedDay() {
        return getBookedDay;
    }

    public String getStrStartTime() {
        return getBookedStart;
    }

    public String getStrEndTime() {
        return getBookedEnd;
    }

    public String getProperty(String propertyName) {
        switch (propertyName) {
            case "interval_ID":
                return "" + getInterval_ID();
            case "booked_day":
                return "" + getStrBookedDay();
            case "start":
                return "" + getStrStartTime();
            case "end":
                return "" + getStrEndTime();
        }
        return "";
    }

    public ArrayList<String> getItems() {
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add("" + getInterval_ID());
        itemList.add(getStrBookedDay());
        itemList.add(getStrStartTime());
        itemList.add(getStrEndTime());
        return itemList;
    }
}

