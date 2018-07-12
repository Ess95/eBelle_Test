package SalonNavigationMenu;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.eodhuno.ebelle_test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SalonNavigationActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String activityTitle;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> groupTitle;
    private Map<String, List<String>> listItem;
    private String[] selectedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon_drawer_layout);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.nav_toolbar);

        drawer = (DrawerLayout) findViewById(R.id.salonNavigationMenu);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,
                R.string.open_navigation_drawer,R.string.close_navigation_drawer);

        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //activityTitle = getTitle().toString();
       // expandableListView = (ExpandableListView) findViewById(R.id.navigationExpandableList);

        //initMenuTitles();

        //View navMenuHeader = getLayoutInflater().inflate(R.layout.salon_drawer_nav_header, null, false);
        //expandableListView.addHeaderView(navMenuHeader);

        //initializeListDataItems();

        //setUpDrawerItems();
        //setUpDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("My Salon");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setUpDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,
                R.string.open_navigation_drawer,R.string.close_navigation_drawer)  {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("My Salon");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(activityTitle);
                invalidateOptionsMenu();

            }
        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(actionBarDrawerToggle);

    }

    private void setUpDrawerItems() {
        /**Log.d("CREATE MENU ITEMS","HEEEEEEEEEERE");

        expandableListAdapter = new SalonExpandableListAdapter(this, groupTitle, listItem);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(groupTitle.get(groupPosition).toString());
            }
        });
        Log.d("EXPLIST","EXPANDABLELIST1");
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle("SALON");
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedChilditem = ((List)(listItem.get(groupTitle.get(groupPosition)))).get(childPosition).toString();
                getSupportActionBar().setTitle(selectedChilditem);

                if(selectedItems[0].equals(groupTitle.get(groupPosition))){
                    //open the Activity
                }else
                    throw new IllegalArgumentException("NOT FOUND");

                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });**/
    }

    private void initializeListDataItems() {
        Log.d("CREATE MENU ITEMS","HEEEEEEEEEERE");
        List<String> title = Arrays.asList("Overview","Appointments","Client Management","Employee Management",
                                            "Inventory","My Services", "Finance","Reports");

        Log.d("CREATE MENU ITEMS22","HEEEEEEEEEERE");

        List<String> overview = Arrays.asList("Overview");
        List<String> appointments = Arrays.asList("Add New Appointment","View Appointments","Cancel Appointment");
        List<String> clientManagement = Arrays.asList("Overview","Add New Client","View Clients");
        List<String> employeeManagement = Arrays.asList("Overview","Add New Employee","View Employees","Access Rights");
        List<String> inventoryManagement = Arrays.asList("Overview","Add New Product","View Products");
        List<String> services = Arrays.asList("Overview","Add New Service","Add New Category","View Services");
        List<String> finance = Arrays.asList("Overview","Customer Payments","Employee Payments");
        List<String> reports = Arrays.asList("Overview","Client Reports","Employee reports","Inventory Reports");

        Log.d("CREATE MENU ITEMS33","HEEEEEEEEEERE");

        listItem = new TreeMap<>();
        listItem.put(title.get(0),overview);
        listItem.put(title.get(1),appointments);
        listItem.put(title.get(2),clientManagement);
        listItem.put(title.get(3),employeeManagement);
        listItem.put(title.get(4),inventoryManagement);
        listItem.put(title.get(5),services);
        listItem.put(title.get(6),finance);
        listItem.put(title.get(7),reports);

        Log.d("CREATE MENU ITEMS44","HEEEEEEEEEERE");

        groupTitle = new ArrayList<>(listItem.keySet());

    }

    private void initMenuTitles() {
        Log.d("CREATE MENU ITEMS6666","HEEEEEEEEEERE");

        selectedItems = new String[]{"Overview","Appointments","Client Management","Employee Management",
                "Inventory","My Services", "Finance","Reports"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
