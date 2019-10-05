package com.ankita.studbud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Quiz_Android_A extends AppCompatActivity {


    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__android_);

        addListenerOnButton();
    }

    public void addListenerOnButton(){

        radioGroup = (RadioGroup) findViewById(R.id.question1);
        btnDisplay = (Button) findViewById(R.id.submit);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(Quiz_Android_A.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }
}


