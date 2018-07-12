package com.example.eodhuno.ebelle_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.AppointmentInterval;
import com.example.eodhuno.ebelle_test.database_objects.Customer;
import com.example.eodhuno.ebelle_test.database_objects.CustomerPaymentHistory;
import com.example.eodhuno.ebelle_test.database_objects.Employee;
import com.example.eodhuno.ebelle_test.database_objects.FullyBookedDay;
import com.example.eodhuno.ebelle_test.database_objects.FullyBookedHours;
import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.Service;
import com.example.eodhuno.ebelle_test.database_objects.Category;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    SQLiteDatabase sqLiteDatabase;
    //Arraylists
    public List<Customer> customerList;
    public List<Employee> employeeList;
    public List<Product> productList;
    public List<Service> serviceList;
    public List<Appointment> appointmentList;
    public List<FullyBookedDay> bookedDayList;
    public List<FullyBookedHours> bookedHoursList;
    public List<AppointmentInterval> appointmentIntervalList;

    public List<Category> categoryList;
    public List<CustomerPaymentHistory>customerPaymentHistoryList;

    public static final String DATABASE_NAME = "eBelleSalon";
    public static final int DATABASE_VERSION = 34;

    //Table names
    public static final String CUSTOMER = "CUSTOMER";
    public static final String CUST_HISTORY_TABLE_NAME = "CUST_HISTORY";
    public static final String EMPLOYEE_TABLE_NAME = "EMPLOYEES";
    public static final String CATEGORY_TABLE_NAME = "CATEGORY";
    public static final String SERVICES_TABLE_NAME = "SERVICES";
    public static final String PRODUCT_TABLE_NAME = "PRODUCTS";
    public static final String APPT_TABLE_NAME = "APPOINTMENTS";
    public static final String FULLY_BOOKED_DAYS = "BOOKED_DAYS";
    public static final String FULLY_BOOKED_HOURS = "BOOKED_HOURS";
    public static final String TIME_INTERVALS = "INTERVALS";
    public static final String SALON_PAYMENT_TABLE_NAME = "SRV_PAYMENT";
    public static final String ACCESS_RIGHTS = "ACCESS_RIGHTS";

    //Access Rights
    public static final String AR_ID = "ID";
    public static final String AR_DESCR = "RIGHTS";
    public static final String AR_USER_ID = "USER";


    //Customers Table
    public static final String CUSTOMER_ID = "ID";
    public static final String CUSTOMER_FNAME = "F_NAME";
    public static final String CUSTOMER_LNAME = "L_NAME";
    public static final String CUSTOMER_GENDER = "GENDER";
    public static final String CUSTOMER_PHONE_NO = "PHONE_NO";
    public static final String CUSTOMER_EMAIL = "EMAIL";
    public static final String CUSTOMER_PASSWORD = "PASSWORD";
    public static final String CUSTOMER_CONFIRM_PASSWORD = "CONFIRM_PASS";

    //Customer Payments/History Table
    public static final String CUST_HISTORY_ID = "HIST_ID";
    public static final String CUST_HISTORY_DATE = "DATE_OF_PAYM";
    public static final String CUST_HISTORY_SERVICES = "SERVICES";
    public static final String CUST_HISTORY_BEAUTICIAN = "BEAUTICIAN";
    public static final String CUST_HISTORY_MODE_OF_PAY = "MODE_OF_PAY";
    public static final String CUST_HISTORY_CHARGES = "TOTAL_CHARGES";

    //Employees Table
    public static final String EMPLOYEE_ID = "EMP_ID";
    public static final String EMPLOYEE_IMAGE = "EMP_IMAGE";
    public static final String EMPLOYEE_FNAME = "EMP_FNAME";
    public static final String EMPLOYEE_LNAME = "EMP_LNAME";
    public static final String EMPLOYEE_GENDER = "GENDER";
    public static final String EMPLOYEE_PHONE_NO = "PHONE_NO";
    public static final String EMPLOYEE_EMAIL = "EMAIL";
    public static final String EMPLOYEE_SERVICES = "SERVICES";
    public static final String EMPLOYEE_RATING = "RATING";
    public static final String EMPLOYEE_MODEPAY = "MODE_OF_PAY";
    public static final String EMPLOYEE_ACCESS_RIGHT = "ACCESS_RIGHT";
    public static final String EMPLOYEE_AVAILABILITY = "AVAILABILITY";



    //Categories Table
    public static final String CATEGORY_ID = "CAT_ID";
    public static final String CATEGORY_IMG = "CAT_IMG";
    public static final String CATEGORY_NAME = "CAT_NAME";
    public static final String CATEGORY_DESCRIPTION = "DESCR";

    //Services_Table
    public static final String SERV_ID = "SERV_ID";
    public static final String CATEGORY_ID_FK = "CAT_ID";
    public static final String SERV_NAME = "SERV_NAME";
    public static final String SERV_PRICE = "PRICE";
    public static final String SERV_DURATION = "DURATION";
    public static final String SERV_DESCRIPTION = "DESCR";
    public static final String SERV_AVAILABILITY = "AVAILABILITY";

    //Products Table
    public static final String PRODUCT_ID = "PROD_ID";
    public static final String SERVICE_ID_FK = "SERV_ID";
    public static final String PRODUCT_NAME = "PROD_NAME";
    public static final String PRODUCT_DESCR = "PRODUCT_DESCR";
    public static final String PRODUCT_QTY = "QUANTITY";
    public static final String PRODUCT_UNIT_COST = "UNIT_PRICE";
    public static final String PRODUCT_REORDER_LEVEL = "REORDER_LEVEL";
    public static final String PRODUCT_AVAILABILITY = "AVAILABILITY";

    //Appointments Table
    public static final String APPT_ID = "APPT_ID";
    public static final String APPT_DATE_CREATED = "DATE_CREATED";
    public static final String CUST_ID_FK = "CUST_ID";
    public static final String APPT_DATE = "APPT_DATE";
    public static final String APPT_TIME = "APPT_TIME";
    public static final String APPT_SERVICES = "SERVICES";
    public static final String APPT_IF_PRODUCT_USED = "IF_PRODUCT_USED";
    public static final String APPT_STYLIST = "BEAUTICIAN";
    public static final String APPT_CONFIRMED = "CONFIRMATION";
    public static final String APPT_SERVICE_CHARGES = "SERVICE_CHARGES";
    public static final String APPT_PRODUCT_CHARGES = "PRODUCT_CHARGES";
    public static final String TOTAL_CHARGES = "TOTAL";


    //Fully booked days
    public static final String B_ID = "ID";
    public static final String B_DAY = "B_DAY";

    //Fully booked hours
    public static final String HOUR_ID = "HOUR_ID";
    public static final String BOOKED_APPT_FK = "APPT_ID";
    public static final String HOUR_DAY = "DAY";
    public static final String START_HOUR = "START_TIME";
    public static final String END_HOUR = "END_TIME";

    //AppointmentTime Intervals
    public static final String INT_ID = "INTV_ID";
    public static final String DAY_OF_WEEK = "DAY_OF_WEEK";
    public static final String START_TIME = "START_TIME";
    public static final String END_TIME = "END_TIME";

    //Salon Payments Table
    public static final String SALON_PAYMENT_SERVPAY_ID = "SERV_PAYM_ID";
    public static final String SALON_PAYMENT_CUSTID_FK = "CUST_HISTORY_ID";
    public static final String SALON_PAYMENT_SERVICES = "SERVICES";
    public static final String SALON_PAYMENT_BEAUTICIAN = "BEAUTICIAN";
    public static final String SALON_PAYMENT_MODE_OF_PAY = "MODE_OF_PAYM";
    public static final String SALON_PAYMENT_TOTAL_CHARGES= "TOTAL_CHARGES";

    //Creating tables in DB
    private static final String CREATE_ACCESS_RIGHT_TABLE = " CREATE TABLE  "+ACCESS_RIGHTS+" (\n" +
            "   "+AR_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+AR_DESCR+" TEXT NOT NULL\n" + ");";

    private static final String CREATE_CUSTOMER_TABLE = " CREATE TABLE  "+CUSTOMER+" (\n" +
            "   "+CUSTOMER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+CUSTOMER_FNAME+" TEXT NOT NULL, \n" +
            "   "+CUSTOMER_LNAME+" TEXT NOT NULL, \n" +
            "   "+CUSTOMER_GENDER+" TEXT NOT NULL, \n" +
            "   "+CUSTOMER_PHONE_NO+" INTEGER NOT NULL, \n" +
            "   "+CUSTOMER_EMAIL+" TEXT NOT NULL, \n" +
            "   "+CUSTOMER_PASSWORD+" TEXT NOT NULL, \n" +
            "   "+CUSTOMER_CONFIRM_PASSWORD+" TEXT NOT NULL\n" + ");";

    private static final String CREATE_CUST_HISTORY_TABLE = " CREATE TABLE  "+CUST_HISTORY_TABLE_NAME+" (\n" +
            "   "+CUST_HISTORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "   "+CUST_HISTORY_DATE+" INTEGER NOT NULL, " +
            "   "+CUST_HISTORY_SERVICES+" TEXT NOT NULL, \n" +
            "   "+CUST_HISTORY_BEAUTICIAN+" TEXT NOT NULL, \n" +
            "   "+CUST_HISTORY_MODE_OF_PAY+" TEXT NOT NULL, \n" +
            "   "+CUST_HISTORY_CHARGES+" INTEGER NOT NULL, " +
            "       FOREIGN KEY ("+CUST_HISTORY_CHARGES+") REFERENCES "+APPT_TABLE_NAME+" ("+TOTAL_CHARGES+"), \n " +
            "       FOREIGN KEY ("+CUST_HISTORY_SERVICES+") REFERENCES "+SERVICES_TABLE_NAME+" ("+SERV_ID+"), \n " +
            "       FOREIGN KEY ("+CUST_HISTORY_BEAUTICIAN+") REFERENCES "+EMPLOYEE_TABLE_NAME+" ("+EMPLOYEE_ID+"))";

    private static final String CREATE_EMPLOYEES_TABLE = " CREATE TABLE  "+EMPLOYEE_TABLE_NAME+" (\n" +
            "   "+EMPLOYEE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+EMPLOYEE_IMAGE+" BLOB, \n" +
            "   "+EMPLOYEE_FNAME+" TEXT NOT NULL, \n" +
            "   "+EMPLOYEE_LNAME+" TEXT NOT NULL, \n" +
            "   "+EMPLOYEE_GENDER+" TEXT NOT NULL, \n" +
            "   "+EMPLOYEE_PHONE_NO+" INTEGER NOT NULL, \n" +
            "   "+EMPLOYEE_EMAIL+" TEXT NOT NULL, \n" +
            "   "+EMPLOYEE_MODEPAY+" TEXT NOT NULL,\n" +
            "   "+EMPLOYEE_SERVICES+" INTEGER NOT NULL, " +
            "   "+EMPLOYEE_RATING+" INTEGER NOT NULL, " +
            "   "+EMPLOYEE_ACCESS_RIGHT+" INTEGER NOT NULL, " +
            "   "+EMPLOYEE_AVAILABILITY+" INTEGER NOT NULL, " +
            "       FOREIGN KEY ("+EMPLOYEE_ACCESS_RIGHT+") REFERENCES "+ACCESS_RIGHTS+" ("+AR_ID+"), \n" +
            "       FOREIGN KEY ("+EMPLOYEE_SERVICES+") REFERENCES "+SERVICES_TABLE_NAME+" ("+SERV_ID+"))";


    private static final String CREATE_SERVICE_CAT_TABLE = " CREATE TABLE  "+CATEGORY_TABLE_NAME+" (\n" +
            "   "+CATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+CATEGORY_IMG+" TEXT NOT NULL, \n" +
            "   "+CATEGORY_NAME+" TEXT NOT NULL, \n" +
            "   "+CATEGORY_DESCRIPTION+" TEXT NOT NULL \n" + ");";

    private static final String CREATE_SERVICES_TABLE = " CREATE TABLE "+SERVICES_TABLE_NAME+" (\n" +
            "   "+SERV_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+SERV_NAME+" TEXT NOT NULL, \n" +
            "   "+SERV_PRICE+" INTEGER NOT NULL, \n" +
            "   "+SERV_DURATION+" INTEGER NOT NULL, \n" +
            "   "+SERV_DESCRIPTION+" TEXT NOT NULL, \n" +
            "   "+SERV_AVAILABILITY+" INTEGER NOT NULL,\n" +
            "   "+CATEGORY_ID_FK+" INTEGER NOT NULL, " +
            "   FOREIGN KEY ("+CATEGORY_ID_FK+") REFERENCES "+CATEGORY_TABLE_NAME+" ("+CATEGORY_ID+"))";

    private static final String CREATE_PRODUCTS_TABLE = " CREATE TABLE "+PRODUCT_TABLE_NAME+" (\n" +
            "   "+PRODUCT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+PRODUCT_NAME+" TEXT NOT NULL, \n" +
            "   "+PRODUCT_DESCR+" TEXT NOT NULL, \n" +
            "   "+PRODUCT_QTY+" INTEGER NOT NULL DEFAULT 0, \n" +
            "   "+PRODUCT_UNIT_COST+" INTEGER NOT NULL DEFAULT 0, \n" +
            "   "+PRODUCT_REORDER_LEVEL+" INTEGER NOT NULL, \n" +
            "   "+PRODUCT_AVAILABILITY+" INTEGER NOT NULL,\n" +
            "   "+SERVICE_ID_FK+" INTEGER NOT NULL, " +
            "       FOREIGN KEY ("+SERVICE_ID_FK+") REFERENCES "+SERVICES_TABLE_NAME+" ("+SERV_ID+"))";

    private static final String CREATE_APPTS_TABLE = " CREATE TABLE "+APPT_TABLE_NAME+" (\n" +
            "   "+APPT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+CUST_ID_FK+" INTEGER NOT NULL, \n" +
            "   "+APPT_DATE_CREATED+" DEFAULT CURRENT_TIMESTAMP, \n" +
            "   "+APPT_DATE+" TEXT NOT NULL, \n" +
            "   "+APPT_TIME+" TEXT NOT NULL, \n" +
            "   "+APPT_SERVICES+" INTEGER NOT NULL, \n" +
            "   "+APPT_IF_PRODUCT_USED+" INTEGER NOT NULL, \n" +
            "   "+APPT_STYLIST+" INTEGER NOT NULL, \n" +
            "   "+APPT_CONFIRMED+" INTEGER NOT NULL, \n" +
            "   "+APPT_SERVICE_CHARGES+" INTEGER NOT NULL, \n" +
            "   "+APPT_PRODUCT_CHARGES+" INTEGER NOT NULL, \n" +
            "   "+TOTAL_CHARGES+" INTEGER NOT NULL, \n" +
            "       FOREIGN KEY ("+APPT_SERVICES+") REFERENCES "+SERVICES_TABLE_NAME+" ("+SERV_ID+"), \n " +
            "       FOREIGN KEY ("+APPT_STYLIST+") REFERENCES "+EMPLOYEE_TABLE_NAME+" ("+EMPLOYEE_ID+"), \n " +
            "       FOREIGN KEY ("+CUST_ID_FK+") REFERENCES "+CUSTOMER+" ("+CUSTOMER_ID+"))";

    private static final String CREATE_BOOKED_DAYS = " CREATE TABLE "+FULLY_BOOKED_DAYS+" (\n" +
            "   "+B_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+B_DAY+" TEXT NOT NULL \n" + ");";

    private static final String CREATE_BOOKED_HOURS = " CREATE TABLE  "+FULLY_BOOKED_HOURS+" (\n" +
            "   "+HOUR_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+HOUR_DAY+" TEXT NOT NULL, \n" +
            "   "+START_HOUR+" TEXT NOT NULL, \n" +
            "   "+END_HOUR+" TEXT NOT NULL, \n" +
            "   "+BOOKED_APPT_FK+" INTEGER NOT NULL, \n" +
            "        FOREIGN KEY ("+BOOKED_APPT_FK+") REFERENCES "+APPT_TABLE_NAME+" ("+APPT_ID+"))";

    private static final String CREATE_TIME_INTERVALS = " CREATE TABLE  "+TIME_INTERVALS+" (\n" +
            "   "+INT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "   "+DAY_OF_WEEK+" TEXT NOT NULL, \n" +
            "   "+START_TIME+" TEXT NOT NULL, \n" +
            "   "+END_TIME+" TEXT NOT NULL \n" + ");";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("ENTERS ONCREATE", "FIIIIIIIIRST");

        sqLiteDatabase.execSQL(CREATE_ACCESS_RIGHT_TABLE);
        sqLiteDatabase.execSQL(CREATE_CUSTOMER_TABLE);
        sqLiteDatabase.execSQL(CREATE_CUST_HISTORY_TABLE);
        sqLiteDatabase.execSQL(CREATE_EMPLOYEES_TABLE);
        sqLiteDatabase.execSQL(CREATE_SERVICE_CAT_TABLE);
        sqLiteDatabase.execSQL(CREATE_SERVICES_TABLE);
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_APPTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_BOOKED_DAYS);
        sqLiteDatabase.execSQL(CREATE_BOOKED_HOURS);
        sqLiteDatabase.execSQL(CREATE_TIME_INTERVALS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("ENTERS ONUPGRADE", "SEEEEEECOND");

        if( oldVersion != newVersion) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ACCESS_RIGHTS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CUSTOMER);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CUST_HISTORY_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SERVICES_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + APPT_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FULLY_BOOKED_DAYS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FULLY_BOOKED_HOURS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TIME_INTERVALS);

            onCreate(sqLiteDatabase);
        }
    }

    //CRUD FOR CUSTOMER TABLE
    public boolean addCustomerProfile(String FirstName, String LastName, String Gender, int PhoneNo, String Email, String Password, String confirmPassword){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_FNAME,FirstName);
        cv.put(CUSTOMER_LNAME, LastName);
        cv.put(CUSTOMER_GENDER, Gender);
        cv.put(CUSTOMER_PHONE_NO,String.valueOf(PhoneNo));
        cv.put(CUSTOMER_EMAIL,Email);
        cv.put(CUSTOMER_PASSWORD,Password);
        cv.put(CUSTOMER_CONFIRM_PASSWORD,confirmPassword);

        sqLiteDatabase.insert(CUSTOMER,null, cv);
        return true;
    }

    public Cursor readAllCustomerProfiles(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+CUSTOMER,null);
    }

    public boolean updateCustomer(int CustID,String FirstName, String LastName,String Gender, int PhoneNo,String Email, String Password, String confirmPassword ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_FNAME,FirstName);
        cv.put(CUSTOMER_LNAME,LastName);
        cv.put(CUSTOMER_GENDER,Gender);
        cv.put(CUSTOMER_PHONE_NO,String.valueOf(PhoneNo));
        cv.put(CUSTOMER_EMAIL,Email);
        cv.put(CUSTOMER_PASSWORD,Password);
        cv.put(CUSTOMER_CONFIRM_PASSWORD,Password);


        //If the value of the statement is greater than 0, it means the update was successsful.
       return sqLiteDatabase.update(CUSTOMER,cv,CUSTOMER_ID+"=?", new String[]{String.valueOf(CustID)}) > 0;
       }

    public boolean deleteCustomer(int CustID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(CUSTOMER,CUSTOMER_ID+"=?", new String[]{String.valueOf(CustID)}) > 0;

    }

    //CRUD FOR EMPLOYEE TABLE
    public boolean addEmployeeProfile(byte[]empImage,String FirstName,String LastName, String Gender, int PhoneNo, String Email,int Services,int rating, String ModeOfPay, int accessRight,int availability){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EMPLOYEE_IMAGE,empImage);
        cv.put(EMPLOYEE_FNAME,FirstName);
        cv.put(EMPLOYEE_LNAME, LastName);
        cv.put(EMPLOYEE_GENDER, Gender);
        cv.put(EMPLOYEE_PHONE_NO,String.valueOf(PhoneNo));
        cv.put(EMPLOYEE_EMAIL,Email);
        cv.put(EMPLOYEE_SERVICES,Services);
        cv.put(EMPLOYEE_RATING,rating);
        cv.put(EMPLOYEE_MODEPAY, ModeOfPay);
        cv.put(EMPLOYEE_ACCESS_RIGHT, accessRight);
        cv.put(EMPLOYEE_AVAILABILITY,availability);


        sqLiteDatabase.insert(EMPLOYEE_TABLE_NAME, null, cv);
        return true;
    }

    public Cursor readAllEmployeeProfiles(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+EMPLOYEE_TABLE_NAME, null);
    }

    public boolean updateEmployee(int EmpID, byte[] empProfileImage, String FirstName, String LastName, String Gender, int PhoneNo, String Email,String Services, String ModeOfPay, int accessRight ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EMPLOYEE_IMAGE,empProfileImage);
        cv.put(EMPLOYEE_FNAME,FirstName);
        cv.put(EMPLOYEE_LNAME,LastName);
        cv.put(EMPLOYEE_GENDER,Gender);
        cv.put(EMPLOYEE_PHONE_NO,String.valueOf(PhoneNo));
        cv.put(EMPLOYEE_EMAIL,Email);
        cv.put(EMPLOYEE_SERVICES,Services);
        cv.put(EMPLOYEE_MODEPAY,ModeOfPay);
        cv.put(EMPLOYEE_ACCESS_RIGHT, accessRight);


        //If the value of the statement is greater than 0, it means the update was successsful.
        return sqLiteDatabase.update(EMPLOYEE_TABLE_NAME,cv,EMPLOYEE_ID+"=?", new String[]{String.valueOf(EmpID)}) > 0;
    }

    public boolean deleteEmployee(int EmpID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(EMPLOYEE_TABLE_NAME,EMPLOYEE_ID+"=?", new String[]{String.valueOf(EmpID)}) > 0;
    }

    //CRUD FOR CATEGORY TABLE
    public boolean addCategory(String txtCatImage, String categoryName,String categoryDescription){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_IMG, txtCatImage);
        cv.put(CATEGORY_NAME, categoryName);
        cv.put(CATEGORY_DESCRIPTION, categoryDescription);

        sqLiteDatabase.insert(CATEGORY_TABLE_NAME, null, cv);
        return true;
    }

    public Cursor readAllServiceCategory(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+CATEGORY_TABLE_NAME, null);
    }

    public boolean updateCategory(int categotyID, String categoryName, String categoryDescription){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_NAME,categoryName);
        cv.put(CATEGORY_DESCRIPTION,categoryDescription);

        //If the value of the statement is greater than 0, it means the update was successsful.
        return sqLiteDatabase.update(CATEGORY_TABLE_NAME,cv,CATEGORY_ID+"=?", new String[]{String.valueOf(categotyID)}) > 0;
    }

    public boolean deleteCategory(int categoryID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(CATEGORY_TABLE_NAME,CATEGORY_ID+"=?", new String[]{String.valueOf(categoryID)}) > 0;
    }

    //CRUD FOR SERVICES TABLE
    public boolean addServices(String serv_Name, String serv_Description,int categoryID_FK,int price, int duration, int availability){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SERV_NAME, serv_Name);
        cv.put(SERV_DESCRIPTION, serv_Description);
        cv.put(SERV_PRICE, price);
        cv.put(CATEGORY_ID_FK, categoryID_FK);
        cv.put(SERV_DURATION,duration);
        cv.put(SERV_AVAILABILITY, availability);

        sqLiteDatabase.insert(SERVICES_TABLE_NAME, null, cv);
        return true;
    }

    public Cursor readAllServices(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+SERVICES_TABLE_NAME, null);
    }

    public boolean updateServices(int servID, int categoryID_FK, String serv_Name, String serv_Description, int price, String availability){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_ID_FK, categoryID_FK);
        cv.put(SERV_NAME, serv_Name);
        cv.put(SERV_DESCRIPTION, serv_Description);
        cv.put(SERV_PRICE, price);
        cv.put(SERV_AVAILABILITY, availability);

        //If the value of the statement is greater than 0, it means the update was successsful.
        return sqLiteDatabase.update(SERVICES_TABLE_NAME,cv,SERV_ID+"=?", new String[]{String.valueOf(servID)}) > 0;
    }

    public boolean deleteServices(int servID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(SERVICES_TABLE_NAME,SERV_ID+"=?", new String[]{String.valueOf(servID)}) > 0;
    }

    //CRUD FOR PRODUCT TABLE
    public boolean addProducts( String prod_Name, String prod_descr, int quantity, int price, int reorderLevel, int availability, int services){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME, prod_Name);
        cv.put(PRODUCT_DESCR, prod_descr);
        cv.put(PRODUCT_QTY, quantity);
        cv.put(PRODUCT_UNIT_COST, price);
        cv.put(PRODUCT_REORDER_LEVEL, reorderLevel);
        cv.put(SERVICE_ID_FK, services);
        cv.put(PRODUCT_AVAILABILITY, availability);

        sqLiteDatabase.insert(PRODUCT_TABLE_NAME, null, cv);
        return true;
    }

    public  Cursor readAllProducts(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+PRODUCT_TABLE_NAME, null);
    }

    public boolean updateProducts(int prodID,String prod_Name,String prod_descr,int quantity, int price,int reorderLevel,int availability,int servID_FK){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SERVICE_ID_FK, servID_FK);
        cv.put(PRODUCT_NAME, prod_Name);
        cv.put(PRODUCT_DESCR, prod_descr);
        cv.put(PRODUCT_QTY, quantity);
        cv.put(PRODUCT_UNIT_COST, price);
        cv.put(PRODUCT_REORDER_LEVEL, reorderLevel);
        cv.put(PRODUCT_AVAILABILITY,availability);

        //If the value of the statement is greater than 0, it means the update was successsful.
        return sqLiteDatabase.update(PRODUCT_TABLE_NAME,cv,PRODUCT_ID+"=?", new String[]{String.valueOf(prodID)}) > 0;
    }

    public boolean deleteProducts(int prodID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(PRODUCT_TABLE_NAME,PRODUCT_ID+"=?", new String[]{String.valueOf(prodID)}) > 0;
    }

    //CRUD FOR APPOINTMENT TABLE
    public boolean addAppointment(int custID_FK, DateTime apptDate, DateTime apptTime,
                           int services, int ifProductUsed, int beautician, int confirmation, int serviceCharges, int productCharges,
                                  int totalApptCharges){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        DateTime dateCreated = new DateTime();
        String dateCreatedStr = ""+dateCreated.getDayOfMonth()+"/"+ dateCreated.getMonthOfYear()+"/" +
                dateCreated.getYear();

        String apptDateStr = ""+apptDate.getDayOfMonth()+"/"+ apptDate.getMonthOfYear()+"/" +
                apptDate.getYear();

        String apptTimeStr = ""+apptTime.getHourOfDay()+":"+ apptTime.getMinuteOfHour();

        ContentValues cv = new ContentValues();
        cv.put(CUST_ID_FK, custID_FK);
        cv.put(APPT_DATE_CREATED, dateCreatedStr);
        cv.put(APPT_DATE,apptDateStr);
        cv.put(APPT_TIME,apptTimeStr);
        cv.put(APPT_SERVICES,services);
        cv.put(APPT_IF_PRODUCT_USED,ifProductUsed);
        cv.put(APPT_STYLIST,beautician);
        cv.put(APPT_CONFIRMED,confirmation);
        cv.put(APPT_SERVICE_CHARGES, serviceCharges);
        cv.put(APPT_PRODUCT_CHARGES, productCharges);
        cv.put(TOTAL_CHARGES, totalApptCharges);


        sqLiteDatabase.insert(APPT_TABLE_NAME, null, cv);
        return true;
    }

    public Cursor readAllAppointments(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+APPT_TABLE_NAME, null);
    }

    public boolean updateAppointment(int apptID, int custID_FK, String dateCreated, String apptDate, String apptTime, String services, String ifProductUsed, int beautician, String confirmation){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CUST_ID_FK, custID_FK);
        cv.put(APPT_DATE_CREATED, dateCreated);
        cv.put(APPT_DATE,apptDate);
        cv.put(APPT_TIME,apptTime);
        cv.put(APPT_SERVICES,services);
        cv.put(APPT_IF_PRODUCT_USED,ifProductUsed);
        cv.put(APPT_STYLIST,beautician);
        cv.put(APPT_CONFIRMED,confirmation);

        return sqLiteDatabase.update(APPT_TABLE_NAME,cv,APPT_ID+"=?", new String[]{String.valueOf(apptID)}) > 0;
    }

    public boolean deleteAppointment(int apptID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(APPT_TABLE_NAME,APPT_ID+"=?", new String[]{String.valueOf(apptID)}) > 0;
    }
    //CRUD FOR FULLY BOOKED DAYS
    public boolean addFullyBookedDays(DateTime bookedDay){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String bookedDayStr = ""+bookedDay.dayOfWeek();

        ContentValues cv = new ContentValues();
        cv.put(B_DAY, bookedDayStr);
        sqLiteDatabase.insert(FULLY_BOOKED_DAYS, null, cv);
        return true;
    }
    public Cursor readAllFullyBookedDay(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+FULLY_BOOKED_DAYS, null);
    }

    public boolean updateFullyBookedDay(int dayBooked_ID, DateTime bookedDay) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String bookedDayStr = "" + bookedDay.dayOfWeek();

        ContentValues cv = new ContentValues();
        cv.put(B_DAY, bookedDayStr);

        return sqLiteDatabase.update(FULLY_BOOKED_DAYS, cv, B_ID + "=?", new String[]{String.valueOf(dayBooked_ID)}) > 0;
    }

    public boolean deleteFullyBookedDay(int dayBooked){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(FULLY_BOOKED_DAYS,B_ID+"=?", new String[]{String.valueOf(dayBooked)}) > 0;
    }

    //CRUD FOR FULLY BOOKED HOURS
    public boolean addFullyBookedHours(DateTime bookedDay, DateTime startHour, DateTime endHour){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String bookedDayStr = ""+bookedDay.dayOfWeek();
        String bookedStartHour = ""+startHour.getHourOfDay()+ ":" + startHour.getMinuteOfHour();
        String bookedEndHour = ""+endHour.getHourOfDay()+ ":" + endHour.getMinuteOfHour();

        ContentValues cv = new ContentValues();
        cv.put(HOUR_DAY, bookedDayStr);
        cv.put(START_HOUR, bookedStartHour);
        cv.put(END_HOUR, bookedEndHour );
        sqLiteDatabase.insert(FULLY_BOOKED_HOURS, null, cv);
        return true;
    }
    public Cursor readAllFullyBookedHours(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+FULLY_BOOKED_HOURS, null);
    }

    public boolean updateFullyBookedHours(int hourBooked_ID, int apptID_FK, DateTime bookedDay, DateTime bookedStartTime, DateTime bookedEndTime) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String bookedDayStr = "" + bookedDay.dayOfWeek();
        String uBookedStartTime = "" + bookedStartTime.getHourOfDay() +":" + bookedStartTime.getMinuteOfHour();
        String uBookedEndTime = "" + bookedStartTime.getHourOfDay() +":" + bookedStartTime.getMinuteOfHour();

        ContentValues cv = new ContentValues();
        cv.put(BOOKED_APPT_FK,apptID_FK);
        cv.put(B_DAY, bookedDayStr);
        cv.put(START_HOUR, uBookedStartTime);
        cv.put(END_HOUR, uBookedEndTime);

        return sqLiteDatabase.update(FULLY_BOOKED_HOURS, cv, B_ID + "=?", new String[]{String.valueOf(hourBooked_ID)}) > 0;
    }

    public boolean deleteFullyBookedHour(int hourBooked){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(FULLY_BOOKED_HOURS,B_ID+"=?", new String[]{String.valueOf(hourBooked)}) > 0;
    }
    //CRUD FOR TIME INTERVALS
    public boolean addApptTimeInterval(DateTime dayOfWeek, DateTime startTime, DateTime endTime){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String bookedDayStr = ""+dayOfWeek.dayOfWeek();
        String bookedStartHour = ""+startTime.getHourOfDay()+ ":" + startTime.getMinuteOfHour();
        String bookedEndHour = ""+endTime.getHourOfDay()+ ":" + endTime.getMinuteOfHour();

        ContentValues cv = new ContentValues();
        cv.put(HOUR_DAY, bookedDayStr);
        cv.put(START_HOUR, bookedStartHour);
        cv.put(END_HOUR, bookedEndHour );
        sqLiteDatabase.insert(FULLY_BOOKED_HOURS, null, cv);
        return true;
    }
    public Cursor readApptTimeInterval(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+TIME_INTERVALS, null);
    }

    public boolean updateApptTimeInterval(int intervalBooked_ID, DateTime bookedDay, DateTime bookedStartTime, DateTime bookedEndTime) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String bookedDayStr = "" + bookedDay.dayOfWeek();
        String uBookedStartTime = "" + bookedStartTime.getHourOfDay() +":" + bookedStartTime.getMinuteOfHour();
        String uBookedEndTime = "" + bookedEndTime.getHourOfDay() +":" + bookedEndTime.getMinuteOfHour();

        ContentValues cv = new ContentValues();
        cv.put(HOUR_DAY, bookedDayStr);
        cv.put(START_TIME, uBookedStartTime);
        cv.put(END_TIME, uBookedEndTime);

        return sqLiteDatabase.update(TIME_INTERVALS, cv, INT_ID + "=?", new String[]{String.valueOf(intervalBooked_ID)}) > 0;
    }

    public boolean deleteApptTimeInterval(int intervalBooked_ID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TIME_INTERVALS,B_ID+"=?", new String[]{String.valueOf(intervalBooked_ID)}) > 0;
    }

    //CRUD FOR CUST_HISTORY TABLE
    public boolean addCustHistory(String customerHistory_DateOfPayment, String customerHistory_Services, String customerHistory_Beautician, int customerHistory_TotalCharges, String customerHistory_ModeOfPay){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CUST_HISTORY_CHARGES, customerHistory_DateOfPayment);
        cv.put(CUST_HISTORY_CHARGES, customerHistory_Services);
        cv.put(CUST_HISTORY_BEAUTICIAN,customerHistory_Beautician);
        cv.put(CUST_HISTORY_CHARGES,customerHistory_TotalCharges);
        cv.put(CUST_HISTORY_MODE_OF_PAY,customerHistory_ModeOfPay);

        sqLiteDatabase.insert(CUST_HISTORY_TABLE_NAME, null, cv);
        return true;
    }

    public Cursor readCustHistory(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+CUST_HISTORY_TABLE_NAME, null);
    }

    public boolean updateCustHistory(int custHistory_ID, String customerHistory_DateOfPayment, String customerHistory_Services, String customerHistory_Beautician, int customerHistory_TotalCharges, String customerHistory_ModeOfPay){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CUST_HISTORY_CHARGES, customerHistory_DateOfPayment);
        cv.put(CUST_HISTORY_CHARGES, customerHistory_Services);
        cv.put(CUST_HISTORY_BEAUTICIAN,customerHistory_Beautician);
        cv.put(CUST_HISTORY_CHARGES,customerHistory_TotalCharges);
        cv.put(CUST_HISTORY_MODE_OF_PAY,customerHistory_ModeOfPay);

        return sqLiteDatabase.update(CUST_HISTORY_TABLE_NAME,cv,CUST_HISTORY_ID+"=?", new String[]{String.valueOf(custHistory_ID)}) > 0;

    }

    public boolean deleteCustHistory(int custHistory_ID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(CUST_HISTORY_TABLE_NAME,CUST_HISTORY_ID+"=?", new String[]{String.valueOf(custHistory_ID)}) > 0;
    }

    //CRUD FOR SALON_PAYMENT TABLE
    public boolean addSalonPayment(int custHistoryID, String services, String beautician, int totalCharges, String modeOfPayment){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SALON_PAYMENT_CUSTID_FK, custHistoryID);
        cv.put(SALON_PAYMENT_SERVICES, services);
        cv.put(SALON_PAYMENT_BEAUTICIAN,beautician);
        cv.put(SALON_PAYMENT_TOTAL_CHARGES,totalCharges);
        cv.put(SALON_PAYMENT_MODE_OF_PAY,modeOfPayment);

        sqLiteDatabase.insert(SALON_PAYMENT_TABLE_NAME, null, cv);
        return true;
    }

    public Cursor readSalonPayment(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+SALON_PAYMENT_TABLE_NAME, null);
        }

    public boolean updateSalonPayment(int servPaymentID, int custHistoryID, String services, String beautician, int totalCharges, String modeOfPayment){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SALON_PAYMENT_CUSTID_FK, custHistoryID);
        cv.put(SALON_PAYMENT_SERVICES, services);
        cv.put(SALON_PAYMENT_BEAUTICIAN,beautician);
        cv.put(SALON_PAYMENT_TOTAL_CHARGES,totalCharges);
        cv.put(SALON_PAYMENT_MODE_OF_PAY,modeOfPayment);

        return sqLiteDatabase.update(SALON_PAYMENT_TABLE_NAME,cv,SALON_PAYMENT_SERVPAY_ID+"=?", new String[]{String.valueOf(servPaymentID)}) > 0;

    }

    public boolean deleteSalonPayment(int servPaymentID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(SALON_PAYMENT_TABLE_NAME,SALON_PAYMENT_SERVPAY_ID+"=?", new String[]{String.valueOf(servPaymentID)}) > 0;

    }

    public List createArrayList(String table) {
        ArrayList<String> rowItems = null;

        switch (table) {
            case CUSTOMER:
                Cursor cursor = readAllCustomerProfiles();
                if (cursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        customerList.clear();
                        customerList.add(new Customer(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getInt(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getString(7)
                        ));
                    }
                    while (cursor.moveToNext());
                }
                break;

            case EMPLOYEE_TABLE_NAME:
                Cursor Empcursor = readAllEmployeeProfiles();
                if (Empcursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        employeeList.clear();
                        employeeList.add(new Employee(
                                Empcursor.getInt(0),
                                Empcursor.getBlob(1),
                                Empcursor.getString(2),
                                Empcursor.getString(3),
                                Empcursor.getString(4),
                                Empcursor.getInt(5),
                                Empcursor.getString(6),
                                Empcursor.getString(7),
                                Empcursor.getInt(8),
                                Empcursor.getInt(9),
                                Empcursor.getInt(10),
                                Empcursor.getInt(11)

                        ));
                    }
                    while (Empcursor.moveToNext());
                }
                break;

            case CATEGORY_TABLE_NAME:
                Cursor CategoryCursor = readAllServiceCategory();
                if (CategoryCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        categoryList.clear();
                        categoryList.add(new Category(
                                CategoryCursor.getInt(0),
                                CategoryCursor.getString(1),
                                CategoryCursor.getString(2),
                                CategoryCursor.getString(3)
                        ));
                    }
                    while (CategoryCursor.moveToNext());
                }
                break;

            case SERVICES_TABLE_NAME:
                Cursor servicesCursor = readAllServices();
                if (servicesCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        serviceList.clear();
                        serviceList.add(new Service(
                                servicesCursor.getInt(0),
                                servicesCursor.getString(1),
                                servicesCursor.getInt(2),
                                servicesCursor.getInt(3),
                                servicesCursor.getString(4),
                                servicesCursor.getInt(5),
                                servicesCursor.getInt(6)
                        ));
                    }
                    while (servicesCursor.moveToNext());
                }
                break;

            case PRODUCT_TABLE_NAME:
                Cursor ProductCursor = readAllProducts();
                if (ProductCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        productList.clear();
                        productList.add(new Product(
                                ProductCursor.getInt(0),
                                ProductCursor.getString(1),
                                ProductCursor.getString(2),
                                ProductCursor.getInt(3),
                                ProductCursor.getInt(4),
                                ProductCursor.getInt(5),
                                ProductCursor.getInt(6),
                                ProductCursor.getInt(7)
                        ));
                    }
                    while (ProductCursor.moveToNext());
                }
                break;

            case APPT_TABLE_NAME:
                Cursor CustApptCursor = readAllAppointments();
                if (CustApptCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        appointmentList.clear();
                        appointmentList.add(new Appointment(
                                CustApptCursor.getInt(0),
                                CustApptCursor.getInt(1),
                                CustApptCursor.getString(2),
                                CustApptCursor.getString(3),
                                CustApptCursor.getString(4),
                                CustApptCursor.getInt(5),
                                CustApptCursor.getInt(6),
                                CustApptCursor.getInt(7),
                                CustApptCursor.getInt(8),
                                CustApptCursor.getInt(9),
                                CustApptCursor.getInt(10),
                                CustApptCursor.getInt(11)

                        ));
                    }
                    while (CustApptCursor.moveToNext());
                }
                break;


            case CUST_HISTORY_TABLE_NAME:
                Cursor CustHistoryCursor = readCustHistory();
                if (CustHistoryCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        customerPaymentHistoryList.clear();
                        customerPaymentHistoryList.add(new CustomerPaymentHistory(
                                CustHistoryCursor.getInt(0),
                                CustHistoryCursor.getString(1),
                                CustHistoryCursor.getString(2),
                                CustHistoryCursor.getString(3),
                                CustHistoryCursor.getInt(4),
                                CustHistoryCursor.getString(5)
                        ));
                    }
                    while (CustHistoryCursor.moveToNext());
                }
                break;

            case FULLY_BOOKED_DAYS:
                Cursor fullyBookedDay = readAllFullyBookedDay();
                if(fullyBookedDay.moveToFirst()){
                    do{
                        bookedDayList.clear();
                        bookedDayList.add(new FullyBookedDay(
                        fullyBookedDay.getInt(0),
                        fullyBookedDay.getString(1)
                        ));
                    }
                    while (fullyBookedDay.moveToNext());
                }
                break;

            case FULLY_BOOKED_HOURS:
                Cursor fullyBookedHours = readAllFullyBookedHours();
                if(fullyBookedHours.moveToFirst()){
                    do{
                        bookedHoursList.clear();
                        bookedHoursList.add(new FullyBookedHours(
                                fullyBookedHours.getInt(0),
                                fullyBookedHours.getInt(1),
                                fullyBookedHours.getString(2),
                                fullyBookedHours.getString(3),
                                fullyBookedHours.getString(4)

                        ));
                    }
                    while (fullyBookedHours.moveToNext());
                }
                break;

            case TIME_INTERVALS:
                Cursor ApptTimeInterval = readApptTimeInterval();
                if(ApptTimeInterval.moveToFirst()){
                    do{
                        appointmentIntervalList.clear();
                        appointmentIntervalList.add(new AppointmentInterval(
                                ApptTimeInterval.getInt(0),
                                ApptTimeInterval.getString(1),
                                ApptTimeInterval.getString(2),
                                ApptTimeInterval.getString(3)

                        ));
                    }
                    while (ApptTimeInterval.moveToNext());
                }
                break;

        }
        return rowItems;

    }

    public ArrayList<String> searchTable(String table, String searchColumn, String searchRow){
        ArrayList<String> rowItems = null;
        for (int i=0; i < createArrayList(table).size(); i++){
            switch (table){
                case CUSTOMER:
                    Customer customer = (Customer) customerList.get(i);
                    String result = customer.getProperty(searchColumn);
                    if(!result.isEmpty()){
                        if(searchRow.trim().equalsIgnoreCase(result.trim())){
                            return customer.getItems();
                        }
                    }
                    break;

                case EMPLOYEE_TABLE_NAME:
                    Employee employee = (Employee) employeeList.get(i);
                    String employee_result = employee.getProperty(searchColumn);
                    if(!employee_result.isEmpty()){
                        if(searchRow.trim().equalsIgnoreCase(employee_result.trim())){
                            return employee.getItems();
                        }
                    }
                    break;

                case PRODUCT_TABLE_NAME:
                    Product product = (Product) productList.get(i);
                    String product_result = product.getProperty(searchColumn);
                    if(!product_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(product_result.trim())) {
                            return product.getItems();
                        }
                    }
                    break;

                case SERVICES_TABLE_NAME:
                    Service service = (Service) serviceList.get(i);
                    String service_result = service.getProperty(searchColumn);
                    if(!service_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(service_result.trim())) {
                            return service.getItems();
                        }
                    }
                    break;

                case CATEGORY_TABLE_NAME:
                    Category category = (Category) categoryList.get(i);
                    String service_category_result = category.getProperty(searchColumn);
                    if(!service_category_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(service_category_result.trim())) {
                            return category.getItems();
                        }
                    }

                    break;
                case APPT_TABLE_NAME:
                    Appointment appointment = (Appointment) appointmentList.get(i);
                    String appointment_result = appointment.getProperty(searchColumn);
                    if(!appointment_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(appointment_result.trim())) {
                            return appointment.getItems();
                        }
                    }
                    break;


                case CUST_HISTORY_TABLE_NAME:
                    CustomerPaymentHistory customerPaymentHistory = (CustomerPaymentHistory) customerPaymentHistoryList.get(i);
                    String customerPaymentHistory_result = customerPaymentHistory.getProperty(searchColumn);
                    if(!customerPaymentHistory_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(customerPaymentHistory_result.trim())) {
                            return customerPaymentHistory.getItems();
                        }
                    }
                    break;

                case FULLY_BOOKED_DAYS:
                    FullyBookedDay fullyBookedDay = (FullyBookedDay) bookedDayList.get(i);
                    String fullyBookedDay_result = fullyBookedDay.getProperty(searchColumn);
                    if(!fullyBookedDay_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(fullyBookedDay_result.trim())) {
                            return fullyBookedDay.getItems();
                        }
                    }
                    break;

                case FULLY_BOOKED_HOURS:
                    FullyBookedHours fullyBookedHours = (FullyBookedHours) bookedHoursList.get(i);
                    String fullyBookedHours_result = fullyBookedHours.getProperty(searchColumn);
                    if(!fullyBookedHours_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(fullyBookedHours_result.trim())) {
                            return fullyBookedHours.getItems();
                        }
                    }
                    break;

                case TIME_INTERVALS:
                    AppointmentInterval appointmentInterval = (AppointmentInterval) appointmentIntervalList.get(i);
                    String appointmentInterval_result = appointmentInterval.getProperty(searchColumn);
                    if(!appointmentInterval_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(appointmentInterval_result.trim())) {
                            return appointmentInterval.getItems();
                        }
                    }
                    break;

            }
        }
        return rowItems;
    }


}