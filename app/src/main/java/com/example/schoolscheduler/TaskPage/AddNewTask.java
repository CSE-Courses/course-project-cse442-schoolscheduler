package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolscheduler.R;
import com.example.schoolscheduler.SQLDatabase;
import com.facebook.stetho.Stetho;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewTask extends AppCompatActivity {

    EditText Title,Subject, Type, Details;
    TextView Due;

    private final String DB_NAME = "MyDBB.db";
    private String TABLE_NAME = "MyTablee";
    private final int DB_VERSION = 1;
    private static final String TAG = "Task";
    private TextView date;
    private DatePickerDialog.OnDateSetListener dateListener;
    SQLDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);
        Stetho.initializeWithDefaults(this);
        DB = new SQLDatabase(this, DB_NAME, null, DB_VERSION, TABLE_NAME);
        DB.checkTable();
        back();

        //datepicker
        date = (TextView) findViewById(R.id.new_date);
        Button date_button = (Button) findViewById(R.id.new_date_picker_button);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddNewTask.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
       dateListener = new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int year, int month, int day) {
               String dat = (month+1) + "/" + day +"/"+ year;
               date.setText(dat);
               //Log.d(TAG, "OnDateSet" +i +"/"+i1+"/"+i2);
           }
       };


    }
    //go back to main page
    public void back() {
        Button save = findViewById(R.id.new_savechangebutton);
        Title = findViewById(R.id.new_title);
        Subject = findViewById(R.id.new_subject_s);
        Type = findViewById(R.id.new_type_s);
        Due = findViewById(R.id.new_date);
        Details = findViewById(R.id.new_details);
        save.setOnClickListener(view -> {
            DB.addData(Title.getText().toString(),
                    Subject.getText().toString(),
                    Type.getText().toString(),
                    Due.getText().toString(),
                    Details.getText().toString());
            Toast.makeText(AddNewTask.this, "Task Added", Toast.LENGTH_SHORT).show();
            open();
        });
    }


    public void open() {
        Intent intent = new Intent(this, Task.class);
        startActivity(intent);
    }

}
