package com.ankita.studbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ankita.studbud.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Quiz2_activity extends AppCompatActivity {

    private RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4;
    private RadioButton radioButton1,radioButton3,radioButton2,radioButton4;
    private Button btnDisplay;
    private String TAG="Info";
    String quiz1_android[]=new String[4];

    String db_ans[]=new String[4];
    public int quiz2_score=0;
    private FirebaseDatabase mDatabase;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRef,mRef1;

    ArrayList<String> ans = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_activity);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mDatabase =FirebaseDatabase.getInstance();
        mFirebaseAuth= FirebaseAuth.getInstance();
        mRef=mDatabase.getReference("Quizzes").child("Android").child("Course1").child("Quiz2");
        mRef1=mDatabase.getReference("Student");


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

                mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int i=0;
                        for (DataSnapshot snapshots: dataSnapshot.getChildren()) {
                            try {
                                if(quiz1_android[i].equals(snapshots.getValue().toString())){
                                    quiz2_score++;
                                }

                                i++;
                            }

                            catch (Exception e){
                                Log.i(TAG, "onDataChange: "+e.getMessage());
                            }

                        }
                        if(quiz2_score==4){
                            Toast.makeText(getApplicationContext(),"Out of Score: Score = "+quiz2_score,Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Quiz2_activity.this, Student_Dashboard_Activity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Insufficient Score : Score = "+quiz2_score, Toast.LENGTH_LONG).show();

                            Intent intent1 = new Intent(Quiz2_activity.this,Student_Dashboard_Activity.class);
                            startActivity(intent1);
                        }

                        mRef1.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/AND_Course2").setValue(Integer.toString(quiz2_score));
                        quiz2_score=0;


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                int selectedId3 = radioGroup3.getCheckedRadioButtonId();
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                int selectedId4 = radioGroup4.getCheckedRadioButtonId();

                radioButton1 = (RadioButton) findViewById(selectedId1);
                radioButton2 = (RadioButton) findViewById(selectedId2);
                radioButton3 = (RadioButton) findViewById(selectedId3);
                radioButton4 = (RadioButton) findViewById(selectedId4);


                quiz1_android[0]=radioButton1.getText().toString();
                quiz1_android[1]=radioButton2.getText().toString();
                quiz1_android[2]=radioButton3.getText().toString();
                quiz1_android[3]=radioButton4.getText().toString();



            }

        });

    }
}
