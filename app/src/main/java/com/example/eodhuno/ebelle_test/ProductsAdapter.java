package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Product;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{

    public List<Product> productListItems;
    Context sCtx;
    Product selectedProduct = null;
    ProductSelectedListener listener;

    public ProductsAdapter(List<Product> productListItems, Context sCtx, ProductSelectedListener lis) {
        this.productListItems = productListItems;
        this.sCtx = sCtx;
        this.listener = lis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productListItems.get(position);

        holder.productCustomWidget.setBigText(0,0,product.getProd_Name());
        //holder.apptCustomWidget.setSmallImage(1,0,R.drawable);
        holder.productCustomWidget.setSmallText(1,1,product.getProdDescr());
        holder.productCustomWidget.setBigText(2,0,""+product.getPrice());
    }

    @Override
    public int getItemCount() {
        return productListItems.size();
    }

    private void triggerProductSelectedListener(Product product){
        listener.onProductSelected(product);
    }

    public interface ProductSelectedListener{
        public void onProductSelected(Product selectedProduct);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CustomWidget productCustomWidget;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    Log.d("AAAAAAAAAAAAA","HERE");
                    triggerProductSelectedListener(productListItems.get(id));
                }
            });

            productCustomWidget = (CustomWidget) itemView.findViewById(R.id.productListCustomItem);
            productCustomWidget.removeTitle();
            productCustomWidget.removeProfilePic();
            productCustomWidget.removeTitleDescription();
            productCustomWidget.removeInfoButton();
            productCustomWidget.removeDeleteButton();
            productCustomWidget.removeEditButton();
            productCustomWidget.removeShrinkButton();
            productCustomWidget.addColumnSpans(new int[]{0,0,1,3},
                    new int[]{1,0,1,1},
                    new int[]{1,1,1,2},
                    new int[]{2,0,1,3});

            productCustomWidget.addViews(1,
                    2,0,
                    1);
            productCustomWidget.addAlignement(0,0, "left");
            productCustomWidget.addAlignement(1,0, "left");
            productCustomWidget.addAlignement(1,1, "left");
            productCustomWidget.addAlignement(2,0, "right");
        }

        /**@Override
        public void onClick(View v) {

        }**/
    }

}

