package com.example.schoolscheduler.TaskPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.schoolscheduler.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolscheduler.SQLDatabase;
import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Task extends AppCompatActivity  {
    String TAG = Task.class.getSimpleName() + "My";
    private final String DB_NAME = "MyDBB.db";
    private String TABLE_NAME = "MyTablee";
    private final int DB_VERSION = 1;
    SQLDatabase DB;
    ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
    ArrayList<String> getNowArray = new ArrayList<>();
    private int Todaylist_size;
    private ListView lv;
    private ArrayList<Model> modelArrayList;
    private CustomAdapterr customAdapter;
    private MenuItem submitOp;
    private MenuItem deleteOp;
    private ArrayList<String> id_del;
    private ArrayList<Integer> ttt;



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
            //modelArrayList = getModel(false);

            //Change the height of ListView
            params.height = Todaylist_size * 140;

            lv.setLayoutParams(params);
            lv.requestLayout();
            customAdapter = new CustomAdapterr(this, arrayList);

            lv.setAdapter(customAdapter);
        }
        //add button
        fab();
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        id_del = new ArrayList<>();
        ttt = new ArrayList<>();

    }

    //get the model list
    private ArrayList<Model> getModel(boolean isSelect, ArrayList<ArrayList<String>> arr) {
        ArrayList<Model> list = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            Model model = new Model();
            model.setSelected(isSelect);
            model.mseti(arrayList.get(i).get(0));
            model.msetn(arrayList.get(i).get(1));
            model.msets(arrayList.get(i).get(2));
            model.msett(arrayList.get(i).get(3));
            model.msetdu(arrayList.get(i).get(4));
            model.msetd(arrayList.get(i).get(5));
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
        getMenuInflater().inflate(R.menu.todo, menu);
        deleteOp = menu.findItem(R.id.delete);
        submitOp = menu.findItem(R.id.submit);
        deleteOp.setVisible(false);
        submitOp.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String tt = "";
        if (id == R.id.delete) {
            for (int k=0; k<id_del.size();k++) {
                //tt = tt + id_del.get(k);
                DB.delete(id_del.get(k));
                int position = ttt.get(k);
                   //arrayList.remove(position);
                //arrayList.clear();
                //modelArrayList.clear();

            }
            arrayList = DB.showOne();
            for (int k=0; k<arrayList.size();k++){
                tt = tt + arrayList.get(k).get(1);
            }
                customAdapter.notifyDataSetChanged();
            Toast.makeText(Task.this, tt, Toast.LENGTH_LONG).show();

            //for (int k=0; k<id_del.size();k++){
                //DB.delete(id_del.get(k));
                //Iterator itr = arrayList.iterator();
                //while (itr.hasNext())
                //{
                   // ArrayList<String> x = (ArrayList<String>)itr.next();
                    //if (x < 10)
                        //itr.remove();
                //}
                //arrayList.remove(position);
                //arrayList = DB.showOne();
                //customAdapter.notifyItemRemoved(position);
            //}

            //id_del.clear();
            Intent intent = new Intent(this, Task.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.submit) {
            Toast.makeText(Task.this, "Act clicked", Toast.LENGTH_LONG).show();
            backtomain();
            //deleteOp.setVisible(false);
            //submitOp.setVisible(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void backtomain() {
        Intent intent = new Intent(this, Task.class);
        startActivity(intent);
    }

    private class CustomAdapterr extends BaseAdapter {
        private Context context;
        public ArrayList<ArrayList<String>> arrayList;
        public  ArrayList<Model> modelArrayList;

        public CustomAdapterr(Context context, ArrayList<ArrayList<String>> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
            //this.arrayList = DB.showOne();
            //Todaylist_size = arrayList.size();
            this.modelArrayList = getModel(false, this.arrayList);
        }

        @Override
        public int getViewTypeCount() {
            return getCount();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return modelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return modelArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.lv_item, null, true);
                holder.CheckBox = (CheckBox) convertView.findViewById(R.id.cb);
                holder.TaskTitleView = (TextView) convertView.findViewById(R.id.task_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.TaskTitleView.setText(modelArrayList.get(position).getTask()[1]);
            holder.CheckBox.setChecked(modelArrayList.get(position).getSelected());
            Global mApp = ((Global)getApplicationContext());
            holder.TaskTitleView.setOnClickListener((v)->{
                getNowArray.clear();
                try {
                    mApp.seti(modelArrayList.get(position).getTask()[0]);
                    mApp.setn(modelArrayList.get(position).getTask()[1]);
                    mApp.sets(modelArrayList.get(position).getTask()[2]);
                    mApp.sett(modelArrayList.get(position).getTask()[3]);
                    mApp.setdu(modelArrayList.get(position).getTask()[4]);
                    mApp.setd(modelArrayList.get(position).getTask()[5]);

                    Intent intent = new Intent(getApplicationContext(), EditTaskContent.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d(TAG, "error: " + e.getMessage());
                }
            });
            holder.CheckBox.setTag(R.integer.one, convertView);
            holder.CheckBox.setTag(position);
            holder.CheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteOp.setVisible(true);
                    submitOp.setVisible(true);
                    id_del.add(modelArrayList.get(position).getTask()[0]);
                    ttt.add(position);
                }
            });
            return convertView;
        }
        private class ViewHolder {
            protected CheckBox CheckBox;
            private TextView TaskTitleView;
        }
    }
}
// create an action bar button



