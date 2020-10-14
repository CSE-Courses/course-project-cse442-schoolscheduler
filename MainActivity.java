package com.example.mycalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAcitivity";

    private TextView Date;
    private Button btnToCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date = (TextView) findViewById(R.id.Date);
        btnToCalendar = (Button) findViewById(R.id.btnToCalendar);


        Intent dateIntent =getIntent();
        String Selected_Date= dateIntent.getStringExtra("date");
        Date.setText(Selected_Date);


        btnToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CalendarPage.class);
                startActivity(intent);
            }
        });
    }
}