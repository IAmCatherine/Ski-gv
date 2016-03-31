package com.example.user.ski_gv;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.user.ski_gv.fragments.HoursFragment;
import com.example.user.ski_gv.fragments.InfoFragment;
import com.example.user.ski_gv.fragments.LiftsFragment;
import com.example.user.ski_gv.fragments.MapFragment;
import com.example.user.ski_gv.fragments.SlopesFragment;
import com.example.user.ski_gv.fragments.WeatherFragment;


public class Main extends AppCompatActivity{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private static final int LAYOUT = R.layout.activity_main;
    FragmentManager fragmentmanager;
    FragmentTransaction ft;
    MapFragment mapFragment;
    LiftsFragment liftsFragment;
    SlopesFragment slopesFragment;
    WeatherFragment weatherFragment;
    InfoFragment infoFragment;
    HoursFragment hoursFragment;
    protected  void onCreate(Bundle savedInstanceState){
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();

        FragmentManager fragmentmanager = getFragmentManager();
        FragmentTransaction ft = fragmentmanager.beginTransaction();
        mapFragment = new MapFragment();
        ft.replace(R.id.viewPager, mapFragment);
        ft.addToBackStack(null);
        ft.commit();


    }

    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        assert toolbar != null;
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                fragmentmanager = getFragmentManager();
                ft = fragmentmanager.beginTransaction();
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.house_of_operation:
                        hoursFragment= new HoursFragment();
                        ft.replace(R.id.viewPager, hoursFragment);
                        toolbar.setTitle(R.string.house_of_operation);
                        break;
                    case R.id.map:
                        mapFragment= new MapFragment();
                        ft.replace(R.id.viewPager,mapFragment);
                        toolbar.setTitle(R.string.map);
                        break;
                    case R.id.open_slopes:
                        slopesFragment = new SlopesFragment();
                        ft.replace(R.id.viewPager, slopesFragment);
                        toolbar.setTitle(R.string.open_slopes);
                        break;
                    case R.id.lifts:
                        liftsFragment = new LiftsFragment();
                        ft.replace(R.id.viewPager,liftsFragment);
                        toolbar.setTitle(R.string.lifts);
                        break;
                    case R.id.weather:
                        weatherFragment = new WeatherFragment();
                        ft.replace(R.id.viewPager, weatherFragment);
                        toolbar.setTitle(R.string.weather);
                        break;
                    case R.id.info:
                        infoFragment = new InfoFragment();
                        ft.replace(R.id.viewPager,infoFragment);
                        toolbar.setTitle(R.string.info);
                        break;
                }
                ft.addToBackStack(null);
                ft.commit();
                return true;
            }
        });

    }





}
