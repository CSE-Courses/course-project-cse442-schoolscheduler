package com.example.schoolscheduler.TaskPage;

import android.app.Application;

import java.util.ArrayList;

public class Global extends Application {
    private String name;
    private String sub;
    private String type;
    private String detail;

    public String[] getS() {
        String[] s = {name,sub,type,detail};
        return s;
    }

    public void setn(String str) {
        name = str;
    }
    public void sets(String str) {
        sub = str;
    }
    public void sett(String str) {
        type = str;
    }
    public void setd(String str) {
        detail = str;
    }
}
