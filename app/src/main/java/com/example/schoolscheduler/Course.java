package com.example.schoolscheduler;

import java.util.List;

/*
    Simple data structure to hold information about the courses added to the app. This will be added
    to the Firebase data store, which can hold entire objects together.

    If you're wondering why the name isn't being stored in this structure, it's because the name is
    used as the key in the Firebase data store to keep the flattened version of these data together.
 */

public class Course {
    public String startTime;
    public String endTime;
    public List<String> days;

    // We're only going to instantiate this object once all of the data has been filled in.
    public Course(String startTime,
                  String endTime,
                  List<String> days) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
    }
}