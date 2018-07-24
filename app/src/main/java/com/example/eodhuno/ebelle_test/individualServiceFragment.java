package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;

import helper.ProductHelper;
import helper.ServiceHelper;


public class individualServiceFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Service currentService = null;

    CustomWidget customCard;
    ServiceHelper serviceHelper;
    ProductHelper productHelper;
    RelativeLayout individualServiceContainer;

    int total = 0;
    int totalProductCharge = 0;

    ArrayList<Product> allProducts = new ArrayList<Product>();
    ArrayList<Integer> checkBoxRows =  new ArrayList<Integer>();
    int buttonRow = 6;

    private OnBackButtonListener onBackButtonListener;

    private OnServiceSelectedListener onServiceSelectedListener;

    public individualServiceFragment() {
        // Required empty public constructor
    }

    public static individualServiceFragment newInstance(String param1, String param2) {
        individualServiceFragment fragment = new individualServiceFragment();
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

    public OnServiceSelectedListener getOnServiceSelectedListener() {
        return onServiceSelectedListener;
    }

    public void setOnServiceSelectedListener(OnServiceSelectedListener onServiceSelectedListener) {
        this.onServiceSelectedListener = onServiceSelectedListener;
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
        View view = inflater.inflate(R.layout.individual_service_fragment, container, false);
        serviceHelper = new ServiceHelper(this.getContext());
        productHelper = new ProductHelper(this.getContext());
        individualServiceContainer = (RelativeLayout)view.findViewById(R.id.individualServiceContainer);

        customCard = view.findViewById(R.id.individualServiceListCustomItem);
        customCard.removeTitle();
        customCard.removeProfilePic();
        customCard.removeTitleDescription();
        customCard.removeInfoButton();
        customCard.removeDeleteButton();
        customCard.removeEditButton();
        customCard.removeShrinkButton();
        customCard.setColumnNumber(3);

        ArrayList<Integer> views = new ArrayList<Integer>();
        views.add(18);
        views.add(2);
        views.add(1); views.add(0);
        views.add(1); views.add(0);
        views.add(1); views.add(0);
        views.add(1);

        customCard.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,1,1,2},
                new int[]{3,1,1,2},
                new int[]{4,1,1,2},
                new int[]{5,1,1,2},
                new int[]{6,1,1,2},
                new int[]{7,0,1,3},
                new int[]{8,1,1,2},
                new int[]{9,0,1,3});

        customCard.addViews(18,
                2,
                1,0,
                1,0,
                1,0,
                1,0,
                1,0,
                22,
                1,0,
                13);
        //customCard.addVerticalBordersToRightOf(new int[]{2,0,7});

        customCard.setBigText(2,0,"Name :");
        customCard.addAlignement(2,0,"left");
        customCard.setSmallText(2,1,"Braiding");
        customCard.addAlignement(2,1,"left");
        customCard.setBigText(3,0,"Price :");
        customCard.addAlignement(3,0,"left");
        customCard.setSmallText(3,1,"ksh 1250");
        customCard.addAlignement(3,1,"left");
        customCard.setBigText(4,0,"Description :");
        customCard.addAlignement(4,0,"left");
        customCard.setSmallText(4,1,"fsdfsd fdssdf dfaad sfsd dsf dfsfsd dsff sdfsd fdsf");
        customCard.addAlignement(4,1,"left");
        customCard.setBigText(5,0,"Products :");
        customCard.addAlignement(5,0,"left");
        customCard.setSmallText(5,1,"fdsfsd sdfjndsf dsfkjnsdf sdjfnsdf sdf");
        customCard.addAlignement(5,1,"left");
        customCard.setBigText(6,0,"Products price:");
        customCard.addAlignement(6,0,"left");
        customCard.setSmallText(6,1,"ksh 0");
        customCard.addAlignement(6,1,"left");
        customCard.setIconText(7,0,"tap to purchase products");
        customCard.getIconTextTextView(7,0).setTextColor(getResources().getColor(R.color.ebellePurple));
        customCard.setBigText(8,0,"TOTAL :");
        customCard.addAlignement(8,0,"left");
        customCard.setSmallText(8,1,"ksh 0");
        customCard.addAlignement(8,1,"left");
        customCard.setButtonText(9,0,"Select Service");


        customCard.addFullHorizontalBorders(4,7);
        customCard.setNavigationBarForwardButtonVisibility(0,0,false);

        customCard.setNavigationBarBackButtonListener(0, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerOnBackButtonListener();
            }
        });

        final PurchaseProducts purchaseProducts = new PurchaseProducts();
        customCard.setIconTextImageOnClickListener(7, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseProducts.setProducts(allProducts);
                purchaseProducts.show(getFragmentManager(),"Ebelle");
            }
        });

        customCard.getIconTextTextView(7,0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purchaseProducts.setProducts(allProducts);
                purchaseProducts.show(getFragmentManager(),"Ebelle");
            }
        });
        purchaseProducts.setmProductSelectedListener(new PurchaseProducts.OnProductSelectedListener() {
            @Override
            public void onProductSelected(ArrayList<Product> productSelected) {
                int totalProductPrice = 0;
                int currtotal = currentService != null ? currentService.getPrice() : 0 ;
                for(Product product:productSelected){
                    totalProductPrice += product.getPrice();
                }
                currtotal += totalProductPrice;
                customCard.setSmallText(6,1,"ksh "+totalProductPrice);
                customCard.setSmallText(8,1,"ksh "+currtotal);

                totalProductCharge = totalProductPrice;
                total = currtotal;
            }

        });

        customCard.setIconTextImage(7,0, R.drawable.add_to_cart);



        customCard.getButton(9,0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("wwwwwwwwww","HERE");
                triggerOnServiceSelectedListener();
            }
        });
        addService();


        return view;


    }

    public Service getCurrentService() {
        return currentService;
    }

    public void setCurrentService(Service currentService) {
        this.currentService = currentService;
        total = currentService.getPrice();
    }

    public void addService(){
        if(currentService != null){
            customCard.setSmallText(2,1,currentService.getServ_Name());
            customCard.setSmallText(3,1,"ksh "+currentService.getPrice());
            customCard.setSmallText(4,1,currentService.getServ_Description());

            ArrayList<Product> products = new ArrayList<Product>();
            try {
                products = ProductHelper.getProductsByServiceID(currentService.getServID());

            } catch (Exception e) {
                e.printStackTrace();
            }

            String productsString = "";

            allProducts.clear();
            for(Product prod:products){
                allProducts.add(prod);
                productsString += (prod.getProd_Name()+"\n");
            }
            customCard.setSmallText(5,1,productsString);
        }

    }

    public void triggerOnBackButtonListener(){
        onBackButtonListener.onBackButtonPressed();
    }

    public void triggerOnServiceSelectedListener(){
        onServiceSelectedListener.onServiceSelected(currentService,totalProductCharge,total);
        triggerOnBackButtonListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnBackButtonListener {
        void onBackButtonPressed();
    }

    public interface OnServiceSelectedListener {

        void onServiceSelected(Service currService, int totalProductCharge, int total);

    }
}
