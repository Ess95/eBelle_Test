package com.example.eodhuno.ebelle_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {

    EditText CustFname, CustLname, Gender, Contact, Email, Password;
    DatabaseManager mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Opens the existing database
        mDatabase = new DatabaseManager(this);

        CustFname = (EditText) findViewById(R.id.CustFname_et);
        CustLname = (EditText) findViewById(R.id.CustLname_et);
        Gender = (EditText) findViewById(R.id.Gender_et);
        Contact = (EditText) findViewById(R.id.Contact_et);
        Email = (EditText) findViewById(R.id.Email_et);
        Password = (EditText) findViewById(R.id.Password_et);

        findViewById(R.id.button_DoIt).setOnClickListener(this);
        findViewById(R.id.button_View).setOnClickListener(this);

    }
    private void addNewCustomer() {

        String newFname = CustFname.getText().toString().trim();
        String newLname = CustLname.getText().toString().trim();
        String newGender = Gender.getText().toString().trim();
        String newPhoneNo = Contact.getText().toString();
        String newEmail = Email.getText().toString().trim();
        String newPassword = Password.getText().toString().trim();

        if(newFname.isEmpty()){
            CustFname.setError("Cannot be empty");
            CustFname.requestFocus();
            return;
            }
        if(newLname.isEmpty()){
            CustLname.setError("Cannot be empty");
            CustLname.requestFocus();
            return;
        }
        if(newGender.isEmpty()){
            Gender.setError("Cannot be empty");
            Gender.requestFocus();
            return;
        }
        if(newPhoneNo.isEmpty()){
            Contact.setError("Cannot be empty");
            Contact.requestFocus();
            return;
        }
        if(newEmail.isEmpty()){
            Email.setError("Cannot be empty");
            Email.requestFocus();
            return;
        }
        if(newPassword.isEmpty()){
            Password.setError("Cannot be empty");
            Password.requestFocus();
            return;
        }

        if(mDatabase.addCustomerProfile(newFname,newLname,newGender,Integer.parseInt(newPhoneNo),newEmail,newPassword))
            Toast.makeText(this,"Profile created successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Profile not created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_DoIt:
                addNewCustomer();

                CustFname.setText("");
                CustLname.setText("");
                Gender.setText("");
                Contact.setText("");
                Email.setText("");
                Password.setText("");

            case R.id.button_View:
                startActivity(new Intent(this, CustomerProfilesActivity.class));
                break;

        }
    }


}

