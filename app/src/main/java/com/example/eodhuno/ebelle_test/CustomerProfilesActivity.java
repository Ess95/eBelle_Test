package com.example.eodhuno.ebelle_test;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.eodhuno.ebelle_test.database_objects.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerProfilesActivity extends AppCompatActivity {

    DatabaseManager sDatabase;

    List<Customer> customerList;
    ListView customerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerprofiles_list_layout);
        sDatabase = new DatabaseManager(this);

        customerList = new ArrayList<>();
        customerListView = (ListView) findViewById(R.id.custListView);

        readCustomers();
    }

    private void readCustomers() {
        Cursor cursor = sDatabase.readAllCustomerProfiles();

        //The cursor checks the records in the database. If it moves to first, it means there is some data thus a record.
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
            Customer_Adapter adapter = new Customer_Adapter(this, R.layout.customer_profile_layout, customerList, sDatabase);
            customerListView.setAdapter(adapter);

        }
    }
}


