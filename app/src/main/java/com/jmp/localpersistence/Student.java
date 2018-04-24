package com.jmp.localpersistence;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String number;
    private String age;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getAge() {
        return age;
    }

    public Student(String name, String number, String age){
        this.name = name;
        this.number = number;
        this.age = age;
    }
}
