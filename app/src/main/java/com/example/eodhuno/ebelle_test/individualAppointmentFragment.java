package com.example.eodhuno.ebelle_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Customer;
import com.example.eodhuno.ebelle_test.database_objects.Employee;
import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;

import helper.AppointmentHelper;
import helper.CustomerHelper;
import helper.EmployeeHelper;
import helper.ProductHelper;
import helper.ServiceHelper;


public class individualAppointmentFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Appointment currentAppointment = null;

    CustomWidget customCard;
    AppointmentHelper appointmentHelper;
    ProductHelper productHelper;
    ServiceHelper serviceHelper;
    CustomerHelper customerHelper;
    EmployeeHelper employeeHelper;
    RelativeLayout individualAppointmentContainer;


    ArrayList<Service> allServices = new ArrayList<Service>();


    private OnBackButtonListener onBackButtonListener;

    private OnAppointmentSelectedListener onAppointmentSelectedListener;

    public individualAppointmentFragment() {
        // Required empty public constructor
    }

    public static individualAppointmentFragment newInstance(String param1, String param2) {
        individualAppointmentFragment fragment = new individualAppointmentFragment();
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

    public OnAppointmentSelectedListener getOnAppointmentSelectedListener() {
        return onAppointmentSelectedListener;
    }

    public void setOnAppointmentSelectedListener(OnAppointmentSelectedListener onAppointmentSelectedListener) {
        this.onAppointmentSelectedListener = onAppointmentSelectedListener;
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
        View view = inflater.inflate(R.layout.individual_appointment_fragment, container, false);
        appointmentHelper = new AppointmentHelper(this.getContext());
        productHelper = new ProductHelper(this.getContext());
        serviceHelper = new ServiceHelper(this.getContext());
        customerHelper = new CustomerHelper(this.getContext());
        employeeHelper = new EmployeeHelper(this.getContext());

        individualAppointmentContainer = (RelativeLayout)view.findViewById(R.id.individualAppointmentContainer);

        customCard = view.findViewById(R.id.individualAppointmentListCustomItem);
        customCard.removeTitle();
        customCard.removeProfilePic();
        customCard.removeTitleDescription();
        customCard.removeInfoButton();
        customCard.removeDeleteButton();
        customCard.removeEditButton();
        customCard.removeShrinkButton();
        customCard.setColumnNumber(3);


        customCard.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,0,1,2},
                new int[]{3,0,1,3},
                new int[]{4,0,1,2},
                new int[]{5,0,1,2},
                new int[]{6,0,1,3},
                new int[]{7,0,1,2},
                new int[]{8,0,1,2},
                new int[]{9,0,1,2});

        customCard.addViews(18,
                1,
                1,0,
                1,
                1,0,
                1,0,
                1,
                1,0,
                1,0,
                1,0);
        //customCard.addVerticalBordersToRightOf(new int[]{2,0,7});

        customCard.setBigText(1,0,"Appointment Details");
        customCard.addAlignement(1,0,"left");
        customCard.setBigText(2,0,"Customer Name ");
        customCard.addAlignement(2,0,"left");
        customCard.setSmallText(2,2,"Naomi");
        customCard.addAlignement(2,2,"");
        customCard.setBigText(3,0," Appointment Schedule");
        customCard.setSmallText(4,0,"Appointment Time");
        customCard.addAlignement(4,0,"left");
        customCard.setBigText(4,2,"11:45 :");
        customCard.addAlignement(4,2,"left");
        customCard.setSmallText(5,0,"Appointment Date");
        customCard.addAlignement(5,0,"left");
        customCard.setBigText(5,2,"11/2/2018");
        customCard.addAlignement(5,2,"left");
        customCard.setBigText(6,0," Service Details");
        customCard.setBigText(7,0,"Service Name");
        customCard.addAlignement(7,0,"left");
        customCard.setSmallText(7,2,"Braids");
        customCard.addAlignement(7,2,"left");
        customCard.setBigText(8,0,"Service Price");
        customCard.addAlignement(8,0,"left");
        customCard.setSmallText(8,2,"1200");
        customCard.addAlignement(8,2,"left");
        customCard.setBigText(9,0,"Stylist Name");
        customCard.addAlignement(9,0,"left");
        customCard.setSmallText(9,2,"Flo");
        customCard.addAlignement(9,2,"left");

        customCard.addFullHorizontalBorders(2,5);
        customCard.setNavigationBarForwardButtonVisibility(0,0,false);

        customCard.setNavigationBarBackButtonListener(0, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerOnBackButtonListener();
            }
        });

        addAppointment();

        return view;


    }

    public Appointment getCurrentAppointment() {
        return currentAppointment;
    }

    public void setCurrentAppointment(Appointment currentAppointment) {
        this.currentAppointment = currentAppointment;
    }

    public void addAppointment(){
        if(currentAppointment != null){
            customCard.setSmallText(4,2,currentAppointment.getApptTime()+"0");
            customCard.setSmallText(5,2,currentAppointment.getApptDate());

            Customer customer = new Customer();
            Employee employee = new Employee();
            Service service = new Service();

            try {
                customer = CustomerHelper.getCustomerById(currentAppointment.getCustID_FK());
                Log.d("CHECKSXXXXXX","CUST NAME "+customer.getFirstName() +" "+ customer.getLastName());

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                service = ServiceHelper.getServiceById(currentAppointment.getServices());

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                employee = EmployeeHelper.getEmployeeById(currentAppointment.getBeautician());
                Log.d("CHECKSXXXXXX","EMP NAME "+employee.getEmp_Fname() +" "+ employee.getEmp_Lname());

            } catch (Exception e) {
                e.printStackTrace();
            }

            customCard.setSmallText(7,2,service.getServ_Name());
            customCard.setSmallText(8,2,""+currentAppointment.getServiceCharge());
            customCard.setSmallText(2,2,customer.getFirstName() +" "+customer.getLastName());
            customCard.setSmallText(9,2,employee.getEmp_Fname() +" " +employee.getEmp_Lname());

        }
    }

    public void triggerOnBackButtonListener(){
        onBackButtonListener.onBackButtonPressed();
    }

    public void triggerOnAppointmentSelectedListener(){
        onAppointmentSelectedListener.onAppointmentSelected(currentAppointment);
        triggerOnBackButtonListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnBackButtonListener {
        void onBackButtonPressed();
    }

    public interface OnAppointmentSelectedListener {

        void onAppointmentSelected(Appointment currAppointment);

    }
}
