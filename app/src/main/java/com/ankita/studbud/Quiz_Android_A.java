package com.ankita.studbud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ankita.studbud.R;


public class Quiz_Android_A extends AppCompatActivity {


    private RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4;
    private RadioButton radioButton1,radioButton3,radioButton2,radioButton4;
    private Button btnDisplay;
    String quiz1_android[]=new String[4];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__android_);

        addListenerOnButton();
    }

    public void addListenerOnButton(){


        radioGroup1 = (RadioGroup) findViewById(R.id.question1);
        radioGroup2 = (RadioGroup) findViewById(R.id.question3);
        radioGroup3 = (RadioGroup) findViewById(R.id.question2);
        radioGroup4 = (RadioGroup) findViewById(R.id.question4);



        btnDisplay = (Button) findViewById(R.id.submit);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup

                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                int selectedId3 = radioGroup3.getCheckedRadioButtonId();
                int selectedId4 = radioGroup4.getCheckedRadioButtonId();




                // find the radiobutton by returned id
                radioButton1 = (RadioButton) findViewById(selectedId1);
                radioButton2 = (RadioButton) findViewById(selectedId2);
                radioButton3 = (RadioButton) findViewById(selectedId3);
                radioButton4 = (RadioButton) findViewById(selectedId4);


                quiz1_android[0]=radioButton1.getText().toString();
                quiz1_android[1]=radioButton2.getText().toString();
                quiz1_android[2]=radioButton3.getText().toString();
                quiz1_android[3]=radioButton4.getText().toString();

                Log.d("0",quiz1_android[0]);
                Log.d("1",quiz1_android[1]);
                Log.d("2",quiz1_android[2]);
                Log.d("3",quiz1_android[3]);


            }

        });

    }

}
