package com.example.try333;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private ListView listViewLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateLessonList();
        listViewLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openLessonActivity(position);
            }
        });
    }

    private void populateLessonList() {
        String[] lessons = {"Simple Present", "Simple Past", "Present Continuous"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lessons);
        listViewLessons = findViewById(R.id.listViewLessons);
        listViewLessons.setAdapter(adapter);
    }


    private void openLessonActivity(int position) {
        Intent intent=new Intent();
        switch (position) {
            case 0:
                intent = new Intent(this, LessonActivity1.class);
                break;
            case 1:
                intent = new Intent(this, LessonActivity2.class);
                break;
            default:
                Toast.makeText(this, "please choose the lesson", Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(intent);
    }


}