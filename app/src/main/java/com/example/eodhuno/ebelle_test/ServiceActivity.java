package com.example.eodhuno.ebelle_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText ServiceName, ServiceDescr, ServicePrice, ServiceDuration;
    Spinner ServCategory;
    Switch ServAvailability;

    DatabaseManager mDatabase;

    int serviceAvailability;
    String serviceCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_service);

        mDatabase = new DatabaseManager(this);

        ServiceName = (EditText) findViewById(R.id.Service_name_edit_text);
        ServiceDescr = (EditText) findViewById(R.id.Service_description_edit_text);
        ServicePrice = (EditText) findViewById(R.id.service_price_edit_text);
        ServiceDuration = (EditText) findViewById(R.id.service_time_edit_text);
        ServCategory = (Spinner) findViewById(R.id.service_category_spinner);
        ServAvailability = (Switch) findViewById(R.id.Availability_switch);

        findViewById(R.id.button2).setOnClickListener(this);
        ((Spinner) findViewById(R.id.service_category_spinner)).setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                addNewService();

                ServiceName.setText("");
                ServiceDescr.setText("");
                ServicePrice.setText("");
                ServiceDuration.setText("");
                break;
                //check for the default value of the spinner and the switch
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        serviceCategory = ServCategory.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void addNewService(){
        String serviceName = ServiceName.getText().toString();
        String serviceDescr = ServiceDescr.getText().toString();
        int servDuration = Integer.parseInt(ServiceDuration.getText().toString());
        int servicePrice = Integer.parseInt(ServicePrice.getText().toString());

        if(serviceName.isEmpty()){
            ServiceName.setError("Product name cannot be empty");
            ServiceName.requestFocus();
            return;
        }
        if(serviceDescr.isEmpty()){
            ServiceDescr.setError("Description cannot be empty");
            ServiceDescr.requestFocus();
            return;
        }
        if(servDuration == 0){
            ServiceDuration.setError("Product quantity cannot be empty");
            ServiceDuration.requestFocus();
            return;
        }
        if(servicePrice == 0){
            ServicePrice.setError("Product Unit Price cannot be empty");
            ServicePrice.requestFocus();
            return;
        }

        if(ServAvailability != null) {
            serviceAvailability = Integer.parseInt(String.valueOf(ServAvailability.isChecked() ? "1" : "0"));

            /**ServAvailability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                }
            });**/
        }


        if(mDatabase.addServices(serviceName,serviceDescr,Integer.parseInt(serviceCategory),servicePrice,servDuration, serviceAvailability)){
            Toast.makeText(this,"Service added successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Service not created successfully", Toast.LENGTH_SHORT).show();
        }

    }
}
