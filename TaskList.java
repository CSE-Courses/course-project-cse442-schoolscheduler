package com.example.mycalendar;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TaskList extends AppCompatActivity {
    private static final String TAG = "TaskList";
    private EditText taskList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_layout);

        taskList = (EditText) findViewById(R.id.editTextTextMultiLine);



    }



}
