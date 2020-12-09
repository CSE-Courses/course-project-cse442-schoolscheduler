package com.example.schoolscheduler.TaskPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import com.example.schoolscheduler.CalendarPage;
import com.example.schoolscheduler.CreateTask;
import com.example.schoolscheduler.R;
import com.example.schoolscheduler.SQLDatabase;
import com.example.schoolscheduler.TimeDialog;
import com.example.schoolscheduler.general;
import com.example.schoolscheduler.settings;
import com.facebook.stetho.Stetho;
import com.google.android.material.navigation.NavigationView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddNewTask extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "AddNewTask";
    EditText Title, Details;
    private final String DB_NAME = "MyDBB.db";
    private String TABLE_NAME = "MyTablee";
    private final int DB_VERSION = 1;
    SQLDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_new_task);

        Button timePick = (Button) findViewById(R.id.timeButton);
        Button datePicker = (Button) findViewById(R.id.new_date_picker_button);
        TextView dateDisplay = (TextView) findViewById(R.id.new_date);
        Intent incomingData = getIntent();
        String date = incomingData.getStringExtra("wholeDay");
        dateDisplay.setText(date);




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
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewTask.this, CalendarPage.class);
                startActivity(intent);
            }
        });



        //code for Navigation Drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {     //Opens the drawer
                //If Home button is pressed
                if(item.getItemId()== R.id.nav_home){
                    Toast.makeText(AddNewTask.this,"Home",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddNewTask.this, general.class);
                    startActivity(intent);
                }
                //If Calendar is pressed
                if(item.getItemId()== R.id.nav_calendar){
                    Toast.makeText(AddNewTask.this,"Calendar",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddNewTask.this,CalendarPage.class);
                    startActivity(intent);
                }
                //If Task is pressed
                if(item.getItemId()==R.id.nav_task){
                    Toast.makeText(AddNewTask.this,"Tasks",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddNewTask.this, Task.class);
                    startActivity(intent);
                }
                //If Setting is pressed
                if(item.getItemId()==R.id.nav_settings){
                    Toast.makeText(AddNewTask.this,"Settings",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddNewTask.this, settings.class);
                    startActivity(intent);
                }


                //Closers the drawer when done
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
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
