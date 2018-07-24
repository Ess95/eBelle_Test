package com.example.eodhuno.ebelle_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Customer;
import com.example.eodhuno.ebelle_test.database_objects.Employee;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import helper.EmployeeHelper;
import helper.ServiceHelper;


public class individualCustomerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Customer currentCustomer = null;


    private String mParam1;
    private String mParam2;

    CustomWidget customerCustomWidget;
    RelativeLayout individualCustomerContainer;

    private OnBackButtonListener onBackButtonListener;
    private OnCustomerSelectedListener onCustomerSelectedListener;


    public individualCustomerFragment() {
        // Required empty public constructor
    }


    public static individualCustomerFragment newInstance(String param1, String param2) {
        individualCustomerFragment fragment = new individualCustomerFragment();
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

    public OnCustomerSelectedListener getOnCustomerSelectedListener() {
        return onCustomerSelectedListener;
    }

    public void setOnCustomerSelectedListener(OnCustomerSelectedListener onCustomerSelectedListener) {
        this.onCustomerSelectedListener = onCustomerSelectedListener;
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
        View view = inflater.inflate(R.layout.individual_customer_fragment, container, false);

        individualCustomerContainer = (RelativeLayout)view.findViewById(R.id.individualCustomerContainer);

        customerCustomWidget = view.findViewById(R.id.individualCustomerListCustomItem);
        customerCustomWidget.removeTitle();
        customerCustomWidget.removeProfilePic();
        customerCustomWidget.removeTitleDescription();
        customerCustomWidget.removeInfoButton();
        customerCustomWidget.removeDeleteButton();
        customerCustomWidget.removeEditButton();
        customerCustomWidget.removeShrinkButton();
        customerCustomWidget.setColumnNumber(3);

        customerCustomWidget.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,0,1,3},
                new int[]{3,0,1,3},
                new int[]{4,0,1,3},
                new int[]{5,1,1,2},
                new int[]{6,1,1,2},
                new int[]{7,0,1,3},
                new int[]{8,0,1,3},
                new int[]{9,1,1,2},
                new int[]{10,1,1,2});

        customerCustomWidget.addViews(18,
                1,
                2,
                20,
                1,
                1,0,
                1,0,
                20,
                1,
                1,0,
                1,0);

        customerCustomWidget.setBigText(1,0,"Lilly Mara");
        customerCustomWidget.addAlignement(1,0,"centre");
        customerCustomWidget.setBigText(4,0,"Contact Information");
        customerCustomWidget.addAlignement(4,0,"centre");
        customerCustomWidget.setBigText(5,0,"Mobile No.");
        customerCustomWidget.addAlignement(5,0,"centre");
        customerCustomWidget.setSmallText(5,1,"0789252717");
        customerCustomWidget.addAlignement(5,1,"left");
        customerCustomWidget.setBigText(6,0,"Email");
        customerCustomWidget.addAlignement(6,0,"centre");
        customerCustomWidget.setSmallText(6,1,"LilMara@gmail.com");
        customerCustomWidget.addAlignement(6,1,"left");
        customerCustomWidget.setSmallText(8,0,"Salon Favourites");
        customerCustomWidget.addAlignement(8,0,"centre");
        customerCustomWidget.setBigText(9,0,"Services");
        customerCustomWidget.addAlignement(9,0,"centre");
        customerCustomWidget.setSmallText(9,1,"Cornrows");
        customerCustomWidget.addAlignement(9,1,"left");
        customerCustomWidget.setBigText(10,0,"Products");
        customerCustomWidget.addAlignement(10,0,"centre");
        customerCustomWidget.setSmallText(10,1,"Mizani");
        customerCustomWidget.addAlignement(10,1,"left");

        customerCustomWidget.setNavigationBarForwardButtonVisibility(0,0,false);

        customerCustomWidget.setNavigationBarBackButtonListener(0, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerOnBackButtonListener();
            }
        });
        addCustomer();
        return view;
    }
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) { this.currentCustomer = currentCustomer; }

    public void addCustomer() {
        if(currentCustomer != null){
            customerCustomWidget.setSmallText(1,0,currentCustomer.getFirstName()+" "+currentCustomer.getLastName());
            customerCustomWidget.setSmallText(5,1,"0"+currentCustomer.getContact());
            customerCustomWidget.setSmallText(6,1,""+currentCustomer.getEmail());
        }
    }

    public void triggerOnBackButtonListener(){
        onBackButtonListener.onBackButtonPressed();
    }

    public void triggerOnCustomerSelectedListener(){
        onCustomerSelectedListener.onCustomerSelected(currentCustomer);
        triggerOnBackButtonListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnBackButtonListener {
        void onBackButtonPressed();
    }

    public interface OnCustomerSelectedListener {

        void onCustomerSelected(Customer currCustomer);

    }
}
