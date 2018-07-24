package com.example.eodhuno.ebelle_test;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.DatabaseManager;
import com.example.eodhuno.ebelle_test.R;
import com.example.eodhuno.ebelle_test.database_objects.Customer;

import java.util.ArrayList;

import helper.CustomerHelper;

public class CustomerActivity extends FragmentActivity {

    DatabaseManager mDatabase = new DatabaseManager(this);
    listCustomerFragment customerListFragment;
    individualCustomerFragment individualCustomerFragment;
    CustomerHelper customerHelper;
    CustomWidget customCustomerWidget;
    RelativeLayout customerContainer;

    ArrayList<Customer> currentCustomer = new ArrayList<Customer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        //Opens the existing database
        mDatabase = new DatabaseManager(this);
        customerHelper = new CustomerHelper(this);
        customerListFragment = new listCustomerFragment();

        customerContainer = (RelativeLayout) findViewById(R.id.customerFragmentContainerLayout);

        ArrayList<Customer> customers = CustomerHelper.getAllCustomers();
            currentCustomer.addAll(customers);
            if(currentCustomer.size() > 0) {
                customerListFragment.addCustomers(currentCustomer);

        }

        individualCustomerFragment = new individualCustomerFragment();
        individualCustomerFragment.setOnBackButtonListener(new individualCustomerFragment.OnBackButtonListener() {
            @Override
            public void onBackButtonPressed() {
                switchCustomerFragments(0,null);
            }
        });

        customerListFragment.setCustomerSelectedListener(new listCustomerFragment.OnFragmentCustomerSelectedListener() {
            @Override
            public void onFragmentCustomerSelected(Customer selectedCustomer) {
                Log.d("HERE222XXXXXXX","1111111111111");
                Customer selCustomer = customerListFragment.getSelectedCustomer();
                switchCustomerFragments(1,selCustomer);

            }
        });
        switchCustomerFragments(0,null);
    }



    private void switchCustomerFragments(int flag, Customer customer) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(flag == 0){
                fragmentTransaction.replace(R.id.customerFragmentContainerLayout,customerListFragment);
                if(currentCustomer.size() > 0) {
                    Log.d("HERE111XXXXXXX","1111111111111");

                    customerListFragment.addCustomers(currentCustomer);
                }
            }else if(flag == 1){
                fragmentTransaction.replace(R.id.customerFragmentContainerLayout,individualCustomerFragment);
                individualCustomerFragment.setCurrentCustomer(customer);
            }
            fragmentTransaction.commit();
        }

    }


