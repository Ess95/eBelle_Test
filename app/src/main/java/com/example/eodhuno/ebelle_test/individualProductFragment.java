package com.example.eodhuno.ebelle_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;

import helper.ProductHelper;
import helper.ServiceHelper;


public class individualProductFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Product currentProduct = null;

    CustomWidget productCustomWidget;
    ServiceHelper serviceHelper;
    ProductHelper productHelper;
    RelativeLayout individualProductContainer;

    private OnBackButtonListener onBackButtonListener;

    public individualProductFragment() {
        // Required empty public constructor
    }

    public static individualProductFragment newInstance(String param1, String param2) {
        individualProductFragment fragment = new individualProductFragment();
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
        View view = inflater.inflate(R.layout.individual_product_fragment, container, false);

        serviceHelper = new ServiceHelper(this.getContext());
        productHelper = new ProductHelper(this.getContext());
        individualProductContainer = (RelativeLayout)view.findViewById(R.id.individualProductContainer);

        productCustomWidget = view.findViewById(R.id.individualProductListCustomItem);
        productCustomWidget.removeTitle();
        productCustomWidget.removeProfilePic();
        productCustomWidget.removeTitleDescription();
        productCustomWidget.removeInfoButton();
        productCustomWidget.removeDeleteButton();
        productCustomWidget.removeEditButton();
        productCustomWidget.removeShrinkButton();
        productCustomWidget.setColumnNumber(3);

        productCustomWidget.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,1,1,2},
                new int[]{3,1,1,2},
                new int[]{4,1,1,2},
                new int[]{5,1,1,2},
                new int[]{6,0,1,2});

        productCustomWidget.addViews(18,
                2,
                1,0,
                1,0,
                1,0,
                1,0,
                0,13);
        productCustomWidget.addVerticalBordersToRightOf(new int[]{2,0,4});

        productCustomWidget.setBigText(2,0,"Name :");
        productCustomWidget.addAlignement(2,0,"left");
        productCustomWidget.setSmallText(2,1,"Braiding");
        productCustomWidget.addAlignement(2,1,"left");
        productCustomWidget.setBigText(3,0,"Price :");
        productCustomWidget.addAlignement(3,0,"left");
        productCustomWidget.setSmallText(3,1,"1250");
        productCustomWidget.addAlignement(3,1,"left");
        productCustomWidget.setBigText(4,0,"Description :");
        productCustomWidget.addAlignement(4,0,"left");
        productCustomWidget.setSmallText(4,1,"fsdfsd fdssdf dfaad sfsd dsf dfsfsd dsff sdfsd fdsf");
        productCustomWidget.addAlignement(4,1,"left");
        productCustomWidget.setSmallText(5,0,"purchase product");
        productCustomWidget.addAlignement(5,0,"right");
        productCustomWidget.setButtonText(5,2,"add to cart");

        productCustomWidget.addFullHorizontalBorders(4);
        productCustomWidget.addVerticalBordersToRightOf(new int[]{2,0,4});
        productCustomWidget.setNavigationBarForwardButtonVisibility(0,0,false);

        productCustomWidget.setNavigationBarBackButtonListener(0, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerOnBackButtonListener();
            }
        });
        addProduct();
        return view;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }

    public void addProduct(){
        if(currentProduct != null){
            productCustomWidget.setSmallText(2,1,currentProduct.getProd_Name());
            productCustomWidget.setSmallText(3,1,""+currentProduct.getPrice());
            productCustomWidget.setSmallText(4,1,currentProduct.getProdDescr());

        }

    }

    public void triggerOnBackButtonListener(){
        onBackButtonListener.onBackButtonPressed();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnBackButtonListener {
        void onBackButtonPressed();
    }
}
