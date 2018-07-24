package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Customer;
import com.example.eodhuno.ebelle_test.database_objects.Employee;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>{

    public List<Customer> customerListItems;
    Context sCtx;
    Customer selectedCustomer = null;
    CustomerSelectedListener listener;

    public CustomerAdapter(List<Customer> customerListItems, Context sCtx, CustomerSelectedListener lis) {
        this.customerListItems = customerListItems;
        this.sCtx = sCtx;
        this.listener = lis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer customer = customerListItems.get(position);
        holder.customerCustomWidget.setBigText(0,1,customer.getFirstName() + " " + customer.getLastName());
    }

    @Override
    public int getItemCount() {
        return customerListItems.size();
    }

    private void triggerCustomerSelectedListener(Customer customer){
        listener.onCustomerSelected(customer);
    }

    public interface CustomerSelectedListener{
        public void onCustomerSelected(Customer selectedCustomer);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CustomWidget customerCustomWidget;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    triggerCustomerSelectedListener(customerListItems.get(id));
                }
            });

            customerCustomWidget = (CustomWidget) itemView.findViewById(R.id.customerListCustomItem);
            customerCustomWidget.removeTitle();
            customerCustomWidget.removeProfilePic();
            customerCustomWidget.removeTitleDescription();
            customerCustomWidget.removeInfoButton();
            customerCustomWidget.removeDeleteButton();
            customerCustomWidget.removeEditButton();
            customerCustomWidget.removeShrinkButton();
            customerCustomWidget.setColumnNumber(3);
            customerCustomWidget.addColumnSpans(new int[]{0,0,1,1},
                    new int[]{0,1,1,2});

            customerCustomWidget.addViews(2,0);

            customerCustomWidget.addAlignement(0,0, "left");
            customerCustomWidget.addAlignement(0,1, "left");
        }


    }

}

