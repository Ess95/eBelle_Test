package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.ebellecustomcard.CustomCalendarView;
import com.example.ebellecustomcard.CustomListView;
import com.example.ebellecustomcard.CustomTimePickerView;
import com.example.ebellecustomcard.CustomWidget;
import com.example.ebellecustomcard.ImagePickerFragment;
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Category;
import com.example.eodhuno.ebelle_test.database_objects.Employee;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Map;

import helper.AppointmentHelper;
import helper.CategoryHelper;
import helper.EmployeeHelper;
import helper.ServiceHelper;
import helper.TimeHelper;

public class AppointmentActivity extends FragmentActivity {
    CustomWidget mCalendarWidget,mSelectCategory,mSelectStylist;
    TimeHelper timeHelper;
    ServiceHelper serviceHelper;
    CategoryHelper categoryHelper;
    DatabaseManager dbManager = new DatabaseManager(this);
    AppointmentHelper appointmentHelper;
    EmployeeHelper employeeHelper;
    Button addButton;
    listServiceFragment serviceListFragment;
    individualServiceFragment individualServiceFragment;
    listEmployeeFragment employeeListFragment;
    individualEmployeeFragment individualEmployeeFragment;
    RelativeLayout fragmentContainer,stylistFragmentContainer,imageFragmentContainerLayout;

    ArrayList<Service> currentServices = new ArrayList<Service>();
    ArrayList<Employee> currentEmployees = new ArrayList<Employee>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        addButton = (Button)findViewById(R.id.addButton);
        mSelectCategory = (CustomWidget)findViewById(R.id.selectCategory);
        fragmentContainer = (RelativeLayout)findViewById(R.id.fragmentContainerLayout);
        stylistFragmentContainer = (RelativeLayout)findViewById(R.id.stylistFragmentContainerLayout);

        //android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.appointmentToolbar);
        //toolbar.setVisibility(View.VISIBLE);

        timeHelper = new TimeHelper();
        appointmentHelper = new AppointmentHelper(this);
        serviceHelper = new ServiceHelper(this);
        categoryHelper = new CategoryHelper(this);
        employeeHelper = new EmployeeHelper(this);

        serviceListFragment = new listServiceFragment();
        individualServiceFragment = new individualServiceFragment();
        individualServiceFragment.setOnBackButtonListener(new individualServiceFragment.OnBackButtonListener() {
            @Override
            public void onBackButtonPressed() {
                switchServiceFragments(0,null);
            }
        });

        individualServiceFragment.setOnServiceSelectedListener(new individualServiceFragment.OnServiceSelectedListener() {
            @Override
            public void onServiceSelected(Service currService, int totalProductCharge, int total) {
                serviceListFragment.setFinalSelectedService(currService);
                serviceListFragment.setTotal(total);
                serviceListFragment.setTotalProductCharge(totalProductCharge);
                serviceListFragment.setServiceRecyclerViewToStart();
            }
        });

        employeeListFragment = new listEmployeeFragment();
        individualEmployeeFragment = new individualEmployeeFragment();
        individualEmployeeFragment.setOnBackButtonListener(new individualEmployeeFragment.OnBackButtonListener() {
            @Override
            public void onBackButtonPressed() {
                switchEmployeeFragments(0,null);
            }
        });

        individualEmployeeFragment.setOnEmployeeSelectedListener(new individualEmployeeFragment.OnEmployeeSelectedListener() {
            @Override
            public void onEmployeeSelected(Employee currEmployee) {
                employeeListFragment.setFinalSelectedEmployee(currEmployee);
            }
        });

        mCalendarWidget = (CustomWidget) findViewById(R.id.appointmentWidget);
        mCalendarWidget.removeTitle();
        mCalendarWidget.removeProfilePic();
        mCalendarWidget.removeTitleDescription();
        mCalendarWidget.removeShrinkButton();
        mCalendarWidget.removeEditButton();
        mCalendarWidget.removeDeleteButton();
        mCalendarWidget.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,0,1,3});

        mCalendarWidget.addViews(1,
                16,
                17);

        mCalendarWidget.setBigText(0,0, "Schedule appointment");
        mCalendarWidget.addAlignement(0,0, "left");

        mCalendarWidget.setDatePickerMinDate(1, 0, new DateTime().minusDays(1));
        mCalendarWidget.setDatePickerMaxDate(1, 0, timeHelper.getDateString(timeHelper.getLastDayOfMonthDateTime(DateTime.now().getMonthOfYear(),
                DateTime.now().getYear())));
        mCalendarWidget.setDatePickerDate(1, 0, new DateTime());

        ArrayList<Integer> disabledSundays = timeHelper.getSundaysOfMonth(DateTime.now().getMonthOfYear(),
                DateTime.now().getYear());
        int[] disabledSundaysArray = new int[disabledSundays.size()];

        int index = 0;

        for(Integer i:disabledSundays){
            disabledSundaysArray[index] = i;
            ++index;
        }

        mCalendarWidget.disableMonthDays(1, 0, DateTime.now().getYear(),
                DateTime.now().getMonthOfYear(), disabledSundaysArray);

        if(DateTime.now().getHourOfDay() >= 20 ){
            mCalendarWidget.disableMonthDays(1, 0, DateTime.now().getYear(),
                    DateTime.now().getMonthOfYear(), DateTime.now().getDayOfMonth());
        }

        mCalendarWidget.setTimePickerDate(2, 0, new DateTime());
        mCalendarWidget.setTimePickerInterval(2, 0, 1,30);
        mCalendarWidget.setTimePickerStartTime(2, 0, 8,0);
        mCalendarWidget.setTimePickerEndTime(2, 0, 20, 0);
        DateTime now = new DateTime();
        if(DateTime.now().getHourOfDay() < 20){
            mCalendarWidget.disableTimePickerHours(2,0, new int[]{8,0,now.getHourOfDay()+1,0});
        }

        mCalendarWidget.setCustomCalendarOoDateSelectedListener(1, 0, new CustomCalendarView.CustomCalendarOnDateSelectedListener() {
            @Override
            public void onDateSelected(DateTime date) {
                mCalendarWidget.clearTimePickerText(2, 0);
                mCalendarWidget.setTimePickerDate(2, 0, date);
                mCalendarWidget.disableTimePickerHours(2,0, new int[]{7,0,8,0});
                try {
                    ArrayList<Integer[]> bookedHoursList = AppointmentHelper.getBookedHours(date);
                    for(Integer[] duration : bookedHoursList){
                        mCalendarWidget.disableTimePickerHours(2,0, new int[]{duration[0],
                                duration[1],
                                duration[2],
                                duration[3]});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mSelectCategory.removeTitle();
        mSelectCategory.removeProfilePic();
        mSelectCategory.removeTitleDescription();
        mSelectCategory.removeShrinkButton();
        mSelectCategory.removeEditButton();
        mSelectCategory.removeDeleteButton();
        mSelectCategory.removeInfoButton();
        mSelectCategory.setColumnNumber(4);
        mSelectCategory.addColumnSpans(new int[]{0,0,1,4},
                new int[]{1,1,1,3},
                new int[]{2,0,1,4});

        mSelectCategory.addViews(1,
                0,23,
                25);

        mSelectCategory.setBigText(0,0, "Category");
        mSelectCategory.addAlignement(0,0, "left");
        mSelectCategory.setSmallText(1,0,"Name");
        mSelectCategory.addAlignement(1,0, "left");
        mSelectCategory.setTapText(1,1,"Tap to select category");
        mSelectCategory.addAlignement(1,1, "left");

        mSelectCategory.getListView(2,0).setVisibility(View.GONE);

        mSelectCategory.setTapTextOnClickListener(1, 1, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Category> categories = CategoryHelper.getAllCategories();
                ArrayList<String> categoryNames = new ArrayList<String>();

                for(Category category:categories){
                    categoryNames.add(category.getCategoryName());
                }

                mSelectCategory.setListViewItems(2,0, categoryNames);

                if(mSelectCategory.getListView(2,0).getVisibility() == View.VISIBLE){
                    mSelectCategory.getListView(2,0).setVisibility(View.GONE);
                }else{
                    mSelectCategory.getListView(2,0).setVisibility(View.VISIBLE);
                }


            }
        });

        mSelectCategory.setListViewOnItemSelectedListener(2, 0, new CustomListView.CustomListViewOnItemSelectedListener() {
            @Override
            public void onCustomListItemSelected(String s, int i) {
                serviceListFragment.removeFinalSelectedService();
                serviceListFragment.refreshEverything();
                fragmentContainer.invalidate();
                switchServiceFragments(0,null);
                Category category = null;
                try {
                    category = CategoryHelper.getCategoryByName(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(category != null){
                    ArrayList<Service> services = new ArrayList<Service>();
                    try{
                        services = ServiceHelper.getServiceByCategoryId(category.getCategotyID());
                    }catch(Exception e){
                    }
                    currentServices.clear();
                    currentServices.addAll(services);
                    serviceListFragment.addServices(services);
                    mSelectCategory.setTapText(1,1,""+category.getCategoryName());
                }
                mSelectCategory.getListView(2,0).setVisibility(View.GONE);
            }
        });

        final Context context = this;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int custId = 1;
                DateTime selectedDt = mCalendarWidget.getSelectedDate(1, 0);
                int[] duration = mCalendarWidget.getTimePickerSelectedTime(2,0);
                DateTime apptTimeDT = new DateTime(selectedDt.getYear(),
                        selectedDt.getMonthOfYear(),
                        selectedDt.getDayOfMonth(),
                        duration[0],
                        duration[1]);
                Service selectedService = serviceListFragment.getSelectedService();
                int serviceId = selectedService.getServID();
                int productUsed = serviceListFragment.getTotalProductCharge() > 0 ? 1 : 0;
                Employee selectedEmployee = employeeListFragment.getSelectedEmployee();
                int beauticianId  = selectedEmployee.getEmpID();
                int confirmation = 0;
                int serviceCharge = selectedService.getPrice();
                int productCharges = serviceListFragment.getTotalProductCharge();
                int total = serviceCharge + productCharges;

                dbManager.addAppointment(custId,selectedDt,apptTimeDT,serviceId,productUsed,
                        beauticianId,confirmation,serviceCharge,productCharges,total);
                startActivity(new Intent(context, CardTestActivity.class));
            }

        });

        serviceListFragment.setServiceSelectedListener(new listServiceFragment.OnFragmentServiceSelectedListener() {
            @Override
            public void onFragmentServiceSelected(Service selectedService) {
                switchServiceFragments(1,selectedService);
                int mins = selectedService.getDuration();
                int[] duration = TimeHelper.getHourMinFromMinutes(mins);
                mCalendarWidget.setTimePickerInterval(2, 0, duration[0],duration[1]);
            }
        });
        switchServiceFragments(0,null);

        employeeListFragment.setEmployeeSelectedListener(new listEmployeeFragment.OnFragmentEmployeeSelectedListener() {
            @Override
            public void onFragmentEmployeeSelected(Employee selectedEmployee) {
                switchEmployeeFragments(1,selectedEmployee);
            }
        });
        switchEmployeeFragments(0,null);
    }

    private void switchEmployeeFragments(int flag, Employee selectedEmployee) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(flag == 0){
            fragmentTransaction.replace(R.id.stylistFragmentContainerLayout,employeeListFragment);
            if(currentEmployees.size() > 0) {
                Log.d("HERE111XXXXXXX","1111111111111");

                employeeListFragment.addEmployees(currentEmployees);
            }
            }else if(flag == 1){
                fragmentTransaction.replace(R.id.stylistFragmentContainerLayout,individualEmployeeFragment);
                individualEmployeeFragment.setCurrentEmployee(selectedEmployee);
            }
            fragmentTransaction.commit();
        }


    public void switchServiceFragments(int flag, Service svr){
        Log.d("yyyyyyyyyy","HERE");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(flag == 0){
            fragmentTransaction.replace(R.id.fragmentContainerLayout,serviceListFragment);
            //Log.d("HERE0000000","00000000000");
            if(currentServices.size() > 0){
                Log.d("HERE111111","1111111111111");
                serviceListFragment.addServices(currentServices);
            }
        }else if(flag == 1){
            fragmentTransaction.replace(R.id.fragmentContainerLayout,individualServiceFragment);
            individualServiceFragment.setCurrentService(svr);
        }
        fragmentTransaction.commit();
    }

}

