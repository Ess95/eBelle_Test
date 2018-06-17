package com.example.eodhuno.ebelle_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText ProductName, ProductDescr, ProdQuantity, ProdUnitPrice;
    Spinner ReorderLevel,ProdSevices ;
    Switch ProdAvailability;
    DatabaseManager mDatabase;

    String productServices;
    int productReorderLevel;
    int productAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_product);

        mDatabase = new DatabaseManager(this);

        ProductName = (EditText) findViewById(R.id.Product_name_edit_text);
        ProductDescr = (EditText) findViewById(R.id.Product_description_edit_text);
        ProdQuantity = (EditText) findViewById(R.id.product_quantity_edit_text);
        ProdUnitPrice = (EditText) findViewById(R.id.Product_unitPrice_edit_text_view);
        ReorderLevel = (Spinner) findViewById(R.id.product_reorderLevel_spinner);
        ProdSevices = (Spinner) findViewById(R.id.product_services_spinner);
        ProdAvailability = (Switch) findViewById(R.id.ProdAvailability);

        findViewById(R.id.AddProduct_button).setOnClickListener(this);
        ((Spinner) findViewById(R.id.product_services_spinner)).setOnItemSelectedListener(this);
        ((Spinner) findViewById(R.id.product_reorderLevel_spinner)).setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        productReorderLevel =Integer.parseInt(ReorderLevel.getItemAtPosition(position).toString());
        productServices= ProdSevices.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void addNewProduct(){
        String productName = ProductName.getText().toString();
        String productDescr = ProductDescr.getText().toString();
        int productQty = Integer.parseInt(ProdQuantity.getText().toString());
        int productUnitPrice = Integer.parseInt(ProdUnitPrice.getText().toString());

        if(productName.isEmpty()){
            ProductName.setError("Product name cannot be empty");
            ProductName.requestFocus();
            return;
        }
        if(productDescr.isEmpty()){
            ProductDescr.setError("Description cannot be empty");
            ProductDescr.requestFocus();
            return;
        }
        if(productQty == 0){
            ProdQuantity.setError("Product quantity cannot be empty");
            ProdQuantity.requestFocus();
            return;
        }
        if(productUnitPrice == 0){
            ProdUnitPrice.setError("Product Unit Price cannot be empty");
            ProdQuantity.requestFocus();
            return;
        }

        if(productServices.isEmpty()){
            ProdQuantity.setError("Service cannot be empty");
            ProdQuantity.requestFocus();
            return;
        }

        if(ProdAvailability != null) {
            ProdAvailability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    productAvailable = Integer.parseInt(String.valueOf(ProdAvailability.isChecked() ? ProdAvailability.getTextOn().toString() : ProdAvailability.getTextOff()).toString());
                }
            });
        }

        if(mDatabase.addProducts(productName,productDescr,productQty,productUnitPrice,productReorderLevel,productServices,productAvailable)){
            Toast.makeText(this,"Product added successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Product not created successfully", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AddProduct_button:
                addNewProduct();

                ProductName.setText("");
                ProductDescr.setText("");
                ProdQuantity.setText("");
                ProdUnitPrice.setText("");
                ReorderLevel.setSelection(1);//Change this to the item selected on the spinner!!!
                ProdSevices.setSelection(1);//Change this to the item selected on the spinner!!!
                ProdAvailability.isChecked();
                break;
        }
    }



}
