package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eodhuno.ebelle_test.database_objects.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class PurchaseProducts extends DialogFragment {

    ListView productList;
    Button purchaseButton;
    CheckedTextView purchaseCheckedTextView;
    ImageButton backBtn;

    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ArrayList<Integer> selectedProductIDs = new ArrayList<Integer>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnProductSelectedListener mProductSelectedListener;

    public OnProductSelectedListener getmProductSelectedListener() {
        return mProductSelectedListener;
    }

    public void setmProductSelectedListener(OnProductSelectedListener mProductSelectedListener) {
        this.mProductSelectedListener = mProductSelectedListener;
    }

    public PurchaseProducts() {
    }

    public static PurchaseProducts newInstance(String param1, String param2) {
        PurchaseProducts fragment = new PurchaseProducts();
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
        View view = inflater.inflate(R.layout.fragment_purchase_products, container, false);

        purchaseButton = view.findViewById(R.id.purchaseProductsBtn);
        purchaseCheckedTextView = view.findViewById(R.id.purchaseProductCheckBox);
        productList = view.findViewById(R.id.purchaseproductList);
        backBtn = view.findViewById(R.id.purchaseBackButton);
        productList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        String[] productStrings = new String[productArrayList.size()];
        int index = 0;
        for(Product product:productArrayList){
            String currProductString = "";
            currProductString = currProductString + product.getProd_Name() + " - ksh " + product.getPrice();
            productStrings[index] = currProductString;
            ++index;
        }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.purchase_product_row_layout,R.id.purchaseProductCheckBox,productStrings);
            productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = ((TextView)view).getText().toString();
                //Toast.makeText(getContext(),selectedItem,Toast.LENGTH_SHORT).show();
                //Checks which products have been selected
                if(selectedProductIDs.contains(position)){
                    selectedProductIDs.remove(selectedProductIDs.indexOf(position));
                }else{
                    selectedProductIDs.add(position);
                }

                Toast.makeText(getContext(),selectedProductIDs.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerOnProductSelectedListener();
                getDialog().dismiss();
            }
        });
        return view;
    }

    public void setProducts(ArrayList<Product> products){
        productArrayList.clear();
        productArrayList.addAll(products);
        String[] productStrings = new String[productArrayList.size()];
        int index = 0;
        for(Product product:productArrayList){
            String currProductString = "";
            currProductString = currProductString + product.getProd_Name() + " - " + product.getPrice();
            productStrings[index] = currProductString;
            ++index;
        }

    }

    private void triggerOnProductSelectedListener(){
        ArrayList<Product> selectedProducts = new  ArrayList<Product>();
        for(int id:selectedProductIDs){
            selectedProducts.add(productArrayList.get(id));

        }
        if (mProductSelectedListener != null){
            mProductSelectedListener.onProductSelected(selectedProducts);
        }
    }

    public interface OnProductSelectedListener {
        void onProductSelected(ArrayList<Product> productSelected);
    }
}
