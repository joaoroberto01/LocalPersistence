package com.jmp.localpersistence;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PersistenceTask extends AsyncTask<Void,Integer,String> {
    private WeakReference<Activity> weakActivity;
    private WeakReference<ProgressBar> weakProgressBar;

    public static int LENGTH = 500;

    public PersistenceTask(Activity activity){
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = weakActivity.get();
        weakProgressBar = new WeakReference<>((ProgressBar) activity.findViewById(R.id.pb_progress));
        weakProgressBar.get().setVisibility(View.VISIBLE);
        weakProgressBar.get().setMax(LENGTH);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected String doInBackground(Void... voids) {
        String students = "";
        Integer progress = 0;
        List<Classroom> classes = Classroom.getOfflineClassrooms(weakActivity.get());
        for (Classroom classroom : classes) {
            students = students.concat(classroom.getName()+"\n");
            for (int i = 0; i < classroom.size();i++) {
                Student student = classroom.get(i);
                students = students.concat(student.getName() + "\n");
                onProgressUpdate(progress++);
            }
            students = students.concat("\n\n");
        }
        return students;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        weakProgressBar.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        weakProgressBar.get().setVisibility(View.GONE);
        TextView textView = weakActivity.get().findViewById(R.id.tv);
        textView.setText(s);

        weakActivity.get().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

    }
}
