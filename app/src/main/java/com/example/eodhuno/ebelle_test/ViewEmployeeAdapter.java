package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Employee;

import java.util.ArrayList;
import java.util.List;

public class ViewEmployeeAdapter extends RecyclerView.Adapter<ViewEmployeeAdapter.ViewHolder>{

    public List<Employee> employeeListItems;
    Context sCtx;
    Employee selectedEmployee = null;
    EmployeeSelectedListener listener;
    ViewEmployeesFragment parentEmployeeFrag;

    public ViewEmployeeAdapter(List<Employee> employeeListItems, Context sCtx, ViewEmployeesFragment parentFrag, EmployeeSelectedListener lis) {
        this.employeeListItems = new ArrayList<Employee>();
        if(parentFrag.getFinalSelectedEmployee() != null){
            this.employeeListItems.clear();
            this.employeeListItems.add(parentFrag.getFinalSelectedEmployee());
            for(Employee currEmployee:employeeListItems){
                if(currEmployee.getEmpID() != parentFrag.getFinalSelectedEmployee().getEmpID()){
                    this.employeeListItems.add(currEmployee);
                }
            }

        }else{
            this.employeeListItems = employeeListItems;
        }
        this.sCtx = sCtx;
        this.listener = lis;
        this.parentEmployeeFrag = parentFrag;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_employee_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employeeListItems.get(position);

        if(parentEmployeeFrag.getFinalSelectedEmployee() != null && employee.getEmpID() == parentEmployeeFrag.getFinalSelectedEmployee().getEmpID() ){
            holder.stylistCustomWidget.getIconTextImageButton(0,0).setVisibility(View.VISIBLE);
        }else{
            holder.stylistCustomWidget.getIconTextImageButton(0,0).setVisibility(View.INVISIBLE);
        }
        holder.stylistCustomWidget.setBigText(1,1,employee.getEmp_Fname() + " " + employee.getEmp_Lname());
        holder.stylistCustomWidget.setRating(2,0,employee.getEmpRating());
    }

    @Override
    public int getItemCount() {
        return employeeListItems.size();
    }

    private void triggerEmployeeSelectedListener(Employee emp){
        listener.onEmployeeSelected(emp);
    }

    public interface EmployeeSelectedListener{
        public void onEmployeeSelected(Employee selectedEmployee);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CustomWidget stylistCustomWidget;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = getAdapterPosition();
                    triggerEmployeeSelectedListener(employeeListItems.get(id));
                }
            });

            stylistCustomWidget = (CustomWidget) itemView.findViewById(R.id.viewEmployeeListCustomItem);
            stylistCustomWidget.removeTitle();
            stylistCustomWidget.removeProfilePic();
            stylistCustomWidget.removeTitleDescription();
            stylistCustomWidget.removeInfoButton();
            stylistCustomWidget.removeDeleteButton();
            stylistCustomWidget.removeEditButton();
            stylistCustomWidget.removeShrinkButton();
            stylistCustomWidget.setColumnNumber(3);
            stylistCustomWidget.addColumnSpans(new int[]{0,0,1,3},
                    new int[]{1,1,1,2},
                    new int[]{2,0,1,3});

            stylistCustomWidget.addViews(22,
                    2,1,
                    9);

            stylistCustomWidget.addAlignement(0,0, "right");
            stylistCustomWidget.setIconText(0,0, "");
            stylistCustomWidget.setIconTextImage(0,0, R.drawable.checkbox_marked);
            stylistCustomWidget.getIconTextImageButton(0,0).setVisibility(View.INVISIBLE);
            stylistCustomWidget.getIconTextTextView(0,0).setVisibility(View.INVISIBLE);
            stylistCustomWidget.addAlignement(1,0, "left");
            stylistCustomWidget.addAlignement(1,1, "left");
            stylistCustomWidget.addAlignement(2,0, "right");


        }


    }

}

