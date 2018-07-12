package com.example.eodhuno.ebelle_test;

import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eodhuno.ebelle_test.Interface.LoadItems;
import com.example.eodhuno.ebelle_test.database_objects.Customer;

import java.util.ArrayList;
import java.util.List;

import SalonAdapter.CardViewAdapter;

public class CustomerProfilesActivity extends AppCompatActivity {

    DatabaseManager sDatabase;
    RecyclerView recyclerView;
    CardViewAdapter mCardViewAdapter;
    Customer_Adapter mCustomerAdapter;

    List<Customer> customerList = new ArrayList<>();

    Button updateCustomer, deleteCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        sDatabase = new DatabaseManager(this);

        readCustomers();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        mCardViewAdapter = new CardViewAdapter(recyclerView,this,customerList);
        recyclerView.setAdapter(mCardViewAdapter);

        mCustomerAdapter = new Customer_Adapter(CustomerProfilesActivity.this,R.layout.customerprofiles_list_layout,customerList,sDatabase);
        updateCustomer = (Button) findViewById(R.id.nButton_Edit);
        deleteCustomer = (Button) findViewById(R.id.nButton_Delete);



        //Loading more customers
        mCardViewAdapter.setLoadedItems(new LoadItems() {
            @Override
            public void loadMoreItems() {
                if(customerList.size() <= 5)
                {
                    customerList.add(null);
                    mCardViewAdapter.notifyItemInserted(customerList.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            customerList.remove(customerList.size()-1);
                            mCardViewAdapter.notifyItemRemoved(customerList.size());

                            int index = customerList.size();
                            int end = index + 5;
                            Log.d("CHECKING","HERE@@@@@@@@@@@@@@@@@2");
                            for(int i = index; i<=end; i++)
                            {
                                Log.d("CHECKING","%%%%%%%%%%%%%"+end);

                                readCustomers();

                            }
                            Log.d("CHECKING","###############"+customerList.size());
                            mCardViewAdapter.notifyDataSetChanged();
                            mCardViewAdapter.setItemLoaded();
                        }
                    },2000);
                }else {
                    Toast.makeText(CustomerProfilesActivity.this,"NOPE!",Toast.LENGTH_SHORT).show();
                }
            }
        });


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
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            }
            while (cursor.moveToNext());
            cursor.close();

            Customer_Adapter adapter = new Customer_Adapter(this, R.layout.activity_recycler_view, customerList, sDatabase);

        }
    }

    private void updateCustomer() {
        Customer customer = new Customer();
        mCustomerAdapter.deleteCustomerProfile(customer);
    }

    /**private void deleteCustomer(){
        Customer customer = new Customer();
        mCustomerAdapter.updateCustomerProfile(customer);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nButton_Edit:
                updateCustomer();
                break;
            case R.id.nButton_Delete:
                deleteCustomer();
                break;
        }

    }**/
}


