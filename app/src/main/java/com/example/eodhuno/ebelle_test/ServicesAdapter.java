package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{

    public List<Service> serviceListItems;
    Context sCtx;
    Service selectedService = null;
    ServiceSelectedListener listener;
    listServiceFragment parentFrag;

    public ServicesAdapter(List<Service> serviceListItems, Context sCtx, listServiceFragment parentFrag, ServiceSelectedListener lis) {
        this.serviceListItems = new ArrayList<Service>();
        if(parentFrag.getFinalSelectedService() != null){
            this.serviceListItems.clear();
            this.serviceListItems.add(parentFrag.getFinalSelectedService());
            for(Service currService:serviceListItems){
                if(currService.getServID() != parentFrag.getFinalSelectedService().getServID()){
                    this.serviceListItems.add(currService);
                }
            }

        }else{
            this.serviceListItems = serviceListItems;
        }

        this.sCtx = sCtx;
        this.listener = lis;
        this.parentFrag = parentFrag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Service service = serviceListItems.get(position);

        if(parentFrag.getFinalSelectedService() != null && service.getServID() == parentFrag.getFinalSelectedService().getServID()){
            if(service.getServID() == parentFrag.getFinalSelectedService().getServID()){
                holder.apptCustomWidget.getIconTextImageButton(0,2).setVisibility(View.VISIBLE);
                holder.apptCustomWidget.getTextview(2,0).setVisibility(View.VISIBLE);
                final TextView tx = holder.apptCustomWidget.getTextview(2,2);
                ((FragmentActivity)sCtx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tx.setText("ksh "+parentFrag.getTotal());
                    }
                });
            }else{
                holder.apptCustomWidget.getIconTextImageButton(0,2).setVisibility(View.INVISIBLE);
                holder.apptCustomWidget.getTextview(2,0).setVisibility(View.INVISIBLE);
            }
        }else{
            holder.apptCustomWidget.setBigText(2,2,"ksh "+service.getPrice());
            TextView tx = holder.apptCustomWidget.getTextview(2,2);
        }
        holder.apptCustomWidget.setBigText(0,0,service.getServ_Name());
        holder.apptCustomWidget.setSmallText(1,1,service.getServ_Description());
    }

    @Override
    public int getItemCount() {
        return serviceListItems.size();
    }

    private void triggerServiceSelectedListener(Service sv){
        listener.onServiceSelected(sv);
    }

    public interface ServiceSelectedListener{
        public void onServiceSelected(Service selectedService);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CustomWidget apptCustomWidget;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    triggerServiceSelectedListener(serviceListItems.get(id));
                }
            });

            apptCustomWidget = (CustomWidget) itemView.findViewById(R.id.serviceListCustomItem);
            apptCustomWidget.removeTitle();
            apptCustomWidget.removeProfilePic();
            apptCustomWidget.removeTitleDescription();
            apptCustomWidget.removeInfoButton();
            apptCustomWidget.removeDeleteButton();
            apptCustomWidget.removeEditButton();
            apptCustomWidget.removeShrinkButton();
            apptCustomWidget.addColumnSpans(new int[]{0,0,1,2},
                    new int[]{1,1,1,2},
                    new int[]{2,0,1,2});

            apptCustomWidget.addViews(1,22,
                    2,0,
                    0,1);
            apptCustomWidget.addAlignement(0,0, "left");
            apptCustomWidget.addAlignement(0,2, "right");
            apptCustomWidget.setIconText(0,2, "");
            apptCustomWidget.setIconTextImage(0,2, R.drawable.checkbox_marked);
            apptCustomWidget.getIconTextImageButton(0,2).setVisibility(View.INVISIBLE);
            apptCustomWidget.getIconTextTextView(0,2).setVisibility(View.INVISIBLE);
            apptCustomWidget.addAlignement(1,0, "left");
            apptCustomWidget.addAlignement(1,1, "left");
            apptCustomWidget.setSmallText(2,0,"Service + Product charges :");
            apptCustomWidget.getTextview(2,0).setVisibility(View.INVISIBLE);
            apptCustomWidget.addAlignement(2,2, "right");
        }
    }
}

