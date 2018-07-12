package helper;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TimeHelper {
    public static ArrayList<Integer> getSundaysOfMonth(int month, int year){
        ArrayList<Integer> sundaysOfWeek = new ArrayList<Integer>();

        DateTime dt1 = new DateTime(year,month,1,12,0);
        int startDay = 1;
        int endDay = dt1.dayOfMonth().getMaximumValue();

        for(int i=startDay; i<=endDay; i++){
            DateTime currDate = new DateTime(year,month,i,12,0);
            if(currDate.getDayOfWeek() == 7){
                sundaysOfWeek.add(i);
            }
        }
        return sundaysOfWeek;
    }

    public static ArrayList<DateTime> getSundaysOfMonthDateTime(int month, int year){
        ArrayList<DateTime> sundaysOfWeekDateTime = new ArrayList<DateTime>();

        DateTime dt1 = new DateTime(year,month,1,12,0);
        int startDay = 1;
        int endDay = dt1.dayOfMonth().getMaximumValue();

        for(int i=startDay; i<=endDay; i++){
            DateTime currDate = new DateTime(year,month,i,12,0);
            if(currDate.getDayOfWeek() == 7){
                sundaysOfWeekDateTime.add(currDate);
            }
        }
        return sundaysOfWeekDateTime;
    }

    public static int getLastDayOfMonth(int month, int year){
        DateTime dt1 = new DateTime(year,month,1,12,0);
        return dt1.dayOfMonth().getMaximumValue();
    }

    public static DateTime getLastDayOfMonthDateTime(int month, int year){
        DateTime dt1 = new DateTime(year,month,1,12,0);
        DateTime dt2 = new DateTime(year,month,dt1.dayOfMonth().getMaximumValue(),12,0);
        return dt2;
    }

    public static String getDateString(DateTime dt){
        return dt.toString("d/M/yyyy");
    }

    public static String getTimeString(DateTime dt){
        return dt.toString("H:m");
    }

    public static DateTime getDateTimeFromString(String dt){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        return dtf.parseDateTime(dt);
    }

    public static int getHourFromTimeText(String time){
        String[] str = time.split(":");
        return Integer.parseInt(str[0]);
    }

    public static int getMinutesFromTimeText(String time){
        String[] str = time.split(":");
        return Integer.parseInt(str[1]);
    }

    public static Integer[] addHours(String time, int durationHour, int durationMins){
        int hour = getHourFromTimeText(time);
        int mins = getMinutesFromTimeText(time);

        DateTime currDate = new DateTime(2018,4,11,hour,mins);
        currDate = currDate.plusHours(durationHour);
        currDate = currDate.plusMinutes(durationMins);

        return new Integer[]{currDate.getHourOfDay(), currDate.getMinuteOfHour()};

    }

    public static Integer[] addMinutes(String time, int durationMins){
        int hour = getHourFromTimeText(time);
        int mins = getMinutesFromTimeText(time);

        DateTime currDate = new DateTime(2018,4,11,hour,mins);
        currDate = currDate.plusMinutes(durationMins);

        return new Integer[]{currDate.getHourOfDay(), currDate.getMinuteOfHour()};

    }

    public static int[] getHourMinFromMinutes(int mins){
        int hours = mins/60;
        int minutes = mins%60;
        return new int[]{hours,minutes};
    }

    public static int getMinFromHourMins(int hour, int mins){
        return (60 * hour) + mins;
    }
}
