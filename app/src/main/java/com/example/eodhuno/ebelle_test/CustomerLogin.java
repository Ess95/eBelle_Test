package com.example.eodhuno.ebelle_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.eodhuno.ebelle_test.database_objects.Customer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

import helper.CustomerHelper;

public class CustomerLogin extends Fragment {
    EditText txtUserName,txtPassword;
    Button btnLogin;
    ProgressBar progressBar;
    CustomerHelper customerHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_customer_login, container, false);

        txtUserName = view.findViewById(R.id.usernameEditText);
        txtPassword = view.findViewById(R.id.passwordEditText);
        btnLogin = view.findViewById(R.id.loginButton);
        progressBar = view.findViewById(R.id.loginProgressBar);

        customerHelper = new CustomerHelper(getContext());

        final FragmentActivity fragmentActivity = getActivity();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                fragmentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.VISIBLE);

                    }
                });

            }
        });

        return view;
    }

    public void userLogin() {
        String user = txtUserName.getText().toString().trim();
        String pass = txtPassword.getText().toString().trim();

        Customer currCustomer = new Customer();
        try {
            currCustomer = CustomerHelper.getCustomerByEmail(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (currCustomer.getPassword().equals(pass)) {
            startActivity(new Intent(getContext(), AppointmentActivity.class));
        }else{
            Toast.makeText(this.getContext(),"NOPE",Toast.LENGTH_SHORT).show();
        }
    }
}
