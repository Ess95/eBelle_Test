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
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;
import java.util.List;

import helper.ServiceHelper;

public class listServiceFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RecyclerView serviceRecyclerView;
    public RecyclerView.Adapter serviceAdapter;
    public LinearLayout containerLayout;
    public CustomNavigationBarView customNavigationBarView;

    private Service selectedService = null;
    private Service finalSelectedService = null;

    private int totalProductCharge, total;

    Boolean prepared = false;

    private OnFragmentServiceSelectedListener serviceSelectedListener;

    List<Service> servList = new ArrayList<Service>();
    Context mainContext;

    public Service getFinalSelectedService() {
        return finalSelectedService;
    }

    public void setFinalSelectedService(Service finalSelectedService) {
        this.finalSelectedService = finalSelectedService;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalProductCharge() {
        return totalProductCharge;
    }

    public void setTotalProductCharge(int totalProductCharge) {
        this.totalProductCharge = totalProductCharge;
    }

    public void removeFinalSelectedService() {
        this.finalSelectedService = null;
        if(serviceAdapter != null){
            serviceAdapter.notifyDataSetChanged();
        }
    }

    public listServiceFragment() {
    }

    public static listServiceFragment newInstance(String param1, String param2) {
        listServiceFragment fragment = new listServiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OnFragmentServiceSelectedListener getServiceSelectedListener() {
        return serviceSelectedListener;
    }

    public void setServiceSelectedListener(OnFragmentServiceSelectedListener serviceSelectedListener) {
        this.serviceSelectedListener = serviceSelectedListener;
    }

    public Service getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(Service selectedService) {
        this.selectedService = selectedService;
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
        View view = inflater.inflate(R.layout.service_list_fragment, container, false);
        serviceRecyclerView = (RecyclerView) view.findViewById(R.id.servicesRecyclerView);
        serviceRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        serviceRecyclerView.setLayoutManager(linearLayoutManager);
        customNavigationBarView = view.findViewById(R.id.serviceNavigation);


        if(!prepared){
            ServiceHelper serviceHelper = new ServiceHelper(getContext());
            servList = new ArrayList<>();
            servList = ServiceHelper.getAllServices();
        }

        serviceAdapter = new ServicesAdapter(servList, this.getContext(), this, new ServicesAdapter.ServiceSelectedListener() {
            @Override
            public void onServiceSelected(Service svr) {
                selectedService = svr;
                triggerServiceSelectedListener(selectedService);
            }
        });
        serviceRecyclerView.setAdapter(serviceAdapter);

        customNavigationBarView.setNavigationBarBackButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceRecyclerView.smoothScrollToPosition(0);
            }
        });

        customNavigationBarView.setNavigationBarForwardButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                serviceRecyclerView.smoothScrollToPosition(currPosition+1);
            }
        });

        return view;
    }

    public void setServiceRecyclerViewToStart(){
        if(serviceRecyclerView != null){
            serviceRecyclerView.scrollToPosition(0);
            serviceRecyclerView.setScrollX(0);

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void refreshEverything(){
        if(!prepared){
            ServiceHelper serviceHelper = new ServiceHelper(getContext());
            servList = new ArrayList<>();
            servList = ServiceHelper.getAllServices();
        }

        serviceAdapter = new ServicesAdapter(servList, this.getContext(), this, new ServicesAdapter.ServiceSelectedListener() {
            @Override
            public void onServiceSelected(Service svr) {
                selectedService = svr;
                triggerServiceSelectedListener(selectedService);
            }
        });
        serviceRecyclerView.setAdapter(serviceAdapter);
    }

    public void addServices(ArrayList<Service> serviceList){
        servList.clear();

        if(getFinalSelectedService() != null){
            servList.add(getFinalSelectedService());
            for(Service currService:serviceList){
                if(currService.getServID() != getFinalSelectedService().getServID()){
                    this.servList.add(currService);
                }
            }
        }else{
            servList.addAll(serviceList);
        }

        if(serviceAdapter != null){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    serviceAdapter.notifyDataSetChanged();
                }
            });
        }
        prepared = true;
    }

    public void triggerServiceSelectedListener(Service selectedService){
        serviceSelectedListener.onFragmentServiceSelected(selectedService);
    }

    public interface OnFragmentServiceSelectedListener {
        void onFragmentServiceSelected(Service selectedService);
    }

}
