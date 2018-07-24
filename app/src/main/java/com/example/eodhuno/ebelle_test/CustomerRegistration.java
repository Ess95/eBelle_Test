package com.example.eodhuno.ebelle_test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helper.CustomerHelper;

public class CustomerRegistration extends Fragment {

    EditText CustFname, CustLname, Gender, Contact, Email, Password, ConfirmPassword;
    DatabaseManager mDatabase;
    Button addButton;
    CustomerHelper customerHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.register_new_customer,container,false);

        mDatabase = new DatabaseManager(getContext());

        CustFname = view.findViewById(R.id.CustFname_et);
        CustLname = view.findViewById(R.id.CustLname_et);
        Gender = view.findViewById(R.id.Gender_et);
        Contact = view.findViewById(R.id.Contact_et);
        Email = view.findViewById(R.id.Email_et);
        Password = view.findViewById(R.id.Password_et);
        ConfirmPassword =view.findViewById(R.id.confirmPassword_et);
        addButton = view.findViewById(R.id.button_Register);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCustomer();
                Log.d("CHECKINGXXXXXXXX","CLICKED");

            }
        });

        return view;
    }
    private void addNewCustomer() {
        int newImage = 0;
        String newFname = CustFname.getText().toString().trim();
        String newLname = CustLname.getText().toString().trim();
        String newGender = Gender.getText().toString().trim();
        String newPhoneNo = Contact.getText().toString();
        String newEmail = Email.getText().toString().trim();
        String newPassword = Password.getText().toString().trim();
        String newConfirmPassword = ConfirmPassword.getText().toString().trim();


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

        if(newConfirmPassword.isEmpty()){
            ConfirmPassword.setError("Cannot be empty");
            ConfirmPassword.requestFocus();
            if(!newConfirmPassword.matches(newPassword))
                ConfirmPassword.setError("Must match the Password");
            return;
        }

        if(mDatabase.addCustomerProfile(newImage,newFname,newLname,newGender,Integer.parseInt(newPhoneNo),newEmail,newPassword,newConfirmPassword)) {
            Toast.makeText(getContext(), "Profile created successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(), "Profile not created", Toast.LENGTH_SHORT).show();
        }
    }
}
