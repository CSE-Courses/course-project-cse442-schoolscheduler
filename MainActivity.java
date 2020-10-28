package com.example.mycalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAcitivity";

    private TextView Date;
    private Button btnToCalendar;
 //Test 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        Date = (TextView) findViewById(R.id.Date);
        //btnToCalendar = (Button) findViewById(R.id.btnToCalendar);


//        Intent dateIntent =getIntent();
//        String Selected_Date= dateIntent.getStringExtra("date");
//        Date.setText(Selected_Date);


//        btnToCalendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,CalendarPage.class);
//                startActivity(intent);
//            }
//        });


        // This is the code for the swipe menu
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Just a toast for Home
                if(item.getItemId()==R.id.nav_home){
                    Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                }
                //Makes it to where if you hit the calendar button it goes to the calendar
                if(item.getItemId()==R.id.nav_calendar){
                    Intent intent = new Intent(MainActivity.this,CalendarPage.class);
                    startActivity(intent);
                }
                //Default code needed to run
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}