package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Customer;

import java.util.ArrayList;

import helper.CustomerHelper;


public class ViewCustomersFragment extends Fragment {

    DatabaseManager mDatabase = new DatabaseManager(getContext());
    listCustomerFragment customerListFragment;
    individualCustomerFragment individualCustomerFragment;
    CustomerHelper customerHelper;
    CustomWidget customCustomerWidget;
    RelativeLayout customerContainer;

    ArrayList<Customer> currentCustomer = new ArrayList<Customer>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public ViewCustomersFragment() {
    }

    public static ViewCustomersFragment newInstance(String param1, String param2) {
        ViewCustomersFragment fragment = new ViewCustomersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_customers, container, false);

        //Opens the existing database
        mDatabase = new DatabaseManager(getContext());
        customerHelper = new CustomerHelper(getContext());
        customerListFragment = new listCustomerFragment();

        customerContainer = (RelativeLayout) view.findViewById(R.id.customerNavFragmentContainerLayout);

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
                Customer selCustomer = customerListFragment.getSelectedCustomer();
                switchCustomerFragments(1,selCustomer);

            }
        });
        switchCustomerFragments(0,null);
        return view;
    }

    private void switchCustomerFragments(int flag, Customer customer) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(flag == 0){
            fragmentTransaction.replace(R.id.customerNavFragmentContainerLayout,customerListFragment);
            if(currentCustomer.size() > 0) {
                Log.d("HERE111XXXXXXX","1111111111111");

                customerListFragment.addCustomers(currentCustomer);
            }
        }else if(flag == 1){
            fragmentTransaction.replace(R.id.customerNavFragmentContainerLayout,individualCustomerFragment);
            individualCustomerFragment.setCurrentCustomer(customer);
        }
        fragmentTransaction.commit();
    }


    }



