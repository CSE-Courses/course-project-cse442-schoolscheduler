package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.schoolscheduler.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolscheduler.SQLDatabase;
import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class Task extends AppCompatActivity {
    private final String DB_NAME = "MyDBB.db";
    private String TABLE_NAME = "MyTablee";
    private final int DB_VERSION = 1;
    SQLDatabase DB;

    ArrayList<String> arrayList = new ArrayList<>();
    private int Todaylist_size;
    private ListView lv;
    private ArrayList<Model> modelArrayList;
    private CustomAdapter customAdapter;
    private Boolean bool;
    private int idc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_task_page);
        //work db
        Stetho.initializeWithDefaults(this);
        DB = new SQLDatabase(this, DB_NAME, null, DB_VERSION, TABLE_NAME);
        DB.checkTable();
        arrayList = DB.showOne();
        Todaylist_size = arrayList.size();
        if (Todaylist_size > 0) {
            //list out all tasks
            lv = (ListView) findViewById(R.id.lv);
            ViewGroup.LayoutParams params = lv.getLayoutParams();
            modelArrayList = getModel(false);

            //Change the height of ListView
            params.height = Todaylist_size * 140;

            lv.setLayoutParams(params);
            lv.requestLayout();
            customAdapter = new CustomAdapter(this, modelArrayList);

            lv.setAdapter(customAdapter);
        }
        //add button
        fab();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //itemClicked(findViewById(R.id.cb));
        //bool =customAdapter.boo();
        //getMenuInflater().inflate(R.menu.


        check();


    }

    //get the model list
    private ArrayList<Model> getModel(boolean isSelect) {
        ArrayList<Model> list = new ArrayList<>();
        for (int i = 0; i < Todaylist_size; i++) {
            Model model = new Model();
            model.setSelected(isSelect);
            model.setTask(arrayList.get(i));
            list.add(model);
        }
        return list;
    }

    //FAB
    public void fab() {
        final Boolean[] isAllFabsVisible = new Boolean[1];
        FloatingActionButton AddFab = findViewById(R.id.add_fab);
        // FAB button
        FloatingActionButton TaskFab = findViewById(R.id.add_task_fab);
        TextView TaskActionText = findViewById(R.id.add_task_action_text);

        TaskFab.setVisibility(View.GONE);
        TaskActionText.setVisibility(View.GONE);
        isAllFabsVisible[0] = false;
        AddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible[0]) {
                            TaskFab.show();
                            TaskActionText.setVisibility(View.VISIBLE);
                            isAllFabsVisible[0] = true;
                        } else {
                            TaskFab.hide();
                            TaskActionText.setVisibility(View.GONE);
                            isAllFabsVisible[0] = false;
                        }
                    }
                });
        TaskFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openNewActivity();
                    }
                });
    }

    //open Edit page
    public void onItemClick(View view) {
        Intent intent = new Intent(getApplicationContext(), EditTaskContent.class);
        startActivity(intent);
    }
    //open Add page
    public void openNewActivity() {
        Intent intent = new Intent(this, AddNewTask.class);
        startActivity(intent);
    }

    // create an action bar button




    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.

        //bool = customAdapter.boo();


        CheckBox chk = (CheckBox) findViewById(R.id.cb);

        //if(bool){
        Toast.makeText(Task.this,
                "Checked", Toast.LENGTH_LONG).show();
        getMenuInflater().inflate(R.menu.todo, menu);

        findViewById(R.id.delete).setVisibility(View.GONE);
        return true;
        //}
        //else{
        // return false;
        // }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {
            idc = id;
            Toast.makeText(Task.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void itemClicked(View v) {
        CheckBox checkBox = (CheckBox)v;
        if (checkBox.isChecked()) {
            //boo = true;
            Toast.makeText(Task.this,
                    "Checked", Toast.LENGTH_LONG).show();
        }
    }

    public void check() {
        //findViewById(R.id.delete).setVisibility(View.GONE);

        CheckBox chk = (CheckBox) findViewById(R.id.cb);
        if (chk != null) {
            chk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //findViewById(R.id.delete).setVisibility(View.VISIBLE);

                    //setVisibility(View.VISIBLE);
                    Toast.makeText(Task.this,
                            "Checked", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

}
// create an action bar button



