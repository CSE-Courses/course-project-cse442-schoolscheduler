package com.example.schoolscheduler.TaskPage;

import android.app.Application;

public class Global extends Application {
    private String name;
    private String sub;
    private String type;
    private String due;
    private String detail;

    public String[] getS() {
        String[] s = {name,sub,type,due,detail};
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
    public void setdu(String str) {
        due = str;
    }
    public void setd(String str) {
        detail = str;
    }
}
