package com.example.myapplicationtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;


public class Task extends AppCompatActivity {
    private TextView bg_empty, today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bg_empty = findViewById(R.id.empty);
        today = findViewById(R.id.today);
        checktask();
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


    }

    private void checktask() {
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


    }
/*
    private void add1() {
        LinearLayout mynewlayout = findViewById(R.id.lLayout);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mynewlayout.addView(0, layoutInflater.inflate(R.layout.list_item, this, false) );
}

 */


}


