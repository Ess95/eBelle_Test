package helper;

import android.content.Context;
import android.database.Cursor;

import com.example.eodhuno.ebelle_test.DatabaseManager;
import com.example.eodhuno.ebelle_test.database_objects.Customer;

import java.util.ArrayList;

public class CustomerHelper {

    static DatabaseManager mDatabase;

    public CustomerHelper(Context context) {
        this.mDatabase = new DatabaseManager(context);
    }

    public static ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> allCustomers = new ArrayList<>();

        Cursor customerProfilesCursor = mDatabase.readAllCustomerProfiles();
        if (customerProfilesCursor.moveToFirst()) {
            do {
                allCustomers.add(new Customer(
                        customerProfilesCursor.getInt(0),
                        customerProfilesCursor.getString(1),
                        customerProfilesCursor.getString(2),
                        customerProfilesCursor.getString(3),
                        customerProfilesCursor.getInt(4),
                        customerProfilesCursor.getString(5),
                        customerProfilesCursor.getString(6),
                        customerProfilesCursor.getString(7)
                ));
            }
            while (customerProfilesCursor.moveToNext());
        }
        return allCustomers;
    }

    public static Customer getCustomerById(int id) throws Exception {
        ArrayList<Customer> customerArrayList = getAllCustomers();
        Customer customer = new Customer();
        boolean found = false;
        for (Customer customer1 : customerArrayList) {
            if (customer1.getCustID() == id) {
                customer = customer1;
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO CUSTOMER WITH ID " + id + "FOUND");
        }
        return customer;
    }

    public static ArrayList<Customer> getCustomerByFullName(String firstName, String lastName) throws Exception {
        ArrayList<Customer> customerArrayList = getAllCustomers();
        ArrayList<Customer> currCustomerName = new ArrayList<>();

        boolean found = false;
        for (Customer customer : customerArrayList) {
            if (customer.getFirstName().equalsIgnoreCase(firstName) && customer.getLastName().equalsIgnoreCase(lastName)) {
                currCustomerName.add(customer);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO CUSTOMER WITH NAME " + firstName + " " + lastName + " FOUND");
        }
        return currCustomerName;
    }

    public static ArrayList<Customer> getCustomerByFirstName(String firstName) throws Exception {
        ArrayList<Customer> customerArrayList = getAllCustomers();
        ArrayList<Customer> currCustomerFirstName = new ArrayList<>();

        boolean found = false;
        for (Customer customer : customerArrayList) {
            if (customer.getFirstName().equalsIgnoreCase(firstName)) {
                currCustomerFirstName.add(customer);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO CUSTOMER WITH NAME " + firstName + " FOUND");
        }
        return currCustomerFirstName;

    }
    public static ArrayList<Customer> getCustomerByLastName(String lastName) throws Exception {
        ArrayList<Customer> customerArrayList = getAllCustomers();
        ArrayList<Customer> currCustomerLastName = new ArrayList<>();

        boolean found = false;
        for (Customer customer : customerArrayList) {
            if (customer.getLastName().equalsIgnoreCase(lastName)) {
                currCustomerLastName.add(customer);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO CUSTOMER WITH NAME " + lastName + " FOUND");
        }
        return currCustomerLastName;
    }
    public static ArrayList<Customer> getEmployeeByGenderFemale(String female) throws Exception {
        ArrayList<Customer> customerArrayList = getAllCustomers();
        ArrayList<Customer> currCustomerFemale = new ArrayList<>();

        boolean found = false;
        for (Customer customer : customerArrayList) {
            if (customer.getGender().equalsIgnoreCase(female)) {
                currCustomerFemale.add(customer);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO CUSTOMER FOUND");
        }
        return currCustomerFemale;
    }
    public static ArrayList<Customer> getEmployeeByGenderMale(String male) throws Exception {
        ArrayList<Customer> customerArrayList = getAllCustomers();
        ArrayList<Customer> currCustomerMale = new ArrayList<>();

        boolean found = false;
        for (Customer customer : customerArrayList) {
            if (customer.getGender().equalsIgnoreCase(male)) {
                currCustomerMale.add(customer);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO CUSTOMER FOUND");
        }
        return currCustomerMale;
    }

}