package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

    private Service selectedService = null;

    List<Service> servList = new ArrayList<Service>();
    Context mainContext;

    private OnFragmentInteractionListener mListener;

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
        serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ServiceHelper serviceHelper = new ServiceHelper(getContext());

        servList = new ArrayList<>();
        servList = ServiceHelper.getAllServices();

        /**for(int i=0;i<=10;i++){
            Service service = new Service(
                    + i+1 ,
                    2,
                    "Braiding",
                    "all kinds of braids",
                    2000,
                    ""+2,
                    1
            );
            servList.add(service);
        }**/
        serviceAdapter = new ServicesAdapter(servList, this.getContext(), new ServicesAdapter.ServiceSelectedListener() {
            @Override
            public void onServiceSelected(Service svr) {
                selectedService = svr;
            }
        });
        serviceRecyclerView.setAdapter(serviceAdapter);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void addServices(ArrayList<Service> serviceList){
        servList.clear();
        servList = serviceList;
        serviceAdapter.notifyDataSetChanged();

    }

}
