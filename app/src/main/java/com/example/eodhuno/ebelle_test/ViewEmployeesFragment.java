package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ebellecustomcard.CustomNavigationBarView;
import com.example.eodhuno.ebelle_test.database_objects.Employee;

import java.util.ArrayList;
import java.util.List;

import helper.EmployeeHelper;


public class ViewEmployeesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RecyclerView employeeRecyclerView;
    public RecyclerView.Adapter employeeAdapter;
    public LinearLayout containerLayout;
    public CustomNavigationBarView employeeNavigation;

    private Employee selectedEmployee = null;
    private Employee finalSelectedEmployee = null;

    public Employee getFinalSelectedEmployee() {
        return finalSelectedEmployee;
    }

    public void setFinalSelectedEmployee(Employee finalSelectedEmployee) {
        this.finalSelectedEmployee = finalSelectedEmployee;
    }

    Boolean prepared = false;

    private listEmployeeFragment.OnFragmentEmployeeSelectedListener employeeSelectedListener;


    List<Employee> empList = new ArrayList<Employee>();
    Context mainContext;

    private listEmployeeFragment.OnFragmentInteractionListener mListener;

    public void removeFinalSelectedService() {
        this.finalSelectedEmployee = null;
        if(employeeAdapter != null){
            employeeAdapter.notifyDataSetChanged();
        }
    }
    public ViewEmployeesFragment() {
    }


    public static ViewEmployeesFragment newInstance(String param1, String param2) {
        ViewEmployeesFragment fragment = new ViewEmployeesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public listEmployeeFragment.OnFragmentEmployeeSelectedListener getEmployeeSelectedListener() {
        return employeeSelectedListener;
    }
    public void setEmployeeSelectedListener(listEmployeeFragment.OnFragmentEmployeeSelectedListener employeeSelectedListener) {
        this.employeeSelectedListener = employeeSelectedListener;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
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

       View view = inflater.inflate(R.layout.fragment_view_employees, container, false);
        employeeRecyclerView = (RecyclerView) view.findViewById(R.id.viewEmployeesRecyclerView);
        employeeRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);
        employeeRecyclerView.setLayoutManager(layoutManager);

        if(!prepared){
            EmployeeHelper employeeHelper = new EmployeeHelper(getContext());
            empList = new ArrayList<>();
            empList = EmployeeHelper.getAllEmployees();
        }

        employeeAdapter = new ViewEmployeeAdapter(empList, this.getContext(), this, new ViewEmployeeAdapter.EmployeeSelectedListener() {
            @Override
            public void onEmployeeSelected(Employee selectedEmployee) {

            }
        });
        employeeRecyclerView.setAdapter(employeeAdapter);


       return view;
    }
    public interface OnFragmentInteractionListener { void onFragmentInteraction(Uri uri);}

    public void addEmployees(ArrayList<Employee> employeeList){
        empList.clear();
        if(getFinalSelectedEmployee() != null){
            Log.d("oooooooooooo","HERE");
            empList.add(getFinalSelectedEmployee());
            for(Employee currEmployee:employeeList){
                if(currEmployee.getEmpID() != getFinalSelectedEmployee().getEmpID()){
                    this.empList.add(currEmployee);
                }
            }
        }else{
            Log.d("oooooooooooo","HERE11111 "+employeeList.toString());
            empList.addAll(employeeList);
        }

        employeeList.addAll(employeeList);
        if(employeeAdapter != null){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    employeeAdapter.notifyDataSetChanged();
                }
            });
            prepared = true;
        }else{
            prepared = true;
        }

    }

    public void triggerEmployeeSelectedListener(Employee selectedEmployee){
        employeeSelectedListener.onFragmentEmployeeSelected(selectedEmployee);
    }

    public interface OnFragmentEmployeeSelectedListener {
        void onFragmentEmployeeSelected(Employee selectedEmployee);
    }




}
