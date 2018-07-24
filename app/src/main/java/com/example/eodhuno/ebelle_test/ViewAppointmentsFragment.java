package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.eodhuno.ebelle_test.database_objects.Appointment;

import java.util.ArrayList;

import helper.AppointmentHelper;


public class ViewAppointmentsFragment extends Fragment {

    RelativeLayout layout;
    listAppointmentFragment listAppointmentFragment;
    individualAppointmentFragment individualAppointmentFragment;

    ArrayList<Appointment> currentAppts = new ArrayList<Appointment>();
    AppointmentHelper appointmentHelper;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public ViewAppointmentsFragment() {
    }


    public static ViewAppointmentsFragment newInstance(String param1, String param2) {
        ViewAppointmentsFragment fragment = new ViewAppointmentsFragment();
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
        View view = inflater.inflate(R.layout.fragment_view_appointments, container, false);

        layout = (RelativeLayout)view.findViewById(R.id.apptViewFragmentContainerLayout);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.viewAppointmentFragmentToolbar);
        toolbar.setVisibility(View.VISIBLE);

        appointmentHelper = new AppointmentHelper(getContext());

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
        return view;
    }
    public void switchServiceFragments(int flag, Appointment appt){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(flag == 0){
            fragmentTransaction.replace(R.id.apptViewFragmentContainerLayout,listAppointmentFragment);
            if(currentAppts.size() > 0){
                listAppointmentFragment.addAppointments(currentAppts);
            }
        }else if(flag == 1){
            fragmentTransaction.replace(R.id.apptViewFragmentContainerLayout,individualAppointmentFragment);
            individualAppointmentFragment.setCurrentAppointment(appt);
        }
        fragmentTransaction.commit();
    }



}
