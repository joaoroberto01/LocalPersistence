package com.jmp.localpersistence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindElements();
    }

    private void bindElements() {
        Button writeButton = findViewById(R.id.bt_write);
        writeButton.setOnClickListener(this);

        Button readButton = findViewById(R.id.bt_read);
        readButton.setOnClickListener(this);
        TextView textView = findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_write:
                Classroom classroom1 = new Classroom("3º INFO A");
                Classroom classroom2 = new Classroom("4º INFO A");

                for (int i = 0; i < 101; i++) {
                    classroom1.add(new Student("Paula Tejando "+String.valueOf(i),String.valueOf(i),"24"));
                    classroom2.add(new Student("Paula Norego "+String.valueOf(i),String.valueOf(i),"24"));
                }

                List<Classroom> classes = new ArrayList<>();
                classes.add(classroom1);
                classes.add(classroom2);

                Classroom.saveOfflineClasses(MainActivity.this, classes);
                break;
            case R.id.bt_read:
                new PersistenceTask(MainActivity.this).execute();
                break;
        }
    }
}
