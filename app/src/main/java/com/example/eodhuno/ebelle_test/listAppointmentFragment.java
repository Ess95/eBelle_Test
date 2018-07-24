package com.example.eodhuno.ebelle_test;

import android.content.Context;
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
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;
import java.util.List;

import helper.AppointmentHelper;
import helper.ServiceHelper;

public class listAppointmentFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RecyclerView appointmentRecyclerView;
    public RecyclerView.Adapter appointmentAdapter;
    public LinearLayout containerLayout;
    public CustomNavigationBarView customNavigationBarView;

    private Appointment selectedAppointment = null;
    private Appointment finalSelectedAppointment = null;

    private int totalProductCharge, total;

    Boolean prepared = false;

    private OnFragmentAppointmentSelectedListener appointmentSelectedListener;

    List<Appointment> servList = new ArrayList<Appointment>();
    Context mainContext;

    public Appointment getFinalSelectedAppointment() {
        return finalSelectedAppointment;
    }

    public void setFinalSelectedAppointment(Appointment finalSelectedAppointment) {
        this.finalSelectedAppointment = finalSelectedAppointment;
    }

    

    public void removeFinalSelectedAppointment() {
        this.finalSelectedAppointment = null;
        if(appointmentAdapter != null){
            appointmentAdapter.notifyDataSetChanged();
        }
    }

    public listAppointmentFragment() {
    }

    public static listAppointmentFragment newInstance(String param1, String param2) {
        listAppointmentFragment fragment = new listAppointmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OnFragmentAppointmentSelectedListener getAppointmentSelectedListener() {
        return appointmentSelectedListener;
    }

    public void setAppointmentSelectedListener(OnFragmentAppointmentSelectedListener appointmentSelectedListener) {
        this.appointmentSelectedListener = appointmentSelectedListener;
    }

    public Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public void setSelectedAppointment(Appointment selectedAppointment) {
        this.selectedAppointment = selectedAppointment;
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
        View view = inflater.inflate(R.layout.appointment_list_fragment, container, false);
        appointmentRecyclerView = (RecyclerView) view.findViewById(R.id.appointmentRecyclerView);
        appointmentRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);
        appointmentRecyclerView.setLayoutManager(linearLayoutManager);


        if(!prepared){
            AppointmentHelper appointmentHelper = new AppointmentHelper(getContext());
            servList = new ArrayList<>();
            servList = AppointmentHelper.getAllAppointments();
        }

        appointmentAdapter = new AppointmentAdapter(servList, this.getContext(), this, new AppointmentAdapter.AppointmentSelectedListener() {
            @Override
            public void onAppointmentSelected(Appointment appointment) {
                selectedAppointment = appointment;
                triggerAppointmentSelectedListener(selectedAppointment);
            }
        });
        appointmentRecyclerView.setAdapter(appointmentAdapter);

        return view;
    }

    public void setAppointmentRecyclerViewToStart(){
        if(appointmentRecyclerView != null){
            appointmentRecyclerView.scrollToPosition(0);
            appointmentRecyclerView.setScrollX(0);

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void refreshEverything(){
        if(!prepared){
            AppointmentHelper appointmentHelper = new AppointmentHelper(getContext());
            servList = new ArrayList<>();
            servList = AppointmentHelper.getAllAppointments();
        }

        appointmentAdapter = new AppointmentAdapter(servList, this.getContext(), this, new AppointmentAdapter.AppointmentSelectedListener() {
            @Override
            public void onAppointmentSelected(Appointment svr) {
                selectedAppointment = svr;
                triggerAppointmentSelectedListener(selectedAppointment);
            }
        });
        appointmentRecyclerView.setAdapter(appointmentAdapter);
    }

    public void addAppointments(ArrayList<Appointment> appointmentList){
        servList.clear();
        servList.addAll(appointmentList);


        if(appointmentAdapter != null){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    appointmentAdapter.notifyDataSetChanged();
                }
            });
            prepared = true;
        }else{
            prepared = true;
        }
    }

    public void triggerAppointmentSelectedListener(Appointment selectedAppointment){
        appointmentSelectedListener.onFragmentAppointmentSelected(selectedAppointment);
    }

    public interface OnFragmentAppointmentSelectedListener {
        void onFragmentAppointmentSelected(Appointment selectedAppointment);
    }

}
