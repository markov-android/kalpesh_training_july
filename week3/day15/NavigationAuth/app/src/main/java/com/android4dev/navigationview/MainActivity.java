package com.android4dev.navigationview;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((AppController) getApplication()).getTracker(AppController.TrackerName.APP_TRACKER);
        Log.d("CATLOG", "Passed AppTracker");

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                Bundle bundle = new Bundle();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.inbox:
                        Tracker t = ((AppController) getApplication()).getTracker(AppController.TrackerName.APP_TRACKER);
                      //  t.enableAdvertisingIdCollection(true);
                        // Build and Send the Analytics Event.
                        HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder();
                        eventBuilder.setAction("Vote Me")
                                .setCategory("ToolBar")
                                .setValue(1L)
                                .setLabel("Settings Menu");
                        t.send(eventBuilder.build());
                        Toast.makeText(getApplicationContext(),"Accesories",Toast.LENGTH_SHORT).show();
                        ContentFragment fragment = new ContentFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("catid", "catalog01_1001_7618");
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    // For rest of the options we just show a toast on click
                    case R.id.starred:
                        Toast.makeText(getApplicationContext(),"Loading Bags...",Toast.LENGTH_LONG).show();
                        fragment = new ContentFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("catid", "catalog01_1001_6046");
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.sent_mail:
                        Toast.makeText(getApplicationContext(),"Loading Beauty",Toast.LENGTH_LONG).show();
                        fragment = new ContentFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("catid", "catalog01_1001_5813");
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.drafts:
                        Toast.makeText(getApplicationContext(),"Loading Blazers...",Toast.LENGTH_LONG).show();
                        fragment = new ContentFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("catid", "catalog01_1001_4177");
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.allmail:
                        Toast.makeText(getApplicationContext(),"Loading Coats...",Toast.LENGTH_LONG).show();
                        fragment = new ContentFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("catid", "catalog01_1001_4172");
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.trash:
                        Toast.makeText(getApplicationContext(),"Loading Denim...",Toast.LENGTH_LONG).show();
                        fragment = new ContentFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("catid", "catalog01_1001_9263");
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.spam:
                        Toast.makeText(getApplicationContext(),"Loading Dresses...",Toast.LENGTH_LONG).show();
                        fragment = new ContentFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("catid", "catalog01_1001_2639");
                        fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_LONG).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();






    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

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

        return super.onOptionsItemSelected(item);
    }
}
