package com.example.schoolscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class completedHomework extends AppCompatActivity {

    TextView homework;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_homework);

        homework = (TextView)findViewById(R.id.homewrokStorage);
        homework.append(getIntent().getCharSequenceExtra("hw"));
    }
}