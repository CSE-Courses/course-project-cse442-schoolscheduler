package com.example.TaskPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditTaskContent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        back();
    }

    public void back(){
        Button save = findViewById(R.id.edit_savebutton);

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(com.example.myapplicationtt.EditTaskContent.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                        openNewActivity();
                    }
                });

    }
    public void openNewActivity() {
        Intent intent = new Intent(this, Task.class);
        startActivity(intent);
    }
}
