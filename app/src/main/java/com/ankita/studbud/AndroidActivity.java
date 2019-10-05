package com.ankita.studbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AndroidActivity extends AppCompatActivity {

    Button course21;
    Button course22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        course21 = (Button)findViewById(R.id.course21);
        course22 = (Button)findViewById(R.id.course22);

        course21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AndroidActivity.this, YoutubeActivity.class);
                intent.putExtra("Course Id", "Android: Course 1");
                startActivity(intent);
            }
        });
        course22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AndroidActivity.this, YoutubeActivity.class);
                intent.putExtra("Course Id", "Android: Course 2");
                startActivity(intent);
            }
        });
    }
}
