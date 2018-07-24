package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

import helper.ServiceHelper;


public class AddEmployeeFragement extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

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
    public AddEmployeeFragement() {
        // Required empty public constructor
    }

    public static AddEmployeeFragement newInstance(String param1, String param2) {
        AddEmployeeFragement fragment = new AddEmployeeFragement();
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
        final View view = inflater.inflate(R.layout.fragment_add_employee_fragement, container, false);

        mDatabase = new DatabaseManager(getContext());
        serviceHelper = new ServiceHelper(getContext());

        empFirstName = (EditText) view.findViewById(R.id.NavEmpFirstName);
        empLastName = (EditText) view.findViewById(R.id.NavEmpLastName);
        empContact = (EditText) view.findViewById(R.id.NavEmpContactNo_editText);
        empEmail = (EditText) view.findViewById(R.id.NavEmpEmail_editText);
        empMale = (RadioButton) view.findViewById(R.id.NavMaleRadioButton);
        empFemale = (RadioButton) view.findViewById(R.id.NavFemaleRadioButton);
        genderRadioGroup = (RadioGroup) view.findViewById(R.id.NavGenderRadioGroup);
        empSalaryMethod = (Spinner) view.findViewById(R.id.NavSalaryMethod_Spinner);
        empServices = (Spinner) view.findViewById(R.id.NavEmpServices_spinner);
        empProfilePicture = (ImageView) view.findViewById(R.id.NavEmpProfilePicture);
        salonAccessRightGroup = (RadioGroup) view.findViewById(R.id.NavempAccessRightGroup);
        mNoAccessRight = (RadioButton) view.findViewById(R.id.NavnoAccessRight);
        mReceptionistAccessRight = (RadioButton) view.findViewById(R.id.NavreceptionistAccessRight);
        mFinanceAccessRight = (RadioButton) view.findViewById(R.id.NavfinanceAccessRight);

        mNoAccessRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        mReceptionistAccessRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        mFinanceAccessRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        view.findViewById(R.id.NavaddEmployee_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
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

            }
        });

        ((Spinner)view.findViewById(R.id.NavEmpServices_spinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedService = empServices.getItemAtPosition(position).toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((Spinner)view.findViewById(R.id.NavSalaryMethod_Spinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                salaryMethod = empSalaryMethod.getSelectedItem().toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadSpinnerDataFromDB();



        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedID = group.getCheckedRadioButtonId();
                RadioButton rGender = (RadioButton)view.findViewById(selectedID);
                gender = rGender.getText().toString();
            }
        });
        return view;
    }

    private void addNewEmployee() {
        //byte [] newEmpImage = imageToByte(empProfilePicture);
        int empImage = 0;
        String newEmpFname = empFirstName.getText().toString().trim();
        String newEmpLname = empLastName.getText().toString().trim();
        String newEmpContact = empContact.getText().toString().trim();
        String newEmpEmail = empEmail.getText().toString().trim();

        ArrayList<Service> services = new ArrayList<Service>();
        try {
            services = ServiceHelper.getServiceByName(selectedService);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Service serv : services) {
            Empservices = serv.getServID();
        }

        if (newEmpFname.isEmpty()) {
            empFirstName.setError("First name cannot be empty");
            empFirstName.requestFocus();
            return;
        }
        if (newEmpLname.isEmpty()) {
            empLastName.setError("Last name cannot be empty");
            empLastName.requestFocus();
            return;
        }
        if (newEmpContact.isEmpty()) {
            empContact.setError("Contact cannot be empty");
            empContact.requestFocus();
            return;
        }
        if (newEmpEmail.isEmpty()) {
            empEmail.setError("Email cannot be empty");
            empEmail.requestFocus();
            return;
        }

        if (mDatabase.addEmployeeProfile(empImage, newEmpFname, newEmpLname, gender, Integer.parseInt(newEmpContact), newEmpEmail, Empservices, 3, salaryMethod, Integer.parseInt(newEmpAccessRight), 0)) {
            Log.d("EMPLOYEE RIGHT: ", " " + Integer.parseInt(newEmpAccessRight));
            Toast.makeText(getContext(), "Employee profile created successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Employee not created successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean isChecked = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.NavnoAccessRight:
                if(isChecked)
                    newEmpAccessRight = String.valueOf(0);
                break;
            case R.id.NavreceptionistAccessRight:
                if(isChecked)
                    newEmpAccessRight = String.valueOf(1);
                break;
            case R.id.NavfinanceAccessRight:
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


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,currEmpServices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        empServices.setAdapter(adapter);

    }


}
