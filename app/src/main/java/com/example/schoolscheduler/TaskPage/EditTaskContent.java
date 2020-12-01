package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolscheduler.TaskPage.Task;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.schoolscheduler.R;

import java.util.Calendar;

public class EditTaskContent extends AppCompatActivity {
    private TextView date;
    private DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        back();
        change();

        //datepicker
        date = (TextView) findViewById(R.id.edit_date);
        Button date_button = (Button) findViewById(R.id.edit_date_picker_button);
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditTaskContent.this,
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
    public void change(){
        Global mApp = ((Global)getApplicationContext());
        String[] s = mApp.getS();
        EditText name = findViewById(R.id.edit_title);
        EditText sub = findViewById(R.id.edit_subject_s);
        EditText type = findViewById(R.id.edit_type_s);
        TextView due = findViewById(R.id.edit_date);
        EditText detail = findViewById(R.id.edit_details);
        name.setText(s[0]);
        sub.setText(s[1]);
        type.setText(s[2]);
        due.setText(s[3]);
        detail.setText(s[4]);
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
