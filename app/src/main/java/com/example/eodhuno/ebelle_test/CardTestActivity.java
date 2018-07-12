package com.example.eodhuno.ebelle_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ebellecustomcard.CustomCalendarView;
import com.example.ebellecustomcard.CustomTimePickerView;
import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Category;
import com.example.eodhuno.ebelle_test.database_objects.Customer;

import org.joda.time.DateTime;

import java.util.ArrayList;

import helper.CategoryHelper;
import helper.CustomerHelper;
import helper.EmployeeHelper;
import helper.ProductHelper;

public class CardTestActivity extends AppCompatActivity {

    CustomWidget customCard;
    DatabaseManager mDatabase;
    ProductHelper productHelper;
    EmployeeHelper employeeHelper;
    CategoryHelper categoryHelper;
    CustomerHelper customerHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_test);
        mDatabase = new DatabaseManager(this);


        /**mDatabase.addServices("Cornrows",
                "Braided",
                1,
                1300,
                135,
                1);

        mDatabase.addServices("Nail sculpting",
                "Sculpt",
                2,
                2300,
                120,
                1);
        mDatabase.addServices("Facial",
                "Mask",
                3,
                1000,
                120,
                1);
        mDatabase.addServices("Face glam",
                "Make up",
                4,
                1000,
                120,
                0);
        mDatabase.addServices("Crochet",
                "Fresh crochet braids",
                1,
                1000,
                180,
                0);
        mDatabase.addServices("Wedding glam",
                "Princess ",
                4,
                4000,
                240,
                1);**/

         customerHelper = new CustomerHelper(this);

        try {
            ArrayList <Customer>customerArrayList = CustomerHelper.getCustomerByLastName("Foxx");
            for(Customer customer:customerArrayList){
                Log.d("CHECKING","CUSTOMER ID: " +customer.getCustID());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        customCard = (CustomWidget)findViewById(R.id.customCard);
        customCard.removeTitle();
        customCard.removeProfilePic();
        customCard.removeTitleDescription();
        customCard.addColumnSpans(new int[]{0,0,1,3},
                new int[]{3,0,1,3},
                new int[]{5,0,1,3},
                new int[]{5,0,1,3},
                new int[]{6,0,1,3},
                new int[]{7,0,1,3},
                new int[]{8,0,1,3},
                new int[]{9,0,1,3});

        customCard.addViews(1,
                0,0,0,
                11,11,11,
                1,
                8,8,8,
                1,
                0,
                13,
                16,
                17);

        customCard.setSmallText(1,0, "Service");
        customCard.setSmallText(1,1, "Product");
        customCard.setSmallText(1,2, "Stylist");
        customCard.setSmallText(6,0, "Only 13 Abuja braids left");

        customCard.setBigText(0,0,"Leaderboard");
        customCard.setBigText(3,0,"Appointments");
        customCard.setBigText(5,0,"Inventory");

        customCard.setVLValue(4, 0, "10", "Today");
        customCard.setVLValue(4, 1, "25", "This week");
        customCard.setVLValue(4, 2, "60", "This month");

        customCard.setProgress(2, 0, 75, "Nails");
        customCard.setProgress(2, 1, 78, "Braids");
        customCard.setProgress(2, 2, 85, "Skin");

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast();
            }
        };

        customCard.setButtonText(7, 0, "SHOW YES");
        customCard.setButtonListener(7, 0, btnListener);

        customCard.setDatePickerMinDate(8, 0, "2/06/2016");
        customCard.setDatePickerMaxDate(8, 0, "26/06/2016");
        customCard.setDatePickerDate(8, 0, "24/06/2016");
        customCard.disableMonthDays(8, 0, 2016, 6, 5,7,15);


        customCard.setTimePickerDate(9, 0, "7/10/2016");
        customCard.setTimePickerInterval(9, 0, 1,10);
        customCard.setTimePickerStartTime(9, 0, 10,10);
        customCard.setTimePickerEndTime(9, 0, 20, 40);
        customCard.disableTimePickerHours(9,0, new int[]{13,0,14,25}, new int[]{18,0,19,05});

        customCard.addHorizontalBordersToBottomOf(new int[]{1,0,3}, new int[]{4,1,2});
        customCard.addVerticalBordersToRightOf(new int[]{1,0,2}, new int[]{1,1,2});

        customCard.addAlignement(0,0, "left");
        customCard.setCustomCalendarOoDateSelectedListener(8, 0, new CustomCalendarView.CustomCalendarOnDateSelectedListener() {
            @Override
            public void onDateSelected(DateTime date) {
                customCard.clearTimePickerText(9, 0);
                customCard.setTimePickerDate(9, 0, date);
            }
        });

        customCard.setCustomTimePickerOnTimeSelectedListener(9, 0,
                new CustomTimePickerView.CustomTimePickerOnTimeSelectedListener() {
                    @Override
                    public void onDateSelected(int[] selectedTimeIntervals) {
                        String txt = ""+selectedTimeIntervals[0]+" : "+selectedTimeIntervals[1] + " to "+
                                selectedTimeIntervals[2]+" : "+selectedTimeIntervals[3];
                        showToast2(txt);
                    }
                });
    }
    public void showToast(){
        Toast.makeText(this,"YAAAAAAAAAAS", Toast.LENGTH_SHORT).show();
    }

    public void showToast2(String text){
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }
}
