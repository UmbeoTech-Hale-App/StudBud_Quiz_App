package com.ankita.studbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AIMLActivity extends AppCompatActivity {

    Button course11;
    Button course12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiml);

        course11 = (Button)findViewById(R.id.course11);
        course12 = (Button)findViewById(R.id.course12);

        course11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AIMLActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });
        course12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AIMLActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });
    }
}
