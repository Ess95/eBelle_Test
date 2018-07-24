package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;
import java.util.List;

import helper.ServiceHelper;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder>{

    public List<Appointment> appointmentListItems;
    Context sCtx;
    Appointment selectedAppointment = null;
    AppointmentSelectedListener listener;
    listAppointmentFragment parentFrag;
    ServiceHelper serviceHelper;

    public AppointmentAdapter(List<Appointment> appointmentListItems, Context sCtx, listAppointmentFragment parentFrag, AppointmentSelectedListener lis) {
        this.appointmentListItems = new ArrayList<Appointment>();
        if(parentFrag.getFinalSelectedAppointment() != null){
            this.appointmentListItems.clear();
            this.appointmentListItems.add(parentFrag.getFinalSelectedAppointment());
            for(Appointment currAppointment:appointmentListItems){
                if(currAppointment.getApptID() != parentFrag.getFinalSelectedAppointment().getApptID()){
                    this.appointmentListItems.add(currAppointment);
                }
            }

        }else{
            this.appointmentListItems = appointmentListItems;
        }

        this.sCtx = sCtx;
        this.listener = lis;
        this.parentFrag = parentFrag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_list_item,parent,false);
        serviceHelper = new ServiceHelper(sCtx);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointmentListItems.get(position);

        Service service = new Service();
        try {
            service = ServiceHelper.getServiceById(appointment.getServices());
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.apptCustomWidget.setSmallText(2,2,service.getServ_Name());
        holder.apptCustomWidget.setSmallText(0,2,appointment.getApptDate());
        holder.apptCustomWidget.setSmallText(1,2,appointment.getApptTime()+"0");

    }

    @Override
    public int getItemCount() {
        return appointmentListItems.size();
    }

    private void triggerAppointmentSelectedListener(Appointment sv){
        listener.onAppointmentSelected(sv);
    }

    public interface AppointmentSelectedListener{
        public void onAppointmentSelected(Appointment selectedAppointment);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CustomWidget apptCustomWidget;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    triggerAppointmentSelectedListener(appointmentListItems.get(id));
                }
            });

            apptCustomWidget = (CustomWidget) itemView.findViewById(R.id.appointmentListCustomItem);
            apptCustomWidget.removeTitle();
            apptCustomWidget.removeProfilePic();
            apptCustomWidget.removeTitleDescription();
            apptCustomWidget.removeInfoButton();
            apptCustomWidget.removeDeleteButton();
            apptCustomWidget.removeEditButton();
            apptCustomWidget.removeShrinkButton();
            apptCustomWidget.addColumnSpans(new int[]{0,0,1,2},
                    new int[]{1,0,1,2},
                    new int[]{2,0,1,2});

            apptCustomWidget.addViews(1,0,
                    1,0,
                    1,0);
            apptCustomWidget.setSmallText(0,0,"Appointment Date :");
            apptCustomWidget.addAlignement(0,0, "left");
            apptCustomWidget.setSmallText(1,0,"Appointment Time :");
            apptCustomWidget.addAlignement(1,0, "left");
            apptCustomWidget.setSmallText(2,0,"Service :");
            apptCustomWidget.addAlignement(2,0, "left");

        }
    }
}

