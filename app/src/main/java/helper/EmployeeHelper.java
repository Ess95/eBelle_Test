package helper;

import android.content.Context;
import android.database.Cursor;

import com.example.eodhuno.ebelle_test.DatabaseManager;
import com.example.eodhuno.ebelle_test.database_objects.Employee;

import java.util.ArrayList;

public class EmployeeHelper {

    public EmployeeHelper() {
    }

    Context context;
    static DatabaseManager mDatabase;

    public EmployeeHelper(Context context) {
        this.mDatabase = new DatabaseManager(context);
    }

    public static ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> allEmployees = new ArrayList<>();

        Cursor employeeProfilesCursor = mDatabase.readAllEmployeeProfiles();
        if (employeeProfilesCursor.moveToFirst()) {
            do {
                allEmployees.add(new Employee(
                        employeeProfilesCursor.getInt(0),
                        employeeProfilesCursor.getBlob(1),
                        employeeProfilesCursor.getString(2),
                        employeeProfilesCursor.getString(3),
                        employeeProfilesCursor.getString(4),
                        employeeProfilesCursor.getInt(5),
                        employeeProfilesCursor.getString(6),
                        employeeProfilesCursor.getString(7),
                        employeeProfilesCursor.getInt(8),
                        employeeProfilesCursor.getInt(9),
                        employeeProfilesCursor.getInt(10),
                        employeeProfilesCursor.getInt(11)
                ));
            }
            while (employeeProfilesCursor.moveToNext());
        }
        return allEmployees;
    }

    public static Employee getEmployeeById(int id) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        Employee employee = new Employee();
        boolean found = false;
        for (Employee emp : employeeArrayList) {
            if (emp.getEmpID() == id) {
                employee = emp;
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE WITH ID " + id + "FOUND");
        }
        return employee;
    }

    public static ArrayList<Employee> getEmployeeByFullName(String firstName, String lastName) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeName = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmp_Fname().equalsIgnoreCase(firstName) && employee.getEmp_Lname().equalsIgnoreCase(lastName)) {
                currEmployeeName.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE WITH NAME " + firstName + " " + lastName + "FOUND");
        }
        return currEmployeeName;

    }

    public static ArrayList<Employee> getEmployeeByFirstName(String firstName) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeFirstName = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmp_Fname().equalsIgnoreCase(firstName)) {
                currEmployeeFirstName.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE WITH NAME " + firstName + " FOUND");
        }
        return currEmployeeFirstName;

    }

    public static ArrayList<Employee> getEmployeeByLastName(String lastName) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeLastName = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmp_Lname().equalsIgnoreCase(lastName)) {
                currEmployeeLastName.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE WITH NAME " + lastName + " FOUND");
        }
        return currEmployeeLastName;
    }

    public static ArrayList<Employee> getEmployeeByGenderFemale(String female) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeFemale = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmp_Gender().equalsIgnoreCase(female)) {
                currEmployeeFemale.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE FOUND");
        }
        return currEmployeeFemale;
    }

    public static ArrayList<Employee> getEmployeeByGenderMale(String male) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeMale = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmp_Gender().equalsIgnoreCase(male)) {
                currEmployeeMale.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE FOUND");
        }
        return currEmployeeMale;
    }

    public static ArrayList<Employee> getEmployeeByServiceID(int serviceID) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeService = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmp_Services() == serviceID) {
                currEmployeeService.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE FOR SERVICE ID " + serviceID + " FOUND");
        }
        return currEmployeeService;
    }

    public static ArrayList<Employee> getEmployeeByModeOfPaySalary(String salary) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeModeOfPaySalary = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmp_ModeOfPay().equalsIgnoreCase(salary)) {
                currEmployeeModeOfPaySalary.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE PAID ON SALARY FOUND");
        }
        return currEmployeeModeOfPaySalary;
    }

    public static ArrayList<Employee> getEmployeeByModeOfPayCommission(String commission) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeModeOfPayComm = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmp_ModeOfPay().equalsIgnoreCase(commission)) {
                currEmployeeModeOfPayComm.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE PAID ON COMMISSION FOUND");
        }
        return currEmployeeModeOfPayComm;
    }

    public static ArrayList<Employee> getEmployeeByRating(int rating) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeRating = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmpRating() == rating) {
                currEmployeeRating.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE WITH RATING " + rating + " FOUND");
        }
        return currEmployeeRating;
    }

    public static ArrayList<Employee> getEmployeeByAccessRight(int accessRight) throws Exception {
        ArrayList<Employee> employeeArrayList = getAllEmployees();
        ArrayList<Employee> currEmployeeAccessRight = new ArrayList<>();

        boolean found = false;
        for (Employee employee : employeeArrayList) {
            if (employee.getEmpAccessRight() == accessRight) {
                currEmployeeAccessRight.add(employee);
                found = true;
            }
        }
        if (!found) {
            throw new Exception("NO EMPLOYEE WITH ACCESS RIGHT " + accessRight + " FOUND");
        }
        return currEmployeeAccessRight;
    }


}



