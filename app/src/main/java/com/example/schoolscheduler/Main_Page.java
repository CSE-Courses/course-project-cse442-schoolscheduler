package com.example.schoolscheduler;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

public class Main_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button BtnToCal = findViewById(R.id.btnToCal);
        BtnToCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Page.this, calendarPage.class);
                startActivity(intent);
            }
        });


    }
}

