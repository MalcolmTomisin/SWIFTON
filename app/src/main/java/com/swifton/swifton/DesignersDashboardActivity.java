package com.swifton.swifton;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.swifton.swifton.Fragments.AddDesignsFragment;
import com.swifton.swifton.Fragments.CustomerOrdersFragment;
import com.swifton.swifton.Fragments.CustomersFragment;
import com.swifton.swifton.Fragments.DashboardFragment;
import com.swifton.swifton.Fragments.DeliveredFragment;
import com.swifton.swifton.Fragments.DesignerProfileFragment;
import com.swifton.swifton.Fragments.MyProductsFragment;
import com.swifton.swifton.Fragments.PendingFragment;

public class DesignersDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mdrawaerLayout;
    private ActionBarDrawerToggle mdrawertoggle;
    private Toolbar mtoolbar;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designers_dashboard);

        mtoolbar = findViewById(R.id.dashToolbar);
        setSupportActionBar(mtoolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mdrawaerLayout = findViewById(R.id.dDrawerLayout);
//        mdrawertoggle = new ActionBarDrawerToggle(this,mdrawaerLayout,R.string.open,R.string.close);
//        mdrawaerLayout.addDrawerListener(mdrawertoggle);
//        mdrawertoggle.syncState();

        mActivityTitle = "Fashionister";

        NavigationView navigationView =  findViewById(R.id.dNavView);
        navigationView.setNavigationItemSelectedListener(this);

        setupDrawer();

        DashboardFragment dashboardFragment = new DashboardFragment();
        FragmentManager dashboardmanager = getSupportFragmentManager();
        dashboardmanager.beginTransaction().replace(R.id.linearLayout_for_fragment, dashboardFragment, dashboardFragment.getTag()).commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mdrawertoggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mdrawertoggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawer() {

        mdrawertoggle = new ActionBarDrawerToggle(this, mdrawaerLayout,
                R.string.open, R.string.close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if( getSupportActionBar() !=null ) {
                    getSupportActionBar().setTitle("Navigation!");
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }else{
                    getSupportActionBar().setTitle("Changed");
                }
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if( getSupportActionBar() !=null ) {
                    getSupportActionBar().setTitle(mActivityTitle);
                    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }else{
                    getSupportActionBar().setTitle("Changed");
                }
            }
        };
        mdrawertoggle.setDrawerIndicatorEnabled(true);
        mdrawaerLayout.addDrawerListener(mdrawertoggle);
    }
//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mdrawertoggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch(id){
            case  R.id.nav_dashboard:
                mActivityTitle = "Dashboard";
                DashboardFragment dashboardFragment = new DashboardFragment();
                FragmentManager dashboardmanager = getSupportFragmentManager();
                dashboardmanager.beginTransaction().replace(R.id.linearLayout_for_fragment, dashboardFragment, dashboardFragment.getTag()).commit();
                break;

            case  R.id.nav_add:
                mActivityTitle = "Add Designs";
                AddDesignsFragment adddesignsFragment = new AddDesignsFragment();
                FragmentManager adddesignmanager = getSupportFragmentManager();
                adddesignmanager.beginTransaction().replace(R.id.linearLayout_for_fragment, adddesignsFragment, adddesignsFragment.getTag()).commit();
                break;

            case  R.id.nav_profile:
                mActivityTitle = "Edit Profile";
                DesignerProfileFragment designerprofileFragment = new DesignerProfileFragment();
                FragmentManager profilemanager = getSupportFragmentManager();
                profilemanager.beginTransaction().replace(R.id.linearLayout_for_fragment, designerprofileFragment, designerprofileFragment.getTag()).commit();
                break;

            case R.id.nav_products:
                mActivityTitle = "Products";
                MyProductsFragment productFragment = new MyProductsFragment();
                FragmentManager productmanager = getSupportFragmentManager();
                productmanager.beginTransaction().replace(R.id.linearLayout_for_fragment, productFragment, productFragment.getTag()).commit();
                break;

            case R.id.nav_pending:
                mActivityTitle = "Pending Orders";
                PendingFragment pendingFragment = new PendingFragment();
                FragmentManager pendingmanager = getSupportFragmentManager();
                pendingmanager.beginTransaction().replace(R.id.linearLayout_for_fragment, pendingFragment, pendingFragment.getTag()).commit();
                break;

            case R.id.nav_orders:
                mActivityTitle = "Orders";
                CustomerOrdersFragment ordersFragment = new CustomerOrdersFragment();
                FragmentManager ordersmanager = getSupportFragmentManager();
                ordersmanager.beginTransaction().replace(R.id.linearLayout_for_fragment, ordersFragment, ordersFragment.getTag()).commit();
                break;

            case R.id.nav_delivered:
                mActivityTitle = "delivered Orders";
               DeliveredFragment deliveredFragment = new DeliveredFragment();
                FragmentManager deliveredmanager = getSupportFragmentManager();
                deliveredmanager.beginTransaction().replace(R.id.linearLayout_for_fragment, deliveredFragment, deliveredFragment.getTag()).commit();
                break;

            case R.id.nav_customers:
                mActivityTitle = "Customers";
                CustomersFragment favoriteFragment = new CustomersFragment();
                FragmentManager customermanager = getSupportFragmentManager();
                customermanager.beginTransaction().replace(R.id.linearLayout_for_fragment, favoriteFragment, favoriteFragment.getTag()).commit();

                break;

            case R.id.nav_share:

                break;

            case R.id.nav_changepassword:
                mActivityTitle = "Change Password";
//                  SettingsFragment settingsFragment = new SettingsFragment();
//                  FragmentManager manager1 = getSupportFragmentManager();
//                  manager1.beginTransaction().replace(R.id.linearLayout_for_fragment, settingsFragment, settingsFragment.getTag()).commit();

                break;
        }
        DrawerLayout drawer = findViewById(R.id.dDrawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
