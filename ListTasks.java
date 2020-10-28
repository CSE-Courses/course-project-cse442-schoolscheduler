package com.example.mycalendar;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListTasks extends AppCompatActivity {

    private static final String TAG = "ListTasks";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tasks_layout);

    }
}
