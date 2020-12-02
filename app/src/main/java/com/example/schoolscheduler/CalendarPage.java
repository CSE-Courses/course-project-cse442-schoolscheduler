package com.example.schoolscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolscheduler.TaskPage.AddNewTask;
import com.example.schoolscheduler.TaskPage.Task;

public class CalendarPage extends AppCompatActivity {
    private static final String TAG = "CalendarPage";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_page_layout);


        CalendarView myCalendarView = findViewById(R.id.calendarView);
        Button calToTaskBtn = findViewById(R.id.calToTaskBtn);
        Button calToMainBtn = findViewById(R.id.calToMainBtn);
        TextView Event = findViewById(R.id.eventListId);


        //Code to get the Upcoming tasks for the week



        //Code when I hit the calendar
        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                Toast.makeText(CalendarPage.this,(month+1)+"/"+day+"/"+year,Toast.LENGTH_SHORT).show();
                String wholeDate= (month+1)+"/"+day+"/"+year;

                Intent intent = new Intent(CalendarPage.this, AddNewTask.class);
//                intent.putExtra("year",year+"");
//                intent.putExtra("month",month+"");
//                intent.putExtra("day",day+"");
                intent.putExtra("wholeDay",wholeDate);
                startActivity(intent);

            }
        });
        //code for the button to go to Task page
        calToTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarPage.this, Task.class);
                startActivity(intent);
            }
        });
        //code for the back Button
        calToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getCallingActivity() != null){
                    Log.d(TAG,getCallingActivity().getClassName());
                }
            }
        });

    }
}
