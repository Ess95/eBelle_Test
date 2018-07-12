package com.example.eodhuno.ebelle_test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ebellecustomcard.CustomWidget;
import com.example.eodhuno.ebelle_test.database_objects.Service;

import java.util.ArrayList;
import java.util.List;

public class ServicesListActivity extends FragmentActivity {

    public RecyclerView serviceRecyclerView;
    public RecyclerView.Adapter serviceAdapter;

    List<Service> servList;
    Button changeFragButton;
    LinearLayout containerLayout;

    Fragment listFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment individualFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list_layout);
        changeFragButton = (Button) findViewById(R.id.change);
        containerLayout = (LinearLayout) findViewById(R.id.containerLayout);


        fragmentManager = getSupportFragmentManager();


        listFragment = new listServiceFragment();
        individualFrag = new individualServiceFragment();

        changeFragButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerLayout,listFragment);
        fragmentTransaction.commit();

    }

    private  void changeFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerLayout,individualFrag);
        fragmentTransaction.commit();
        fragmentManager.popBackStackImmediate();
    }
}
