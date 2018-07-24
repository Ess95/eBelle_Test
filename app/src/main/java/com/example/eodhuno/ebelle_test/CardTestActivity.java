package com.example.eodhuno.ebelle_test;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import com.example.ebellecustomcard.CustomCalendarView;
//import com.example.ebellecustomcard.CustomTimePickerView;
//import com.example.ebellecustomcard.CustomWidget;
import com.example.ebellecustomcard.CustomCalendarView;
import com.example.ebellecustomcard.CustomListView;
import com.example.ebellecustomcard.CustomTimePickerView;
import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Category;
import com.example.eodhuno.ebelle_test.database_objects.Customer;
import com.example.eodhuno.ebelle_test.database_objects.Employee;
import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import org.joda.time.DateTime;

import java.util.ArrayList;

import helper.AppointmentHelper;
import helper.CategoryHelper;
import helper.CustomerHelper;
import helper.EmployeeHelper;
import helper.ProductHelper;
import helper.ServiceHelper;
import helper.TimeHelper;

public class CardTestActivity extends AppCompatActivity {
    RelativeLayout layout;
    listAppointmentFragment listAppointmentFragment;
    individualAppointmentFragment individualAppointmentFragment;

    ArrayList<Appointment> currentAppts = new ArrayList<Appointment>();
    AppointmentHelper appointmentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        layout = (RelativeLayout)findViewById(R.id.apptFragmentContainerLayout);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.viewAppointmentToolbar);
        toolbar.setVisibility(View.VISIBLE);

        appointmentHelper = new AppointmentHelper(this);

        ArrayList<Appointment> appts = new ArrayList<Appointment>();
        try{
            appts = AppointmentHelper.getAllAppointments();
        }catch(Exception e){
        }
        currentAppts.clear();
        currentAppts.addAll(appts);

        listAppointmentFragment = new listAppointmentFragment();
        individualAppointmentFragment = new individualAppointmentFragment();
        individualAppointmentFragment.setOnBackButtonListener(new individualAppointmentFragment.OnBackButtonListener() {
            @Override
            public void onBackButtonPressed() {
                switchServiceFragments(0,null);
            }
        });

        listAppointmentFragment.setAppointmentSelectedListener(new listAppointmentFragment.OnFragmentAppointmentSelectedListener() {
            @Override
            public void onFragmentAppointmentSelected(Appointment selectedAppointment) {
                switchServiceFragments(1,selectedAppointment);
            }
        });
        switchServiceFragments(0,null);
    }

    public void switchServiceFragments(int flag, Appointment appt){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(flag == 0){
            fragmentTransaction.replace(R.id.apptFragmentContainerLayout,listAppointmentFragment);
            if(currentAppts.size() > 0){
                listAppointmentFragment.addAppointments(currentAppts);
            }
        }else if(flag == 1){
            fragmentTransaction.replace(R.id.apptFragmentContainerLayout,individualAppointmentFragment);
            individualAppointmentFragment.setCurrentAppointment(appt);
        }
        fragmentTransaction.commit();
    }
}




