package com.example.eodhuno.ebelle_test.database_objects;

public class UserAuthentication {
    public UserAuthentication(){}

    int passID, empID;
    String empUsername,empPassword;

    public UserAuthentication(int passID, int empID, String empUsername, String empPassword) {
        this.passID = passID;
        this.empID = empID;
        this.empUsername = empUsername;
        this.empPassword = empPassword;
    }

    public int getPassID() {
        return passID;
    }

    public int getEmpID() {
        return empID;
    }

    public String getEmpUsername() {
        return empUsername;
    }

    public String getEmpPassword() {
        return empPassword;
    }
}
