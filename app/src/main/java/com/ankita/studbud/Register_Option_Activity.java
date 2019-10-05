package com.ankita.studbud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register_Option_Activity extends AppCompatActivity {

    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__option_);


        button1=findViewById(R.id.studentRegistration);
        button2=findViewById(R.id.teacherRegistration);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferToHelperRegister = new Intent(Register_Option_Activity.this, Register_Teacher_Activity.class);
                startActivity(transferToHelperRegister);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferToReliefCentreRegister = new Intent(Register_Option_Activity.this, Register_Student_Activity.class);
                startActivity(transferToReliefCentreRegister);
            }
        });

    }
}
