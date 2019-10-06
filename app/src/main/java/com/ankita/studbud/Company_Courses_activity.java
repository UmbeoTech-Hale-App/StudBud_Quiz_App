package com.ankita.studbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Company_Courses_activity extends AppCompatActivity {

    Button course31;
    Button course22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__courses_activity);


        course31 = (Button)findViewById(R.id.course21);
        course22 = (Button)findViewById(R.id.course22);

        course31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Company_Courses_activity.this, YoutubeActivity.class);
                intent.putExtra("Course Id", "Programming Course 1");
                startActivity(intent);
            }
        });
        course22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Company_Courses_activity.this, YoutubeActivity.class);
                intent.putExtra("Course Id", "Android: Course 2");
                startActivity(intent);
            }
        });
    }
    }

