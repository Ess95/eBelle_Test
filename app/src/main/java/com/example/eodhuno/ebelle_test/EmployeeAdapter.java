package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eodhuno.ebelle_test.database_objects.Employee;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    Context ctx;
    int layoutResID;
    List<Employee> employeeList;
    DatabaseManager mDatabase;

    public EmployeeAdapter(Context ctx, int layoutResID, List<Employee> employeeList,DatabaseManager mDatabase) {
        super(ctx, layoutResID, employeeList);
        this.ctx = ctx;
        this.layoutResID = layoutResID;
        this.employeeList = employeeList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    //This method binds data to the view and returns the view
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);

        View view = layoutInflater.inflate(layoutResID,null);

        ImageView imgProfile = view.findViewById(R.id.ViewEProfilePicture);
        TextView textViewFname = view.findViewById(R.id.textViewEFirstName);
        TextView textViewLname = view.findViewById(R.id.textViewELastName);
        TextView textgender = view.findViewById(R.id.textViewEGender);
        TextView textViewContact = view.findViewById(R.id.textEViewContact);
        TextView textViewEmail = view.findViewById(R.id.textEViewEmail);
        TextView textViewServices = view.findViewById(R.id.textViewEServices);
        TextView textViewSalaryMethod = view.findViewById(R.id.textViewESalaryMethod);


        final Employee employee= employeeList.get(position);

        //imgProfile.setImageResource(R.id.ViewEProfilePicture);
        textViewFname.setText(employee.getEmp_Fname());
        textViewLname.setText(employee.getEmp_Lname());
        textgender.setText(employee.getEmp_Gender());
        textViewContact.setText(String.valueOf(employee.getEmp_PhoneNo()));
        textViewEmail.setText(employee.getEmp_Email());
        textViewServices.setText(employee.getEmp_Services());
        textViewSalaryMethod.setText(employee.getEmp_ModeOfPay());

        /**view.findViewById(R.id.button_Delete_Emp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });**/

        view.findViewById(R.id.button_Delete_Emp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee(employee);
            }
        });
        view.findViewById(R.id.button_Edit_Emp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    updateEmployee(employee);            }
        });

        return view;

    }

    private void updateEmployee(Employee employee) {

    }

    private void deleteEmployee(Employee employee) {
    }

}

