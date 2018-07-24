package com.example.eodhuno.ebelle_test;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Employee;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;

import helper.EmployeeHelper;
import helper.ServiceHelper;


public class individualEmployeeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Employee currentEmployee = null;


    private String mParam1;
    private String mParam2;

    CustomWidget employeeCustomWidget;
    EmployeeHelper employeeHelper;
    ServiceHelper serviceHelper;
    RelativeLayout individualEmployeeContainer;

    private OnBackButtonListener onBackButtonListener;

    private OnEmployeeSelectedListener onEmployeeSelectedListener;


    public individualEmployeeFragment() {
        // Required empty public constructor
    }


    public static individualEmployeeFragment newInstance(String param1, String param2) {
        individualEmployeeFragment fragment = new individualEmployeeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OnBackButtonListener getonBackButtonListener() {
        return onBackButtonListener;
    }

    public void setOnBackButtonListener(OnBackButtonListener mListener) {
        this.onBackButtonListener = mListener;
    }

    public OnEmployeeSelectedListener getOnEmployeeSelectedListener() {
        return onEmployeeSelectedListener;
    }

    public void setOnEmployeeSelectedListener(OnEmployeeSelectedListener onEmployeeSelectedListener) {
        this.onEmployeeSelectedListener = onEmployeeSelectedListener;
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
        View view = inflater.inflate(R.layout.individual_employee_fragment, container, false);

        employeeHelper = new EmployeeHelper(getContext());
        serviceHelper = new ServiceHelper(getContext());
        individualEmployeeContainer = (RelativeLayout)view.findViewById(R.id.individualEmployeeContainer);

        employeeCustomWidget = view.findViewById(R.id.individualEmployeeListCustomItem);
        employeeCustomWidget.removeTitle();
        employeeCustomWidget.removeProfilePic();
        employeeCustomWidget.removeTitleDescription();
        employeeCustomWidget.removeInfoButton();
        employeeCustomWidget.removeDeleteButton();
        employeeCustomWidget.removeEditButton();
        employeeCustomWidget.removeShrinkButton();
        employeeCustomWidget.setColumnNumber(3);

        employeeCustomWidget.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,1,1,2},
                new int[]{3,1,1,2},
                new int[]{4,1,1,2},
                new int[]{5,1,1,2},
                new int[]{6,0,1,2});

        employeeCustomWidget.addViews(18,
                2,
                1,0,
                1,9,
                1,0,
                1,0,
                13);

        employeeCustomWidget.setBigText(2,0,"Stylist Name");
        employeeCustomWidget.addAlignement(2,0,"left");
        employeeCustomWidget.setSmallText(2,1,"Lilly Mara");
        employeeCustomWidget.addAlignement(2,1, "left");
        employeeCustomWidget.setBigText(3,0,"Rating");
        employeeCustomWidget.addAlignement(3,0,"left");
        employeeCustomWidget.setRating(3,1,4);
        employeeCustomWidget.addAlignement(3,1, "left");
        employeeCustomWidget.setBigText(4,0,"Availability");
        employeeCustomWidget.addAlignement(4,0,"left");
        employeeCustomWidget.setBigText(5,0,"Services");
        employeeCustomWidget.addAlignement(5,0,"left");
        employeeCustomWidget.setSmallText(5,1,"Crochet");
        employeeCustomWidget.addAlignement(5,1,"left");
        employeeCustomWidget.setButtonText(6,0,"Choose Stylist");
        employeeCustomWidget.addAlignement(6,0,"right");

        employeeCustomWidget.getButton(6, 0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                triggerOnEmployeeSelectedListener();
            }
        });
        employeeCustomWidget.setNavigationBarForwardButtonVisibility(0,0,false);

        employeeCustomWidget.setNavigationBarBackButtonListener(0, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerOnBackButtonListener();
            }
        });
        addEmployee();
        return view;
    }
    public Employee getCurrentEmployee() {
        return currentEmployee;
    }

    public void setCurrentEmployee(Employee currentEmployee) { this.currentEmployee = currentEmployee; }

    private void addEmployee() {
        if(currentEmployee != null){
            employeeCustomWidget.setSmallText(2,1,currentEmployee.getEmp_Fname()+" "+currentEmployee.getEmp_Lname());
            employeeCustomWidget.setRating(3,1,+currentEmployee.getEmpRating());

            String currServ = "";
                try {
                    Service service = ServiceHelper.getServiceById(currentEmployee.getEmp_Services());
                    currServ += service.getServ_Name();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                employeeCustomWidget.setSmallText(5,1,currServ);


            String availability = "";
            if(currentEmployee.getEmpAvailability() == 1){
                availability = "Yes";
            }else{
                availability = "No";
            }
            employeeCustomWidget.setSmallText(4,1,availability);
            employeeCustomWidget.addAlignement(4,1,"left");

        }
    }

    public void triggerOnBackButtonListener(){
        onBackButtonListener.onBackButtonPressed();
    }

    public void triggerOnEmployeeSelectedListener(){
        onEmployeeSelectedListener.onEmployeeSelected(currentEmployee);
        triggerOnBackButtonListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnBackButtonListener {
        void onBackButtonPressed();
    }

    public interface OnEmployeeSelectedListener {

        void onEmployeeSelected(Employee currEmployee);

    }
}
