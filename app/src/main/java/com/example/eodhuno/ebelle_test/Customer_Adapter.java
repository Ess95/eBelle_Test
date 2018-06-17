package com.example.eodhuno.ebelle_test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eodhuno.ebelle_test.database_objects.Customer;

import java.util.List;

public class Customer_Adapter extends ArrayAdapter<Customer> {

    Context ctx;
    int layoutResID;
    List<Customer> customerList;
    DatabaseManager mDatabase;


    public Customer_Adapter(Context ctx, int layoutResID, List<Customer> customerList, DatabaseManager mDatabase){
        super(ctx,layoutResID,customerList);

        this.ctx = ctx;
        this.layoutResID = layoutResID;
        this.customerList = customerList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    //This method binds data to the view and returns the view
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);

        View view = layoutInflater.inflate(layoutResID,null);

        TextView textViewFname = view.findViewById(R.id.textViewFirstName);
        TextView textViewContact = view.findViewById(R.id.textViewContact);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);

        final Customer customer = customerList.get(position);

        textViewFname.setText(customer.getFirstName());
        textViewContact.setText(String.valueOf(customer.getContact()));
        textViewEmail.setText(customer.getEmail());

        view.findViewById(R.id.button_Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCustomerProfile(customer);
            }
        });

        view.findViewById(R.id.button_Edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCustomerProfile(customer);
            }
        });

        return view;

    }

    private void updateCustomerProfile(final Customer customer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.update_customer_profile, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextFname = view.findViewById(R.id.CustFname_et);
        final EditText editTextLname = view.findViewById(R.id.CustLname_et);
        final EditText editTextGender = view.findViewById(R.id.Gender_et);
        final EditText editTextContact = view.findViewById(R.id.Contact_et);
        final EditText editTextEmail = view.findViewById(R.id.Email_et);
        final EditText editTextPassword = view.findViewById(R.id.Password_et);


        editTextFname.setText(customer.getFirstName());
        editTextContact.setText(String.valueOf(customer.getContact()));
        editTextEmail.setText(customer.getEmail());

        view.findViewById(R.id.button_UpdateCustomerProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedFName = editTextFname.getText().toString().trim();
                String updatedLName = editTextLname.getText().toString().trim();
                String updatedGender = editTextGender.getText().toString().trim();
                String updatedContact = editTextContact.getText().toString().trim();
                String updatedEmail = editTextEmail.getText().toString().trim();
                String updatedPassword = editTextPassword.getText().toString().trim();

                if(updatedFName.isEmpty()){
                    editTextFname.setError("Field cannot be empty");
                    editTextContact.requestFocus();
                    return;
                }
                if(updatedContact.isEmpty()){
                    editTextContact.setError("Field cannot be empty");
                    editTextContact.requestFocus();
                    return;
                }
                if(updatedEmail.isEmpty()){
                    editTextEmail.setError("Field cannot be empty");
                    editTextEmail.requestFocus();
                    return;
                }
                //If all validations are passed, allow update of new values to the database
                if(mDatabase.updateCustomer(customer.getCustID(),updatedFName, updatedLName, updatedGender, Integer.parseInt(updatedContact),updatedEmail,updatedPassword)) {
                    Toast.makeText(ctx, "Profile updated successfully", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(ctx, "Update unsuccessfully", Toast.LENGTH_LONG).show();

                reloadCustomerProfiles();
                alertDialog.dismiss();
            }

    });
    }
    private void reloadCustomerProfiles() {
        String sql = "SELECT * FROM customers";
        Cursor cursor = mDatabase.readAllCustomerProfiles();

        if (cursor.moveToFirst()) {
            customerList.clear();
            //Reloads updated records from the database to the ArrayList
            do {
                customerList.add(new Customer(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));
            }
            while (cursor.moveToNext());
            notifyDataSetChanged();

        }
    }

    private void deleteCustomerProfile(final Customer customer){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("Are you sure you want to delete " + customer.getFirstName().toString() +"'s profile?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(mDatabase.deleteCustomer(customer.getCustID())){
                    Toast.makeText(ctx,"Profile deleted successfully",Toast.LENGTH_LONG).show();
                };
                reloadCustomerProfiles();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
