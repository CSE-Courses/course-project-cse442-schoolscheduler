package com.example.mycalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TaskMaker extends AppCompatActivity {

    private static final String TAG = "TaskMaker";

    private Button BackToCal;
    private Button MakeTask;
    private EditText Task;
    private EditText Time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_layout);


        BackToCal = (Button) findViewById(R.id.backToCal);
        MakeTask = (Button) findViewById(R.id.makeTask);
        Task = (EditText) findViewById(R.id.typeTask);
        Time = (EditText) findViewById(R.id.typeTime);








        BackToCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskMaker.this,CalendarPage.class);
                startActivity(intent);
            }
        });

        MakeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String StoreTask = Task.getText().toString();
                String StoreTime = Time.getText().toString();

                Intent dataIntent = getIntent();
                String theYear = dataIntent.getStringExtra("sendYear");
                String theMonth = dataIntent.getStringExtra("sendMonth");
                String theDay = dataIntent.getStringExtra("sendDay");


                Intent intent = new Intent(TaskMaker.this,CalendarPage.class);
                intent.putExtra("sendTask",StoreTask);
                intent.putExtra("sendTime",StoreTime);
                intent.putExtra("sendYear",theYear);
                intent.putExtra("sendMonth",theMonth);
                intent.putExtra("sendDay",theDay);


                startActivity(intent);
            }
        });

    }
}
