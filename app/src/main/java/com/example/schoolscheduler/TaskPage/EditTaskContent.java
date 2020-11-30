package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolscheduler.TaskPage.Task;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.schoolscheduler.R;

public class EditTaskContent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        back();
        change();
    }
    public void change(){
        Global mApp = ((Global)getApplicationContext());
        String[] s = mApp.getS();
        EditText name = findViewById(R.id.edit_title);
        EditText sub = findViewById(R.id.edit_subject_s);
        EditText type = findViewById(R.id.edit_type_s);
        EditText detail = findViewById(R.id.edit_details);
        name.setText(s[0]);
        sub.setText(s[1]);
        type.setText(s[2]);
        detail.setText(s[3]);
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
