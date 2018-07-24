package helper;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.eodhuno.ebelle_test.DatabaseManager;
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Employee;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ServiceHelper {
    static DatabaseManager mDatabase;

    public ServiceHelper(Context context) {
        this.mDatabase = new DatabaseManager(context);
    }

    public static ArrayList<Service> getAllServices() {
        ArrayList<Service> allServices = new ArrayList<>();

        Cursor serviceCursor = mDatabase.readAllServices();
        if (serviceCursor.moveToFirst()) {
            do {
                allServices.add(new Service(
                        serviceCursor.getInt(0),
                        serviceCursor.getString(1),
                        serviceCursor.getString(2),
                        serviceCursor.getInt(3),
                        serviceCursor.getInt(4),
                        serviceCursor.getString(5),
                        serviceCursor.getInt(6),
                        serviceCursor.getInt(7)
                ));
            }
            while (serviceCursor.moveToNext());
        }
        return allServices;
    }

    public static Service getServiceById(int id) throws Exception {
        ArrayList<Service> servicesArrayList = getAllServices();
        Service service = new Service();
        boolean found = false;
        for (Service serv : servicesArrayList) {
            if (serv.getServID() == id) {
                service = serv;
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* SERVICE WITH ID " + id + " NOT FOUND");
        }
        return service;
    }

    public static ArrayList<Service> getServiceByName(String service) throws Exception {
        ArrayList<Service> servicesArrayList = getAllServices();
        ArrayList<Service> currServiceName = new ArrayList<>();

        boolean found = false;
        for (Service servName : servicesArrayList) {
            if (servName.getServ_Name().equalsIgnoreCase(service)) {
                currServiceName.add(servName);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO SERVICE WITH NAME " + service + " FOUND");
        }
        return currServiceName;
    }

    public static ArrayList<Service> getServicesByAvailability() throws Exception {
        ArrayList<Service> serviceArrayList = getAllServices();
        ArrayList<Service> currAvailableServices = new ArrayList<Service>();

        boolean found = false;
        for (Service availService : serviceArrayList) {
            if (availService.getAvailability() == 1) {
                currAvailableServices.add(availService);
                found = true;
            }

        }
        if (!found) {
            throw new Exception("*ODHUNO* NO AVAILABLE SERVICES FOUND");
        }
        return currAvailableServices;
    }

    public static ArrayList<Service> getServiceByCategoryId(int id) throws Exception {
        ArrayList<Service> servicesArrayList = getAllServices();
        ArrayList<Service> currServiceCategory = new ArrayList<>();

        boolean found = false;
        for (Service serv : servicesArrayList) {
            if (serv.getCategoryID_FK() == id) {
                currServiceCategory.add(serv);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO SERVICE IN CATEGORY ID " + id + " FOUND");
        }
        return currServiceCategory;
    }

    public static ArrayList<Service> getServiceDuration(int startTime, int endTime) throws Exception {
        ArrayList<Service> serviceArrayList = getAllServices();
        ArrayList<Service> currServiceDuration = new ArrayList<>();

        boolean found = false;
        for (Service serv : serviceArrayList) {
            if (serv.getDuration() >= startTime && serv.getDuration() <= endTime ) {
                currServiceDuration.add(serv);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO SERVICE IN DURATION RANGE " + startTime + " TO " +endTime+ " FOUND");
        }
        return currServiceDuration;

    }


    public static ArrayList<Service> getServicesByPriceRange(int low, int high) throws Exception {
        ArrayList<Service> serviceArrayList = getAllServices();
        ArrayList<Service> currServicePrice = new ArrayList<>();

        boolean found = false;
        for (Service serv : serviceArrayList) {
            if (serv.getPrice() >= low && serv.getPrice() <= high ) {
                currServicePrice.add(serv);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO SERVICE IN PRICE RANGE " + low + " TO " +high+ " FOUND");
        }
        return currServicePrice;
    }


    /**public ArrayList<Service> getDescendingOrderedServicePriceList(int low, int high){
        ArrayList<Service> serviceArrayList = getAllServices();
        serviceArrayList.sort(Comparator.comparing(Service.getPrice()));
    }**/
    public static int serviceUsageMetrics(int id) throws Exception {
        ArrayList<Appointment> appointments = AppointmentHelper.getAppointmentsByService(id);
        return appointments.size();
    }

    public static int getServiceUsageMetricsByDateRange(int id, DateTime startDate, DateTime endTime) throws Exception {
        ArrayList<Appointment> appointments = AppointmentHelper.getAppointmentByServiceAndDuration(id,startDate,endTime);
        return appointments.size();
    }
    public static int getServicePaymentMetricsByDateRange(int id, DateTime startDate, DateTime endTime) throws Exception {
        ArrayList<Appointment> appointments = AppointmentHelper.getAppointmentByServiceAndDuration(id,startDate,endTime);

        int revenue = 0;
        boolean found = false;
        for (Appointment appt : appointments) {
            revenue += appt.getServiceCharge();
            found = true;
        }
        if (!found) {
            throw new Exception("*ODHUNO* NO APPOINTMENTS FOUMD FOR SERVICE ID " +id);
        }

        return revenue;
    }
}
