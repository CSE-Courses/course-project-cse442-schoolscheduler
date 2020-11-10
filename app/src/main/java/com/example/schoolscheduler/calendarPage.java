package com.example.schoolscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class calendarPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_page);

        CalendarView myCalendarView = findViewById(R.id.calendarView);
        Button calToTaskBtn = findViewById(R.id.calToTaskBtn);
        Button calToMainBtn = findViewById(R.id.calToMainBtn);


        //Code when I hit the calendar
        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                Intent intent = new Intent(calendarPage.this,TaskList.class);
                startActivity(intent);
            }
        });
        //code for the button to go to Task page
        calToTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(calendarPage.this, TaskList.class);
                startActivity(intent);
            }
        });
        //code for the back Button
        calToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(calendarPage.this,Main_Page.class);
                startActivity(intent);
            }
        });

    }
}
