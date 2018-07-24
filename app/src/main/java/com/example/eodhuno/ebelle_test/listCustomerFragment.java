package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.eodhuno.ebelle_test.database_objects.Customer;
import com.example.eodhuno.ebelle_test.database_objects.Employee;

import java.util.ArrayList;
import java.util.List;

import helper.CustomerHelper;
import helper.EmployeeHelper;

public class listCustomerFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RecyclerView customerRecyclerView;
    public RecyclerView.Adapter customerAdapter;
    public LinearLayout containerLayout;

    private Customer selectedCustomer = null;

    Boolean prepared = false;

    private OnFragmentCustomerSelectedListener customerSelectedListener;

    List<Customer> customerList = new ArrayList<Customer>();
    Context mainContext;

    private OnFragmentInteractionListener mListener;

    public listCustomerFragment() {
    }

    public static listCustomerFragment newInstance(String param1, String param2) {
        listCustomerFragment fragment = new listCustomerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OnFragmentCustomerSelectedListener getCustomerSelectedListener() {
        return customerSelectedListener;
    }
    public void setCustomerSelectedListener(OnFragmentCustomerSelectedListener customerSelectedListener) {
        this.customerSelectedListener = customerSelectedListener;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
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
        View view = inflater.inflate(R.layout.customer_list_fragment, container, false);
        customerRecyclerView = (RecyclerView) view.findViewById(R.id.customersRecyclerView);
        customerRecyclerView.setHasFixedSize(true);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false));

        if(!prepared){
            CustomerHelper customerHelper = new CustomerHelper(getContext());
            customerList = new ArrayList<>();
            customerList = CustomerHelper.getAllCustomers();
        }

        customerAdapter = new CustomerAdapter(customerList, this.getContext(), new CustomerAdapter.CustomerSelectedListener() {
            @Override
            public void onCustomerSelected(Customer customer) {
                selectedCustomer = customer;
                triggerCustomerSelectedListener(selectedCustomer);
            }
        });
        customerRecyclerView.setAdapter(customerAdapter);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener { void onFragmentInteraction(Uri uri);}

    public void addCustomers(ArrayList<Customer> customerList){
        customerList.clear();
        customerList.addAll(customerList);
        if(customerAdapter != null){
            customerAdapter.notifyDataSetChanged();
            prepared = true;
        }else{
            prepared = false;
        }

    }

    public void triggerCustomerSelectedListener(Customer selectedCustomer){
        customerSelectedListener.onFragmentCustomerSelected(selectedCustomer);
    }

    public interface OnFragmentCustomerSelectedListener {
        void onFragmentCustomerSelected(Customer selectedCustomer);
    }

}
