package com.example.g5_jsoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutUs extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home)

                {
                    Intent intent = new Intent(AboutUs.this, MainPage.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.about_us) {
                    // Handle menu item 2

                    return true;
                } else if (item.getItemId() == R.id.settings) {

                    Intent intent = new Intent(AboutUs.this, SettingsActivity.class);
                    startActivity(intent);
                    // Handle menu item 3
                    return true;

                }

                else {
                    return false;
                }
            }
        });
    }
}

