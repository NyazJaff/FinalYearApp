package com.jaffn01.mydriving.uni.mydriving;


import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.config.Configuration;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Context context;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle =  new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.naviagtion_view);
        setupDrawerContent(navigationView);

    }

    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openJourneyDetails(){
//        FrameLayout fl = (FrameLayout) findViewById(R.id.fragment_place);
//        fl.removeAllViews();
//        Fragment fragment = null;
//        FragmentManager fm = null;
//        FragmentTransaction ft = null;
//        Bundle arguments;
//
//        fragment = new WishListView();
//        arguments = new Bundle();
//        arguments.putString("viewType","lendedItems");
//        fragment.setArguments(arguments);
//        fm = getSupportFragmentManager();
//        ft = fm.beginTransaction();
//        ft.replace(R.id.fragment_place,fragment,"WishList").addToBackStack("WishList").commit();
//        fm.executePendingTransactions();
//        mDrawerLayout.closeDrawers();
//        break;
//
//
//        AlertDialog.Builder  mBuilder  = new AlertDialog.Builder(this);
//        View mView = this.getLayoutInflater().inflate(R.layout.view_history_summary, null);
//
//
//        mBuilder.setView(mView);
//        final AlertDialog dialog = mBuilder.create();
//        dialog.show();


    }

    public  void selectIterDrawe(MenuItem menuItem){
        FrameLayout fl = (FrameLayout) findViewById(R.id.fragment_place_for_summary);
        fl.removeAllViews();
        Fragment myFragment = null;
        Class fragmentClass;
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (menuItem.getItemId()){
            case R.id.start:
                fragmentClass = Start.class;
                break;
            case R.id.journeys:
                fragmentClass = AllJourneys.class;
                break;

            case R.id.contact:
                fragmentClass = Contact.class;
                break;
            default:
                fragmentClass = Start.class;
        }

        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        fragmentManager.beginTransaction().replace(R.id.fragment_place_for_summary, myFragment,menuItem.getTitle().toString()).addToBackStack(menuItem.getTitle().toString()).commit();
        menuItem.setCheckable(true);
        TextView title = (TextView) toolbar.findViewById(R.id.titleTxt);
        title.setText(menuItem.getTitle());
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();

    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectIterDrawe(item);
                return false;
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.home){
            Toast.makeText(this,"This is home!", Toast.LENGTH_SHORT).show();
            mDrawerLayout.closeDrawers();}
//        }else if(id == R.id.settings){
//            Toast.makeText(this,"This is settings", Toast.LENGTH_SHORT).show();
//            mDrawerLayout.closeDrawers();
//        }else if(id == R.id.log){
//            Toast.makeText(this,"This is log", Toast.LENGTH_SHORT).show();
//            mDrawerLayout.closeDrawers();
//        }

        return false;
    }

}
