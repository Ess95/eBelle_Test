package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;
import java.util.List;

import helper.ProductHelper;
import helper.ServiceHelper;

public class listProductFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RecyclerView productRecyclerView;
    public RecyclerView.Adapter productAdapter;
    public LinearLayout containerLayout;

    private Product selectedProduct = null;

    Boolean prepared = false;

    private OnFragmentProductSelectedListener productSelectedListener;

    List<Product> productList = new ArrayList<Product>();
    Context mainContext;

    public listProductFragment() {
    }

    public static listProductFragment newInstance(String param1, String param2) {
        listProductFragment fragment = new listProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OnFragmentProductSelectedListener getProductSelectedListener() {
        return productSelectedListener;
    }

    public void setProductSelectedListener(OnFragmentProductSelectedListener productSelectedListener) {
        this.productSelectedListener = productSelectedListener;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
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
        View view = inflater.inflate(R.layout.product_list_fragment, container, false);
        productRecyclerView = (RecyclerView) view.findViewById(R.id.productsRecyclerView);
        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false));

        if(!prepared){
            Log.d("HERE222222","222222222222222");
            ProductHelper productHelper = new ProductHelper(getContext());
            productList = new ArrayList<>();
            productList = ProductHelper.getAllProducts();
        }

        productAdapter = new ProductsAdapter(productList, this.getContext(), new ProductsAdapter.ProductSelectedListener() {
            @Override
            public void onProductSelected(Product product) {
                selectedProduct = product;
                triggerProductSelectedListener(selectedProduct);
            }
        });
        productRecyclerView.setAdapter(productAdapter);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener { void onFragmentInteraction(Uri uri);}

    public void addProducts(ArrayList<Product> productList){
        productList.clear();
        productList.addAll(productList);
        if(productAdapter != null){
            productAdapter.notifyDataSetChanged();
            prepared = true;
        }else{
            Log.d("HERE3333333","333333333333");
            prepared = true;
        }
    }

    public void triggerProductSelectedListener(Product selectedProduct){
        productSelectedListener.onFragmentProductSelected(selectedProduct);
    }

    public interface OnFragmentProductSelectedListener {
        void onFragmentProductSelected(Product selectedProduct);
    }

}
