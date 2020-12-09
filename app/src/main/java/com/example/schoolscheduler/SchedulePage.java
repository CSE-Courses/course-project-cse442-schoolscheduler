package com.example.schoolscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SchedulePage extends AppCompatActivity {
    FirebaseConnection connection = new FirebaseConnection("saved-data/schoolscheduler");
    DatabaseReference coursesRef = connection.ref.child("courses");
    Query coursesQuery = coursesRef.orderByKey();
    ArrayList<Course> courses;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_page_layout);
        rv = findViewById(R.id.courseList);
        FloatingActionButton addClassButton = findViewById(R.id.addClassFab);

        addClassButton.setOnClickListener(view -> {
            Intent intent = new Intent(SchedulePage.this, NewCourse.class);
            startActivity(intent);
        });

        coursesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Iterable<DataSnapshot> it = snapshot.child("courses").getChildren();
                    courses = new ArrayList<>();
                    for (DataSnapshot course : it) {
                        Course c = course.getValue(Course.class);
                        courses.add(c);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("course retrieval", error.toString());
            }
        });
    }
}
