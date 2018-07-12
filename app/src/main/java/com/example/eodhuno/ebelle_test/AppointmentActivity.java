package com.example.eodhuno.ebelle_test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ebellecustomcard.CustomCalendarView;
import com.example.ebellecustomcard.CustomTimePickerView;
import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Appointment;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Map;

import helper.AppointmentHelper;
import helper.ServiceHelper;
import helper.TimeHelper;

public class AppointmentActivity extends FragmentActivity {
    CustomWidget mCalendarWidget;
    TimeHelper timeHelper;
    ServiceHelper serviceHelper;
    DatabaseManager dbManager = new DatabaseManager(this);
    AppointmentHelper appointmentHelper;
    Button addButton;
    Spinner stylistSpinner;
    listServiceFragment serviceListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        FragmentManager fragmentManager = getSupportFragmentManager();

        serviceListFragment = (listServiceFragment)fragmentManager.findFragmentById(R.id.servListContainer);

        addButton = (Button)findViewById(R.id.addButton);
        stylistSpinner = (Spinner)findViewById(R.id.stylistSpinner);

        timeHelper = new TimeHelper();
        appointmentHelper = new AppointmentHelper(this);
        serviceHelper = new ServiceHelper(this);

        mCalendarWidget = (CustomWidget) findViewById(R.id.appointmentWidget);
        mCalendarWidget.removeTitle();
        mCalendarWidget.removeProfilePic();
        mCalendarWidget.removeTitleDescription();
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

        mCalendarWidget.setTimePickerDate(2, 0, new DateTime());
        mCalendarWidget.setTimePickerInterval(2, 0, 1,30);
        mCalendarWidget.setTimePickerStartTime(2, 0, 8,0);
        mCalendarWidget.setTimePickerEndTime(2, 0, 20, 0);
        DateTime now = new DateTime();
        mCalendarWidget.disableTimePickerHours(2,0, new int[]{8,0,now.getHourOfDay()+1,0});

        mCalendarWidget.setCustomCalendarOoDateSelectedListener(1, 0, new CustomCalendarView.CustomCalendarOnDateSelectedListener() {
            @Override
            public void onDateSelected(DateTime date) {
                mCalendarWidget.clearTimePickerText(2, 0);
                mCalendarWidget.setTimePickerDate(2, 0, date);
                mCalendarWidget.disableTimePickerHours(2,0, new int[]{7,0,8,0});
                if(date.getDayOfMonth() == new DateTime().getDayOfMonth()){
                    DateTime now = new DateTime();
                    try {
                        ArrayList<Integer[]> bookedHoursList = AppointmentHelper.getBookedHours(now);
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
            }
        });

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
                int productUsed = 0;
                int beauticianId  = 2;
                int confirmation = 1;
                int serviceCharge = 500;
                int productCharges = 1500;
                int total = serviceCharge + productCharges;

                dbManager.addAppointment(custId,selectedDt,apptTimeDT,serviceId,productUsed,
                        beauticianId,confirmation,serviceCharge,productCharges,total);
            }
        });





        //customCard.disableTimePickerHours(9,0, new int[]{13,0,14,25}, new int[]{18,0,19,05});
    }
}
