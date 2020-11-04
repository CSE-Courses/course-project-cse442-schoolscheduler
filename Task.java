package com.example.myapplicationtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Task extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private TextView bg_empty, today;

    private ListView lv;
    private ScrollView sv;
    private ArrayList<Model> modelArrayList;
    private CustomAdapter customAdapter;
    private Button btnselect, btndeselect, btnnext;
    private String[] animallist = new String[]{"Lion", "Tiger", "Leopard", "Cat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //InputStream is = getResources().openRawResource(R.raw.task);//read from task file
            //String txt = readText(is);
            //if (txt.length() != 0) {
                setContentView(R.layout.main_task_page);
                //bg_empty.setText("Some tasks!");
            //} else {
                //setContentView(R.layout.activity_main2);
                //bg_empty.setText("Tap on the plus to add new to-do items.");
                //today.setText("");
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*bg_empty = findViewById(R.id.empty2);
        today = findViewById(R.id.today);
        checktask();
        fab();
        edit_click();
        //addToday();
        //InputStream xml = getResources().openRawResource(R.raw.item);
        try {
            add();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        // Gets linearlayout

        //fab();

        lv = (ListView) findViewById(R.id.lv);

        // Gets the layout params that will allow you to resize the layout
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        modelArrayList = getModel(false);
        int size = modelArrayList.size();
// Changes the height and width to the specified *pixels*
        params.height = size*150;

        lv.setLayoutParams(params);
        lv.requestLayout();
        customAdapter = new CustomAdapter(this, modelArrayList);
        lv.setAdapter(customAdapter);

        lv.setOnItemClickListener(this);
    }


    public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3)
    {
        // based on the item clicked go to the relevant activity
        //String clickedItem = (String)adapter.getItemAtPosition(position);
        Toast.makeText(getApplicationContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Model> getModel(boolean isSelect) {
        ArrayList<Model> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Model model = new Model();
            model.setSelected(isSelect);
            model.setAnimal(animallist[i]);
            list.add(model);
        }
        return list;
    }




    /*private void checktask() {
        try {
            InputStream is = getResources().openRawResource(R.raw.task);//read from task file
            String txt = readText(is);
            if (txt.length() != 0) {
                bg_empty.setText("Some tasks!");
            } else {
                bg_empty.setText("Tap on the plus to add new to-do items.");
                today.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readText(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }


    private static void add() throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("/layout/item.xml");
        Element root = document.getDocumentElement();
        Node newserver = document.createElement("new_server");
        root.appendChild(newserver);


    }*/
/*
    public void fab() {
        // Register all the FABs with their IDs
        // This FAB button is the Parent
        final Boolean[] isAllFabsVisible = new Boolean[1];
        FloatingActionButton AddFab = findViewById(R.id.add_fab);
        // FAB button
        FloatingActionButton TaskFab = findViewById(R.id.add_task_fab);

        TextView TaskActionText;
        TaskActionText = findViewById(R.id.add_task_action_text);

        // Now set all the FABs and all the action name
        // texts as GONE
        TaskFab.setVisibility(View.GONE);
        TaskActionText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible[0] = false;

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
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

        // reminder
        TaskFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(Task.this, "Task Added", Toast.LENGTH_SHORT).show();
                        openNewActivity();
                    }
                });
    }

    public void openNewActivity() {
        Intent intent = new Intent(this, AddNewTask.class);
        startActivity(intent);
    }


/*
    public void edit_click() {
        // Register all the FABs with their IDs
        // This FAB button is the Parent

        RelativeLayout task_click = findViewById(R.id.RelativeLayout1);

        task_click.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(Task.this, "Task Added", Toast.LENGTH_SHORT).show();
                        openEditTaskContent();
                    }
                });
    }

    public void openEditTaskContent() {
        Intent intent = new Intent(this, EditTaskContent.class);
        startActivity(intent);
    }

     */



}




