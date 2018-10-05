package me.riddhi.gada.olcademy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MyCourses extends AppCompatActivity {

    String value;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_courses);
        Intent i = getIntent();
        value = i.getStringExtra("CourseName");
        getSupportActionBar().setTitle("My Courses");
    }


}
