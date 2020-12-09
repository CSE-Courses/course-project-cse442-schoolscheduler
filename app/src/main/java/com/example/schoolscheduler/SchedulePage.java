package com.example.schoolscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SchedulePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_page_layout);

        Button addClassButton = findViewById(R.id.addClassFab);

        addClassButton.setOnClickListener(view -> {
            Intent intent = new Intent(SchedulePage.this, NewCourse.class);
            startActivity(intent);
        });
    }
}
