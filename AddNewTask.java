package com.example.myapplicationtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNewTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
        back();
    }

    public void back(){
        Button save = findViewById(R.id.savechangebutton);

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(Task.this, "Task Added", Toast.LENGTH_SHORT).show();
                        openNewActivity();
                    }
                });

    }

    public void openNewActivity() {
        Intent intent = new Intent(this, Task.class);
        startActivity(intent);
    }
}