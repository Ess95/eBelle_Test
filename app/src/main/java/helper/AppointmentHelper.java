package helper;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.ebellecustomcard.CustomTimePickerView;
import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.DatabaseManager;
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppointmentHelper {
    static DatabaseManager mDatabase;

    public AppointmentHelper(Context context) {
        this.mDatabase = new DatabaseManager(context);
    }

    public static ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> allAppointments = new ArrayList<>();

        Cursor apptCuror = mDatabase.readAllAppointments();
        if (apptCuror.moveToFirst()) {
            do {
                allAppointments.add(new Appointment(
                        apptCuror.getInt(0),
                        apptCuror.getInt(1),
                        apptCuror.getString(2),
                        apptCuror.getString(3),
                        apptCuror.getString(4),
                        apptCuror.getInt(5),
                        apptCuror.getInt(6),
                        apptCuror.getInt(7),
                        apptCuror.getInt(8),
                        apptCuror.getInt(9),
                        apptCuror.getInt(10),
                        apptCuror.getInt(11)

                ));
            }
            while (apptCuror.moveToNext());
        }
        return allAppointments;
    }

    public static Appointment getAppointmentById(int id) throws Exception {
        ArrayList<Appointment> appointmentArrayList = getAllAppointments();
        Appointment appointment = new Appointment();
        boolean found = false;
        for (Appointment appt : appointmentArrayList) {
            if (appt.getApptID() == id) {
                appointment = appt;
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* APPOINTMENT WITH ID " + id + " NOT FOUND");
        }
        return appointment;
    }

    public static ArrayList<Appointment> getAppointmentsByDate(DateTime dt) throws Exception {
        ArrayList<Appointment> appointmentArrayList = getAllAppointments();
        ArrayList<Appointment> currDateAppointments = new ArrayList<>();

        String date = TimeHelper.getDateString(dt);
        boolean found = false;
        for (Appointment appt : appointmentArrayList) {
            if (appt.getApptDate().equals(date)) {
                currDateAppointments.add(appt);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS WITH DATE " + date + " FOUND");
        }
        return currDateAppointments;
    }

    public static ArrayList<Appointment> getAppointmentsByDateAndTime(DateTime dt) throws Exception {
        ArrayList<Appointment> appointmentArrayList = getAppointmentsByDate(dt);
        ArrayList<Appointment> currTimeAppointments = new ArrayList<>();

        String time = TimeHelper.getTimeString(dt);
        boolean found = false;
        for (Appointment appt : appointmentArrayList) {
            if (appt.getApptTime().equals(time)) {
                currTimeAppointments.add(appt);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS WITH TIME " + time + " FOUND");
        }
        return currTimeAppointments;
    }

    public static ArrayList<Appointment> getAppointmentsByService(int serviceId) throws Exception {
        ArrayList<Appointment> appointmentArrayList = getAllAppointments();
        ArrayList<Appointment> currServiceAppointments = new ArrayList<>();

        boolean found = false;
        for (Appointment appt : appointmentArrayList) {
            if (appt.getServices() == serviceId) {
                currServiceAppointments.add(appt);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS WITH SERVICE_ID " + serviceId + " FOUND");
        }
        return currServiceAppointments;
    }

    public static ArrayList<Appointment> getAppointmentsByBeauticianID(int beauticianID) throws Exception {
        ArrayList<Appointment> appointmentArrayList = getAllAppointments();
        ArrayList<Appointment> currBeauticianAppointments = new ArrayList<>();

        boolean found = false;
        for (Appointment appt : appointmentArrayList) {
            if (appt.getServices() == beauticianID) {
                currBeauticianAppointments.add(appt);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS WITH BEAUTICIAN_ID " + beauticianID + " FOUND");
        }
        return currBeauticianAppointments;
    }

    public static ArrayList<Appointment> getAppointmentsByCustomerID(int custID) throws Exception {
        ArrayList<Appointment> appointmentArrayList = getAllAppointments();
        ArrayList<Appointment> currCustomerAppointments = new ArrayList<>();

        boolean found = false;
        for (Appointment appt : appointmentArrayList) {
            if (appt.getCustID_FK() == custID) {
                currCustomerAppointments.add(appt);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS WITH CUST_ID " + custID + " FOUND");
        }
        return currCustomerAppointments;
    }

    public static Map<String, ArrayList<Appointment>> getAppointmentsByDateRange(DateTime startDate, DateTime endDate) throws Exception {
        String startDateStr = TimeHelper.getDateString(startDate);
        Map<String, ArrayList<Appointment>> dateRangeMap = new HashMap<String, ArrayList<Appointment>>();
        while (startDate.isBefore(endDate.plusDays(1))) {
            String currDateStr = TimeHelper.getDateString(startDate);
            try {
                ArrayList<Appointment> currDateAppointments = getAppointmentsByDate(startDate);
                dateRangeMap.put(currDateStr, currDateAppointments);
            } catch (Exception e) {
            }
            startDate = startDate.plusDays(1);
        }
        if (dateRangeMap.size() == 0) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS WITH RANGE " + startDateStr + " TO " + TimeHelper.getDateString(endDate) + " FOUND");

        }
        return dateRangeMap;
    }

    public static ArrayList<Appointment> getAppointmentsByTimeRange(DateTime date, String startTime, String endTime) throws Exception {
        ArrayList<Appointment> currDayAppointmentArrayList = getAppointmentsByDate(date);
        ArrayList<Appointment> timeRangeMap = new ArrayList<Appointment>();

        int startHour = TimeHelper.getHourFromTimeText(startTime);
        int startMinute = TimeHelper.getMinutesFromTimeText(startTime);
        int endHour = TimeHelper.getHourFromTimeText(endTime);
        int endMinute = TimeHelper.getMinutesFromTimeText(endTime);

        for (Appointment appt : currDayAppointmentArrayList) {
            int currHour = TimeHelper.getHourFromTimeText(appt.getApptTime());
            int currMinute = TimeHelper.getMinutesFromTimeText(appt.getApptTime());
            if (currHour >= startHour && currHour <= endHour) {
                if (currHour == startHour) {
                    if ((currMinute < startMinute)) {
                        continue;
                    }
                } else if (currHour == endHour) {
                    if ((currMinute > endMinute)) {
                        continue;
                    }
                }
                timeRangeMap.add(appt);
            }
        }

        if (timeRangeMap.size() == 0) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS WITH RANGE " + startTime + " TO " + endTime + " FOUND");

        }
        return timeRangeMap;
    }

    public static ArrayList<Appointment> getAllConfirmedAppointments() throws Exception {
        ArrayList<Appointment> appointmentArrayList = getAllAppointments();
        ArrayList<Appointment> currConfirmedAppointments = new ArrayList<Appointment>();

        boolean found = false;
        for (Appointment appt : appointmentArrayList) {
            if (appt.getConfirmation() == 1) {
                currConfirmedAppointments.add(appt);
                found = true;
            }

        }
        if (!found) {
            throw new Exception("*ODHUNO* NO CONFIRMED APPOINTMENTS FOUND");
        }
        return currConfirmedAppointments;
    }

    public static ArrayList<Appointment> getAllUnconfirmedAppointments() throws Exception {
        ArrayList<Appointment> appointmentArrayList = getAllAppointments();
        ArrayList<Appointment> currUnconfirmedAppointments = new ArrayList<>();

        boolean found = false;
        for (Appointment appt : appointmentArrayList) {
            if (appt.getConfirmation() == 0) {
                currUnconfirmedAppointments.add(appt);
                found = true;
            }

        }
        if (!found) {
            throw new Exception("*ODHUNO* NO UNCONFIRMED APPOINTMENTS FOUND");
        }
        return currUnconfirmedAppointments;
    }

    public static Map<String, ArrayList<Appointment>> getConfirmedAppointmentsByRange(DateTime startDate, DateTime endDate) throws Exception {
        Map<String, ArrayList<Appointment>> appointmentMap =  new HashMap<String, ArrayList<Appointment>>();
        try {
            appointmentMap = getAppointmentsByDateRange(startDate,endDate);
        } catch (Exception e) {
        }

        Map<String, ArrayList<Appointment>> currConfirmedAppointments = new HashMap<String, ArrayList<Appointment>>();

        for(String date:appointmentMap.keySet()){
            ArrayList<Appointment> appts = appointmentMap.get(date);
            ArrayList<Appointment> confirmedAppts = new ArrayList<Appointment>();
            for(Appointment appt:appts){
                if(appt.getConfirmation() == 1){
                    confirmedAppts.add(appt);
                }
            }
            if(confirmedAppts.size() > 0 ){
                currConfirmedAppointments.put(date,confirmedAppts);
            }
        }
        if (currConfirmedAppointments.size() == 0) {
            throw new Exception("*ODHUNO* NO CONFIRMED APPOINTMENTS WITH RANGE " + TimeHelper.getDateString(startDate) + " TO " + TimeHelper.getDateString(endDate) + " FOUND");
        }
        return currConfirmedAppointments;

    }

    public static Map<String, ArrayList<Appointment>> getUnconfirmedAppointmentsByRange(DateTime startDate, DateTime endDate) throws Exception {
        Map<String, ArrayList<Appointment>> appointmentMap =  new HashMap<String, ArrayList<Appointment>>();
        try {
            appointmentMap = getAppointmentsByDateRange(startDate,endDate);
        } catch (Exception e) {
        }

        Map<String, ArrayList<Appointment>> currUnconfirmedAppointments = new HashMap<String, ArrayList<Appointment>>();

        for(String date:appointmentMap.keySet()) {
            ArrayList<Appointment> appts = appointmentMap.get(date);
            ArrayList<Appointment> unConfirmedAppts = new ArrayList<Appointment>();
            for (Appointment appt : appts) {
                if (appt.getConfirmation() == 0) {
                    unConfirmedAppts.add(appt);
                }
            }
            if (unConfirmedAppts.size() > 0) {
                currUnconfirmedAppointments.put(date, unConfirmedAppts);
            }
        }
            if (currUnconfirmedAppointments.size() == 0) {
                throw new Exception("*ODHUNO* NO UNCONFIRMED APPOINTMENTS WITH RANGE " + TimeHelper.getDateString(startDate) + " TO " + TimeHelper.getDateString(endDate) + " FOUND");
            }

        return currUnconfirmedAppointments;
    }

    public static ArrayList<Appointment> getAppointmentByServiceAndDuration(int servID, DateTime startDate, DateTime endDate) throws Exception {
        Map<String, ArrayList<Appointment>> appointmentMap = getAppointmentsByDateRange(startDate,endDate);
        ArrayList<Appointment> currAppointmentArrayList = new ArrayList<Appointment>();

        for(String date:appointmentMap.keySet()){
            ArrayList<Appointment> appointmentArrayList = appointmentMap.get(date);

            for(Appointment appt:appointmentArrayList){
                if(appt.getServices() == servID){
                    currAppointmentArrayList.add(appt);
                }
            }
        }
        if (currAppointmentArrayList.size() == 0) {
            throw new Exception("*ODHUNO* NO CONFIRMED APPOINTMENTS WITH RANGE " + TimeHelper.getDateString(startDate) + " TO " + TimeHelper.getDateString(endDate) + " FOUND");
        }
        return currAppointmentArrayList;



    }

    public static ArrayList<Appointment> getAppointmentByCategoryId(int catID) throws Exception {
        ArrayList<Service> serviceArrayList = ServiceHelper.getServiceByCategoryId(catID);
        ArrayList<Appointment> currAppointmentCategory = new ArrayList<>();

        ArrayList<Integer> serviceCategoryIDs = new  ArrayList<>();
        for(Service service:serviceArrayList){
            Log.d("JJJJJJJ","SERVICE ID :"+service.getServID());
            serviceCategoryIDs.add(service.getServID());
        }

        boolean found = false;
        for (Integer i : serviceCategoryIDs) {
            ArrayList<Appointment> appointmentArrayList = new ArrayList<Appointment>();
            try{
                appointmentArrayList = getAppointmentsByService(i);
            }catch(Exception e){
                //do nothing
            }

            if(appointmentArrayList.size() > 0){
                currAppointmentCategory.addAll(appointmentArrayList);
                found = true;
            }

        }
        if (!found) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS WITH CATEGORY_ID " + catID + " FOUND");
        }
        return currAppointmentCategory;
    }

    public static ArrayList<Integer[]> getBookedHours(DateTime date) throws Exception {
        ArrayList<Appointment> currDayAppointmentArrayList = getAppointmentsByDate(date);
        ArrayList<Integer[]> bookedHours = new ArrayList<Integer[]>();

        boolean found = false;
        for(Appointment appt:currDayAppointmentArrayList){
            Integer startHour = TimeHelper.getHourFromTimeText(appt.getApptTime());
            Integer startMinute = TimeHelper.getMinutesFromTimeText(appt.getApptTime());

            Integer duration = ServiceHelper.getServiceById(appt.getServices()).getDuration();
            Integer[] endTime = TimeHelper.addMinutes(appt.getApptTime(),duration);
            bookedHours.add(new Integer[]{startHour,startMinute,endTime[0],endTime[1]});

            found = true;
        }

        if (!found) {
            throw new Exception("*ODHUNO* NO BOOKED HOURS FOR THE DAY " + date + " FOUND");
        }
        return bookedHours;
    }

}
