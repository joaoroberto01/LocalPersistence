package com.jmp.localpersistence;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersistenceTask extends AsyncTask<Void,Integer,String> {
    private Activity activity;
    private ProgressBar progressBar;

    public PersistenceTask(Activity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar = activity.findViewById(R.id.pb_progress);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMax(200);
    }

    @Override
    protected String doInBackground(Void... voids) {
        String students = "";
        Integer progress = 0;
        List<Classroom> classes =  Classroom.getOfflineClassrooms(activity);
        Log.i("NAME",classes.get(0).getName());
//        for (Classroom classroom : classes) {
//            for (Student student : classroom){
//                students = students.concat(student.getName() + "\n");
//                onProgressUpdate(progress++);
//            }
//            students = students.concat("\n\n");
//        }
        return students;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressBar.setVisibility(View.GONE);
        TextView textView = activity.findViewById(R.id.tv);
        textView.setText(s);

    }
}
