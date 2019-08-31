package com.webhostapp.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class parentfragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomnav = findViewById(R.id.bottom_nav);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new home_fragment()).commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navlistner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selecteditem = null;
            switch (menuItem.getItemId()) {
                case R.id.frag_profile:
                    selecteditem = new profile_fragment();
                    break;
                case R.id.frag_bookings:
                    selecteditem = new booking_fragment();
                    break;
                case R.id.frag_help:
                    selecteditem=new help_fragment();
                    break;
                case R.id.frag_home:
                    selecteditem=new home_fragment();

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,selecteditem).commit();
            return true;
        }
    };
}
