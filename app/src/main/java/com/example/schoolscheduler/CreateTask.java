package com.example.schoolscheduler;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class CreateTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_task_layout);

        Button makeToListBtn = (Button) findViewById(R.id.makeToListBtn);
        Button makeToCalBtn = (Button) findViewById(R.id.makeToCalBtn);
        Button timePicker = (Button) findViewById(R.id.timeDialogBtn);

        makeToCalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateTask.this,CalendarPage.class);
                startActivity(intent);
            }
        });
        makeToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateTask.this,TaskList.class);
                startActivity(intent);
            }
        });
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment timePicker = new TimeDialog();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String min = minute+"";
        if (minute<10){
            min = "0"+min;
        }

        if (hour == 0 ){
            hour = 12;
            String time = "Time Set To "+hour+":"+min+" AM";
            Toast.makeText(CreateTask.this, time, Toast.LENGTH_LONG).show();

        }else if(hour >= 13){
            hour = hour - 12;
            String time = "Time Set To "+hour+":"+min+" PM";
            Toast.makeText(CreateTask.this, time, Toast.LENGTH_LONG).show();
        }else if(hour==12){
            String time = "Time Set To "+hour+":"+min+" PM";
            Toast.makeText(CreateTask.this, time, Toast.LENGTH_LONG).show();
        } else {
            String time = "Time Set to "+hour+":"+min+" AM";
            Toast.makeText(CreateTask.this, time, Toast.LENGTH_LONG).show();
        }



    }
}
