package com.example.eodhuno.ebelle_test;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import helper.ServiceHelper;

public class EmployeeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText empFirstName, empLastName, empContact,empEmail;
    Spinner empSalaryMethod,empServices;
    ImageView empProfilePicture;
    RadioGroup salonAccessRightGroup, genderRadioGroup;
    RadioButton mReceptionistAccessRight, mFinanceAccessRight, mNoAccessRight, empMale, empFemale;

    DatabaseManager mDatabase;
    ServiceHelper serviceHelper;
    String  salaryMethod, gender, newEmpAccessRight,selectedService;
    int Empservices;

    final int TAKE_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_employee_profile);

        mDatabase = new DatabaseManager(this);
        serviceHelper = new ServiceHelper(this);

        empFirstName = (EditText) findViewById(R.id.EmpFirstName);
        empLastName = (EditText) findViewById(R.id.EmpLastName);
        empContact = (EditText) findViewById(R.id.EmpContactNo_editText);
        empEmail = (EditText) findViewById(R.id.EmpEmail_editText);
        empMale = (RadioButton) findViewById(R.id.maleRadioButton);
        empFemale = (RadioButton) findViewById(R.id.femaleRadioButton);
        genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        empSalaryMethod = (Spinner) findViewById(R.id.SalaryMethod_Spinner);
        empServices = (Spinner) findViewById(R.id.EmpServices_spinner);
        empProfilePicture = (ImageView) findViewById(R.id.EmpProfilePicture);
        salonAccessRightGroup = (RadioGroup) findViewById(R.id.empAccessRightGroup);
        mNoAccessRight = (RadioButton) findViewById(R.id.noAccessRight);
        mReceptionistAccessRight = (RadioButton) findViewById(R.id.receptionistAccessRight);
        mFinanceAccessRight = (RadioButton) findViewById(R.id.financeAccessRight);

        findViewById(R.id.addEmployee_button).setOnClickListener(this);

        ((Spinner) findViewById(R.id.EmpServices_spinner)).setOnItemSelectedListener(this);
        ((Spinner) findViewById(R.id.SalaryMethod_Spinner)).setOnItemSelectedListener(this);


        loadSpinnerDataFromDB();

        empProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(EmployeeActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, TAKE_PICTURE);
                }
        });
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedID = group.getCheckedRadioButtonId();
                RadioButton rGender = (RadioButton)findViewById(selectedID);
                gender = rGender.getText().toString();
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addEmployee_button:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage("Are you sure you want to save this employee?");
                alertDialog.setCancelable(true);

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addNewEmployee();
                        dialog.cancel();
                    }
                });

                alertDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedService = empServices.getItemAtPosition(position).toString().trim();
        salaryMethod = empSalaryMethod.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        empSalaryMethod.setEnabled(true);
        empServices.setEnabled(true);
    }

   /** private byte [] imageToByte(ImageView empProfilePicture){
        try {
            Bitmap bitmap = ((BitmapDrawable) empProfilePicture.getDrawable()).getBitmap();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            return byteArray;

        }catch (ClassCastException e){
            Toast.makeText(this,"Please add a photo",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        Log.d("REQUEST PERMISSION","HEEEEEEEEEEEEEEEEERE");
        if(requestCode == TAKE_PICTURE) {
            Log.d("REQUEST PERMISSION2","HEEEEEEEEEEEEEEEEERE");

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,TAKE_PICTURE);
                }
                else{
                Toast.makeText(this,"NOOOOO",Toast.LENGTH_LONG).show();
                }
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("onACTIVITY RESULT","HEEEEEEEEEEEEEEEEERE");

        if(requestCode == TAKE_PICTURE && resultCode == RESULT_OK && data != null){

            Uri uri = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                empProfilePicture.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }**/

    private void addNewEmployee() {
        //byte [] newEmpImage = imageToByte(empProfilePicture);
        int empImage = 0;
        String newEmpFname = empFirstName.getText().toString().trim();
        String newEmpLname = empLastName.getText().toString().trim();
        String newEmpContact= empContact.getText().toString().trim();
        String newEmpEmail= empEmail.getText().toString().trim();

        ArrayList<Service> services = new ArrayList<Service>();
        try {
            services = ServiceHelper.getServiceByName(selectedService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Service serv:services){
            Empservices = serv.getServID();
        }


        if(newEmpFname.isEmpty()){
            empFirstName.setError("First name cannot be empty");
            empFirstName.requestFocus();
            return;
        }
        if(newEmpLname.isEmpty()){
            empLastName.setError("Last name cannot be empty");
            empLastName.requestFocus();
            return;
        }
        if(newEmpContact.isEmpty()){
            empContact.setError("Contact cannot be empty");
            empContact.requestFocus();
            return;
        }
        if(newEmpEmail.isEmpty()){
            empEmail.setError("Email cannot be empty");
            empEmail.requestFocus();
            return;
        }

        /**if (newEmpImage == null) {
            empProfilePicture.requestFocus();
            return;
        }**/

        if(mDatabase.addEmployeeProfile(empImage,newEmpFname,newEmpLname,gender,Integer.parseInt(newEmpContact),newEmpEmail,Empservices,3,salaryMethod,Integer.parseInt(newEmpAccessRight),0)){
            Log.d("EMPLOYEE RIGHT: ", " "+ Integer.parseInt(newEmpAccessRight));
            Toast.makeText(this,"Employee profile created successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Employee not created successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean isChecked = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.noAccessRight:
                if(isChecked)
                    newEmpAccessRight = String.valueOf(0);
                break;
            case R.id.receptionistAccessRight:
                if(isChecked)
                    newEmpAccessRight = String.valueOf(1);
                break;
            case R.id.financeAccessRight:
                if(isChecked)
                    newEmpAccessRight = String.valueOf(2);
                break;
        }
    }

    private void loadSpinnerDataFromDB(){
        ArrayList<Service> services = ServiceHelper.getAllServices();
        ArrayList<String> currEmpServices = new ArrayList<String>();

        for(Service service:services){
            currEmpServices.add(service.getServ_Name());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,currEmpServices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        empServices.setAdapter(adapter);

    }
}

