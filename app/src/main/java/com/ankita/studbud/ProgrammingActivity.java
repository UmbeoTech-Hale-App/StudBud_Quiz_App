package com.ankita.studbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProgrammingActivity extends AppCompatActivity {

    Button course31;
    Button course32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming);

        course31 = (Button)findViewById(R.id.course31);
        course32 = (Button)findViewById(R.id.course32);

        course31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProgrammingActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });
        course32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProgrammingActivity.this, YoutubeActivity.class);
                startActivity(intent);
            }
        });
    }
}
