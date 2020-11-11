package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolscheduler.R;
import com.example.schoolscheduler.SQLDatabase;
import com.facebook.stetho.Stetho;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewTask extends AppCompatActivity {

    EditText Title, Details;
    private final String DB_NAME = "MyDBB.db";
    private String TABLE_NAME = "MyTablee";
    private final int DB_VERSION = 1;
    SQLDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        Stetho.initializeWithDefaults(this);
        DB = new SQLDatabase(this, DB_NAME, null, DB_VERSION, TABLE_NAME);
        DB.checkTable();
        back();

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

}
