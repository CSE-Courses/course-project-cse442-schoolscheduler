package com.example.mycalendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarPage extends AppCompatActivity {

    private static final String TAG = "CalendarPage";
    private CalendarView myCalendarView;
    private Button backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        myCalendarView = (CalendarView) findViewById(R.id.calendarView);
        backBtn = (Button) findViewById(R.id.backBtn);
        final Intent dataIntent = getIntent();
        final String Task = dataIntent.getStringExtra("sendTask");
        final String Time = dataIntent.getStringExtra("sendTime");

        Intent incomingIntent = getIntent();
        String year = incomingIntent.getStringExtra("sendYear");
        String month = incomingIntent.getStringExtra("sendMonth");
        String day = incomingIntent.getStringExtra("sendDay");




       // myCalendarView.setDateTextAppearance();

        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //String date = (month+1)+"/"+dayOfMonth+"/"+year;
                //Log.d(TAG,"onSelectDayChange: date: "+date);

                Intent intent = new Intent(CalendarPage.this,TaskMaker.class);
                intent.putExtra("sendYear",year);
                intent.putExtra("sendMonth",month);
                intent.putExtra("sendDay",dayOfMonth);
                TextView task = new TextView(getApplicationContext());
                task.setText("task");
                myCalendarView.addView(task);
                startActivity(intent);
            }
        });




        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
