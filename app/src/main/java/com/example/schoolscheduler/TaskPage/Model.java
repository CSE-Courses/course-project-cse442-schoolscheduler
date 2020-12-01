package com.example.schoolscheduler.TaskPage;

public class Model {
    private boolean isSelected;
    private String n;
    private String s;
    private String t;
    private String du;
    private String d;
    public String[] getTask() {
        String[] str = {n,s,t,du,d};
        return str;
    }
    public void setn(String n) { this.n = n; }
    public void sets(String n) {
        this.s = n;
    }
    public void sett(String n) {
        this.t = n;
    }
    public void setdu(String n) {
        this.du = n;
    }
    public void setd(String n) {
        this.d = n;
    }
    public boolean getSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}