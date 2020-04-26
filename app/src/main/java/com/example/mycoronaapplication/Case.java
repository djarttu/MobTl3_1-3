package com.example.mycoronaapplication;

import org.json.JSONObject;

public class Case {
    String name;
    int maara;
    public Case(String name) {
        this.name = name;
    }
    public void setMaara(int maara) {
        this.maara++;
    }



}
