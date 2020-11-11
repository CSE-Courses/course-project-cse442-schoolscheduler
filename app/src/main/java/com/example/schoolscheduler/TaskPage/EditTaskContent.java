package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.schoolscheduler.R;

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
                        Toast.makeText(EditTaskContent.this, "Changes Saved", Toast.LENGTH_SHORT).show();
                        open();
                    }
                });

    }
    public void open() {
        Intent intent = new Intent(this, Task.class);
        startActivity(intent);
    }
}
