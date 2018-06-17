package com.example.eodhuno.ebelle_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Customer;
import com.example.eodhuno.ebelle_test.database_objects.CustomerPaymentHistory;
import com.example.eodhuno.ebelle_test.database_objects.Employee;
import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.SalonCustomerServicePayment;
import com.example.eodhuno.ebelle_test.database_objects.Service;
import com.example.eodhuno.ebelle_test.database_objects.ServiceCategory;

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
    public List<ServiceCategory> serviceCategoryList;
    public List<CustomerPaymentHistory>customerPaymentHistoryList;
    public List<SalonCustomerServicePayment> salonCustomerServicePayments;

    public static final String DATABASE_NAME = "eBelleSalon";
    public static final int DATABASE_VERSON = 5;

    //Table names
    public static final String TABLE_NAME = "customers";
    public static final String CUST_HISTORY_TABLE_NAME = "CUST_HISTORY";
    public static final String EMPLOYEE_TABLE_NAME = "EMPLOYEES";
    public static final String CATEGORY_TABLE_NAME = "CATEGORY";
    public static final String SERVICES_TABLE_NAME = "SERVICES";
    public static final String PRODUCT_TABLE_NAME = "PRODUCTS";
    public static final String APPT_TABLE_NAME = "APPOINTMENTS";
    public static final String SALON_PAYMENT_TABLE_NAME = "SRV_PAYMENT";

    //Customers Table
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FNAME = "F_Name";
    public static final String COLUMN_LNAME = "L_Name";
    public static final String COLUMN_GENDER = "Gender";
    public static final String COLUMN_PHONE_NO = "PhoneNo";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "Password";

    //Customer Payments/History Table (Client backend)
    public static final String CUST_HISTORY_ID = "HIST_ID";
    public static final String CUST_HISTORY_DATE = "DATE_OF_PAYM";
    public static final String CUST_HISTORY_SERVICES = "SERVICES";
    public static final String CUST_HISTORY_BEAUTICIAN = "BEAUTICIAN";
    public static final String CUST_HISTORY_MODE_OF_PAY = "MODE_OF_PAY";
    public static final String CUST_HISTORY_CHARGES = "TOTAL CHARGES";

    //Employees Table
    public static final String EMPLOYEE_ID = "EMP_ID";
    public static final String EMPLOYEE_FNAME = "EMP_FNAME";
    public static final String EMPLOYEE_LNAME = "EMP_LNAME";
    public static final String EMPLOYEE_GENDER = "GENDER";
    public static final String EMPLOYEE_PHONE_NO = "PHONE_NO";
    public static final String EMPLOYEE_EMAIL = "EMAIL";
    public static final String EMPLOYEE_SERVICES = "SERVICES";
    public static final String EMPLOYEE_MODE_OF_PAY = "MODE OF PAY";

    //Categories Table
    public static final String CATEGORY_ID = "CAT_ID";
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
    public static final String APPT_DATE_CREATED = "DATE CREATED";
    public static final String CUST_ID_FK = "CUST_ID";
    public static final String APPT_DATE = "APPT_DATE";
    public static final String APPT_TIME = "APPT_TIME";
    public static final String APPT_SERVICES = "SERVICES";
    public static final String APPT_STYLIST = "BEAUTICIAN";
    public static final String APPT_CONFIRMED = "CONFIRMATION";

    //Salon Payments Table
    public static final String SALON_PAYMENT_SERVPAY_ID = "SERV_PAYM_ID";
    public static final String SALON_PAYMENT_CUSTID_FK = "CUST_HISTORY_ID";
    public static final String SALON_PAYMENT_SERVICES = "SERVICES";
    public static final String SALON_PAYMENT_BEAUTICIAN = "BEAUTICIAN";
    public static final String SALON_PAYMENT_MODE_OF_PAY = "MODE_OF_PAYM";
    public static final String SALON_PAYMENT_TOTAL_CHARGES= "TOTAL CHARGES";

    //Creating tables in DB
    private static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE  "+TABLE_NAME+" (\n" +
            "   "+COLUMN_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "   "+COLUMN_FNAME+" varchar(200) NOT NULL, \n" +
            "   "+COLUMN_LNAME+" varchar(200) NOT NULL, \n" +
            "   "+COLUMN_GENDER+" varchar(200) NOT NULL, \n" +
            "   "+COLUMN_PHONE_NO+" INTEGER NOT NULL, \n" +
            "   "+COLUMN_EMAIL+" varchar(200) NOT NULL, \n" +
            "   "+COLUMN_PASSWORD+" varchar(200) NOT NULL\n" + ");";

    private static final String CREATE_CUST_HISTORY_TABLE ="CREATE TABLE  "+CUST_HISTORY_TABLE_NAME+" (\n" +
            "   "+CUST_HISTORY_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            "   "+CUST_HISTORY_DATE+" INTEGER NOT NULL, " +
            "   "+CUST_HISTORY_SERVICES+" varchar(200) NOT NULL, \n" +
            "   "+CUST_HISTORY_BEAUTICIAN+" varchar(200) NOT NULL, \n" +
            "   "+CUST_HISTORY_MODE_OF_PAY+" varchar(200) NOT NULL, \n" +
            "   "+CUST_HISTORY_CHARGES+" INTEGER NOT NULL\n" + ");";

    private static final String CREATE_EMPLOYEES_TABLE = "CREATE TABLE  "+EMPLOYEE_TABLE_NAME+" (\n" +
            "   "+EMPLOYEE_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "   "+EMPLOYEE_FNAME+" varchar(200) NOT NULL, \n" +
            "   "+EMPLOYEE_LNAME+" varchar(200) NOT NULL, \n" +
            "   "+EMPLOYEE_GENDER+" varchar(200) NOT NULL, \n" +
            "   "+EMPLOYEE_PHONE_NO+" INTEGER NOT NULL, \n" +
            "   "+EMPLOYEE_EMAIL+" varchar(200) NOT NULL, \n" +
            "   "+EMPLOYEE_SERVICES+" varchar(200) NOT NULL, \n" +
            "   "+EMPLOYEE_MODE_OF_PAY+" INTEGER NOT NULL\n" + ");";

    private static final String CREATE_SERVICE_CAT_TABLE = " CREATE TABLE  "+CATEGORY_TABLE_NAME+" (\n" +
            "   "+CATEGORY_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "   "+CATEGORY_NAME+" varchar(200) NOT NULL, \n" +
            "   "+CATEGORY_DESCRIPTION+" varchar(200) NOT NULL\n" + ");";

    private static final String CREATE_SERVICES_TABLE = " CREATE TABLE "+SERVICES_TABLE_NAME+" (\n" +
            "   "+SERV_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "   "+SERV_NAME+" varchar(200) NOT NULL, \n" +
            "   "+SERV_PRICE+" varchar(200) NOT NULL, \n" +
            "   "+SERV_DURATION+" varchar(200) NOT NULL, \n" +
            "   "+SERV_DESCRIPTION+" INTEGER NOT NULL, \n" +
            "   "+SERV_AVAILABILITY+" flag INTEGER DEFAULT 0 NOT NULL,\n" +
            "   "+CATEGORY_ID_FK+" INTEGER NOT NULL, FOREIGN KEY ("+CATEGORY_ID_FK+") REFERENCES "+CATEGORY_TABLE_NAME+" ("+CATEGORY_ID+"))";

    private static final String CREATE_PRODUCTS_TABLE = " CREATE TABLE "+PRODUCT_TABLE_NAME+" (\n" +
            "   "+PRODUCT_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "   "+PRODUCT_NAME+" varchar(200) NOT NULL, \n" +
            "   "+PRODUCT_DESCR+" varchar(200) NOT NULL, \n" +
            "   "+PRODUCT_QTY+" INTEGER NOT NULL, \n" +
            "   "+PRODUCT_UNIT_COST+" INTEGER NOT NULL, \n" +
            "   "+PRODUCT_REORDER_LEVEL+" INTEGER NOT NULL, \n" +
            "   "+PRODUCT_AVAILABILITY+" INTEGER NOT NULL,\n" +
            "   "+SERVICE_ID_FK+" INTEGER NOT NULL, FOREIGN KEY ("+SERVICE_ID_FK+") REFERENCES "+SERVICES_TABLE_NAME+" ("+SERV_ID+"))";

    private static final String CREATE_APPTS_TABLE = " CREATE TABLE "+APPT_TABLE_NAME+" (\n" +
            "   "+APPT_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "   "+APPT_DATE_CREATED+" INTEGER NOT NULL, \n" +
            "   "+APPT_DATE+" varchar(200) NOT NULL, \n" +
            "   "+APPT_TIME+" varchar(200) NOT NULL, \n" +
            "   "+APPT_SERVICES+" varchar(200) NOT NULL, \n" +
            "   "+APPT_STYLIST+" varchar(200) NOT NULL, \n" +
            "   "+APPT_CONFIRMED+" varchar(200) NOT NULL,\n" +
            "   "+CUST_ID_FK+" varchar(200) NOT NULL, FOREIGN KEY ("+CUST_ID_FK+") REFERENCES "+TABLE_NAME+" ("+COLUMN_ID+"))";

    private static final String CREATE_SALON_PAYMENT_TABLE =" CREATE TABLE "+SALON_PAYMENT_TABLE_NAME+"(\n" +
            "   "+SALON_PAYMENT_SERVPAY_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            "   "+SALON_PAYMENT_SERVICES+" varchar(200) NOT NULL, \n" +
            "   "+SALON_PAYMENT_BEAUTICIAN+" varchar(200) NOT NULL, \n" +
            "   "+SALON_PAYMENT_MODE_OF_PAY+" varchar(200) NOT NULL, \n" +
            "   "+SALON_PAYMENT_TOTAL_CHARGES+" INTEGER NOT NULL,\n" +
            "   "+SALON_PAYMENT_CUSTID_FK+" INTEGER NOT NULL, FOREIGN KEY ("+SALON_PAYMENT_CUSTID_FK+") REFERENCES "+TABLE_NAME+" ("+COLUMN_ID+"))";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSON);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("ENTERS ONCREATE", "FIIIIIIIIRST");

        sqLiteDatabase.execSQL(CREATE_CUSTOMER_TABLE);
        sqLiteDatabase.execSQL(CREATE_CUST_HISTORY_TABLE);
        sqLiteDatabase.execSQL(CREATE_EMPLOYEES_TABLE);
        sqLiteDatabase.execSQL(CREATE_SERVICE_CAT_TABLE);
        sqLiteDatabase.execSQL(CREATE_SERVICES_TABLE);
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_APPTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_SALON_PAYMENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("ENTERS ONUPGRADE", "SEEEEEECOND");

        if( oldVersion != newVersion) {

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CUST_HISTORY_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SERVICES_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + APPT_TABLE_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SALON_PAYMENT_TABLE_NAME);

            onCreate(sqLiteDatabase);
        }
    }

    //CRUD FOR CUSTOMER TABLE
    public boolean addCustomerProfile(String FirstName, String LastName, String Gender, int PhoneNo, String Email, String Password){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FNAME,FirstName);
        cv.put(COLUMN_LNAME, LastName);
        cv.put(COLUMN_GENDER, Gender);
        cv.put(COLUMN_PHONE_NO,String.valueOf(PhoneNo));
        cv.put(COLUMN_EMAIL,Email);
        cv.put(COLUMN_PASSWORD,Password);

        sqLiteDatabase.insert(TABLE_NAME, null, cv);
        return true;
    }

    Cursor readAllCustomerProfiles(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

    boolean updateCustomer(int CustID,String FirstName, String LastName,String Gender, int PhoneNo,String Email, String Password ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FNAME,FirstName);
        cv.put(COLUMN_LNAME,LastName);
        cv.put(COLUMN_GENDER,Gender);
        cv.put(COLUMN_PHONE_NO,String.valueOf(PhoneNo));
        cv.put(COLUMN_EMAIL,Email);
        cv.put(COLUMN_PASSWORD,Password);

        //If the value of the statement is greater than 0, it means the update was successsful.
       return sqLiteDatabase.update(TABLE_NAME,cv,COLUMN_ID+"=?", new String[]{String.valueOf(CustID)}) > 0;
       }

    boolean deleteCustomer(int CustID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID+"=?", new String[]{String.valueOf(CustID)}) > 0;

    }

    //CRUD FOR EMPLOYEE TABLE
    boolean addEmployeeProfile(String FirstName, String LastName, String Gender, int PhoneNo, String Email,String Services, String ModeOfPay){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EMPLOYEE_FNAME,FirstName);
        cv.put(EMPLOYEE_LNAME, LastName);
        cv.put(EMPLOYEE_GENDER, Gender);
        cv.put(EMPLOYEE_PHONE_NO,String.valueOf(PhoneNo));
        cv.put(EMPLOYEE_EMAIL,Email);
        cv.put(EMPLOYEE_SERVICES,Services);
        cv.put(EMPLOYEE_MODE_OF_PAY, ModeOfPay);

        sqLiteDatabase.insert(EMPLOYEE_TABLE_NAME, null, cv);
        return true;
    }

    Cursor readAllEmployeeProfiles(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+EMPLOYEE_TABLE_NAME, null);
    }

    boolean updateEmployee(int EmpID, String FirstName, String LastName, String Gender, int PhoneNo, String Email,String Services, String ModeOfPay ){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EMPLOYEE_FNAME,FirstName);
        cv.put(EMPLOYEE_LNAME,LastName);
        cv.put(EMPLOYEE_GENDER,Gender);
        cv.put(EMPLOYEE_PHONE_NO,String.valueOf(PhoneNo));
        cv.put(EMPLOYEE_EMAIL,Email);
        cv.put(EMPLOYEE_SERVICES,Services);
        cv.put(EMPLOYEE_MODE_OF_PAY,ModeOfPay);

        //If the value of the statement is greater than 0, it means the update was successsful.
        return sqLiteDatabase.update(EMPLOYEE_TABLE_NAME,cv,COLUMN_ID+"=?", new String[]{String.valueOf(EmpID)}) > 0;
    }

    boolean deleteEmployee(int EmpID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(EMPLOYEE_TABLE_NAME,EMPLOYEE_ID+"=?", new String[]{String.valueOf(EmpID)}) > 0;
    }

    //CRUD FOR CATEGORY TABLE
    boolean addCategory(String categoryName, String categoryDescription){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_NAME, categoryName);
        cv.put(CATEGORY_DESCRIPTION, categoryDescription);

        sqLiteDatabase.insert(CATEGORY_TABLE_NAME, null, cv);
        return true;
    }

    Cursor readAllServiceCategory(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+CATEGORY_TABLE_NAME, null);
    }

    boolean updateCategory(int categotyID, String categoryName, String categoryDescription){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CATEGORY_NAME,categoryName);
        cv.put(CATEGORY_DESCRIPTION,categoryDescription);

        //If the value of the statement is greater than 0, it means the update was successsful.
        return sqLiteDatabase.update(CATEGORY_TABLE_NAME,cv,CATEGORY_ID+"=?", new String[]{String.valueOf(categotyID)}) > 0;
    }

    boolean deleteCategory(int categoryID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(CATEGORY_TABLE_NAME,CATEGORY_ID+"=?", new String[]{String.valueOf(categoryID)}) > 0;
    }

    //CRUD FOR SERVICES TABLE
    boolean addServices(String serv_Name, String serv_Description,String categoryID_FK,int price, String duration, String availability){
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

    Cursor readAllServices(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+SERVICES_TABLE_NAME, null);
    }

    boolean updateServices(int servID, int categoryID_FK, String serv_Name, String serv_Description, int price, String availability){
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

    boolean deleteServices(int servID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(SERVICES_TABLE_NAME,SERV_ID+"=?", new String[]{String.valueOf(servID)}) > 0;
    }

    //CRUD FOR PRODUCT TABLE
    boolean addProducts( String prod_Name, String prod_descr, int quantity, int price, int reorderLevel, String services, int availability){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME, prod_Name);
        cv.put(PRODUCT_DESCR, prod_descr);
        cv.put(PRODUCT_QTY, String.valueOf(quantity));
        cv.put(PRODUCT_UNIT_COST, String.valueOf(price));
        cv.put(PRODUCT_REORDER_LEVEL, String.valueOf(reorderLevel));
        cv.put(SERVICE_ID_FK, services);
        cv.put(PRODUCT_AVAILABILITY, String.valueOf(availability));

        sqLiteDatabase.insert(PRODUCT_TABLE_NAME, null, cv);
        return true;
    }

    Cursor readAllProducts(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+PRODUCT_TABLE_NAME, null);
    }

    boolean updateProducts(int prodID, int servID_FK, String prod_Name, int price,int reorderLevel,String services, String availability){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SERVICE_ID_FK, servID_FK);
        cv.put(PRODUCT_NAME, prod_Name);
        cv.put(PRODUCT_UNIT_COST,String.valueOf(price));
        cv.put(PRODUCT_REORDER_LEVEL,String.valueOf(reorderLevel));
        cv.put(String.valueOf(PRODUCT_AVAILABILITY),availability);

        //If the value of the statement is greater than 0, it means the update was successsful.
        return sqLiteDatabase.update(PRODUCT_TABLE_NAME,cv,PRODUCT_ID+"=?", new String[]{String.valueOf(prodID)}) > 0;
    }

    boolean deleteProducts(int prodID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(PRODUCT_TABLE_NAME,PRODUCT_ID+"=?", new String[]{String.valueOf(prodID)}) > 0;
    }

    //CRUD FOR APPOINTMENT TABLE
    boolean addAppointment(int custID_FK, String dateCreated, String apptDate, String apptTime, String services, String beautician, String confirmation){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CUST_ID_FK, custID_FK);
        cv.put(APPT_DATE_CREATED, dateCreated);
        cv.put(APPT_DATE,apptDate);
        cv.put(APPT_TIME,apptTime);
        cv.put(APPT_SERVICES,services);
        cv.put(APPT_STYLIST,beautician);
        cv.put(APPT_CONFIRMED,confirmation);

        sqLiteDatabase.insert(APPT_TABLE_NAME, null, cv);
        return true;
    }

    Cursor readAllAppointments(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+APPT_TABLE_NAME, null);
    }

    boolean updateAppointment(int apptID, int custID_FK, String dateCreated, String apptDate, String apptTime, String services, String beautician, String confirmation){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CUST_ID_FK, custID_FK);
        cv.put(APPT_DATE_CREATED, dateCreated);
        cv.put(APPT_DATE,apptDate);
        cv.put(APPT_TIME,apptTime);
        cv.put(APPT_SERVICES,services);
        cv.put(APPT_STYLIST,beautician);
        cv.put(APPT_CONFIRMED,confirmation);

        return sqLiteDatabase.update(PRODUCT_TABLE_NAME,cv,APPT_ID+"=?", new String[]{String.valueOf(apptID)}) > 0;
    }

    boolean deleteAppointment(int apptID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(APPT_TABLE_NAME,APPT_ID+"=?", new String[]{String.valueOf(apptID)}) > 0;
    }

    //CRUD FOR CUST_HISTORY TABLE

    boolean addCustHistory(String customerHistory_DateOfPayment, String customerHistory_Services, String customerHistory_Beautician, int customerHistory_TotalCharges, String customerHistory_ModeOfPay){
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

    Cursor readCustHistory(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+CUST_HISTORY_TABLE_NAME, null);
    }

    boolean updateCustHistory(int custHistory_ID, String customerHistory_DateOfPayment, String customerHistory_Services, String customerHistory_Beautician, int customerHistory_TotalCharges, String customerHistory_ModeOfPay){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CUST_HISTORY_CHARGES, customerHistory_DateOfPayment);
        cv.put(CUST_HISTORY_CHARGES, customerHistory_Services);
        cv.put(CUST_HISTORY_BEAUTICIAN,customerHistory_Beautician);
        cv.put(CUST_HISTORY_CHARGES,customerHistory_TotalCharges);
        cv.put(CUST_HISTORY_MODE_OF_PAY,customerHistory_ModeOfPay);

        return sqLiteDatabase.update(CUST_HISTORY_TABLE_NAME,cv,CUST_HISTORY_ID+"=?", new String[]{String.valueOf(custHistory_ID)}) > 0;

    }

    boolean deleteCustHistory(int custHistory_ID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(CUST_HISTORY_TABLE_NAME,CUST_HISTORY_ID+"=?", new String[]{String.valueOf(custHistory_ID)}) > 0;
    }


    //CRUD FOR SALON_PAYMENT TABLE
    boolean addSalonPayment(int custHistoryID, String services, String beautician, int totalCharges, String modeOfPayment){
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

    Cursor readSalonPayment(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM "+SALON_PAYMENT_TABLE_NAME, null);
        }

    boolean updateSalonPayment(int servPaymentID, int custHistoryID, String services, String beautician, int totalCharges, String modeOfPayment){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(SALON_PAYMENT_CUSTID_FK, custHistoryID);
        cv.put(SALON_PAYMENT_SERVICES, services);
        cv.put(SALON_PAYMENT_BEAUTICIAN,beautician);
        cv.put(SALON_PAYMENT_TOTAL_CHARGES,totalCharges);
        cv.put(SALON_PAYMENT_MODE_OF_PAY,modeOfPayment);

        return sqLiteDatabase.update(SALON_PAYMENT_TABLE_NAME,cv,SALON_PAYMENT_SERVPAY_ID+"=?", new String[]{String.valueOf(servPaymentID)}) > 0;

    }

    boolean deleteSalonPayment(int servPaymentID){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(SALON_PAYMENT_TABLE_NAME,SALON_PAYMENT_SERVPAY_ID+"=?", new String[]{String.valueOf(servPaymentID)}) > 0;

    }

    private List createArrayList(String table) {
        switch (table) {

            case TABLE_NAME:
                Cursor cursor = readAllCustomerProfiles();
                if (cursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        customerList.add(new Customer(
                                cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getInt(4),
                                cursor.getString(5),
                                cursor.getString(6)
                        ));
                    }
                    while (cursor.moveToNext());
                }

            case EMPLOYEE_TABLE_NAME:
                Cursor Empcursor = readAllEmployeeProfiles();
                if (Empcursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        employeeList.add(new Employee(
                                Empcursor.getInt(0),
                                Empcursor.getString(1),
                                Empcursor.getString(2),
                                Empcursor.getString(3),
                                Empcursor.getInt(4),
                                Empcursor.getString(5),
                                Empcursor.getString(6),
                                Empcursor.getString(7)
                        ));
                    }
                    while (Empcursor.moveToNext());
                }
            case CATEGORY_TABLE_NAME:
                Cursor CategoryCursor = readAllServiceCategory();
                if (CategoryCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        serviceCategoryList.add(new ServiceCategory(
                                CategoryCursor.getInt(0),
                                CategoryCursor.getString(1),
                                CategoryCursor.getString(2)
                        ));
                    }
                    while (CategoryCursor.moveToNext());
                }

            case SERVICES_TABLE_NAME:
                Cursor ServicesCursor = readAllServices();
                if (ServicesCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        serviceList.add(new Service(
                                ServicesCursor.getInt(0),
                                ServicesCursor.getInt(1),
                                ServicesCursor.getString(2),
                                ServicesCursor.getString(3),
                                ServicesCursor.getInt(4),
                                ServicesCursor.getString(5),
                                ServicesCursor.getString(6)
                        ));
                    }
                    while (ServicesCursor.moveToNext());
                }
            case PRODUCT_TABLE_NAME:
                Cursor ProductCursor = readAllProducts();
                if (ProductCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        productList.add(new Product(
                                ProductCursor.getInt(0),
                                ProductCursor.getInt(1),
                                ProductCursor.getString(2),
                                ProductCursor.getString(3),
                                ProductCursor.getInt(4),
                                ProductCursor.getInt(5),
                                ProductCursor.getInt(6)
                        ));
                    }
                    while (ProductCursor.moveToNext());
                }

            case APPT_TABLE_NAME:
                Cursor CustApptCursor = readAllAppointments();
                if (CustApptCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        appointmentList.add(new Appointment(
                                CustApptCursor.getInt(0),
                                CustApptCursor.getInt(1),
                                CustApptCursor.getString(2),
                                CustApptCursor.getString(3),
                                CustApptCursor.getString(4),
                                CustApptCursor.getString(5),
                                CustApptCursor.getString(6),
                                CustApptCursor.getString(7)
                        ));
                    }
                    while (CustApptCursor.moveToNext());
                }

            case SALON_PAYMENT_TABLE_NAME:
                Cursor SalonPaymentCursor = readSalonPayment();
                if (SalonPaymentCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
                        salonCustomerServicePayments.add(new SalonCustomerServicePayment(
                                SalonPaymentCursor.getInt(0),
                                SalonPaymentCursor.getInt(1),
                                SalonPaymentCursor.getString(2),
                                SalonPaymentCursor.getString(3),
                                SalonPaymentCursor.getInt(4),
                                SalonPaymentCursor.getString(5)
                        ));
                    }
                    while (SalonPaymentCursor.moveToNext());
                }

            case CUST_HISTORY_TABLE_NAME:
                Cursor CustHistoryCursor = readCustHistory();
                if (CustHistoryCursor.moveToFirst()) {
                    //Adds the records to the ArrayList
                    do {
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

        }
        return employeeList;

    }

    private ArrayList<String> searchTable(String table, String searchColumn, String searchRow){
        ArrayList<String> rowItems = null;
        for (int i=0; i < createArrayList(table).size(); i++){
            switch (table){
                case TABLE_NAME:
                    Customer customer = (Customer) customerList.get(i);
                    String result = customer.getProperty(searchColumn);
                    if(!result.isEmpty()){
                        if(searchRow.trim().equalsIgnoreCase(result.trim())){
                            return customer.getItems();
                        }
                        return rowItems;
                    }
                    break;
                case EMPLOYEE_TABLE_NAME:
                    Employee employee = (Employee) employeeList.get(i);
                    String employee_result = employee.getProperty(searchColumn);
                    if(!employee_result.isEmpty()){
                        if(searchRow.trim().equalsIgnoreCase(employee_result.trim())){
                            return employee.getItems();
                        }
                        return rowItems;
                    }
                    break;
                case PRODUCT_TABLE_NAME:
                    Product product = (Product) productList.get(i);
                    String product_result = product.getProperty(searchColumn);
                    if(!product_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(product_result.trim())) {
                            return product.getItems();
                        }
                        return rowItems;
                    }
                    break;
                case SERVICES_TABLE_NAME:
                    Service service = (Service) serviceList.get(i);
                    String service_result = service.getProperty(searchColumn);
                    if(!service_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(service_result.trim())) {
                            return service.getItems();
                        }
                        return rowItems;
                    }
                    break;

                case CATEGORY_TABLE_NAME:
                    ServiceCategory serviceCategory = (ServiceCategory) serviceCategoryList.get(i);
                    String service_category_result = serviceCategory.getProperty(searchColumn);
                    if(!service_category_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(service_category_result.trim())) {
                            return serviceCategory.getItems();
                        }
                        return rowItems;
                    }
                    break;
                case APPT_TABLE_NAME:
                    Appointment appointment = (Appointment) appointmentList.get(i);
                    String appointment_result = appointment.getProperty(searchColumn);
                    if(!appointment_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(appointment_result.trim())) {
                            return appointment.getItems();
                        }
                        return rowItems;
                    }
                    break;

                case SALON_PAYMENT_TABLE_NAME:
                    SalonCustomerServicePayment salonCustomerServicePayment = (SalonCustomerServicePayment) salonCustomerServicePayments.get(i);
                    String salonCustomerServicePayment_result = salonCustomerServicePayment.getProperty(searchColumn);
                    if(!salonCustomerServicePayment_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(salonCustomerServicePayment_result.trim())) {
                            return salonCustomerServicePayment.getItems();
                        }
                        return rowItems;
                    }
                    break;
                case CUST_HISTORY_TABLE_NAME:
                    CustomerPaymentHistory customerPaymentHistory = (CustomerPaymentHistory) customerPaymentHistoryList.get(i);
                    String customerPaymentHistory_result = customerPaymentHistory.getProperty(searchColumn);
                    if(!customerPaymentHistory_result.isEmpty()) {
                        if (searchRow.trim().equalsIgnoreCase(customerPaymentHistory_result.trim())) {
                            return customerPaymentHistory.getItems();
                        }
                        return rowItems;
                    }
                    break;
            }
        }
        return rowItems;
    }


}