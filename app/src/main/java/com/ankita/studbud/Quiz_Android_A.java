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

public class Quiz_Android_A extends AppCompatActivity {

    private RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4;
    private RadioButton radioButton1,radioButton3,radioButton2,radioButton4;
    private Button btnDisplay;
    String quiz1_android[]=new String[4];
    private String TAG="Info";


    String db_ans[]=new String[4];
    public int quiz2_score=0;
    private FirebaseDatabase mDatabase;
    FirebaseAuth mFirebaseAuth;
    private DatabaseReference mRef,mRef1;

    ArrayList<String> ans = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__android_);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mDatabase =FirebaseDatabase.getInstance();
        mFirebaseAuth= FirebaseAuth.getInstance();
        mRef=mDatabase.getReference("Quizzes").child("Android").child("Course1").child("Quiz1");
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
                            Toast.makeText(getApplicationContext(),"Out of Score : Score = "+quiz2_score,Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Quiz_Android_A.this,YoutubeActivity.class);
                            startActivity(intent);
                        }
                        else{

                            Toast.makeText(getApplicationContext(),"Insufficient Score : Score = "+quiz2_score,Toast.LENGTH_LONG).show();

                            Intent intent=new Intent(Quiz_Android_A.this, YoutubeActivity.class);
                            startActivity(intent);
                        }

                        mRef1.child(mFirebaseAuth.getInstance().getCurrentUser().getUid()+"/AND_Course1").setValue(Integer.toString(quiz2_score));
                        quiz2_score=0;


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                // get selected radio button from radioGroup

                int selectedId1 = radioGroup1.getCheckedRadioButtonId();
                int selectedId3 = radioGroup3.getCheckedRadioButtonId();
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();
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
