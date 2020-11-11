package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;
import com.example.schoolscheduler.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
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

}