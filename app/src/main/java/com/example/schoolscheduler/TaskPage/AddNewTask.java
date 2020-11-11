package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.schoolscheduler.CreateTask;
import com.example.schoolscheduler.R;
import com.example.schoolscheduler.SQLDatabase;
import com.example.schoolscheduler.TimeDialog;
import com.facebook.stetho.Stetho;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddNewTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    EditText Title, Details;
    private final String DB_NAME = "MyDBB.db";
    private String TABLE_NAME = "MyTablee";
    private final int DB_VERSION = 1;
    SQLDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        Button timePick = (Button) findViewById(R.id.timeButton);

        Stetho.initializeWithDefaults(this);
        DB = new SQLDatabase(this, DB_NAME, null, DB_VERSION, TABLE_NAME);
        DB.checkTable();
        back();
        timePick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimeDialog();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });

    }
    //go back to main page
    public void back() {
        Button save = findViewById(R.id.new_savechangebutton);
        Title = findViewById(R.id.new_title);
        Details = findViewById(R.id.new_details);
        save.setOnClickListener(view -> {
            DB.addData(Title.getText().toString()
                    , Details.getText().toString());
            Toast.makeText(AddNewTask.this, "Task Added", Toast.LENGTH_SHORT).show();
            open();
        });
    }


    public void open() {
        Intent intent = new Intent(this, Task.class);
        startActivity(intent);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String finalTime;
        String min = minute+"";

        // Prints out the right time as a Toast and sets the timeAsString variable
        if (minute<10){
            min = "0"+min;
        }
        if (hour == 0 ){
            hour = 12;
            String time = hour+":"+min+" AM";
            finalTime = time;
            Toast.makeText(AddNewTask.this, time, Toast.LENGTH_LONG).show();

        }else if(hour >= 13){
            hour = hour - 12;
            String time = hour+":"+min+" PM";
            finalTime = time;
            Toast.makeText(AddNewTask.this, time, Toast.LENGTH_LONG).show();
        }else if(hour==12){
            String time = hour+":"+min+" PM";
            finalTime = time;
            Toast.makeText(AddNewTask.this, time, Toast.LENGTH_LONG).show();
        } else {
            String time = hour+":"+min+" AM";
            finalTime = time;
            Toast.makeText(AddNewTask.this, time, Toast.LENGTH_LONG).show();
        }
    }
}
