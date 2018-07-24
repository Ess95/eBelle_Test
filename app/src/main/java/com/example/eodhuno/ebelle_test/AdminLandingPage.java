package com.example.eodhuno.ebelle_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ebellecustomcard.CustomWidget;

public class AdminLandingPage extends AppCompatActivity {
    CustomWidget overviewLeaderBoard,overviewAppointment,overviewInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_landing_page);

        overviewLeaderBoard = (CustomWidget) findViewById(R.id.salonLeaderBoard);
        overviewLeaderBoard.removeTitle();
        overviewLeaderBoard.removeProfilePic();
        overviewLeaderBoard.removeTitleDescription();
        overviewLeaderBoard.removeDeleteButton();
        overviewLeaderBoard.removeEditButton();
        overviewLeaderBoard.removeInfoButton();
        overviewLeaderBoard.removeShrinkButton();
        overviewLeaderBoard.setColumnNumber(3);
        overviewLeaderBoard.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,1},
                new int[]{2,1,1,1});

        overviewLeaderBoard.addViews(1,
         0,0,0,
         11,11,2);

        overviewLeaderBoard.setBigText(0,0,"Leaderboard");
        overviewLeaderBoard.addAlignement(0,0,"left");

        overviewLeaderBoard.setSmallText(1,0,"Service");
        overviewLeaderBoard.setSmallText(1,1,"Product");
        overviewLeaderBoard.setSmallText(1,2,"Stylist");
        overviewLeaderBoard.setProgress(2,0,70,"Nails");
        overviewLeaderBoard.setProgress(2,1,20,"Cantu Butter");


        overviewAppointment = (CustomWidget) findViewById(R.id.appointmentOverviewWidget);
        overviewAppointment.removeTitle();
        overviewAppointment.removeProfilePic();
        overviewAppointment.removeTitleDescription();
        overviewAppointment.removeDeleteButton();
        overviewAppointment.removeEditButton();
        overviewAppointment.removeInfoButton();
        overviewAppointment.removeShrinkButton();
        overviewAppointment.setColumnNumber(3);
        overviewAppointment.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,1},
                new int[]{2,0,1,3});

        overviewAppointment.addViews(1,
                8,8,8,
                13);

        overviewAppointment.setBigText(0,0,"Appointments");
        overviewAppointment.addAlignement(0,0,"left");
        overviewAppointment.setVLValue(1,0,"20","Today");
        overviewAppointment.setVLValue(1,1,"5","This Week");
        overviewAppointment.setVLValue(1,2,"10","This Month");
        overviewAppointment.setButtonText(2,0,"View Appointments");
        overviewAppointment.addAlignement(2,0,"right");


        overviewInventory = (CustomWidget) findViewById(R.id.inventoryWarningWidget);
        overviewInventory.removeTitle();
        overviewInventory.removeProfilePic();
        overviewInventory.removeTitleDescription();
        overviewInventory.removeDeleteButton();
        overviewInventory.removeEditButton();
        overviewInventory.removeInfoButton();
        overviewInventory.removeShrinkButton();
        overviewInventory.setColumnNumber(3);
        overviewInventory.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,0,1,3});

        overviewInventory.addViews(1,
                0,
                13);

        overviewInventory.setBigText(0,0,"Inventory Update");
        overviewInventory.addAlignement(0,0,"left");
        overviewInventory.setSmallText(1,0,"Only 13 Abuja Braids left.");
        overviewInventory.addAlignement(1,0,"left");
        overviewInventory.setButtonText(2,0,"Update Inventory");
        overviewInventory.addAlignement(2,0,"right");

    }
}
