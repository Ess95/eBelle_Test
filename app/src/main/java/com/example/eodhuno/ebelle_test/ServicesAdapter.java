package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{

    public List<Service> serviceListItems;
    Context sCtx;
    Service selectedService = null;
    ServiceSelectedListener listener;

    public ServicesAdapter(List<Service> serviceListItems, Context sCtx, ServiceSelectedListener lis) {
        this.serviceListItems = serviceListItems;
        this.sCtx = sCtx;
        this.listener = lis;
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

        holder.apptCustomWidget.setBigText(0,0,service.getServ_Name());
        //holder.apptCustomWidget.setSmallImage(1,0,R.drawable);
        holder.apptCustomWidget.setSmallText(1,1,service.getServ_Description());
        holder.apptCustomWidget.setBigText(2,0,""+service.getPrice());
    }

    @Override
    public int getItemCount() {
        return serviceListItems.size();
    }

    private void triggerServiceSelectedListener(Service sv){
        Log.d("GGGGGGG",sv.getServ_Name());
        listener.onServiceSelected(sv);
    }

    public interface ServiceSelectedListener{
        public void onServiceSelected(Service selectedService);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CustomWidget apptCustomWidget;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            apptCustomWidget = (CustomWidget) itemView.findViewById(R.id.serviceListCustomItem);
            apptCustomWidget.removeTitle();
            apptCustomWidget.removeProfilePic();
            apptCustomWidget.removeTitleDescription();
            apptCustomWidget.addColumnSpans(new int[]{0,0,1,3},
                    new int[]{1,0,1,1},
                    new int[]{1,1,1,2},
                    new int[]{2,0,1,3});

            apptCustomWidget.addViews(1,
                    2,0,
                    1);
            apptCustomWidget.addAlignement(0,0, "left");
            apptCustomWidget.addAlignement(1,1, "left");
            apptCustomWidget.addAlignement(2,0, "right");
        }

        @Override
        public void onClick(View v) {
            int id = getAdapterPosition();
            triggerServiceSelectedListener(serviceListItems.get(id));
        }
    }

}

