package com.jmp.localpersistence;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Classroom {
    private String name;
    private List<Student> students = new ArrayList<>();

    public Classroom(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Student student){
        students.add(student);
    }

    public Student get(int index){
        return students.get(index);
    }

    public int size(){
        return students.size();
    }

    public static void saveOfflineClasses(Activity activity, List<Classroom> classroom) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(classroom);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
            sharedPreferences.edit().putString("students", json).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<Classroom> getOfflineClassrooms(Activity activity){
        try {
            Gson gson = new Gson();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
            String json = sharedPreferences.getString("students", "");
            Type type = new TypeToken<List<Classroom>>(){}.getType();

            return gson.fromJson(json, type);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
