package com.example.g5_jsoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    return true;
                } else if (item.getItemId() == R.id.about_us) {
                    // Handle menu item 2
                    Intent intent = new Intent(MainPage.this, AboutUs.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.settings) {

                    Intent intent = new Intent(MainPage.this, SettingsActivity.class);
                    startActivity(intent);
                    // Handle menu item 3
                    return true;

                } else {
                    return false;
                }
            }
        });

    }



    public void translate(View view) {
        startActivity(new Intent(this, Translate.class));

    }



}