package com.example.eodhuno.ebelle_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class EmployeeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText empFirstName, empLastName, empContact,empEmail;
    Button empMale, empFemale;
    Spinner empSalaryMethod,empServices;

    DatabaseManager mDatabase;
    String Empservices;
    String salaryMethod;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_employee_profile);

        mDatabase = new DatabaseManager(this);

        empFirstName = (EditText) findViewById(R.id.EmpFirstName);
        empLastName = (EditText) findViewById(R.id.EmpLastName);
        empContact = (EditText) findViewById(R.id.EmpFirstName);
        empEmail = (EditText) findViewById(R.id.EmpEmail_editText);
        empMale = (Button) findViewById(R.id.Male_Image);
        empFemale = (Button) findViewById(R.id.Female_Image);
        empSalaryMethod = (Spinner) findViewById(R.id.SalaryMethod_Spinner);
        empServices = (Spinner) findViewById(R.id.EmpServices_spinner);

        findViewById(R.id.addEmployee_button).setOnClickListener(this);
        findViewById(R.id.Female_Image).setOnClickListener(this);
        findViewById(R.id.Male_Image).setOnClickListener(this);
        ((Spinner) findViewById(R.id.SalaryMethod_Spinner)).setOnItemSelectedListener(this);
        ((Spinner) findViewById(R.id.EmpServices_spinner)).setOnItemSelectedListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Female_Image:
                 gender = empFemale.getText().toString();
            case R.id.Male_Image:
                 gender = empMale.getText().toString();
            case R.id.addEmployee_button:
                addNewEmployee();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        salaryMethod = empSalaryMethod.getItemAtPosition(position).toString();
        Empservices = empServices.getItemAtPosition(position).toString();
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void addNewEmployee() {
        String newEmpFname = empFirstName.getText().toString();
        String newEmpLname = empLastName.getText().toString();
        int newEmpContact= Integer.parseInt(empContact.getText().toString());
        String newEmpEmail = empEmail.getText().toString();

        if(newEmpFname.isEmpty()){
            empFirstName.setError("Product name cannot be empty");
            empFirstName.requestFocus();
            return;
        }
        if(newEmpLname.isEmpty()){
            empLastName.setError("Description cannot be empty");
            empLastName.requestFocus();
            return;
        }
        if(newEmpContact == 0){
            empContact.setError("Product quantity cannot be empty");
            empContact.requestFocus();
            return;
        }
        if(newEmpEmail.isEmpty()){
            empEmail.setError("Product Unit Price cannot be empty");
            empEmail.requestFocus();
            return;
        }

        if(gender.isEmpty()){
            empFemale.setError("Please select gender");
            empFemale.requestFocus();
            return;
        }

        if(mDatabase.addEmployeeProfile(newEmpFname,newEmpLname,gender,newEmpContact,newEmpEmail,Empservices,salaryMethod)){
            Toast.makeText(this,"Employee profile created successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Employee not created successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
